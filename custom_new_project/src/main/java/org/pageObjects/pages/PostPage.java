package org.pageObjects.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.pageObjects.base.ParentPage;
import org.pageObjects.elements.HeaderForLoggedUser;

@Getter
public class PostPage extends ParentPage {
    final String successMessage_XPATH = "//div[contains(text(),'New post successfully created.')]";
    public final static String deleteButton_XPATH = "//*[@data-original-title='Delete']";

    HeaderForLoggedUser headerForLoggedUser;

    public PostPage(Page page) {
        super(page);
        headerForLoggedUser = new HeaderForLoggedUser(page);
    }

}
