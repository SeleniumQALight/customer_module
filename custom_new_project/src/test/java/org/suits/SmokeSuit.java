package org.suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTest.LoginNotValidTest;
import org.loginTest.LoginTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        LoginNotValidTest.class

})
public class SmokeSuit {
}
