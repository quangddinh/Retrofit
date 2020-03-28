package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.Demo1;
import com.example.Demo4;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create request, class chứa tất cả request của use, gửi request lên server
        // tạo 1 luồng bên ngoài
        // có bnhiu request thì đưa hết vào 1 cái interface
        // mỗi lần muốn sử dụng tạo 1 cái retrofit
        // sau đó truyền đường dẫn tới server (end point)

        // 1. setup các extension hỗ trợ- retrofit, rx, gson
        // host https:khoapham.vn
        // 1. khởi tạo retrofit


        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(300,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://khoapham.vn/KhoaPhamTraining/json/tien/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();



        // 2. khởi tạo interface chứa các request
        // https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json
        // demo1.json là end point, đường dẫn cuối
        // khởi tạo interface xong rồi giờ sẽ gẵn vào
        //3.

        ApiService apiService = retrofit.create(ApiService.class);

        //4. Gọi request, request trong api server giờ ta call api service về
        // gọi getData và kiểu dl trả về là Ober

        apiService.getDataDemo1()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Demo1>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Demo1 demo1) {
                        Log.d("BBB",demo1.getMonhoc());
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });
        apiService.getDataDemo4()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Demo4>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(List<Demo4> demo4s) {
                        Log.d("AAA" , demo4s.get(1).getHocphi());
                        Log.d("AAA" , demo4s.get(2).getKhoahoc());
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
