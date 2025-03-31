package com.example.sliderimages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sliderimages.Model.Images;

import java.util.List;

public class ImagesViewPaper2Adapter extends RecyclerView.Adapter<ImagesViewPaper2Adapter.ImagesViewHolder> {
    private List<Images> imagesList;

    public ImagesViewPaper2Adapter(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_images, parent, false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        Images images = imagesList.get(position);
        holder.imageView.setImageResource(images.getImageId());
    }

    @Override
    public int getItemCount() {
        if (imagesList != null) {
            return imagesList.size();
        }
        return 0;
    }

   public class ImagesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
        }
    }
}
