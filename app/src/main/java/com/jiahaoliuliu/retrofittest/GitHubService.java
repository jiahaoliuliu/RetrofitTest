package com.jiahaoliuliu.retrofittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jiahao on 10/08/16.
 */
public interface GitHubService {
    @GET("users/{user}")
    Call<GitModel> listRepos(@Path("user") String user);

}
