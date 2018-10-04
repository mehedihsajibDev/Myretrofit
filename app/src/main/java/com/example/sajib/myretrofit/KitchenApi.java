package com.example.sajib.myretrofit;






import com.example.sajib.myretrofit.Model.KitchenList;
import com.example.sajib.myretrofit.Model.Modeltwo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KitchenApi {
    @GET("api/web/get-req-data/kitchen-filter")
    public Call<List<Modeltwo>> getKitchens();
}
