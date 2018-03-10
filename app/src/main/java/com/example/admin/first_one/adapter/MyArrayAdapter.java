package com.example.admin.first_one.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.first_one.R;
import com.example.admin.first_one.model.MyModel;

import java.util.List;

/**
 * Created by admin on 2018/3/10.
 */

public class MyArrayAdapter extends ArrayAdapter<MyModel> {
    List<MyModel> modellist;
    Context context;
    private LayoutInflater mInflater;

    public MyArrayAdapter(Context context, List<MyModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modellist = objects;
    }

    @Override
    public MyModel getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        MyModel item = getItem(position);
        vh.textViewName.setText(item.getName());
        vh.textViewEmail.setText(item.getEmail());

        Glide.with(context).load(item.getImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap
                .ic_launcher).into(vh.imageView);

        return vh.rootView;
    }

    public static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewName;
        public final TextView textViewEmail;

        public ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewName, TextView textViewEmail) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewName = textViewName;
            this.textViewEmail = textViewEmail;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewEmail = (TextView) rootView.findViewById(R.id.textViewEmail);
            return new ViewHolder(rootView, imageView, textViewName, textViewEmail);
        }
    }
}
