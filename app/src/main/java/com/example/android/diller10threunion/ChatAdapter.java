package com.example.android.diller10threunion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Random;

public class ChatAdapter extends ArrayAdapter<Message> {

    private int userColor;

    public ChatAdapter(@NonNull Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        Integer[] colors = new Integer[] {R.color.red, R.color.brown,
                R.color.indigo, R.color.yellow, R.color.pink,R.color.purple, R.color.orange};
        userColor = colors[new Random().nextInt()];
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.
                    activity_chat_item, parent, false);
        }
        TextView mUserName = convertView.findViewById(R.id.tv_user_name);
        TextView mTextMessage = convertView.findViewById(R.id.tv_chat_text);
        Message message = getItem(position);

        mUserName.setText(message.getName());
        mUserName.setTextColor(userColor);
        mTextMessage.setText(message.getText());

        return convertView;
    }
}
