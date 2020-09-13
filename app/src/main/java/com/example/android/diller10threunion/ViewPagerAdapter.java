package com.example.android.diller10threunion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.viewPagerViewHolder>{

    private Integer[] images;
    private Integer id;

    public ViewPagerAdapter(Integer[] images, Integer id){

        this.images = images;
        this.id = id;
    }

    @NonNull
    @Override
    public viewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_view_pager, parent,
                                false);
        return new viewPagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewPagerViewHolder holder, int position) {
        Integer currentImage = images[position];
        holder.morePics.setImageResource(currentImage);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class viewPagerViewHolder extends RecyclerView.ViewHolder {
        ImageView morePics;
        viewPagerViewHolder(View itemView){
            super(itemView);
            morePics = itemView.findViewById(R.id.iv_image);
            if (id == 1){
                morePics.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
        }
    }
}
