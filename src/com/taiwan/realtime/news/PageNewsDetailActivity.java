package com.taiwan.realtime.news;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import com.taiwan.imageload.ImageLoader;
import com.taiwan.news.api.NewsAPI;
import com.taiwan.news.entity.News;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class PageNewsDetailActivity extends Activity {
	
	private ProgressDialog progressDialog   = null;
	
	private TextView textNewsSource;
	private TextView textNewsTitle;
//	private TextView textNewsContent;
	private TextView textNewsDatetime;
	
	private ScrollView newsScrollView;
	private ImageLoader imageLoader;
	private LinearLayout newsDetailImages;
	
	private News thisNews;
	private Bundle myBundle;
	private int newsPosition;
	private Button buttonUp;
	private Button buttonDown;
	private int[] newsIDs ;
	private ArrayList<Integer> newsIDsArray = new ArrayList<Integer>();
	private Boolean LoadOrNot = true;
	private Boolean LoadingOrNot = false; //when false, can load from server; when true, can't load from server
	private int sourceInt;
	private int categoryInt;
	private int pageNum;
	
	private ArrayList<News> newCategoryNews;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_news_detail);
        
        myBundle = this.getIntent().getExtras(); 
        newsIDs = myBundle.getIntArray("NewsIDs");
        sourceInt = myBundle.getInt("SourceInt");
        categoryInt = myBundle.getInt("CategoryInt");
        newsPosition = myBundle.getInt("NewsPosition");
        pageNum = myBundle.getInt("PageNum");
        
        pourNewsIDs();
        
        if(newsIDs.length == 1){
        	new loadNewsIDsTask().execute();
        }
        
        new LoadNewsTask().execute();
        
   	}
   
    private void pourNewsIDs() {
		// TODO Auto-generated method stub
		for(int i=0; i< newsIDs.length;i++){
			newsIDsArray.add(newsIDs[i]);
		}
	}

	private class LoadNewsTask extends AsyncTask<Void, Void, Void> {
    	
    	@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = ProgressDialog.show(PageNewsDetailActivity.this, null, null);

        }
    	
		@Override
		protected Void doInBackground(Void... params) {

			thisNews = NewsAPI.getNewsDetail(newsIDsArray.get(newsPosition));
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			findUIs();
			setUIs();
		}

		

	}
    
    private class UpdateNewsTask extends AsyncTask<Void, Void, Void> {
    	
    	@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = ProgressDialog.show(PageNewsDetailActivity.this, null, null);

        }
    	
		@Override
		protected Void doInBackground(Void... params) {
			
			thisNews = NewsAPI.getNewsDetail(newsIDsArray.get(newsPosition));
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			setUIs();
		}
	}
    
    private void findUIs() {
		// TODO Auto-generated method stub
    	textNewsSource = (TextView) findViewById (R.id.text_news_source);
    	textNewsTitle = (TextView) findViewById (R.id.text_news_title);
//    	textNewsContent = (TextView) findViewById (R.id.text_news_content);
    	textNewsDatetime = (TextView) findViewById (R.id.text_news_datetime);
    	newsScrollView =(ScrollView) findViewById (R.id.news_detail_scrollview);
    	newsDetailImages =(LinearLayout) findViewById (R.id.news_detail_images);
    	buttonUp = (Button) findViewById (R.id.button_up);
    	buttonDown = (Button) findViewById (R.id.button_down);
    	
    	buttonUp.setOnClickListener(new OnClickListener(){  
            public void onClick(View v) { 
            	if(newsPosition == 0){
            		Toast.makeText(getApplicationContext(), "無上一則", Toast.LENGTH_SHORT).show();
            	}else{
            		clearLayoutImages();
	            	newsPosition = newsPosition - 1;
	            	new UpdateNewsTask().execute();
            	}
            }

			
        });
    	
    	buttonDown.setOnClickListener(new OnClickListener(){  
            public void onClick(View v) {
            	
            	if(newsPosition + 1 == newsIDsArray.size()){
            		Toast.makeText(getApplicationContext(), "無下一則", Toast.LENGTH_SHORT).show();
            	}else{
            		clearLayoutImages();
	            	newsPosition = newsPosition + 1;
	            	new UpdateNewsTask().execute();
            	}
            	
            	if(LoadOrNot && newsIDsArray.size()- newsPosition < 5){
            		new loadNewsIDsTask().execute();
            	}
            	
            }  
        });
	}
    
    private void clearLayoutImages() {
		// TODO Auto-generated method stub

			try {
				newsDetailImages.removeViewsInLayout(0, newsDetailImages.getChildCount());
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		
	}  
    
    private class loadNewsIDsTask extends AsyncTask<Void, Void, Void> {
    	
    	@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }
    	
		@Override
		protected Void doInBackground(Void... params) {
			if(!LoadingOrNot){
				LoadingOrNot = true;
				pageNum = pageNum+1;
				newCategoryNews = NewsAPI.getCateroyNews(sourceInt, categoryInt, pageNum);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if(LoadingOrNot){
				if (newCategoryNews == null){
					LoadOrNot = false;
				}else{
					
					for(int i= 0 ; i< newCategoryNews.size(); i++){
						newsIDsArray.add(newCategoryNews.get(i).getId()); //error at here
					}			
				}
				LoadingOrNot = false;
			}
			
			super.onPostExecute(result);			
		}
	}
    
    
    
    private void setUIs() {
		// TODO Auto-generated method stub
    	textNewsSource.setText(thisNews.getSource()+"---"+thisNews.getCategoryName());
    	textNewsTitle.setText(thisNews.getTitle());
//    	textNewsContent.setText(thisNews.getContent());
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");  
        String dateString = formatter.format(thisNews.getReleaseTime());  
    	textNewsDatetime.setText(dateString);
    	
    	TextView textNewsContent = new TextView(this);
    	newsDetailImages.addView(textNewsContent);
    	LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)textNewsContent.getLayoutParams();
    	params.setMargins(5, 5, 5, 5);
    	textNewsContent.setLayoutParams(params); 	
    	textNewsContent.setText(thisNews.getContent());
    	
    	if (thisNews.getPictures().size()>0){
    		
    		imageLoader = new ImageLoader(PageNewsDetailActivity.this, 150);
    		
    		for(int i=0; i< thisNews.getPictures().size(); i++){
    			
    			ImageView iv = new ImageView(this);
    			newsDetailImages.addView(iv);
    			LinearLayout.LayoutParams imageParams = (LinearLayout.LayoutParams)iv.getLayoutParams();
    			imageParams.setMargins(5, 5, 5, 5);
    			iv.setLayoutParams(imageParams);
    			imageLoader.DisplayImage(thisNews.getPictures().get(i).getUrl(), iv);		
    			
    			TextView textImage = new TextView(this);
    			newsDetailImages.addView(textImage);
    			LinearLayout.LayoutParams imageTextParams = (LinearLayout.LayoutParams)textImage.getLayoutParams();
    			imageTextParams.gravity = Gravity.CENTER;
    			imageTextParams.setMargins(5, 5, 5, 5);
    			textImage.setGravity(Gravity.CENTER);
    	    	textNewsContent.setLayoutParams(imageTextParams);
    	    	textImage.setText(thisNews.getPictures().get(i).getIntro());
    				
    		}
    		
    	}
    	
	}
    
}
