package com.hameeda.wallpaperapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView wallpaperRecycler;
    private String API_KEY = "25851508-9c5d7a3ec08bee9bed54e5b5f";
    private String baseURL = "https://pixabay.com/api/?key=";
    private ArrayList<ImagesModel> imagesModelArrayList;
    private ImagesAdapter imagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wallpaperRecycler = findViewById(R.id.recyclerWallpaper);
        imagesModelArrayList=new ArrayList<>();
        imagesAdapter=new ImagesAdapter(MainActivity.this,imagesModelArrayList);
        wallpaperRecycler.setAdapter(imagesAdapter);
        getWallpapers();
    }

    private void getWallpapers() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                baseURL + API_KEY,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try {
                            // Loop through the array elements
                            for (int i = 0; i < response.getJSONArray("hits").length(); i++) {
                                // Get current json object
                                JSONObject jsonObject=response.getJSONArray("hits").getJSONObject(i);
                                ImagesModel imagesModel=new ImagesModel();
                                imagesModel.setId(jsonObject.getInt("id"));
                                imagesModel.setPreviewURL(jsonObject.getString("previewURL"));
                                imagesModel.setLargeImageURL(jsonObject.getString("largeImageURL"));
                                imagesModel.setTags(jsonObject.getString("tags"));
                                imagesModelArrayList.add(imagesModel);


                            }
                            imagesAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred

                    }
                }
        );

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
    }
}