package com.pageobjects;

import com.utilites.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumDevPageObjects {

    public SeleniumDevPageObjects(){
        PageFactory.initElements(DriverFactory.getWebDriver(),this);
    }
    @FindBy(linkText = "Projects")
    private  WebElement linkProject;
    @FindBy(linkText = "Documentation")
    private  WebElement linkDocumentation;

    public void clickProjects(){
        linkProject.click();
    }

    public void clickDocumentations(){
        linkDocumentation.click();
    }

}
