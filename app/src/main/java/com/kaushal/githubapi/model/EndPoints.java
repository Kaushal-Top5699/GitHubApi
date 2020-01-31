package com.kaushal.githubapi.model;

import com.kaushal.githubapi.viewmodel.GitHubRepo;
import com.kaushal.githubapi.viewmodel.GitHubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndPoints {

    @GET("/users/{user}")
    //Our getter setter class in viewModel
    Call<GitHubUser> getUser(@Path("user") String user);
                    //getUser() method will called in UserActivity

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getRepo(@Path("user") String name);
}
