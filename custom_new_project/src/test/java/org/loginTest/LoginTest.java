package org.loginTest;

import org.baseTest.BaseTestUI;
import org.junit.Ignore;
import org.junit.Test;

public class LoginTest extends BaseTestUI {
    @Test
//    @Ignore

    public void TR3_loginWithValidCred() {
        getPageProviderThreadLocal()
                .getLoginPage()
                .openPage()
                .loginWithValidCredentials();

        getPageProviderThreadLocal().getHomePage()
                .checkIsElementDisplayed(getPageProviderThreadLocal().getHomePage().getHeaderForLoggedUser().getAvatar_XPATH()+12);


    }
}
