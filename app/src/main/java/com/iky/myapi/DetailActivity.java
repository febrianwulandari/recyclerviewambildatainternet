package com.iky.myapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    TextView judul,nama,nationality,brithplace,birthdate,description;
    ImageView gambar;
    private String id_player;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        judul = findViewById(R.id.judul);
        gson = new Gson();
        nama = findViewById(R.id.nama);
        nationality = findViewById(R.id.nationality);
        birthdate = findViewById(R.id.TglLahir);
        brithplace = findViewById(R.id.tmptLahir);
        description = findViewById(R.id.description);
        gambar = findViewById(R.id.gambardeatil);

        id_player = getIntent().getStringExtra("idPlayer");
        //karena data berubah ubah maka id player di masukkan dengan penghubung tanda tambah
        String url = "https://www.thesportsdb.com/api/v1/json/1/lookupplayer.php?id="+id_player;

        //ambil data dengan volley dan juga gson
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //ambil nilai dan set ke komponen
                        SportResult result = gson.fromJson(response,SportResult.class);
                        Sport sport = result.getSports().get(0);
                        nama.setText(sport.getName());
                        nationality.setText(sport.getNationality());
                        birthdate.setText(sport.getBirthDate());
                        brithplace.setText(sport.getBirthPlace());
                        description.setText(sport.getDescription());
                        Picasso.get().load(sport.getImagePath()).into(gambar);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(stringRequest);
    }
}
