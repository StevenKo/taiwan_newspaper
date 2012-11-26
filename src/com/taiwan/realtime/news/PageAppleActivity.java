package com.taiwan.realtime.news;

import java.util.ArrayList;

import com.taiwan.imageload.ListAdapter;
import com.taiwan.news.api.NewsAPI;
import com.taiwan.news.entity.Category;
import com.taiwan.news.entity.News;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class PageAppleActivity extends Activity {
	
	private ListView myList;
	private ListAdapter myListAdapter;
	private ArrayList<Category> myCategroyArray = new ArrayList<Category>();
	private ArrayList<News> myPromotionArray = new ArrayList<News>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_apple);
        
        myCategroyArray = NewsAPI.getSourceCategory(0);
        
        
        myList=(ListView)findViewById(R.id.list_page1);
        myListAdapter=new ListAdapter(this, myCategroyArray);
        myList.setAdapter(myListAdapter);
        
       
   	}
    
}

