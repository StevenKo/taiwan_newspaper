package com.taiwan.realtime.news;


import com.taiwan.news.api.NewsAPI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;



public class TabActivity extends android.app.TabActivity  {

	private String str1;
	
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

		mTabHost.setCurrentTab(0);
		
	}

}


