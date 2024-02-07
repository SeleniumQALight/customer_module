package org.registrationNewUserTests;

import com.google.common.base.Optional;
import com.nordstrom.automation.junit.ArtifactParams;
import com.nordstrom.automation.junit.AtomIdentity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.baseTest.BaseTest;
import org.baseTest.BaseTestUI;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Map;

@RunWith(Parameterized.class)
public class ValidationMessagesTest extends BaseTestUI implements ArtifactParams {

//    @Test
//    @junitparams.Parameters(method = "parametersForCheckErrorsTest")
//    public void TR07_checkValidationMessages(String userName, String email, String password, int expectedMessages){
//        getPageProviderThreadLocal().getLoginPage()
//                .openPage()
//                .enterText(LoginPage.usernameRegistrationInput_XPATH, userName)
//                .enterText(LoginPage.emailRegistrationInput_XPATH, email)
//                .enterText(LoginPage.passwordRegistrationInput_XPATH, password)
//                .click(LoginPage.signUpButton_XPATH)
//                .waitTime(1000)
//                .checkNumberOfElements(expectedMessages, LoginPage.listErrorsMessagesLocator)
//        ;
//    }
//
//    public Object[][] parametersForCheckErrorsTest() {
//        return new Object[][]{
//                {"test", "trtr", "123456",  3},
//                {"test", "tr@tr.com", "123", 2}
//        };
//    }



    private static final Logger LOGGER = LogManager.getLogger(ValidationMessagesTest.class);

    @Rule
    public final AtomIdentity identity = new AtomIdentity(this);

    private String input;

    public ValidationMessagesTest(String input) {
        this.input = input;
    }

    @Parameters
    public static Object[] data() {
        return new Object[] { "param1", "param2" };
    }

    @Test
    @Ignore
    public void simpleParameterizedTest() {
        LOGGER.info("running test: " + getDescription().getMethodName() + ", parameter: " + input);
    }


    @Override
    public AtomIdentity getAtomIdentity() {
        return new AtomIdentity(this);
    }

    @Override
    public Description getDescription() {
        return identity.getDescription();
    }


    @Override
    public Optional<Map<String, Object>> getParameters() {
        return identity.getParameters();
    }
}
