package org.pageObjects.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.pageObjects.base.ParentPage;
import org.pageObjects.elements.HeaderForLoggedUser;


@Getter
public class HomePage extends ParentPage {
    private HeaderForLoggedUser headerForLoggedUser;

    public HomePage(Page page) {
        super(page);
        headerForLoggedUser = new HeaderForLoggedUser(page);
    }
}
