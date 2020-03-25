package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // b1: create request, class chứa tất cả request của use
        // có bnhiu request thì đưa hết vào 1 cái interface
        // mỗi lần muốn sử dụng tạo 1 cái retrofit
        // sau đó truyền đường dẫn tới server (end point)
    }
}
