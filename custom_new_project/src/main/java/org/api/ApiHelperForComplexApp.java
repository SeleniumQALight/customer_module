package org.api;

import com.microsoft.playwright.APIResponse;
import org.data.TestData;
import org.junit.Assert;
import org.propertiesInterfaces.ConfigProvider;

import java.util.HashMap;
import java.util.Map;

public class ApiHelperForComplexApp {
    ApiHelper apiHelper = new ApiHelper();

    public String getToken(){
      return getToken(ConfigProvider.configHiddenProperties.login(), ConfigProvider.configHiddenProperties.password());
    }

    public String getToken(String userName, String pass){

        Map<String, String> data = new HashMap<>();
        data.put("username", TestData.LOGIN_DEFAULT_API);
        data.put("password", TestData.PASSWORD_DEFAULT_API);

        APIResponse apiResponse = apiHelper.sendPOST(EndPoints.LOGIN, data);

        Assert.assertTrue(apiResponse.ok());
        return apiResponse.text().replace("\"","");
    }

    public APIResponse getAllPostsByUser(){
        return getAllPostsByUser(TestData.LOGIN_DEFAULT_API);
    }

    public APIResponse getAllPostsByUser(String userName){

        APIResponse apiResponse = apiHelper.sendGET(String.format(EndPoints.POSTS_BY_USER, userName));

        Assert.assertTrue(apiResponse.ok());
        return apiResponse;
    }

}
