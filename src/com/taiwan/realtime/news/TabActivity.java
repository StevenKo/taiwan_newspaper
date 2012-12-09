package com.taiwan.realtime.news;


import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.taiwan.news.api.NewsAPI;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;



public class TabActivity extends android.app.TabActivity  {

	private String str1;
	private AdView adView;
	private TabHost mTabHost;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabbarscreen);
		

		mTabHost = getTabHost();

		setupTab(PageAppleActivity.class,"tab1", R.drawable.tab_apple);
		setupTab(PageFreeActivity.class,"tab2", R.drawable.tab_free);
		setupTab(PageUnoActivity.class,"tab3", R.drawable.tab_uno);
		setupTab(PageChinaTimesActivity.class,"tab4", R.drawable.tab_chinese);
		setupTab(PageEconomicActivity.class,"tab5", R.drawable.tab_eco);
		
		// delete rightest bar
		View rightestTab = getTabWidget().getChildAt(4);
		rightestTab.findViewById(R.id.tabSplitter).setVisibility(View.GONE);
        
        View firstTab = getTabWidget().getChildAt(0);
        firstTab.findViewById(R.id.tabDivider).setVisibility(View.VISIBLE);
        
		mTabHost.setCurrentTab(0);
		
		
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
		    @Override
		    public void onTabChanged(String tag) {
		        // reset some styles
		        clearTabStyles();
		        View tabView = null;
		        // Use the "tag" for the tab spec to determine which tab is selected
		        if (tag.equals("tab1")) {
		            tabView = getTabWidget().getChildAt(0);
		        }else if (tag.equals("tab2")) {
		            tabView = getTabWidget().getChildAt(1);
		        }else if (tag.equals("tab3")) {
		            tabView = getTabWidget().getChildAt(2);
		        }else if (tag.equals("tab4")) {
		            tabView = getTabWidget().getChildAt(3);
		        }else if (tag.equals("tab5")) {
		            tabView = getTabWidget().getChildAt(4);
		        }
		        tabView.findViewById(R.id.tabDivider).setVisibility(View.VISIBLE);
		    }       
		});

		
		
//		AdTask adTask = new AdTask();
//       adTask.execute();
		
	}
	
	
	
	
	private void setupTab(Class<?> ccls, String name, Integer iconId) {
	    Intent intent = new Intent().setClass(this, ccls);


	    View tab = LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null);
	    ImageView image = (ImageView) tab.findViewById(R.id.icon);
	    TextView text = (TextView) tab.findViewById(R.id.tabDivider);
	    if(iconId != null){
	        image.setImageResource(iconId);
	    }

	    TabSpec spec = mTabHost.newTabSpec(name).setIndicator(tab).setContent(intent);
	    mTabHost.addTab(spec);

	}
	
	private void clearTabStyles() {
	    for (int i = 0; i < getTabWidget().getChildCount(); i++) {
	    	View tab = getTabWidget().getChildAt(i);
	        tab.findViewById(R.id.tabDivider).setVisibility(View.INVISIBLE);
	    }
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


