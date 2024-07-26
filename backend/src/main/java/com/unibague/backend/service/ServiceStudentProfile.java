package com.unibague.backend.service;

import com.unibague.backend.model.AcademicProgram;
import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.model.StudentProfile;
import com.unibague.backend.repository.*;
import com.unibague.backend.util.FetchExternalData;
import com.unibague.backend.util.IntegraStudentNomenclature;
import com.unibague.backend.util.Sex;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ServiceStudentProfile {

    @Autowired
    private RepositoryStudentProfile repositoryStudentProfile;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RepositoryAssesmentPeriod repositoryAssesmentPeriod;

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private RepositoryAcademicProgram repositoryAcademicProgram;

    @Autowired
    RepositoryResearchSeedbed repositoryResearchSeedbed;

    @Autowired
    ServiceUser serviceUser;

    private Map<String, Object> getJsonByStudentIdentification(String identification) throws Exception{
        String urlReturn = FetchExternalData.fetchExternalDataFromStudent(identification);
        return FetchExternalData.fromStringJsonToMap(urlReturn);
    }

    public Optional<List<ResearchSeedbed>> getStudentProfileResearchSeedbeds(String identification) {
        return repositoryStudentProfile.getStudentProfileResearchSeedbeds(identification);
    }

    /**
     * This method adds a student profile to the database, the method suppose that the student profile is not already in the database
     * @param studentProfile a json with the student profile information
     * @return true if the student profile was added successfully, false otherwise
     */
    public Boolean addStudentProfile(HashMap<String, String> studentProfile) {
        try{
            Map<String, Object> map = getJsonByStudentIdentification(studentProfile.get("identification_number"));
            String identification = String.valueOf(map.get(IntegraStudentNomenclature.IDENTIFICATION));

            Long researchSeedbedId = Long.valueOf(studentProfile.get("research_seedbed_id"));
            String academicProgramId = (String) map.get("program_code");

            if(repositoryResearchSeedbed.findById(researchSeedbedId).isEmpty() || !repositoryAcademicProgram.existsByProgramCode(academicProgramId)){
                return false;
            }

            StudentProfile s = studentProfileByIdentification(identification);
            ResearchSeedbed rs = repositoryResearchSeedbed.findById(researchSeedbedId).get();
            s.setResearchSeedbeds(List.of(rs));
            s.setAssesmentPeriod(rs.getAssesmentPeriod());
            s.setAcademicPrograms(List.of(repositoryAcademicProgram.findByProgramCode(academicProgramId)));

            repositoryStudentProfile.saveAndFlush(s);

            return true;
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<StudentProfile> getStudentProfiles() {
        return (List<StudentProfile>) repositoryStudentProfile.findAll();
    }

    public List<StudentProfile> getStudentProfiles1() {
        return (List<StudentProfile>) repositoryStudentProfile.findAll1();
    }

    public List<StudentProfile> getStudentProfiles2() {
        return (List<StudentProfile>) repositoryStudentProfile.findAll2();
    }

    @Transactional
    public Boolean addStudentProfilesByExcel(List<Map<String, String>> listOfMaps, String apid, String rsid) {

        if(listOfMaps == null || listOfMaps.isEmpty()){
            return false;
        }

        serviceUser.addNewUsers(listOfMaps);

        List<StudentProfile> studentProfiles = new ArrayList<StudentProfile>();
        for (Map<String, String> stringStringMap : listOfMaps) {
            String studentIdentification = stringStringMap.get(IntegraStudentNomenclature.IDENTIFICATION);
            StudentProfile studentProfile = new StudentProfile();
            studentProfile.setIdentificationNumber(studentIdentification);
            studentProfiles.add(studentProfile);
        }

        ResearchSeedbed rs = repositoryResearchSeedbed.findById(Long.valueOf(rsid)).get();

        for (StudentProfile studentProfile : studentProfiles) {
            String document = studentProfile.getIdentificationNumber();
            if(document == null || document.isEmpty() || document.isBlank()){
                continue;
            }
            try{
                studentProfile = studentProfileByIdentification(document);
            }
            catch (Exception e){
                System.out.printf("Error: %s", e.getMessage());
                e.printStackTrace();
                return false;
            }
            studentProfile.setAssesmentPeriod(rs.getAssesmentPeriod());
            studentProfile.setResearchSeedbeds(List.of(rs));

            ResearchSeedbed researchSeedbed = repositoryResearchSeedbed.findById(Long.valueOf(rsid)).get();
            if (researchSeedbed.getStudentsProfiles().stream().anyMatch(sp -> sp.getIdentificationNumber().equals(document))){
                continue;
            }
            else{
                repositoryStudentProfile.save(studentProfile);
            }
        }
        return true;
    }

    /**
     * This method return a pre-created student profile with the information of the student with the identification number
     * @param identification the identification number of the student
     * @return A pre-created student profile with the information of the student with the identification number
     */
    private StudentProfile studentProfileByIdentification(String identification) throws Exception {

        Map<String, Object> map = getJsonByStudentIdentification(identification);

        if(repositoryUser.findByUserIdentification(identification).isEmpty()){

            HashMap<String, String> userMap = new HashMap<>();

            userMap.put(IntegraStudentNomenclature.IDENTIFICATION, identification);
            userMap.put(IntegraStudentNomenclature.EMAIL, String.valueOf(map.get(IntegraStudentNomenclature.EMAIL)));
            userMap.put("isExternalUser", "false");

            serviceUser.addUser(userMap);
        }

        StudentProfile sp = new StudentProfile();

        sp.setUserStudent(repositoryUser.findByUserIdentification(identification).get());
        sp.setEmail(String.valueOf(map.get(IntegraStudentNomenclature.EMAIL)));
        sp.setIdentificationNumber(String.valueOf(map.get(IntegraStudentNomenclature.IDENTIFICATION)));
        sp.setName(String.valueOf(map.get(IntegraStudentNomenclature.NAME)));
        sp.setPhoneNumber(String.valueOf(map.get(IntegraStudentNomenclature.TELEPHONE)));

        String semester = String.valueOf(map.get(IntegraStudentNomenclature.SEMESTER));
        //Some students have the semester as an empty string because they are already graduated
        sp.setSemester(semester.isEmpty() ? 0 : Byte.parseByte(semester));

        sp.setSex(map.get(IntegraStudentNomenclature.SEX).equals("F") ? Sex.FEMALE : Sex.MALE);
        sp.setUserCode(String.valueOf(map.get(IntegraStudentNomenclature.CODE)));
        sp.setWasActive(false);

        return sp;
    }

    public Boolean addStudentProfileToAResearchSeedbed(HashMap<String, String> studentProfile){
        try{
            Map<String, Object> map = getJsonByStudentIdentification(studentProfile.get("identification_number"));

            Long researchSeedbedId = Long.valueOf(studentProfile.get("research_seedbed_id"));

            if(map.get("identification") == null || repositoryResearchSeedbed.findById(researchSeedbedId).isEmpty()){
                return false;
            }

            String identification = String.valueOf(map.get("identification"));
            ResearchSeedbed rs = repositoryResearchSeedbed.findById(researchSeedbedId).get();
            AssesmentPeriod ap = rs.getAssesmentPeriod();

            StudentProfile sp;
            if (repositoryStudentProfile.findByUserIdentificationAndAssesmentPeriodId(identification, ap.getId()).isEmpty()) {
                addStudentProfile(studentProfile);
            }
            sp = repositoryStudentProfile.findByUserIdentificationAndAssesmentPeriodId(identification, ap.getId()).get();
            List<ResearchSeedbed> researchSeedbeds = new ArrayList<>(sp.getResearchSeedbeds());

            if(!rs.equals(researchSeedbeds.getFirst())){
                researchSeedbeds.add(rs);
                sp.setResearchSeedbeds(researchSeedbeds);
            }
            
            repositoryStudentProfile.save(sp);
            return true;
        }
        catch (Exception e){
            System.out.printf("Error: %s", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<StudentProfile> getStudentProfilesByResearchSeedbedId(Long researchSeedbedId) {
        return repositoryStudentProfile.findAllByResearchSeedbedId(researchSeedbedId);
    }

    public List<StudentProfile> getStudentProfilesByUserIdentification(Long userIdentification) {
        return repositoryStudentProfile.findByUserIdentification(userIdentification);
    }

}
