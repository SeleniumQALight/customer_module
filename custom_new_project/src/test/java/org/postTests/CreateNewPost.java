package org.postTests;

import org.baseTest.BaseTestUI;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.pageObjects.pages.CreatePostPage;
import org.pageObjects.pages.MyProfilePage;
import org.utilities.TimeUtil;

public class CreateNewPost extends BaseTestUI {
    private final String title = "TC06 - New Post Taras " + TimeUtil.getDateAndTimeFormatted();
    @Test
    @Ignore
    public void TR6_createNewPost(){
        getPageProviderThreadLocal().getCreatePostPage()
                .openPage()
                .enterText(CreatePostPage.titleInput_XPATH, title)
                .enterText(CreatePostPage.bodyInput_XPATH, "Body of new Post Taras")
                .click(CreatePostPage.saveButton_XPATH)

                .checkIsElementDisplayed(pageProvider.getPostPage().getSuccessMessage_XPATH())
                .click(pageProvider.getPostPage().getHeaderForLoggedUser().getMyProfileButton_XPATH())

                .checkIsElementDisplayed(MyProfilePage.tabPosts_XPATH)
                .checkNumberOfElements(1, MyProfilePage.getPostsList(title))
        ;
    }

    @After
    public void deletePosts(){
        log.info("After: Delete posts");
        getPageProviderThreadLocal().getMyProfilePage()
                .openPage()
                .deletePostsTillPresent(title)

        ;
    }
}
