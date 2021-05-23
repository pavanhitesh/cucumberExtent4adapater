package com.generalactions;

import com.exception.AutomationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utilites.DriverFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

public class APIActions {
    ObjectMapper objectMapper;
    private Map<String,Object> properties;
    private RequestSpecification requestSpecification;
    private Response response;
    private String key;
    private static final String CONTENT_TYPE="Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";

    public APIActions(String key){
        this.key = key;
        properties = DriverFactory.getConfiguration(key);
        objectMapper = new ObjectMapper();
    }

    private Response getAPIResponse(String endPoint){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(getPropertyValue("baseURI"));
        requestSpecification.header(CONTENT_TYPE,CONTENT_TYPE_VALUE);
        response = requestSpecification.relaxedHTTPSValidation().get(endPoint);
        return response;
    }

    public void configureAPI(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(getPropertyValue("baseURI"));
        requestSpecification.header(CONTENT_TYPE,CONTENT_TYPE_VALUE);
    }

    public Map<String,Object> getIndiaCovidInfo(String getURI){
        Map<String,Object> info = new HashMap<String,Object>();
        try{
            response = requestSpecification.relaxedHTTPSValidation().get(getURI);
            if(response.getStatusCode()!=200){
                throw  new AutomationException(String.format("Unable to get Data [%s]",response.asString()));
            }
            info = objectMapper.readValue(response.getBody().asString(),objectMapper.getTypeFactory().constructType(Map.class));
        }catch(Exception e){
            throw new AutomationException("Unable to get API Response [%s]",e);
        }
        return info;
    }



    private String getPropertyValue(String key){
        try {
            return String.valueOf(this.properties.get(key));
        }catch(Exception e){
            throw new AutomationException(String.format("Unable to find the key [%s] from properties session [%s]",key,this.key));
        }
    }


}
