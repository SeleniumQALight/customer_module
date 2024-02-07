package org.loginTest;

import org.baseTest.BaseTest;
import org.baseTest.BaseTestUI;
import org.junit.Ignore;
import org.junit.Test;




public class LoginNotValidTest extends BaseTestUI {
    @Test
//  @Ignore
    public void TR2_loginWithNotValidCred() {
        getPageProviderThreadLocal().getLoginPage()
                .openPage()
                .loginWithNotValidCredentials("NotValidLogin", "NotValidPass");

        getPageProviderThreadLocal().getHomePage()
                .checkIsElementNotDisplayed(
                        getPageProviderThreadLocal().getHomePage().getHeaderForLoggedUser().getAvatar_XPATH());


    }
}
