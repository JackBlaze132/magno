package com.unibague.backend.service;

import com.unibague.backend.model.AcademicProgram;
import com.unibague.backend.model.StudentProfile;
import com.unibague.backend.repository.RepositoryAcademicProgram;
import com.unibague.backend.repository.RepositoryAssesmentPeriod;
import com.unibague.backend.repository.RepositoryStudentProfile;
import com.unibague.backend.repository.RepositoryUser;
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

    public Boolean addStudentProfile(HashMap<String, String> studentProfile) {
        try{

            String urlReturn = FetchExternalData.fetchExternalDataFromStudent(studentProfile.get("identification_number"));
            System.out.println(urlReturn);
            Map<String, Object> map = FetchExternalData.fromStringJsonToMap(urlReturn);

            StudentProfile s = new StudentProfile();

            s.setEmail(String.valueOf(map.get("email")));
            s.setIdentificationNumber(String.valueOf(map.get("identification")));
            s.setName(String.valueOf(map.get("name")));
            s.setPhoneNumber(String.valueOf(map.get("telephone")));
            s.setSemester(Byte.parseByte(String.valueOf(map.get("semester"))));
            s.setSex(map.get("sexo").equals("F") ? Sex.FEMALE : Sex.MALE);
            s.setUserCode(String.valueOf(map.get("code_student")));
            s.setWasActive(false);

            s.setAssesmentPeriod(repositoryAssesmentPeriod.findById(Long.valueOf(studentProfile.get("assesment_period_id"))).get());
            s.setUserStudent(repositoryUser.findByUserIdentification(studentProfile.get("identification_number")).get());
            List<AcademicProgram> list = new ArrayList<>();
            list.add(repositoryAcademicProgram.findByProgramCode(studentProfile.get("program_codes")));
            s.setAcademicPrograms(list);

            System.out.println(s.toString());


            //repositoryStudentProfile.save(s);
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
}
