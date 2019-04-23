package com.example.nh.webservice_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Items_Adapter extends ArrayAdapter<Items> {

    public Items_Adapter( Context context,  List<Items> objects) {
        super(context, 0, objects);


    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        convertView =LayoutInflater.from(getContext()).inflate(R.layout.custom_row,parent,false);
        TextView textView=convertView.findViewById(R.id.txt1);
        TextView textView1=convertView.findViewById(R.id.txt2);
        ImageView imageView=convertView.findViewById(R.id.image1);


        Items items=getItem(position);
        textView.setText(items.getName());
        textView1.setText(""+items.getLikes());
        Picasso.with(getContext()).load(items.getImage()).into(imageView);







        return convertView;
    }
}
