package com.taiwan.realtime.news;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import com.taiwan.news.api.NewsAPI;
import com.taiwan.news.entity.News;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class PageNewsDetailActivity extends Activity {
	
	private ProgressDialog progressDialog   = null;
	
	private TextView textNewsSource;
	private TextView textNewsTitle;
	private TextView textNewsContent;
	
	private News thisNews;
	private Bundle myBundle;
	private int newsPosition;
	private Button buttonUp;
	private Button buttonDown;
	private int[] newsIDs ;
	private Boolean LoadOrNot = true;
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
        
        new LoadNewsTask().execute();
        
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

			thisNews = NewsAPI.getNewsDetail(newsIDs[newsPosition]);
			
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
			
			thisNews = NewsAPI.getNewsDetail(newsIDs[newsPosition]);
			
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
    	textNewsContent = (TextView) findViewById (R.id.text_news_content);
    	buttonUp = (Button) findViewById (R.id.button_up);
    	buttonDown = (Button) findViewById (R.id.button_down);
    	
    	buttonUp.setOnClickListener(new OnClickListener(){  
            public void onClick(View v) { 
            	if(newsPosition == 0){
            		Toast.makeText(getApplicationContext(), "無上一則", Toast.LENGTH_SHORT).show();
            	}else{
	            	newsPosition = newsPosition - 1;
	            	new UpdateNewsTask().execute();
            	}
            }  
        });
    	
    	buttonDown.setOnClickListener(new OnClickListener(){  
            public void onClick(View v) {
            	
            	if(newsPosition == newsIDs.length){
            		Toast.makeText(getApplicationContext(), "無下一則", Toast.LENGTH_SHORT).show();
            	}else{
	            	newsPosition = newsPosition + 1;
	            	new UpdateNewsTask().execute();
            	}
            	
            	if(LoadOrNot && newsPosition > newsIDs.length-5){
            		new loadNewsIDsTask().execute();
            	}
            	
            }  
        });
	}
    
    private class loadNewsIDsTask extends AsyncTask<Void, Void, Void> {
    	
    	@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }
    	
		@Override
		protected Void doInBackground(Void... params) {
			
			pageNum = pageNum+1;
			newCategoryNews = NewsAPI.getCateroyNews(sourceInt, categoryInt, pageNum);
			
			return null;
		}

		@SuppressLint("NewApi")
		@Override
		protected void onPostExecute(Void result) {
			
			if (newCategoryNews == null){
				LoadOrNot = false;
			}else{
				
				int k = newsIDs.length;
				int[] a = newsIDs;
				newsIDs = new int[newsIDs.length+15]; //load 15 news per loading
				newsIDs = Arrays.copyOf(a, k);
				
				for(int i= 0 ; i< newCategoryNews.size(); i++){
					newsIDs[k+i] = newCategoryNews.get(i).getId(); //error at here
				}			
			}
			
			super.onPostExecute(result);			
		}
	}
    
    
    
    private void setUIs() {
		// TODO Auto-generated method stub
    	textNewsSource.setText(thisNews.getSource()+"---"+thisNews.getCategoryName());
    	textNewsTitle.setText(thisNews.getTitle());
    	textNewsContent.setText(thisNews.getContent());
    	
    	
    	
	}
    
}
