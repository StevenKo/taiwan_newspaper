package com.taiwan.imageload;

import java.util.ArrayList;

import com.taiwan.news.entity.Category;
import com.taiwan.realtime.news.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<Category> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public ListAdapter(Activity a, ArrayList<Category> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.item_list, null);

        TextView text=(TextView)vi.findViewById(R.id.text_list);;
        text.setText(data.get(position).getCateName());
        return vi;
    }
}