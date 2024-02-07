package org.baseTest;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import org.junit.runner.RunWith;
import org.propertiesInterfaces.TestLinkConfigProperties;
import org.testLink.TestLinkManager;


import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import static org.baseTest.BaseTestUI.getPg;
import static org.testLink.ReportOfWorkingWithTestLink.printListToFile;


public class BaseTest {

    protected Playwright playwright;
//    protected Logger log = Logger.getLogger(this.getClass());
    protected final Logger log = LogManager.getLogger(this.getClass());

    private TestLinkConfigProperties testLinkConfigProperties = ConfigFactory.create(TestLinkConfigProperties.class);


    protected static ThreadLocal<Playwright> pw = new ThreadLocal<>();

    public static Playwright getPw() {
        return pw.get();
    }

    TestLinkManager testLinkManager;
    public static ArrayList<String> listOfTestCasesNotFoundInTestLink = new ArrayList<>();
    public static ArrayList<String> listOfTestCasesFoundInTestLink = new ArrayList<>();

    {
        try {
            testLinkManager = new TestLinkManager();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUpBaseTest() {
        log.warn("Test name: " + testName.getMethodName() + " started");
        log.debug("BaseTest: setUp");
        playwright = Playwright.create();
        pw.set(playwright);
        log.info("Playwright was created");
    }

    @AfterClass
    public static void tearDownForAllSuit(){
//        playwright.close();

        String path = "./target/testCasesNotFoundInTestLink.txt";
        printListToFile(listOfTestCasesNotFoundInTestLink, listOfTestCasesFoundInTestLink, path);
        System.out.println("File with test cases not found in TestLink was created in path " + path);

    }

    @Rule
    public TestName testName = new TestName();

    @Rule()
    public final TestWatcher watchman = new TestWatcher() {


        @Override
        protected void failed(Throwable e, Description description) {
            String testCasePrefix = testLinkConfigProperties.TEST_CASE_PREFIX();
            String testCaseExternalId = testName.getMethodName().split("_")[0].replace(testCasePrefix, testCasePrefix +"-");
            log.error("Test failed: " + testName.getMethodName());
//            testLinkManager.reportResult(testCaseExternalId, ExecutionStatus.FAILED, e.getMessage());
//            public void reportResult(String testcaseFullExternalId, int version, String testProjectName, String testPlanName, String buildName, String status){
            testLinkManager.reportResult(testCaseExternalId, 1, testLinkConfigProperties.TEST_PROJECT_NAME(), testLinkConfigProperties.TEST_PLAN_NAME(), testLinkConfigProperties.BUILD_NAME(), ExecutionStatus.FAILED, e.getMessage());
        }

        @Override
        protected void succeeded(Description description) {
//            getPg().screenshot(new Page.ScreenshotOptions()
//                    .setPath(Paths.get("./target/screenshots/"+testName.getMethodName()+ "/screenshot.png"))
//                    .setFullPage(true));
//            File file = new File("./target/screenshots/"+testName.getMethodName()+ "/screenshot.png");
//            ReportPortal.emitLog("ScreenShot", LogLevel.INFO.name(), new Date(), file);

            String testCasePrefix = testLinkConfigProperties.TEST_CASE_PREFIX();
            String testCaseExternalId = testName.getMethodName().split("_")[0].replace(testCasePrefix, testCasePrefix +"-");
           log.info("Test passed: " + testName.getMethodName());
//            testLinkManager.reportResult(testCaseExternalId, ExecutionStatus.PASSED, " Test passed");
            testLinkManager.reportResult(testCaseExternalId, 1, testLinkConfigProperties.TEST_PROJECT_NAME(), testLinkConfigProperties.TEST_PLAN_NAME(), testLinkConfigProperties.BUILD_NAME(), ExecutionStatus.PASSED, " Test passed");

        }

    };


}
