package com.taiwan.imageload;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.taiwan.news.entity.News;
import com.taiwan.realtime.news.R;

public class ListNewsAdapter extends BaseAdapter {

    private final Activity        activity;
    private final ArrayList<News> data;
    private static LayoutInflater inflater = null;
    public ImageLoader            imageLoader;

    public ListNewsAdapter(Activity a, ArrayList<News> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(activity.getApplicationContext(), 70);
        
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
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.item_news_list, null);

        TextView text = (TextView) vi.findViewById(R.id.text_news_list);
        ImageView image = (ImageView) vi.findViewById(R.id.image_news_list);
        TextView textDate = (TextView) vi.findViewById(R.id.text_list_date);
        
        text.setText(data.get(position).getTitle());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");  
        String dateString = formatter.format(data.get(position).getReleaseTime()); 
        textDate.setText(dateString);
        
        if(data.get(position).getPictures().size()>0){
        	imageLoader.DisplayImage(data.get(position).getPictures().get(0).getUrl(), image);
        }else{
        	image.setImageResource(R.drawable.app_icon);
        }
        
        return vi;
    }
}
