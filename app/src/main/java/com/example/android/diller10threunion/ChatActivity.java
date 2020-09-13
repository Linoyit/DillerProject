package com.example.android.diller10threunion;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ChatActivity extends AppCompatActivity {

    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListener;
    private ChatAdapter mChatAdapter;
    private ListView mChatListView;
    private EditText mChatEditText;
    private TextView mSendButton;
    private static String mUserName;
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    private Integer shownChatMessages = 20;
    private Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initViews();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("chat");
        query = mMessageDatabaseReference.orderByKey().limitToLast(shownChatMessages);

        List<Message> messages = new ArrayList<>();
        mChatAdapter = new ChatAdapter(this, R.layout.activity_chat_item, messages);
        mChatListView.setAdapter(mChatAdapter);
        mUserName = getIntent().getStringExtra("user_name");

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot,
                                     @Nullable String previousChildName) {
                Message message = snapshot.getValue(Message.class);
                mChatAdapter.add(message);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot,
                                       @Nullable String previousChildName) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot,
                                     @Nullable String previousChildName) {}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        query.addChildEventListener(mChildEventListener);
        chatEditTextListener();
        sendButtonListener();
    }

    private void sendButtonListener() {
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message(mUserName, mChatEditText.getText().toString());
                message.setTime(getTime());
                mMessageDatabaseReference.push().setValue(message);
                mChatEditText.setText("");
            }
        });
    }

    private String getTime(){
        String date = Calendar.getInstance().getTime().toString();
        String time = date.split(" ")[3];
        String[] s = time.split(":");
        return s[0] + ":" + s[1];
    }

    private void chatEditTextListener() {
        mChatEditText.setFilters(new InputFilter[]{new InputFilter
                .LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mChatEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0){
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void initViews(){
        mChatListView = findViewById(R.id.listView_chat_activity);
        mChatEditText = findViewById(R.id.messageEditText);
        mSendButton = findViewById(R.id.sendButton);
    }

    public void onPreviousTextClick(View view) {
        shownChatMessages = shownChatMessages + 5;
        query.removeEventListener(mChildEventListener);
        mChatAdapter.clear();
        query = mMessageDatabaseReference.orderByKey().limitToLast(shownChatMessages);
        query.addChildEventListener(mChildEventListener);
    }
    public static String getUserName(){return mUserName;}
}
