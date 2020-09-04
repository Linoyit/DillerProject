package com.example.android.diller10threunion;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<Message> {

    private Gallery mainGallery = new Gallery();
    private List<Message> messages;

    public Adapter(@NonNull Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        this.messages = objects;
        for (Message message : messages) {
            mainGallery.addUrl(message.getPhotoUrl());
        }
    }

    public Gallery getMainGallery() {
        return mainGallery;
    }

    @Override
    public void add(@Nullable Message object) {
        super.add(object);
        assert object != null;
        if (!mainGallery.getPhotoUrls().contains(object.getPhotoUrl())) {
            mainGallery.addUrl(object.getPhotoUrl());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.
                    activity_item, parent, false);
        }

        ImageView photoImageView = convertView.findViewById(R.id.photoImageView);
        Message message = messages.get(position);
        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext())
                    .load(message.getPhotoUrl())
                    .into(photoImageView);
        }
        else {
            photoImageView.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public void remove(@Nullable Message object) {
        super.remove(object);
        mainGallery.removeUrl(object.getPhotoUrl());
        notifyDataSetChanged();
    }
}
