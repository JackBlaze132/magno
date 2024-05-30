package com.unibague.backend.controller;

import com.unibague.backend.service.ServiceUpload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ControllerUpload {

    private final ServiceUpload serviceUpload;

    public ControllerUpload(ServiceUpload serviceUpload) {
        this.serviceUpload = serviceUpload;
    }

    @PostMapping(path = "/uploadExcel")
    public void uploadExcel(@RequestParam("file")MultipartFile file) {
        try{
            serviceUpload.uploadExcel(file);
        }
        catch (Exception e){
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }
}
