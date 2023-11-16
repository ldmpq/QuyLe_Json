package com.example.datajson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Quote> {

    public MyAdapter(Context context, List<Quote> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Quote item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        TextView contentTextView = convertView.findViewById(R.id.textContent);
        TextView authorTextView = convertView.findViewById(R.id.textAuthor);

        contentTextView.setText(item.getContent());
        authorTextView.setText(item.getAuthor());

        return convertView;
    }
}

