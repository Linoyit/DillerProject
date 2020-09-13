package com.example.android.diller10threunion;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ChatAdapter extends ArrayAdapter<Message> {

    private int userColor;
    private String mUserName;
    private Integer colorCounter = 0;
    private Integer[] colors = new Integer[] {R.color.purple, R.color.red,
            R.color.correctAnswerGreen, R.color.indigo, R.color.yellow,
            R.color.pink, R.color.orange};
    private HashMap<String, Integer> map;

    public ChatAdapter(@NonNull Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        mUserName = ChatActivity.getUserName();
        userColor = colors[colorCounter];
        map = new HashMap<>();
        map.put(mUserName, userColor);
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
        TextView mTimeSent = convertView.findViewById(R.id.tv_time_sent);
        Message message = getItem(position);

        mUserName.setText(message.getName());

        if (!map.containsKey(message.getName())){
            advanceCounter();
            int otherColor = colors[colorCounter];
            map.put(message.getName(), otherColor);
        }
        mUserName.setTextColor(map.get(message.getName()));

        mTimeSent.setText(message.getTime());
        mTextMessage.setText(message.getText());
        return convertView;
    }

    private void advanceCounter(){
        colorCounter = (colorCounter + 1) % colors.length;
    }
}
