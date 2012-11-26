package com.taiwan.realtime.news;

import java.util.ArrayList;

import com.taiwan.imageload.GalleryAdapter;
import com.taiwan.imageload.ListAdapter;
import com.taiwan.news.api.NewsAPI;
import com.taiwan.news.entity.Category;
import com.taiwan.news.entity.News;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Gallery;
import android.widget.ListView;

public class PageAppleActivity extends Activity {
	
	private ListView myList;
	private ListAdapter myListAdapter;
	private Gallery myGallery;
	private GalleryAdapter myGalleryAdpter;
	private ArrayList<Category> myCategroyArray = new ArrayList<Category>();
	private ArrayList<News> myPromotionArray = new ArrayList<News>();
	private int deviceWidth;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_apple);
        
        deviceWidth = PageAppleActivity.this.getWindowManager().getDefaultDisplay().getWidth();
        
        myCategroyArray = NewsAPI.getSourceCategory(0);
        myPromotionArray = NewsAPI.getPromotionNews(0);
        
        myList=(ListView)findViewById(R.id.list_apple);
        myListAdapter=new ListAdapter(this, myCategroyArray);
        myList.setAdapter(myListAdapter);
        
        myGallery = (Gallery)findViewById(R.id.gallery_apple);
        myGalleryAdpter = new GalleryAdapter(this, myPromotionArray, deviceWidth);
        myGallery.setAdapter(myGalleryAdpter);
        
        
       
   	}
    
}

