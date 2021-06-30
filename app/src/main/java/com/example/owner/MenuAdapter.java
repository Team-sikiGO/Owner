package com.example.owner;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {
    private ArrayList<MenuItem> food_Data = new ArrayList<MenuItem>();

    public MenuAdapter() {
    }

    @Override
    public int getCount() {
        return food_Data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.edit_menu_item, parent, false);
        }

        ImageView foodImageView = (ImageView) convertView.findViewById(R.id.imagefood);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.textName);
        TextView priceTextView = (TextView) convertView.findViewById(R.id.textPrice);

        MenuItem menu_item = food_Data.get(position);

        foodImageView.setImageDrawable(food_Data.get(position).food_image);
        nameTextView.setText(food_Data.get(position).food_name);
        priceTextView.setText(food_Data.get(position).price);

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return food_Data.get(position);
    }

    public void addItem(Drawable image, String name, String price) {
        MenuItem item = new MenuItem();

        item.setFood_image(image);
        item.setFood_name(name);
        item.setPrice(price);

        food_Data.add(item);
    }
}
