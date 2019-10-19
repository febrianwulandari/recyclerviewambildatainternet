package com.iky.myapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SportAdapter adapter;
    private ArrayList<Sport> sports;
    private RecyclerView rv_sports;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new Gson();
        rv_sports = findViewById(R.id.rv_sport);
        adapter = new SportAdapter(this);
        sports = new ArrayList<>();
        ambilData();

        LinearLayoutManager ln = new LinearLayoutManager
                (this,LinearLayoutManager.HORIZONTAL,false);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this,1);

        StaggeredGridLayoutManager lm3  = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        DividerItemDecoration divider =
                new DividerItemDecoration(this, ln.getOrientation());
        rv_sports.setLayoutManager(gridLayoutManager);
        rv_sports.setAdapter(adapter);
        rv_sports.addItemDecoration(divider);

        adapter.setListener(new OnClickListener() {
            @Override
            public void aksiKlik(int position) {
                //cara untuk berpindah halaman msinactivity.this (this karena sedang berjalan dan , untuk tempat tujuan
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                startActivity(intent);

            }
        });
    }

    public void ambilData(){
        //meminta request dengan volley dan jika request berhasil tampilkan kedalam recyclerview dengan adapter
        RequestQueue queue = Volley.newRequestQueue(this);
        //url ambil dari url json
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.thesportsdb.com/api/v1/json/1/searchplayers.php?t=Arsenal",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //ambil data dulu dari response -> json ->arraylist
                        SportResult result = gson.fromJson(response,SportResult.class);
                        sports = result.getSports();
                        //tampilkan data ke adapter

                        adapter.setSports(sports);
                    }
                },
                    new Response.ErrorListener(){
                        @Override
                                public void onErrorResponse(VolleyError error){

                        }
                    }
                );
        queue.add(stringRequest);
    }
}
