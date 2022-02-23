package com.hameeda.wallpaperapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.VH> {
    private Context context;
    private ArrayList<ImagesModel> imagesModelArrayList;

    public ImagesAdapter(Context context, ArrayList<ImagesModel> imagesModelArrayList) {
        this.context = context;
        this.imagesModelArrayList = imagesModelArrayList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wallpaper, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ImagesModel imagesModel = imagesModelArrayList.get(position);
        Glide.with(context)
                .load(imagesModel.previewURL)
                .placeholder(R.drawable.wallpaper_placeholder)
                .into(holder.wallpaperImage);
        holder.tvTags.setText(imagesModel.getTags());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,FullScreenImageActivity.class);
                intent.putExtra("image",imagesModel.largeImageURL);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagesModelArrayList.size();
    }

    protected class VH extends RecyclerView.ViewHolder {
        ImageView wallpaperImage;
        TextView tvTags;

        public VH(@NonNull View itemView) {
            super(itemView);
            wallpaperImage = itemView.findViewById(R.id.imgWallpaper);
            tvTags = itemView.findViewById(R.id.tvWallName);
        }
    }
}
