package com.edy.katzir.ebayfashion;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

public class SwipeListAdapter extends ArrayAdapter {
    List data;
    Context context;
    int layoutResID;

    public SwipeListAdapter(Context context, int layoutResourceId, List data) {
        super(context, layoutResourceId, data);

        this.data = data;
        this.context = context;
        this.layoutResID = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SwipeListHolder holder;
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResID, parent, false);

            holder = new SwipeListHolder();

            // Set the Holder's Views
            holder.image = (ImageView) row.findViewById(R.id.img);
            holder.title = (TextView) row.findViewById(R.id.txt);

            row.setTag(holder);
        }
        else {
            holder = (SwipeListHolder) row.getTag();
        }

        final SwipeListRow itemData = (SwipeListRow) data.get(position);
        holder.title.setText(itemData.getName());
        if (itemData.getImageBitmap() == null) {
            holder.image.setImageResource(R.mipmap.ic_launcher);
        }
        else {
            holder.image.setImageBitmap(itemData.getImageBitmap());
        }
        // Image Clicked
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return row;

    }

    static class SwipeListHolder {
        ImageView image;

        TextView title;
    }
}
