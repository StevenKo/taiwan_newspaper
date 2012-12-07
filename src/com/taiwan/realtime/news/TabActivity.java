package com.taiwan.realtime.news;


import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.taiwan.news.api.NewsAPI;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TabHost;



public class TabActivity extends android.app.TabActivity  {

	private String str1;
	private AdView adView;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabbarscreen);
		

		TabHost mTabHost = getTabHost();

		mTabHost.addTab(mTabHost.newTabSpec("tab1")
				.setIndicator(null,getResources().getDrawable(R.drawable.tab_apple))
				.setContent(new Intent(this, PageAppleActivity.class)));

		mTabHost.addTab(mTabHost.newTabSpec("tab2")
				.setIndicator(null,getResources().getDrawable(R.drawable.tab_free))
				.setContent(new Intent(this, PageAppleActivity.class)));

		mTabHost.addTab(mTabHost.newTabSpec("tab3")
				.setIndicator(null,getResources().getDrawable(R.drawable.tab_uno))
				.setContent(new Intent(this, PageAppleActivity.class)));

		mTabHost.addTab(mTabHost.newTabSpec("tab4")
				.setIndicator(null,getResources().getDrawable(R.drawable.tab_chinese))
				.setContent(new Intent(this, PageAppleActivity.class)));

		mTabHost.addTab(mTabHost.newTabSpec("tab5")
				.setIndicator(null,getResources().getDrawable(R.drawable.tab_eco))
				.setContent(new Intent(this, PageAppleActivity.class)));

//		mTabHost.setCurrentTab(0);
		
		AdTask adTask = new AdTask();
        adTask.execute();
		
	}
	
	public void setAd() {
    	// Create the adView
    	Resources res = getResources();
    	String admobKey = res.getString(R.string.admob_key);

        adView = new AdView(this, AdSize.BANNER, admobKey);

        // Lookup your LinearLayout assuming it's been given
        // the attribute android:id="@+id/mainLayout"
        LinearLayout layout = (LinearLayout)findViewById(R.id.ad_linearlayout);

        // Add the adView to it
        layout.addView(adView);

        // Initiate a generic request to load it with an ad
        adView.loadAd(new AdRequest());
    }
    
    class AdTask extends AsyncTask<Integer, Integer, String> {
		@Override
		protected String doInBackground(Integer... arg0) {
			
			return null;
		}
		
		 @Override  
	     protected void onPostExecute(String result) {
			 setAd();
			 super.onPostExecute(result);

		 }
    }

}


