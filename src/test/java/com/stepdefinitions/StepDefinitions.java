package com.stepdefinitions;

import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumber.adapter.ExtentReport;
import com.generalactions.APIActions;
import com.generalactions.SendMail;
import com.pageobjects.SeleniumDevPageObjects;
import com.utilites.DriverFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import java.util.*;

public class StepDefinitions {
    Session session;
    Message message;
    Map<String,Object> responseMap;
    APIActions apiActions;

    SeleniumDevPageObjects seleniumDevPageObjects = new SeleniumDevPageObjects();


    @Given("user configure the gmail configuration to send mail")
    public void user_configure_the_gmail_configuration_to_send_mail() {
        session = SendMail.setGmailProperties();
    }

    @When("user enter the message to send {string} to the mailId {string}")
    public void user_enter_the_message_to_send_to_the_mailId(String messageBody, String mailTo) throws MessagingException {
        message = SendMail.getMessage(session,messageBody,mailTo);
    }

    @Then("user send the message")
    public void user_send_the_message() throws MessagingException {
        SendMail.sendMessage(message);
    }


    @Given("user configured the api with details from the properties file {string}")
    public void user_configured_the_api_with_details_from_the_properties_file(String apiKey) {
        apiActions = new APIActions(apiKey);
        apiActions.configureAPI();
    }

    @When("user get the latest covid information from endpoint {string}")
    public void user_get_the_latest_covid_information_from_endpoint(String endPoint) {
        responseMap = apiActions.getIndiaCovidInfo(endPoint);
    }

    @Then("validate the response with following fields displayed")
    public void validate_the_response_with_following_fields_displayed(List<String> headers) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType
        System.out.println(headers);
        LinkedHashMap<String,String> data = new LinkedHashMap<>();
        headers.forEach(head->{
            Map<String,String> da = new HashMap<>();
            data.put(head,String.valueOf(responseMap.get(head)));
        });

        ExtentReport.addMapAsTable(data,"Please find the results");
        System.out.println(responseMap);

    }

    @Given("User navigate to url {string}")
    public void user_navigate_to_url(String url) {
        DriverFactory.getWebDriver().get(url);
    }

    @When("User navigate to projects")
    public void user_navigate_to_projects() {
       seleniumDevPageObjects.clickProjects();
    }

    @When("User navigate to documents")
    public void user_navigate_to_documents() {
       seleniumDevPageObjects.clickDocumentations();
    }

    @Then("user sucessfully navigated to projects")
    public void user_sucessfully_navigated_to_projects() {
        String title = DriverFactory.getWebDriver().getTitle();
       Assert.assertEquals(title,"Selenium Projects");
    }

    @Then("user sucessfully navigated to documents")
    public void user_sucessfully_navigated_to_documents() {
        String title = DriverFactory.getWebDriver().getTitle();
       Assert.assertEquals(title,"The Selenium Browser Automation Project :: Documentation for Selenium");
    }

}
