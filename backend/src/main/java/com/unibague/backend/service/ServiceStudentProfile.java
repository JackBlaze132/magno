package com.unibague.backend.service;

import com.unibague.backend.model.AcademicProgram;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Boolean addStudentProfile(HashMap<String, String> studentProfile) {
        try{

            String urlReturn = FetchExternalData.fetchExternalDataFromStudent(studentProfile.get("identification_number"));
            Map<String, Object> map = FetchExternalData.fromStringJsonToMap(urlReturn);
            String identification = String.valueOf(map.get("identification"));

            StudentProfile s = studentProfileByIdentification(identification);

            s.setAssesmentPeriod(repositoryAssesmentPeriod.findById(Long.valueOf(studentProfile.get("assesment_period_id"))).get());

            List<AcademicProgram> list = new ArrayList<>();
            list.add(repositoryAcademicProgram.findByProgramCode(studentProfile.get("program_codes")));
            s.setAcademicPrograms(list);

            List<ResearchSeedbed> researchSeedbeds = new ArrayList<>();
            researchSeedbeds.add(repositoryResearchSeedbed.findById(Long.valueOf(studentProfile.get("research_seedbed_id"))).get());
            s.setResearchSeedbeds(researchSeedbeds);

            System.out.println(s.toString());


            repositoryStudentProfile.save(s);
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

        List<StudentProfile> studentProfiles = new ArrayList<StudentProfile>();
        for (Map<String, String> stringStringMap : listOfMaps) {
            String studentIdentification = stringStringMap.get(IntegraStudentNomenclature.IDENTIFICATION);
            StudentProfile studentProfile = new StudentProfile();
            studentProfile.setIdentificationNumber(studentIdentification);
            studentProfiles.add(studentProfile);
        }
        for (StudentProfile studentProfile : studentProfiles) {
            String document = studentProfile.getIdentificationNumber();
            if(document == null || document.isEmpty() || document.isBlank()){
                continue;
            }
            studentProfile = studentProfileByIdentification(document);
            studentProfile.setAssesmentPeriod(repositoryAssesmentPeriod.findById(Long.valueOf(apid)).get());
            studentProfile.setResearchSeedbeds(List.of(repositoryResearchSeedbed.findById(Long.valueOf(rsid)).get()));
            repositoryStudentProfile.save(studentProfile);
        }
        return true;
    }

    private StudentProfile studentProfileByIdentification(String identification) {

        String stringJson = FetchExternalData.fetchExternalDataFromStudent(identification);
        Map<String, Object> map = FetchExternalData.fromStringJsonToMap(stringJson);

        StudentProfile s = new StudentProfile();

        s.setEmail(String.valueOf(map.get(IntegraStudentNomenclature.EMAIL)));
        s.setIdentificationNumber(String.valueOf(map.get(IntegraStudentNomenclature.IDENTIFICATION)));
        s.setName(String.valueOf(map.get(IntegraStudentNomenclature.NAME)));
        s.setPhoneNumber(String.valueOf(map.get(IntegraStudentNomenclature.TELEPHONE)));

        String semester = String.valueOf(map.get(IntegraStudentNomenclature.SEMESTER));
        s.setSemester(semester.isEmpty() ? 0 : Byte.parseByte(semester));

        s.setSex(map.get(IntegraStudentNomenclature.SEX).equals("F") ? Sex.FEMALE : Sex.MALE);
        s.setUserCode(String.valueOf(map.get(IntegraStudentNomenclature.CODE)));
        s.setWasActive(false);
        s.setUserStudent(repositoryUser.findByUserIdentification(identification).get());
        s.setAcademicPrograms(List.of(repositoryAcademicProgram.findByProgramCode(String.valueOf(map.get(IntegraStudentNomenclature.PROGRAM_CODE)))));

        return s;
    }

    public List<StudentProfile> getStudentProfilesByResearchSeedbedId(Long researchSeedbedId) {
        return repositoryStudentProfile.findAllByResearchSeedbedId(researchSeedbedId);
    }

}
