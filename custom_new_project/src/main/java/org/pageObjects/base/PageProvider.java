package org.pageObjects.base;

import com.microsoft.playwright.Page;
import org.pageObjects.pages.*;


public class PageProvider {
    static Page page;

    public PageProvider(Page page) {
        this.page = page;
    }

    public LoginPage getLoginPage() {
        return new LoginPage(page);
    }

    public HomePage getHomePage(){
        return new HomePage(page);
    }

    public CreatePostPage getCreatePostPage() {
        return new CreatePostPage(page);
    }

    //post page
    public PostPage getPostPage() {
        return new PostPage(page);
    }

    //my profile page
    public MyProfilePage getMyProfilePage() {
        return new MyProfilePage(page);
    }


}
