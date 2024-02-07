package org.baseTest;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.pageObjects.base.PageProvider;
import org.testLink.TestLinkManager;
import org.utilities.TimeUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;


public class BaseTestUI extends BaseTest {

    private Browser browser;
    private Page page;


    private static ThreadLocal<Browser> br = new ThreadLocal<>();
    private static ThreadLocal<Page> pg = new ThreadLocal<>();

//    protected Logger log = Logger.getLogger(this.getClass());
    protected final Logger log = LogManager.getLogger(this.getClass());
    protected static PageProvider pageProvider;




    public static Browser getBr() {
        return br.get();
    }

    public static Page getPg() {
        return pg.get();
    }


    private static ThreadLocal<PageProvider> pageProviderThreadLocal = new ThreadLocal<>();



    public static PageProvider getPageProviderThreadLocal() {
        return pageProviderThreadLocal.get();
    }



    @Before
    public void setUp() {
        log.warn("Test name: " + testName.getMethodName() + " started");
        log.debug("BaseTestUI: setUp");
//        browser = getPw().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        //chrome browser
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        br.set(browser);
        page = getBr().newPage();
        pg.set(page);
        pageProvider = new PageProvider(getPg());
        pageProviderThreadLocal.set(pageProvider);
        log.debug("Test Execution started !!!");
    }

    @After
    public void tearDown() {
        getPg().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("./target/screenshots/"+testName.getMethodName()+ "/screenshot.png"))
                .setFullPage(true));
        TimeUtil.waitABit(1);
        File file = new File("./target/screenshots/"+testName.getMethodName()+ "/screenshot.png");
        ReportPortal.emitLog("ScreenShot", LogLevel.INFO.name(), new Date(), file);

        log.debug("Test Execution finished !!!");
        log.debug("BaseTest: tearDown");
        getPg().close();
        getBr().close();
        getPw().close();
       // log.debug("Page, Browser, playwright closed");
        log.info("Test name: " + testName.getMethodName() + " finished");
    }






}
