package com.javier.welcomeui;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.schedulers.Schedulers;

/**
 * Created by User on 1/24/2017.
 */

public class RetrofitHelper {
    public static String BASE_URL = "https://api.github.com";

    public static class Factory {
        public static Retrofit create() {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(
                            RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }

        public static rx.Observable<List<ResultAPI>> create(String user) {
            Retrofit retrofit = create();
            GitHubService service = retrofit.create(GitHubService.class);
            return service.getRepos(user);
        }


    }

    public interface GitHubService {
        @GET("/users/{user}/repos")
        rx.Observable<List<ResultAPI>> getRepos(@Path("user") String user);
    }
}




