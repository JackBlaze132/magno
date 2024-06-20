package com.unibague.backend.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FetchExternalData {

    private final static RestTemplate restTemplate = new RestTemplate();

    public static String fetchExternalDataFromStudent(String identificationNumber) {
        String url = "http://integra.unibague.edu.co/studentInfo?api_token=$2y$42$s/9xFMDieYOEvYD/gfPqFAeFzvWXt13feXyterJzQ9rZKrbLpBYUqo&code_user=" + identificationNumber +"&type=I";
        try{
            return restTemplate.getForObject(url, String.class).replace("[", "").replace("]", "");
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    public static Map<String, Object> fetchExternalDataFromFunctionary(String identificationNumber){
        String url = "http://integra.unibague.edu.co/functionariesChart/functionaries?api_token=$2y$10$s/5xSDieUMEvYD/gfNqFAeFzvWXt13jhWuugpJzQ9rZQrbGpBYUxi";
        try{
            String jsonString = restTemplate.getForObject(url, String.class);
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(jsonString);

            Gson gson = new Gson();
            List<Map<String, Object>> list = gson.fromJson(jsonElement, new TypeToken<List<Map<String, Object>>>() {}.getType());
            return getFunctionaryData(identificationNumber, list);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return new HashMap<String, Object>();
    }

    private static Map<String, Object> getFunctionaryData(String identificationNumber, List<Map<String, Object>> list) {
        long startTime = System.nanoTime();
        Map<String, Map<String, Object>> identificationMap = new HashMap<>();

        for (Map<String, Object> map : list) {
            String identification = (String) map.get("identification");
            identificationMap.computeIfAbsent(identification, k -> new HashMap<>()).putAll(map);
        }

        Map<String, Object> mapByIdentificacion = identificationMap.get(identificationNumber);
        long endTime= System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime);
        return mapByIdentificacion;
    }

    public static Map<String, Object> fromStringJsonToMap(String json) {
        try{
            JSONObject jsonObject = new JSONObject(json);
            Map<String, Object> map = jsonObject.toMap();
            return map;
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            System.out.println(json + "fin del json");
            return null;
        }
    }

}
