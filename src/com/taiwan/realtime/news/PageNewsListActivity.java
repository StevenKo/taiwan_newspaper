package com.taiwan.realtime.news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.costum.android.widget.LoadMoreListView;
import com.costum.android.widget.LoadMoreListView.OnLoadMoreListener;
import com.google.ads.AdView;
import com.google.analytics.tracking.android.EasyTracker;
import com.taiwan.imageload.ListNewsAdapter;
import com.taiwan.news.api.NewsAPI;
import com.taiwan.news.entity.News;
import com.vpon.adon.android.VponDestroy;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class PageNewsListActivity extends Activity implements AdWhirlInterface{

	private ProgressDialog progressDialog   = null;
	private LoadMoreListView myList;
	private Bundle mBundle;
	private int sourceInt;
	private int categoryInt;
	private ArrayList<News> myNewsArray = new ArrayList<News>();
	private ListNewsAdapter myListNewsAdapter;
	private int pageNum;
	private TextView textCategory;
	private String sourceName;
	private String categoryName;
	private Boolean loadOrNot = true;
	private Boolean first = true;
	private String adWhirlKey = "9ebc42f0a4584518a55be69c3651e4b3"; //adWhirl license key
	private LinearLayout        linearNetwork;
    private Button              btnReload;
//    private LinearLayout        linearProgress;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_news_list);
        textCategory = (TextView) findViewById(R.id.text_category);
        linearNetwork = (LinearLayout) findViewById(R.id.linear_network);
//        linearProgress = (LinearLayout) findViewById(R.id.linear_progress);
        setReloadBtn();
        
        mBundle = this.getIntent().getExtras(); 
        sourceInt = mBundle.getInt("SourceInt");
        categoryInt = mBundle.getInt("CategoryInt");
        sourceName = mBundle.getString("SourceName");
        categoryName = mBundle.getString("CategoryName");
        
        changeTitleBanner();
        
        
        if(isOnline()){
//        	linearProgress.setVisibility(View.VISIBLE);
        	 progressDialog = ProgressDialog.show(PageNewsListActivity.this, null, null);
             progressDialog.setCancelable(true);
        	new LoadDataTaskFirst().execute();
        }else{
        	linearNetwork.setVisibility(View.VISIBLE);
        }
        
        setAdAdwhirl();
        
   	}
    
    private void setReloadBtn() {
		// TODO Auto-generated method stub
		btnReload = (Button) findViewById(R.id.btn_list_reload);
		btnReload.setOnClickListener(new OnClickListener(){  
            public void onClick(View v) {
            	if(isOnline()){
	            	linearNetwork.setVisibility(View.GONE);
	            	new LoadDataTaskFirst().execute();
            	}else{
            		Toast.makeText(getApplicationContext(), "無網路連線!", Toast.LENGTH_SHORT).show();
            	}
            }		
        });
	}
    
    private void changeTitleBanner() {
		if(sourceInt == 1){
			textCategory.setBackgroundResource(R.drawable.banner_apple);
		}else if(sourceInt == 2){
			textCategory.setBackgroundResource(R.drawable.banner_free);
		}else if(sourceInt == 3){
			textCategory.setBackgroundResource(R.drawable.banner_uno);
		}else if(sourceInt == 4){
			textCategory.setBackgroundResource(R.drawable.banner_chinatimes);
		}else if(sourceInt == 5){
			textCategory.setBackgroundResource(R.drawable.banner_eco);
		}
		
	}
    
    private void setAdAdwhirl() {
		// TODO Auto-generated method stub
		AdWhirlManager.setConfigExpireTimeout(1000 * 60); 
        AdWhirlTargeting.setAge(23);
        AdWhirlTargeting.setGender(AdWhirlTargeting.Gender.MALE);
        AdWhirlTargeting.setKeywords("online games gaming");
        AdWhirlTargeting.setPostalCode("94123");
        AdWhirlTargeting.setTestMode(false);
   		
        AdWhirlLayout adwhirlLayout = new AdWhirlLayout(this, adWhirlKey);	

        RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.adonViewlist);
        
    	adwhirlLayout.setAdWhirlInterface(this);
	 	 	
	 	mainLayout.addView(adwhirlLayout);
		
		mainLayout.invalidate();
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
			
//		    if(linearProgress.getVisibility() == View.VISIBLE){
//		    	linearProgress.setVisibility(View.GONE);
//		    }
			
			if(progressDialog.isShowing()){
		    	progressDialog.dismiss();
		    }
			
			super.onPostExecute(result);
		

		}

	}
    
    private void setList() {
		
    	
    	myList = (LoadMoreListView) findViewById(R.id.news_list);
        
    	textCategory.setText(categoryName);
    	
    	myListNewsAdapter = new ListNewsAdapter(this,myNewsArray);
    	myList.setAdapter(myListNewsAdapter);
    	
    	myList.setOnLoadMoreListener(new OnLoadMoreListener() {
			public void onLoadMore() {
				// Do the work to load more items at the end of list
				if(isOnline()){
					new LoadDataTask().execute();
				}else{
					linearNetwork.setVisibility(View.VISIBLE);
				}
				
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
			
			if (loadOrNot){
				pageNum = pageNum + 1;
				ArrayList<News> addArray = NewsAPI.getCateroyNews(sourceInt, categoryInt, pageNum);
				
				if(addArray.size()==0){
					loadOrNot = false;
					pageNum = pageNum -1;			
				}else{			
					for (int i = 0; i < addArray.size(); i++){
						myNewsArray.add(addArray.get(i));
					}
				}
			}
			

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			if(!loadOrNot && first){
				Toast.makeText(getApplicationContext(), "無下一則",Toast.LENGTH_SHORT).show();
				first = false; // toast only one times
			}
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
    
    @Override
	public void adWhirlGeneric() {
		// TODO Auto-generated method stub		
	}
    
    @Override
	protected void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this); // Add this method.
	}
    @Override
	protected void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this); // Add this method.
	}
	protected void onDestroy() {		
		
		VponDestroy.remove(this);
		super.onDestroy();
	}
    
    
    public void rotationHoriztion(int beganDegree, int endDegree, AdView view) {
		final float centerX = 320 / 2.0f;
		final float centerY = 48 / 2.0f;
		final float zDepth = -0.50f * view.getHeight();
	
		Rotate3dAnimation rotation = new Rotate3dAnimation(beganDegree, endDegree, centerX, centerY, zDepth, true);
		rotation.setDuration(1000);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new Animation.AnimationListener() {
			public void onAnimationStart(Animation animation) {
			}
	
			public void onAnimationEnd(Animation animation) {
			}
	
			public void onAnimationRepeat(Animation animation) {
			}
		});
		view.startAnimation(rotation);
	}
    
    public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
    
    @Override
    public void  onBackPressed  () {  
	    if(progressDialog.isShowing()){
	    	progressDialog.dismiss();
	    }else{
	    	finish();
	    }     
    }
    
}
