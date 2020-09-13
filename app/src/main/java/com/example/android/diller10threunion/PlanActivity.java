package com.example.android.diller10threunion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlanActivity extends AppCompatActivity {

    TextView volunteer;
    TextView volunteerPlace;
    TextView volunteerTime;
    TextView celebrate;
    TextView celebrateTime;
    TextView celebratePlace;
    Button directionVolunteer;
    Button directionCelebrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        initViews();
        directionVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMaps("geo:31.672660,34.559202");
            }
        });

        directionCelebrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMaps("geo:31.664009,34.579094");
            }
        });
    }

    private void initViews() {
        volunteer = findViewById(R.id.tv_volunteer);
        volunteerPlace = findViewById(R.id.tv_volunteer_place);
        volunteerTime = findViewById(R.id.tv_volunteer_time);
        celebrate = findViewById(R.id.tv_celebrate);
        celebratePlace = findViewById(R.id.tv_celebrate_place);
        celebrateTime = findViewById(R.id.tv_celebrate_time);
        directionVolunteer = findViewById(R.id.directionLink);
        directionCelebrate = findViewById(R.id.directionLink2);
    }

    private void openMaps(String geo){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(geo));
        Intent chooser = Intent.createChooser(intent,"Launch Maps");
        startActivity(chooser);
    }
}
