package org.pageObjects.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.pageObjects.base.ParentPage;
import org.pageObjects.elements.HeaderForLoggedUser;

import static org.baseTest.BaseTestUI.getPageProviderThreadLocal;

@Getter
public class CreatePostPage extends ParentPage {
    public static String titleInput_XPATH = "//input[@id='post-title']";
    public static String bodyInput_XPATH = "//textarea[@id='post-body']";
    public static String saveButton_XPATH = "//button[contains(text(),'Save New Post')]";

    private HeaderForLoggedUser headerForLoggedUser;

    public CreatePostPage(Page page) {
        super(page);
        headerForLoggedUser = new HeaderForLoggedUser(page);
    }

    public CreatePostPage openPage() {
        getPageProviderThreadLocal().getLoginPage().openPage().loginWithValidCredentials();
        checkIsElementDisplayed(getPageProviderThreadLocal().getHomePage().getHeaderForLoggedUser().getAvatar_XPATH());
        click(headerForLoggedUser.getCreatePostButton_XPATH());
        checkIsElementDisplayed(titleInput_XPATH);
        return this;
    }
}
