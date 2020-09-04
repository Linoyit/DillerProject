package com.example.android.diller10threunion;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GalleryActivity extends AppCompatActivity{

    private GridView gridView;
    private Adapter mMessageAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageReference;
    private FloatingActionButton mPhotoPickerButton;
    private static final int RC_PHOTO_PICKER =  2;
    private boolean isLongClickOnItem = false;
    private Integer onLongClick;
    private boolean isToDelete = false;
    private final int delete_item_id = 111;
    private List<Message> mMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getWindow().getDecorView().setTag(View.SYSTEM_UI_FLAG_FULLSCREEN);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();

        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("messages");
        mStorageReference = mFirebaseStorage.getReference().child("gallery_photos");
        gridView = findViewById(R.id.images_gridView);
        mMessages = new ArrayList<>();

        attachDatabaseListener();

        mMessageAdapter = new Adapter(this, R.layout.activity_item, mMessages);
        gridView.setAdapter(mMessageAdapter);

        /*click to view image on full screen and swipe images*/
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GalleryActivity.this,
                        FullScreenSwipe.class);

                Gallery main = mMessageAdapter.getMainGallery();
                intent.putStringArrayListExtra("imageUrls",
                        (ArrayList<String>) main.getPhotoUrls());
                main.addClickedUrl(i);
                intent.putExtra("imagePosition", main.getClickedUrl());
                startActivity(intent);
            }
        });

        /*Long click to delete existing image*/
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                isLongClickOnItem = true;
                onLongClick = i;
                invalidateOptionsMenu();
                return true;
            }
        });

        mPhotoPickerButton = findViewById(R.id.fab);
        /*Floating button to add new image to gallery*/
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "complete action using"),
                        RC_PHOTO_PICKER);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        attachDatabaseListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        detachDatabaseListener();
        mMessageAdapter.clear();
    }

    private void attachDatabaseListener(){
        if (mChildEventListener == null){
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot,
                                         @Nullable String previousChildName) {
                    Message message = snapshot.getValue(Message.class);
                    assert message != null;
                    message.setKey(snapshot.getKey());
                    mMessageAdapter.add(message);
                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot,
                                           @Nullable String previousChildName) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()){
                        Message message = postSnapshot.getValue(Message.class);
                        mMessages.add(message);
                        mMessageAdapter = new Adapter(GalleryActivity.this,
                                R.layout.activity_item, mMessages);
                        gridView.setAdapter(mMessageAdapter);
                    }
                }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    Message message = snapshot.getValue(Message.class);
                    mMessageAdapter.remove(message);
                }
                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot,
                                         @Nullable String previousChildName) {}
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(GalleryActivity.this, error.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            };
            mMessageDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void detachDatabaseListener(){
        if (mChildEventListener != null) {
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            StorageReference photoRef = mStorageReference
                    .child(Objects.requireNonNull(selectedImageUri.getLastPathSegment()));
            uploadFileToFirebase(photoRef, selectedImageUri);
        }
    }

    private void uploadFileToFirebase(final StorageReference storageReference, final Uri mImageUri){
        storageReference.putFile(mImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Message message = new Message(uri.toString());
                        mMessageDatabaseReference.push().setValue(message);
                        Toast.makeText(GalleryActivity.this,
                                "העלאה הושלמה", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),
                                "העלאה נכשלה. נסה שנית",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(GalleryActivity.this,
                        "לא נבחר קובץ, בבקשה נסה שוב",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (isLongClickOnItem) {
            if (menu.findItem(delete_item_id) == null) {
                MenuItem deleteItem = menu.add(Menu.NONE, delete_item_id, 5,
                        "Delete");
                deleteItem.setIcon(R.drawable.baseline_delete_outline_white_18dp);
                deleteItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
                deleteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        isToDelete = true;
                        final Message message = mMessageAdapter.getItem(onLongClick);
                        assert message != null;
                        final String selectedKey = message.getKey();
                        StorageReference photoRef = mFirebaseStorage
                                .getReferenceFromUrl(message.getPhotoUrl());
                        photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                mMessageDatabaseReference.child(selectedKey).removeValue();
                                Toast.makeText(GalleryActivity.this, "התמונה נמחקה",
                                        Toast.LENGTH_SHORT).show();
                                mMessageAdapter.remove(message);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(GalleryActivity.this, "התמונה לא נמצאה. נסה שנית",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                        isLongClickOnItem = false;
                        invalidateOptionsMenu();
                        return true;
                    }
                });
            } else {
                menu.removeItem(delete_item_id);
                isLongClickOnItem = false;
                invalidateOptionsMenu();
            }
        } else {
            menu.removeItem(delete_item_id);
        }
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
