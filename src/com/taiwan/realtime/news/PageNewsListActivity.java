package com.taiwan.realtime.news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import com.costum.android.widget.LoadMoreListView;
import com.costum.android.widget.LoadMoreListView.OnLoadMoreListener;
import com.taiwan.imageload.ListNewsAdapter;
import com.taiwan.news.api.NewsAPI;
import com.taiwan.news.entity.News;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class PageNewsListActivity extends Activity {

	
	private LoadMoreListView myList;
	private Bundle mBundle;
	private int sourceInt;
	private int categoryInt;
	private ArrayList<News> myNewsArray = new ArrayList<News>();
	private ListNewsAdapter myListNewsAdapter;
	private int pageNum;
	private TextView textCategory;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_news_list);
        
        mBundle = this.getIntent().getExtras(); 
        sourceInt = mBundle.getInt("SourceInt");
        categoryInt = mBundle.getInt("CategoryInt");
        
        new LoadDataTaskFirst().execute();

		
        
   	}
    
    private class LoadDataTaskFirst extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			
			pageNum =1;
			myNewsArray = NewsAPI.getCateroyNews(sourceInt, categoryInt, pageNum);
		       	       
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			setList();
				

			super.onPostExecute(result);
		

		}

	}
    
    private void setList() {
		
    	textCategory = (TextView) findViewById(R.id.text_category);
    	myList = (LoadMoreListView) findViewById(R.id.news_list);
        
    	textCategory.setText(myNewsArray.get(0).getCategoryName()+" --- "+myNewsArray.get(0).getCategoryName());
    	
    	myListNewsAdapter = new ListNewsAdapter(this,myNewsArray);
    	myList.setAdapter(myListNewsAdapter);
    	
    	myList.setOnLoadMoreListener(new OnLoadMoreListener() {
			public void onLoadMore() {
				// Do the work to load more items at the end of list
				// here
				new LoadDataTask().execute();
			}
		});

		myList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				mBundle.putInt("NewsPosition", position);				
				mBundle.putInt("PageNum", pageNum);
				
				int[] newsIDs = getNewsIdArray();
				mBundle.putIntArray("NewsIDs", newsIDs);
				
				Intent intent = new Intent(PageNewsListActivity.this, PageNewsDetailActivity.class);
				intent.putExtras(mBundle);
				startActivity(intent);
		
			}

			private int[] getNewsIdArray() {
				
				int[] a = new int[myNewsArray.size()];
				
				for (int i=0;i<myNewsArray.size();i++){
					a[i] = myNewsArray.get(i).getId();
				}
				
				return a;
			}
		});
	}
    
    
    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			
			pageNum = pageNum + 1;
			ArrayList<News> addArray = NewsAPI.getCateroyNews(sourceInt, categoryInt, pageNum);
			
			for (int i = 0; i < addArray.size(); i++)
				
				myNewsArray.add(addArray.get(i));

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {


			// We need notify the adapter that the data have been changed
			myListNewsAdapter.notifyDataSetChanged();

			// Call onLoadMoreComplete when the LoadMore task, has finished
			myList.onLoadMoreComplete();

			super.onPostExecute(result);
		}

		@Override
		protected void onCancelled() {
			// Notify the loading more operation has finished
			myList.onLoadMoreComplete();
		}
	}
    
    
    
    
}
