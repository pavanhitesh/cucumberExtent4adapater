package com.utilites;

import com.exception.AutomationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class DriverFactory {

    private static final String API_CONFIGURATION_PATH = String.format("%s/src/test/resources/config/%s",System.getProperty("user.dir"),"Configuration.json");
    private static List<Map<String,Object>> configList = new ArrayList<>();
    private static WebDriver webDriver;
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new InheritableThreadLocal<>();

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

    private static void createWebDriverInstance(){
        if(webDriverThreadLocal.get()==null){
            System.setProperty("webdriver.chrome.driver",String.format("%s/src/test/resources/chromedriver.exe",System.getProperty("user.dir")));
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("start-maximized");
            webDriver = new ChromeDriver(chromeOptions);
            webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            webDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            webDriverThreadLocal.set(webDriver);
        }
    }

    public static WebDriver getWebDriver(){
        createWebDriverInstance();
        return webDriverThreadLocal.get();
    }

}
