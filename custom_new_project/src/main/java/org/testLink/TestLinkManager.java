package org.testLink;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStepResult;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import org.baseTest.BaseTest;
import org.utilities.TimeUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.baseTest.BaseTest.listOfTestCasesFoundInTestLink;

public class TestLinkManager {
    public static final String DEV_KEY = "1b2fa5b7289a68959e1a2652cff55ef0";
    public static final String URL = "http://localhost:8888/testlink2/lib/api/xmlrpc/v1/xmlrpc.php";

    private final String TEST_CASE_NAME = "First test case";

    private final String testProjectName = "TestProject";
    private final String testPlanName = "New first testplan";
    private final String testSuitName  = "first test suit";
    private final String buildName = "2.003.1";
//    private final int testSuitId  = 2;


//    private TestLinkManager TEST_LINK_MANAGER = new TestLinkManager();



    private TestLinkAPI testLinkAPI;

    public TestLinkManager() throws MalformedURLException {
        testLinkAPI = new TestLinkAPI(new URL(URL), DEV_KEY);
    }

    public Integer getTestPlanID (){
        return testLinkAPI.getTestPlanByName(testPlanName, testProjectName).getId();
    }

    public int getTestCaseIDByName(String testCaseName){

        try {
            return testLinkAPI.getTestCaseIDByName(testCaseName, testSuitName, testProjectName, null);
        }catch (TestLinkAPIException e){
            System.out.println("!!!! Cant get caseId. There is not test with name " + testCaseName);
            throw  new TestLinkAPIException(e);
        }


    }

    public String getTestCaseName(){
        return testLinkAPI.getTestCase(3, 1, 1).getPlatform().getName();
    }

//    public int getSuiteIDByName(String suiteName){
//        return testLinkAPI.getsuit(suiteName, testProjectName, null);
//    }
//
//    public TestCase[] getTestCasesForSuite(){
//        return testLinkAPI.getTestCasesForTestSuite(1, true, TestCaseDetails.FULL);
//
//    }


    private TestCase getTestCaseIdByExternalID(String testCaseExternalID, int version){
        return testLinkAPI.getTestCaseByExternalId(testCaseExternalID, version);
    }

    private int getTestCaseExternalIDByName(String testCaseName){
        return testLinkAPI.getTestCaseIDByName(testCaseName, testSuitName, testProjectName, null);
    }

    private int getBuildIDByName(){
        return testLinkAPI.getLatestBuildForTestPlan(getTestPlanID()).getId();
    }

    public void reportResult(ExecutionStatus status, String notes){
        reportResult(TEST_CASE_NAME, status, notes);
    }

    public void reportResult(String testcaseFullExternalId, int version, String testProjectName, String testPlanName, String buildName, ExecutionStatus status, String notes){
        try {
            TestCase currentTestCase = getTestCaseIdByExternalID(testcaseFullExternalId, version);

            testLinkAPI.reportTCResult(currentTestCase.getId(), // Integer testCaseId
                    currentTestCase.getExternalId(), // String testCaseExternalId
                    getTestPlanID(),
                    status,
                    new ArrayList<>(), // List<TestCaseStepResult> steps
                    getBuildIDByName(),
                    buildName,
                    "Note :  " + new Date() + " \n" + notes, // String notes
                    1, // Integer executionDuration
                    true,  // Boolean guess
                    "Bug ID", // String bugId
                    0, // Integer platformId
                    "Windows", // String platformName
                    new HashMap<>(), // Map<String,String> customFields
                    false, // Boolean overwrite
                    "automation", // String user
                    TimeUtil.getDateAndTimeFormattedWithSpace() // "2021-09-01 00:00:00"    // String timestamp
            );
            listOfTestCasesFoundInTestLink.add(testcaseFullExternalId + " \n");
        } catch (TestLinkAPIException e) {
            System.out.println("!!!! There is not test with name " + testcaseFullExternalId);
            BaseTest.listOfTestCasesNotFoundInTestLink.add(testcaseFullExternalId + " \n");
        }
    }


    public void reportResult(String testCaseName, ExecutionStatus status, String notes){
        try {
//            System.out.println("Project name: " + testLinkAPI.getProjects()[0].getName() + " id: " + testLinkAPI.getProjects()[0].getId());
//            System.out.println(testLinkAPI.getProjects()[0]);
//            TestPlan projectTestPlan = testLinkAPI.getProjectTestPlans(testLinkAPI.getProjects()[0].getId())[0];
//            System.out.println("test plan " + testPlanName + " id: " + projectTestPlan.getId());
//            TestSuite testSuite = testLinkAPI.getFirstLevelTestSuitesForTestProject(1)[0];
//            System.out.println("test suit " + testSuitName + " id: " + testSuite.getId());


//            testLinkAPI.testCaseService.getTestCasesForTestSuite(2,false, TestCaseDetails.FULL);
//            testLinkAPI.getTestCaseByExternalId("TR-3", 1)

            testLinkAPI.reportTCResult(getTestCaseIDByName(testCaseName),
                    getTestCaseExternalIDByName(testCaseName),
                    getTestPlanID(),
                    status,
                    new ArrayList<>(), // List<TestCaseStepResult> steps
                    getBuildIDByName(),
                    buildName,
                    "<b>Notes : </b> " + new Date() + " \n" + notes, // String notes
                    1, // Integer executionDuration
                    true,  // Boolean guess
                    "Bug ID", // String bugId
                    0, // Integer platformId
                    "Windows", // String platformName
                    new HashMap<>(), // Map<String,String> customFields
                    false, // Boolean overwrite
                    "automation", // String user
                    TimeUtil.getDateAndTimeFormattedWithSpace() // "2021-09-01 00:00:00"    // String timestamp
            );
            listOfTestCasesFoundInTestLink.add(testCaseName + " \n");
        } catch (TestLinkAPIException e) {
            System.out.println("!!!! There is not test with name " + testCaseName);
            BaseTest.listOfTestCasesNotFoundInTestLink.add(testCaseName + " \n");
        }

    }



}
