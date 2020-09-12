package com.golive.fms.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NotAdapter extends ArrayAdapter<NotModel> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<NotModel> notModelArray;

    public NotAdapter(@NonNull Context context, int resource, @NonNull ArrayList<NotModel> objects) {
        super(context, resource,objects);

        this.context = context;
        this.notModelArray = objects;
        layoutInflater = LayoutInflater.from(context);
    }

    @Nullable
    @Override
    public NotModel getItem(int position) {
        return notModelArray.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //super.getView(position, convertView, parent);

        convertView = layoutInflater.inflate(R.layout.list_view_item, null);

        TextView not = convertView.findViewById(R.id.textView_note);
        TextView date = convertView.findViewById(R.id.textView_date);
        ImageView image = convertView.findViewById(R.id.image_not);

        NotModel notModel = notModelArray.get(position);

        String notString  = notModel.getNot();
        String dateString = notModel.getDate();
        int imageRes      = notModel.getImage();

        image.setImageResource(imageRes);
        not.setText(notString);
        date.setText(dateString);


        return convertView;
    }
}
