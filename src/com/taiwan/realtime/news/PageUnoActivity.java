package com.taiwan.realtime.news;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.taiwan.imageload.GalleryAdapter;
import com.taiwan.imageload.ListAdapter;
import com.taiwan.news.api.NewsAPI;
import com.taiwan.news.entity.Category;
import com.taiwan.news.entity.News;


public class PageUnoActivity extends Activity {
	private ListView            myList;
    private ListAdapter         myListAdapter;
    private Gallery             myGallery;
    private GalleryAdapter      myGalleryAdpter;
    private ArrayList<Category> myCategroyArray  = new ArrayList<Category>();
    private ArrayList<News>     myPromotionArray = new ArrayList<News>();
    private int                 deviceWidth;
    private int                 height;
    private int                 PicPosition;
    private final Handler       handler          = new Handler();
    private ProgressDialog      progressDialog   = null;
    private static TextView     mDotsText[];
    private int                 mDotsCount;
    private LinearLayout        mDotsLayout;
    private LinearLayout        downLoadingLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_uno);

        deviceWidth = PageUnoActivity.this.getWindowManager().getDefaultDisplay().getWidth();
        float scale = getResources().getDisplayMetrics().density;
        height =(int)(150*scale + 0.5f);

        // download first, and then set UIs on postexecute.
        new DownloadCategoryTask().execute();
        
        new DownloadPromotionTask().execute();

    }
    

    private class DownloadCategoryTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
//            progressDialog = ProgressDialog.show(PageAppleActivity.this, "", "Loading, please wait...");

        }

        @Override
        protected Object doInBackground(Object... params) {
            // TODO Auto-generated method stub

            myCategroyArray = NewsAPI.getSourceCategory(NewsAPI.UNION);
  
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
//          progressDialog.dismiss();
            setListUI();

        }
    }
    
    private void setListUI() {
		// TODO Auto-generated method stub
    	 myList = (ListView) findViewById(R.id.list_uno);
         myListAdapter = new ListAdapter(this, myCategroyArray);
         myList.setAdapter(myListAdapter);
         
         myList.setOnItemClickListener(new OnItemClickListener() {
 			@Override
 			public void onItemClick(AdapterView<?> parent, View view,
 					int position, long id) {

	 				Intent intent = new Intent(PageUnoActivity.this, PageNewsListActivity.class);
	 				Bundle bundle = new Bundle();
	 				bundle.putInt("CategoryInt", myCategroyArray.get(position).getId()); 
	 				bundle.putInt("SourceInt", 3); //means Apple
	 				bundle.putString("CategoryName", myCategroyArray.get(position).getCateName());
	 				bundle.putString("SourceName", "聯合報");
	 				intent.putExtras(bundle);
	   				startActivity(intent);
 		

 			}
 		});
	}
    
    

    private class DownloadPromotionTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
//            progressDialog = ProgressDialog.show(PageAppleActivity.this, "", "Loading, please wait...");

        }

        @Override
        protected Object doInBackground(Object... params) {
            // TODO Auto-generated method stub

            myPromotionArray = NewsAPI.getPromotionNews(3);
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
//          progressDialog.dismiss();
            setGalleryUI();

        }
	
    }
    
    private void setGalleryUI() {
        // TODO Auto-generated method stub
       

        myGallery = (Gallery) findViewById(R.id.gallery_uno);
        myGalleryAdpter = new GalleryAdapter(this, myPromotionArray, deviceWidth, height);
        myGallery.setAdapter(myGalleryAdpter);

        mDotsLayout = (LinearLayout) findViewById(R.id.image_count);
        downLoadingLayout = (LinearLayout) findViewById(R.id.linear_downloading);
        downLoadingLayout.setVisibility(View.GONE);
        mDotsCount = myGallery.getAdapter().getCount();
        mDotsText = new TextView[mDotsCount];
        // here we set the dots
        for (int i = 0; i < mDotsCount; i++) {
            mDotsText[i] = new TextView(this);
            mDotsText[i].setText(".");
            mDotsText[i].setTextSize(30);
            mDotsText[i].setTypeface(null, Typeface.BOLD);
            mDotsText[i].setTextColor(android.graphics.Color.GRAY);
            mDotsText[i].setPadding(5, -30, 5, 5);
            mDotsLayout.addView(mDotsText[i]);

        }

        myGallery.setOnItemSelectedListener((new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < mDotsCount; i++) {
                	PageUnoActivity.mDotsText[i].setTextColor(Color.GRAY);
                }

                PageUnoActivity.mDotsText[position].setTextColor(Color.WHITE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        }));
        
        myGallery.setOnItemClickListener(new OnItemClickListener() {
 			@Override
 			public void onItemClick(AdapterView<?> parent, View view,
 					int position, long id) {

	 				Intent intent = new Intent(PageUnoActivity.this, PageNewsDetailActivity.class);
	 				Bundle bundle = new Bundle();
	 				bundle.putInt("CategoryInt", myCategroyArray.get(position).getId()); 
	 				bundle.putInt("SourceInt", 3); //means Apple
	 				bundle.putInt("NewsPosition", 0);				
	 				bundle.putInt("PageNum", 1);
					
	 				int[] newsIDs = {myPromotionArray.get(position).getId()};
	 				bundle.putIntArray("NewsIDs", newsIDs);
	 				
	 				intent.putExtras(bundle);
	   				startActivity(intent);
 		

 			}
 		});
        
             
//        runnable.run();
    }
    
    @Override
    public void onBackPressed () {
        this.getParent().onBackPressed();
    }
    
//    private final Runnable runnable = new Runnable() {
//        public void run() {
//            myslideshow();
//            handler.postDelayed(this, 5000);
//        }
//    };
//
//	private void myslideshow() {
//		PicPosition = myGallery.getSelectedItemPosition() + 1;
//		if (PicPosition >= myPromotionArray.size()) {
//		// PicPosition = myGallery.getSelectedItemPosition();
//			myGallery.setSelection(0);
//		} else {
//			myGallery.setSelection(PicPosition);// move to the next gallery element.
//		}
//	}
}
