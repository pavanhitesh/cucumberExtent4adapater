package com.testrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.BeforeClass;

import java.text.SimpleDateFormat;
import java.util.Date;


@CucumberOptions(
        features = {"src/test/resources/FeatureFiles"},
        glue = {"com.stepdefinitions"},
        plugin = {"json:target/cucumber-report.json","com.cucumber.adapter.ExtentCucumberAdapter:"},
        dryRun = false,
        tags = {"@Covid"},
        strict = true)

public class TestRunner extends AbstractTestNGCucumberTests {

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        Date date = new Date();
        String dateStringFormat = dateFormat.format(date);
        System.setProperty("extent.reporter.spark.start","true");
        System.setProperty("extent.reporter.spark.out",String.format("extentReport/%s.html",dateStringFormat));
    }

    @BeforeClass
    public void startTest(){
        TestNGCucumberRunner testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        testNGCucumberRunner.provideScenarios();

    }

}
