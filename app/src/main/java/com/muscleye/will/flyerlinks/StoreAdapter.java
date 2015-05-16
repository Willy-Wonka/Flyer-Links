package com.muscleye.will.flyerlinks;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.muscleye.will.flyerlinks.model.Store;

import java.util.List;

public class StoreAdapter extends ArrayAdapter<Store>
{
    private Context context;
    @SuppressWarnings("unused")
    private List<Store> storeList;

    public StoreAdapter(Context context, int resource, List<Store> objects) {
        super(context, resource, objects);
        this.context = context;
        this.storeList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_store, parent, false);

        //Display store info in the TextView widget
        Store store = storeList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        tv.setText(store.getName()+ "\n" + store.getAddress() +"\n"+ unitDistance(store.getDistance())+"\n");

        return view;
    }

    private String unitDistance(double distance)
    {
        String result;
        if (distance >= 1000)
        {
            distance /= 1000;
            result = String.valueOf((double)(Math.round(distance*10)/10.0)) + "km";
        }
        else
        {
            result = (int)distance + "m";
        }
        return result;
    }
}