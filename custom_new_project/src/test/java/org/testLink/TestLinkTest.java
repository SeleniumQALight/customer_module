package org.testLink;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import org.baseTest.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

import java.net.MalformedURLException;

public class TestLinkTest extends BaseTest {
    @Test
//    @Ignore
    public void TR1_testTestLink() throws MalformedURLException {
        System.out.println("testTestLink");
        TestLinkManager testLinkManager = new TestLinkManager();
        System.out.println(testLinkManager.getTestPlanID());
//        System.out.println(testLinkManager.getTestCaseIDByName("TR_-1 : First test case - Version 1"));
//        System.out.println(testLinkManager.getTestCaseName());
        //testLinkManager.reportResult("First test case", ExecutionStatus.PASSED, "First test case passed");

    }
}
