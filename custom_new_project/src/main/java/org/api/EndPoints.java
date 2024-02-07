package org.api;

public interface EndPoints {
    String BASE_URL = "https://aqa-complexapp.onrender.com";
    String POSTS_BY_USER = "/api/postsByAuthor/%s";
    String LOGIN =  "/api/login";
    String CREATE_POST = "/api/create-post";
    String DELETE_POST = "/api/post/{0}";
}
