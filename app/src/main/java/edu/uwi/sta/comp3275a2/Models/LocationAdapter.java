package edu.uwi.sta.comp3275a2.Models;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

import edu.uwi.sta.comp3275a2.R;

/**
 * Created by Raydon on 4/2/2016.
 */

public class LocationAdapter extends BaseAdapter {

    Context context;
    List<locations> courseList;

    public LocationAdapter(Context context, List<locations> list) {

        this.context = context;
        courseList = list;
    }

    @Override
    public int getCount() {

        return courseList.size();
    }

    @Override
    public Object getItem(int position) {

        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {

        locations contactListItems = courseList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.locations, null);

        }

        TextView tvSlNo = (TextView) convertView.findViewById(R.id.long_txtview);
        tvSlNo.setText(contactListItems.getLong());
        TextView tvName = (TextView) convertView.findViewById(R.id.lat_txtview);
        tvName.setText(contactListItems.getLat());


        return convertView;
    }

}
