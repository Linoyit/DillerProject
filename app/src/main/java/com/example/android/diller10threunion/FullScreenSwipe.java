package com.example.android.diller10threunion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.List;

public class FullScreenSwipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);

        Intent intent = getIntent();
        List<String> photoUrls = intent.getStringArrayListExtra("imageUrls");
        Integer currentImagePosition = intent.getIntExtra("imagePosition", 0);

        ViewPager viewPager = findViewById(R.id.viewPagerFullScreen);
        assert photoUrls != null;
        FullImageAdapter adapter = new FullImageAdapter(this,
                photoUrls.toArray(new String[0]),
                currentImagePosition);
        viewPager.setAdapter(adapter);
    }

    public static class FullImageAdapter extends PagerAdapter {

        private Context mContext;
        private String[] photoUrls;
        private Integer count;

        FullImageAdapter(Context context, String[] urls, Integer clickedPhoto) {
            mContext = context;
            photoUrls = urls;
            count = clickedPhoto;
        }

        @Override
        public int getCount() {
            return photoUrls.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView =  new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            int index = (position+count) % getCount();
            Glide.with(imageView.getContext())
                    .load(photoUrls[index])
                    .into(imageView);
            container.addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ImageView) object);
        }
    }
}
