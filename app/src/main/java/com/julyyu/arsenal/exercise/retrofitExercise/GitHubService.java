package com.julyyu.arsenal.exercise.retrofitExercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by julyyu on 2018/2/6.
 */

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<String>> listRepos(@Path("user") String user);
}
