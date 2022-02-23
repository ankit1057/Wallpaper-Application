package com.hameeda.wallpaperapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;

public class FullScreenImageActivity extends AppCompatActivity {
    private ZoomageView imageWallpaper;
    private String imageURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        imageURL=getIntent().getStringExtra("image");
        imageWallpaper=findViewById(R.id.imgWallpaper);
        Glide.with(this)
                .load(imageURL)
                .placeholder(R.drawable.wallpaper_placeholder)
                .into(imageWallpaper);

    }
}