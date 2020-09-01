package com.example.android.diller10threunion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity{

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private static final int RC_SIGN_IN = 123;
    private static String mUserName = "hey";
    private static final String ANONYMOUS = "anonymous";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    onSignedInInitialize(user.getDisplayName());

                } else {
                    onSignedOutCleanup();
                    startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                            .setAvailableProviders(Arrays
                                    .asList(new AuthUI.IdpConfig.GoogleBuilder().build(),
                                    new AuthUI.IdpConfig.EmailBuilder().build())).build(),
                            RC_SIGN_IN);
                }
            }
        };

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(
                new Integer[] {R.drawable.beach, R.drawable.time, R.drawable.pic2});
        ViewPager2 viewPager2 = findViewById(R.id.viewPagerMain);
        viewPager2.setAdapter(viewPagerAdapter);
    }

    private void onSignedInInitialize(String user){
        mUserName = user;
    }
    private void onSignedOutCleanup(){
        mUserName = ANONYMOUS;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "You're signed in. Welcome",
                        Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.signout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.signOut) {
            AuthUI.getInstance().signOut(this);
            onSignedOutCleanup();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    public void onPlanClick(View view) {
        Intent intent = new Intent(MainActivity.this, PlanActivity.class);
        startActivity(intent);
    }

    public void onGameClick(View view) {
        Date date = new Date(System.currentTimeMillis());
        String dateToString = "" + date;
        String time = dateToString.split(" ")[3];
        if (dateToString.contains("Thu Sep 10")){
            String[] timeSplit = time.split(":");
            String hour = timeSplit[0];
            if (hour.equals("20") || Integer.parseInt(hour) > 20){
               openGameActivity();
            } else {
                Toast.makeText(this, "Not yet. wait a little longer",
                        Toast.LENGTH_LONG).show();
            }
        } else {
             openGameActivity();
        }
    }

    private void openGameActivity(){
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra(QuestionsListFactory.USER_NAME, mUserName);
        startActivity(intent);
    }

    public void onGalleryClick(View view) {
        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
        startActivity(intent);
    }
}