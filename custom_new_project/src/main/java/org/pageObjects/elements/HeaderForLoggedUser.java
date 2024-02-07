package org.pageObjects.elements;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.pageObjects.base.CommonActions;

@Getter
public class HeaderForLoggedUser extends CommonActions {
    private String avatar_XPATH = "body > header > div > div > form > button";
    private String createPostButton_XPATH = "//header//a[contains(text(),'Create Post')]";
    private String myProfileButton_XPATH = "//img[@alt='My profile']";


    public HeaderForLoggedUser(Page page) {
        super(page);
    }
}
