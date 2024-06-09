package com.unibague.backend.service;

import com.unibague.backend.model.AcademicProgram;
import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.model.StudentProfile;
import com.unibague.backend.model.User;
import com.unibague.backend.repository.*;
import com.unibague.backend.util.FetchExternalData;
import com.unibague.backend.util.Sex;
import org.json.JSONObject;
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

    public Boolean addStudentProfilesByExcel(List<StudentProfile> studentProfiles, String apid, String rsid) {
        for (StudentProfile studentProfile : studentProfiles) {
            String document = studentProfile.getIdentificationNumber();
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

        s.setEmail(String.valueOf(map.get("email")));
        s.setIdentificationNumber(String.valueOf(map.get("identification")));
        s.setName(String.valueOf(map.get("name")));
        s.setPhoneNumber(String.valueOf(map.get("telephone")));
        s.setSemester(Byte.parseByte(String.valueOf(map.get("semester"))));
        s.setSex(map.get("sexo").equals("F") ? Sex.FEMALE : Sex.MALE);
        s.setUserCode(String.valueOf(map.get("code_student")));
        s.setWasActive(false);
        s.setUserStudent(repositoryUser.findByUserIdentification(identification).get());
        s.setAcademicPrograms(List.of(repositoryAcademicProgram.findByProgramCode(String.valueOf(map.get("program_code")))));

        return s;
    }

}
