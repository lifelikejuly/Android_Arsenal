package com.julyyu.arsenal.exercise.retrofitExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.julyyu.uilibrary.fragment.BaseFragment;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by julyyu on 2018/2/6.
 */

public class RetrofitFragment extends BaseFragment{
    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Retrofit.Builder builder = new Retrofit.Builder();
//        Retrofit retrofit = new Retrofit.Builder()
        Retrofit retrofit = builder.baseUrl("https://api.github.com/")
//                .addCallAdapterFactory()
//                .addConverterFactory()
//                .callbackExecutor()
//                .callFactory()
//                .client()
//                .validateEagerly()
                .build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);
        gitHubService.listRepos("")
                .enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {

                    }
                });

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("https://api.github.com/")
                .build();

        // 异步获取
        okHttpClient.newCall(request)
                .enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                    }
                });
        // 同步获取
        try {
            okhttp3.Response response = okHttpClient.newCall(request)
                    .execute();
            response.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
