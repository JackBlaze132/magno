package com.unibague.backend.util;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class FetchExternalData {

    private final static RestTemplate restTemplate = new RestTemplate();

    public static String fetchExternalDataFromStudent(String identification_number) {
        String url = "http://integra.unibague.edu.co/studentInfo?api_token=$2y$42$s/9xFMDieYOEvYD/gfPqFAeFzvWXt13feXyterJzQ9rZKrbLpBYUqo&code_user=" + identification_number +"&type=I";
        try{
            return restTemplate.getForObject(url, String.class).replace("[", "").replace("]", "");
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    public static Map<String, Object> fromStringJsonToMap(String json) {
        JSONObject jsonObject = new JSONObject(json);
        Map<String, Object> map = jsonObject.toMap();
        return map;
    }

}
