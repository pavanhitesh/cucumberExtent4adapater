package com.utilites;

import com.exception.AutomationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;


public class DriverFactory {

    private static final String API_CONFIGURATION_PATH = String.format("%s/src/test/resources/config/%s",System.getProperty("user.dir"),"Configuration.json");
    private static List<Map<String,Object>> configList = new ArrayList<>();

    private static void loadConfiguration(){
        try{
            if(configList.isEmpty()){
                ObjectMapper mapper = new ObjectMapper();
                configList = mapper.readValue(new File(API_CONFIGURATION_PATH),mapper.getTypeFactory().constructCollectionType(List.class,Map.class));
            }
        }catch (Exception e){
            throw new AutomationException(String.format("Exception at loading Configuration with [%s]",e));
        }
    }

    public static Map<String,Object> getConfiguration(String key){
        loadConfiguration();
        Optional<Map<String, Object>> configuration;
        configuration = configList.stream().filter(map->map.get("key").equals(key)).findAny();
        return configuration.get();
    }


}
