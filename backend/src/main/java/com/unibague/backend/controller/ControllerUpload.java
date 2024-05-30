package com.unibague.backend.controller;

import com.unibague.backend.service.ServiceUpload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class ControllerUpload {

    private final ServiceUpload serviceUpload;

    public ControllerUpload(ServiceUpload serviceUpload) {
        this.serviceUpload = serviceUpload;
    }

    @PostMapping(path = "/uploadExcel")
    public List<Map<String, String>> uploadExcel(@RequestParam("file")MultipartFile file) {
        try{
            return serviceUpload.uploadExcel(file);
        }
        catch (Exception e){
            System.out.println("Error: ");
            e.printStackTrace();
        }
        return null;
    }
}
