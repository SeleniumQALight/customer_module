package org.apiTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.ApiHelper;
import org.api.ApiHelperForComplexApp;
import org.api.EndPoints;
import org.api.dto.responseDto.PostDto;
import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class FirstApiTest extends BaseTest {
    protected final Logger log = LogManager.getLogger(this.getClass());

    ApiHelper apiHelper = new ApiHelper();
    ApiHelperForComplexApp apiHelperForComplexApp = new ApiHelperForComplexApp();

    @Test
//    @Ignore
    public void TR4firstApiTest() {

        Map<String, String> data = new HashMap<>();
        data.put("username", TestData.LOGIN_DEFAULT_API);
        data.put("password", TestData.PASSWORD_DEFAULT_API);

        APIResponse apiResponse = apiHelper.sendPOST(EndPoints.LOGIN, data);

        Assert.assertTrue(apiResponse.ok());


    }


    @Test
    @Ignore(value = "now is ignore")
    public void TR5_getAllPostsByUser() {
        APIResponse apiResponse = apiHelperForComplexApp.getAllPostsByUser();
        Assert.assertTrue(apiResponse.ok());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PostDto[]  myObject = objectMapper.readValue(apiResponse.text(), PostDto[].class);
            // apiResponse.jsonBody() - returns json body as a Map
            System.out.println(myObject[0].getTitle());
            System.out.println(myObject[0].getAuthor().getUsername());
        }   catch (Exception e) {
                e.printStackTrace();
            }
    }


}
