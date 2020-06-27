package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BoardAdapter extends BaseAdapter {

    private ArrayList<BoardItem> boardItems = new ArrayList<BoardItem>();

    @Override
    public int getCount() {
        return boardItems.size();
    }

    @Override
    public Object getItem(int position) {
        return boardItems.get(position);
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
            convertView = inflater.inflate(R.layout.board_item, parent, false);
        }

        BoardItem item = boardItems.get(position);

        TextView title_tv =convertView.findViewById(R.id.board_title);
        ImageView image = convertView.findViewById(R.id.board_image);
        TextView body_tv = convertView.findViewById(R.id.board_body);
        Button rpl_btn = convertView.findViewById(R.id.board_rpl_btn);

        title_tv.setText(item.getTitle());
        image.setImageResource(R.drawable.one);
        body_tv.setText(item.getBody());

        rpl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ASDASDASD");
            }
        });

        return convertView;
    }

    public void addItem(String title, String imageSrc, String body) {
        BoardItem item = new BoardItem();

        item.setTitle(title);
        item.setImageSrc(imageSrc);
        item.setBody(body);

        boardItems.add(item);
    }
}
