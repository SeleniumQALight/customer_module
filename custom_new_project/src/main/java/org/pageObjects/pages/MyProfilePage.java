package org.pageObjects.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import org.junit.Assert;
import org.pageObjects.base.ParentPage;
import org.pageObjects.elements.HeaderForLoggedUser;

import java.util.ArrayList;
import java.util.List;

import static org.baseTest.BaseTestUI.getPageProviderThreadLocal;
import static org.baseTest.BaseTestUI.getPg;


public class MyProfilePage extends ParentPage {
    public final static String tabPosts_XPATH = "//a[@class='profile-nav-link nav-item nav-link active']";
    public final static String successMessage = "//*[text()='Post successfully deleted.']";

    private static String postTitleLocator = "//*[text()='%s']";

    @Getter
    HeaderForLoggedUser headerForLoggedUser;

    public MyProfilePage(Page page) {
        super(page);
        headerForLoggedUser = new HeaderForLoggedUser(page);
    }

    // get post list with title
    public static String getPostsList(String title) {
        return String.format(postTitleLocator, title);
    }

    public MyProfilePage openPage() {
        getPageProviderThreadLocal().getLoginPage().openPage();
        if (isElementDisplayed(getPageProviderThreadLocal().getLoginPage().getUsernameInput_XPATH())) {
            getPageProviderThreadLocal().getLoginPage().loginWithValidCredentials();
        }
        checkIsElementDisplayed(getPageProviderThreadLocal().getHomePage().getHeaderForLoggedUser().getAvatar_XPATH());
        click(headerForLoggedUser.getMyProfileButton_XPATH());
        checkIsElementDisplayed(tabPosts_XPATH);
        log.info("My Profile page is opened");
        return this;
    }

    public void deletePostsTillPresent(String title) {
        List<ElementHandle> postList = getPg().querySelectorAll(getPostsList(title));
        int postListSize = postList.size();
        for (int i = 0; i < postListSize; i++) {
            click(getPostsList(title));
            click(PostPage.deleteButton_XPATH);
            checkIsElementDisplayed(successMessage);
            log.info("Post with title " + title + " was deleted");
//            postList = getPg().querySelectorAll(getPostsList(title));
        }
        if (!getPg().querySelectorAll(getPostsList(title)).isEmpty()) {
            log.error("There are more than 0 posts with title " + title + " on the page after deleting posts");
            Assert.fail("There are more than 0 posts with title " + title + " on the page after deleting posts");
        } else {
            log.info(postListSize + " posts with title " + title + " were deleted");
            log.info("There are 0 posts with title " + title + " on the page after deleting posts");
        }
    }
}
