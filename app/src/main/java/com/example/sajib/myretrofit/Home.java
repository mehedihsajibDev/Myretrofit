package com.example.sajib.myretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.sajib.myretrofit.Model.KitchenList;
import com.example.sajib.myretrofit.Model.Modeltwo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {
    
    private RecyclerView recyclerView;
    private KitchenListAdapter adapter;
    private ArrayList<Modeltwo> kitchenLists;
    SearchView searchView;

    String baseUrl="http://apptitive.com/projects/web/foodpeon_api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupRecyclerView();


        loadData();

        openSearchview();
    }


    private void loadData() {
        Gson gson = new GsonBuilder().setLenient().create();
        Log.d("" + gson, "JSON");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Log.i("JSON", "yahooo  " + gson);

        KitchenApi retroFIT = retrofit.create(KitchenApi.class);

        Call<List<Modeltwo>> call = retroFIT.getKitchens();

        call.enqueue(new Callback<List<Modeltwo>>() {
            @Override
            public void onResponse(final Call<List<Modeltwo>> call, final Response<List<Modeltwo>> response) {
                Log.i("JSON", "yahoooaa  " + response);
                try{
                    kitchenLists = new ArrayList<>(response.body());
                    if (!kitchenLists.isEmpty()) {
                        adapter = new KitchenListAdapter(kitchenLists, getApplicationContext());
                        recyclerView.setAdapter(adapter);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(Home.this, "Not Found", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<List<Modeltwo>> call, Throwable t) {
                Log.i("JSON", "yahooobb  " + call);

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG)
                        .show();

            }
        });
    }

    public void openSearchview() {
        searchView = (SearchView) findViewById(R.id.searchEditText);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (adapter != null) adapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null) adapter.getFilter().filter(newText);
                return true;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //loadData();
                return true;
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
