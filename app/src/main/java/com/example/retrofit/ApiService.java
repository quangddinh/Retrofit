package com.example.retrofit;

import com.example.Demo1;
import com.example.Demo4;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("demo1.json")
    Observable<Demo1> getDataDemo1();

    @GET("demo4.json")
    Observable<List<Demo4>> getDataDemo4();



}
