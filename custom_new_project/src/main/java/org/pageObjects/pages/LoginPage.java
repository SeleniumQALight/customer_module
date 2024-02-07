package org.pageObjects.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.constants.SystemConstant;
import org.junit.Assert;
import org.pageObjects.base.CommonActions;
import org.pageObjects.base.ParentPage;

import static org.baseTest.BaseTestUI.getPg;

@Getter
public class LoginPage extends ParentPage {


    private String usernameInput_XPATH = "body > header > div > form > div > div:nth-child(1) > input";
    private String passwordInput_XPATH = "body > header > div > form > div > div:nth-child(2) > input";
    private String loginButton_XPATH = "//header//button";

    public static final String usernameRegistrationInput_XPATH = "//*[@id='username-register']";
    public static final String emailRegistrationInput_XPATH = "//*[@id='email-register']";
    public static final String passwordRegistrationInput_XPATH = "//*[@id='password-register']";
    public static String signUpButton_XPATH = "//button[text()='Sign up for OurApp']";

    public static String listErrorsMessagesLocator = "//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(Page page) {
        super(page);
    }


    public LoginPage openPage() {
        try {
            getPg().navigate(SystemConstant.BASE_URL);
            log.warn("LoginPage was opened. Url : "  ); //TODO: add url
        } catch (Exception e) {
            log.error("Can't open LoginPage" + e);
            Assert.fail("Can't open LoginPage" +    e);
        }
        return this;
    }

    public HomePage loginWithValidCredentials() {
        String username = SystemConstant.USERNAME;
        String password = SystemConstant.PASSWORD;
        try {
            getPg().fill(usernameInput_XPATH, username);
            getPg().fill(passwordInput_XPATH, password);
            getPg().click(loginButton_XPATH);
            log.warn("Login performed with username: " + username + " and default password");
        } catch (Exception e) {
            log.error("Can't login with username: " + username + " and default password");
            Assert.fail("Can't login with username: " + username + " and default password");
        }
        return new HomePage(page);
    }

    public HomePage loginWithNotValidCredentials(String username, String password) {

        try {
            getPg().fill(usernameInput_XPATH, username);
            getPg().fill(passwordInput_XPATH, password);
            getPg().click(loginButton_XPATH);
            log.warn("Login performed with username: " + username + " and  password: " + password);
        } catch (Exception e) {
            log.error("Can't login with username: " + username + " and  password: " + password);
            Assert.fail("Can't login with username: " + username + " and  password: " + password);
        }
        return new HomePage(page);
    }
}
