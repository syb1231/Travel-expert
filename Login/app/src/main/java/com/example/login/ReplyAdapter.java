package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReplyAdapter extends BaseAdapter {

    private ArrayList<ReplyItem> replyItems = new ArrayList<ReplyItem>();

    @Override
    public int getCount() {
        return replyItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reply_item, parent, false);
        }

        TextView reply_author = convertView.findViewById(R.id.reply_author);
        TextView reply_body = convertView.findViewById(R.id.reply_body);

        ReplyItem item = replyItems.get(position);

        reply_author.setText(item.getAuthor());
        reply_body.setText(item.getBody());

        return convertView;
    }

    public void addItem(String author, String body){
        ReplyItem item = new ReplyItem();

        item.setAuthor(author);
        item.setBody(body);

        replyItems.add(item);
    }
}
