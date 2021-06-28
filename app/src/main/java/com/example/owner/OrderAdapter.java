package com.example.owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {
    private ArrayList<Order_item> d_data = new ArrayList<Order_item>();

    public OrderAdapter() {
    }

    @Override
    public int getCount() {
        return d_data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_item, parent, false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.detail_resName);
        TextView descTextView = (TextView) convertView.findViewById(R.id.detail_btn);

        Order_item listViewItem = d_data.get(position);

        titleTextView.setText(d_data.get(position).details_resName);
        descTextView.setText(d_data.get(position).details_btn);

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return d_data.get(position);
    }

    public void addItem(String title, String desc) {
        Order_item item = new Order_item();

        item.setDetails_resName(title);
        item.setDetails_btn(desc);

        d_data.add(item);
    }
}
