package com.unibague.backend.service;

import com.unibague.backend.model.AcademicProgram;
import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.model.StudentProfile;
import com.unibague.backend.repository.*;
import com.unibague.backend.util.ExceptionLogger;
import com.unibague.backend.util.FetchExternalData;
import com.unibague.backend.util.IntegraStudentNomenclature;
import com.unibague.backend.util.Sex;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    ServiceUpload serviceUpload;

    private Map<String, Object> getJsonByStudentIdentification(String identification) throws Exception {
        String urlReturn = FetchExternalData.fetchExternalDataFromStudent(identification);
        return FetchExternalData.fromStringJsonToMap(urlReturn);
    }

    public List<ResearchSeedbed> getStudentProfileResearchSeedbeds(String identification) {
        Optional<List<ResearchSeedbed>> researchSeedbeds = repositoryStudentProfile.getStudentProfileResearchSeedbeds(identification);
        if (researchSeedbeds.isPresent()){
            return researchSeedbeds.get();
        }else{
            ExceptionLogger.logException(new Exception("Error getting research seedbeds from student profile with identification number: " + identification));
            return new ArrayList<>();
        }
    }

    /**
     * This method adds a student profile to the database, the method suppose that the student profile is not already in the database
     *
     * @param studentProfile a json with the student profile information
     * @return true if the student profile was added successfully, false otherwise
     */
    public Boolean addStudentProfile(HashMap<String, String> studentProfile) {
        try {
            Map<String, Object> map = getJsonByStudentIdentification(studentProfile.get("identification_number"));
            String identification = String.valueOf(map.get(IntegraStudentNomenclature.IDENTIFICATION));

            Long researchSeedbedId = Long.valueOf(studentProfile.get("research_seedbed_id"));
            String academicProgramId = (String) map.get("program_code");

            if (repositoryResearchSeedbed.findById(researchSeedbedId).isEmpty() || !repositoryAcademicProgram.existsByProgramCode(academicProgramId)) {
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
            ExceptionLogger.logException(e);
            System.out.printf("Error: %s", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<StudentProfile> getStudentProfiles() {
        return repositoryStudentProfile.findAll();
    }

    /**
     * This method adds students profiles to a research seedbed by an Excel file, notice that
     * this method just make a call to the method addStudentProfileToAResearchSeedbed for each student in the Excel file
     *
     * @param file              The Excel file with "identification" column, "email" column and their respective values
     * @param researchSeedbedId The id of the research seedbed where the students will be added
     * @return true if the students were added successfully, false otherwise
     */
    @Transactional
    public Boolean addStudentsProfilesByExcel(MultipartFile file, String researchSeedbedId) {

        try {

            List<Map<String, String>> listOfMaps = serviceUpload.uploadExcel(file);

            if (listOfMaps == null || listOfMaps.isEmpty()) {
                return false;
            }

            for (Map<String, String> map : listOfMaps) {

                if (map.get(IntegraStudentNomenclature.IDENTIFICATION) == null || map.get(IntegraStudentNomenclature.IDENTIFICATION).isEmpty()) {
                    continue;
                }

                map.put("research_seedbed_id", researchSeedbedId);
                map.put("identification_number", map.get(IntegraStudentNomenclature.IDENTIFICATION));
                addStudentProfileToAResearchSeedbed((HashMap<String, String>) map);
            }
            return true;
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            ExceptionLogger.logException(e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method return a pre-created student profile with the information of the student with the identification number
     *
     * @param identification the identification number of the student
     * @return A pre-created student profile with the information of the student with the identification number
     */
    private StudentProfile studentProfileByIdentification(String identification) throws Exception {

        Map<String, Object> map = getJsonByStudentIdentification(identification);

        if (repositoryUser.findByUserIdentification(identification).isEmpty()) {

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

    //TODO: Implement the functionalities related to postgraduate students
    public Boolean addStudentProfileToAResearchSeedbed(HashMap<String, String> studentProfile) {
        try {
            Map<String, Object> map = getJsonByStudentIdentification(studentProfile.get("identification_number"));

            Long researchSeedbedId = Long.valueOf(studentProfile.get("research_seedbed_id"));

            if (map.get("identification") == null || repositoryResearchSeedbed.findById(researchSeedbedId).isEmpty()) {
                return false;
            }

            String identification = String.valueOf(map.get("identification"));
            ResearchSeedbed rs = repositoryResearchSeedbed.findById(researchSeedbedId).get();
            AssesmentPeriod ap = rs.getAssesmentPeriod();

            StudentProfile sp;
            if (repositoryStudentProfile.findByUserIdentificationAndAssesmentPeriodId(identification, ap.getId()).isEmpty()) {
                return addStudentProfile(studentProfile);
            }
            sp = repositoryStudentProfile.findByUserIdentificationAndAssesmentPeriodId(identification, ap.getId()).get();
            List<ResearchSeedbed> researchSeedbeds = new ArrayList<>(sp.getResearchSeedbeds());

            //If the student is already in the research seedbed (rs), return false
            if (researchSeedbeds.stream().noneMatch(r -> r.getId().equals(researchSeedbedId))) {
                researchSeedbeds.add(rs);
                sp.setResearchSeedbeds(researchSeedbeds);
            } else {
                return false;
            }

            repositoryStudentProfile.save(sp);
            return true;
        } catch (Exception e) {
            ExceptionLogger.logException(e);
            System.out.printf("Error: %s", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method deletes a student profile from a research seedbed
     *
     * @param map a JSON WITH THE KEYS "research_seedbed_id" and "student_profile_id"
     * @return true if the student profile was deleted successfully, false otherwise
     */
    public Boolean deleteStudentProfileFromResearchSeedbed(HashMap<String, String> map) {
        try {
            Long researchSeedbedId = Long.valueOf(map.get("research_seedbed_id"));
            Long studentProfileId = Long.valueOf(map.get("student_profile_id"));

            if (repositoryResearchSeedbed.findById(researchSeedbedId).isEmpty() || repositoryStudentProfile.findById(studentProfileId).isEmpty()) {
                return false;
            }

            ResearchSeedbed rs = repositoryResearchSeedbed.findById(researchSeedbedId).get();
            StudentProfile sp = repositoryStudentProfile.findById(studentProfileId).get();

            if (sp.getResearchSeedbeds().stream().anyMatch(r -> r.getId().equals(researchSeedbedId))) {
                List<ResearchSeedbed> researchSeedbeds = new ArrayList<>(sp.getResearchSeedbeds());
                researchSeedbeds.removeIf(r -> r.getId().equals(researchSeedbedId));
                sp.setResearchSeedbeds(researchSeedbeds);
                repositoryStudentProfile.save(sp);
                return true;
            }
            return false;

        } catch (Exception e) {
            ExceptionLogger.logException(e);
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
