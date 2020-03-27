package com.example.retrofit;

import com.example.Demo1;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    // trong interface này chứa tất cả các request
//  https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json




    @GET("demo1.json")
    Observable<Demo1> getDataDemo1();
    // ober sẽ format lại định dạng dl theo ý mình




// call rồi nhưng phải tạo 1 kiểu dữ liệu để hứng
    // 1 hứng về string
    // 2 hứng object format về thành 1 OOP để dễ gọi

}
