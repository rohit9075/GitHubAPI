package com.rohit.githubapi.rest;

import com.rohit.githubapi.model.GitHubUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubUserEndPoints {

//    @GET("/users/{user}")
//    Call<GitHubUser> getUser(@Path("user") String user);

    @GET("/users/{user}")
    Call<GitHubUser> getUser(@Path("user")  String user);
}



