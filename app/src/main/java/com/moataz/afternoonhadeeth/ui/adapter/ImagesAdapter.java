package com.moataz.afternoonhadeeth.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.image.Images;
import com.moataz.afternoonhadeeth.databinding.ListImagesBinding;
import com.moataz.afternoonhadeeth.ui.view.activity.DisplayImageActivity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Images> items = null;

    @SuppressLint("NotifyDataSetChanged")
    public void setImagesList(List<Images> items) {
        this.items = items;
        Collections.shuffle(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImagesViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.list_images,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Images images = items.get(position);
        ((ImagesViewHolder) holder).listImagesBinding.setImagesModel(images);
        ((ImagesViewHolder) holder).setOnClick(images);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }

    static class ImagesViewHolder extends RecyclerView.ViewHolder {
        ListImagesBinding listImagesBinding;

        ImagesViewHolder(@NonNull ListImagesBinding itemView) {
            super(itemView.getRoot());
            listImagesBinding = itemView;
        }

        void setOnClick(Images images) {
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DisplayImageActivity.class);
                intent.putExtra("imageUrl", images.getImageUrl());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
