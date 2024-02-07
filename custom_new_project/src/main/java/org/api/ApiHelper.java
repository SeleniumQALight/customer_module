package org.api;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.baseTest.BaseTestUI;

import java.util.HashMap;
import java.util.Map;

public class ApiHelper {

    protected final Logger log = LogManager.getLogger(this.getClass());

    private APIRequestContext request;


    private Map<String, String> headers ;

    public ApiHelper(){
        headers = new HashMap<>();
    }

    public Map<String, String> setHeader(String key, String value){
        headers.put(key, value);
        return headers;
    }

    private Map<String, String> setHeadersApplicationJson(){
        headers.put("Accept", "application/json");
        return headers;
    }

    public APIRequestContext getRequestWithDefaultHeaders(){
        return getRequestWithHeaders(setHeadersApplicationJson());
    }

    public APIRequestContext getRequestWithHeaders(Map<String, String> headers){
        final String BASE_URL = EndPoints.BASE_URL;
        System.out.println();
        log.info("REQUEST:");
        log.info("BASE_URL: " + BASE_URL);
        log.info("Headers: " );
        headers.forEach((key, value) -> log.info(key + " : " + value));

        return BaseTestUI.getPw().request().newContext(new APIRequest.NewContextOptions()
                // All requests we send go to this API endpoint.
                .setBaseURL(BASE_URL)
                .setExtraHTTPHeaders(headers));

    }

    /**
     * Send Post request with default headers
     * @param url
     * @param data
     * @return
     */
    public APIResponse sendPOST(String url, Map<String, String> data) {
        return sendPOST(url, data, getRequestWithDefaultHeaders());
    }

    public APIResponse sendPOST(String url, Map<String, String> data, APIRequestContext request){
        log.info("POST: " + url);
        log.info("BODY DATA: " );
        data.forEach((key, value) -> log.info(key + " : " + value));
        APIResponse apiResponse = request.post(url,
            RequestOptions.create().setData(data));
        System.out.println();
        log.info("RESPONSE:");
        log.info("Status: " + apiResponse.status());
        log.info("Text: " + apiResponse.text());
        return apiResponse;
    }

    public APIResponse sendGET(String url){
        return sendGET(url, getRequestWithDefaultHeaders());
    }

    public APIResponse sendGET(String url, APIRequestContext request ){
        log.info("GET: " + url);

        APIResponse apiResponse = request.get(url);
        System.out.println();
        log.info("RESPONSE:");
        log.info("Status: " + apiResponse.status());
        log.info("Text: " + apiResponse.text());
        return apiResponse;
    }



}
