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

public class PageNewsListActivity extends Activity {

	// list with the data to show in the listview
	private LinkedList<String> mListItems;

	// The data to be displayed in the ListView
	private String[] mNames = { "Fabian", "Carlos", "Alex", "Andrea", "Karla",
				"Freddy", "Lazaro", "Hector", "Carolina", "Edwin", "Jhon",
				"Edelmira", "Andres" };
	
	
	
	private ArrayAdapter<String> adapter;
	
	private LoadMoreListView myList;
	private Bundle mBundle;
	private int sourceInt;
	private int categoryInt;
	private ArrayList<News> myNewsArray = new ArrayList<News>();
	private ListNewsAdapter myListNewsAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_news_list);
        
        mBundle = this.getIntent().getExtras(); 
        sourceInt = mBundle.getInt("SourceInt");
        categoryInt = mBundle.getInt("CategoryInt");
        
        myList = (LoadMoreListView) findViewById(R.id.news_list);
        myNewsArray = NewsAPI.getCateroyNews(sourceInt, categoryInt, 1);
        myListNewsAdapter = new ListNewsAdapter(this,myNewsArray);
        myList.setAdapter(myListNewsAdapter);
        
//      mListItems = new LinkedList<String>();
//		mListItems.addAll(Arrays.asList(mNames));
//
//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);
//		
//		
//		myList.setAdapter(adapter);

		// set a listener to be invoked when the list reaches the end
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
				
				Intent intent = new Intent(PageNewsListActivity.this, PageNewsDetailActivity.class);
				startActivity(intent);

			}
		});
        
   	}
    
    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			if (isCancelled()) {
				return null;
			}

			// Simulates a background task
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			for (int i = 0; i < myNewsArray.size(); i++)
				myNewsArray.add(myNewsArray.get(i));

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
//			mListItems.add("Added after load more");

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
