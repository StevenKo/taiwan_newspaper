package com.taiwan.realtime.news;



import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.analytics.tracking.android.EasyTracker;
import com.taiwan.news.api.NewsAPI;
import com.vpon.adon.android.VponDestroy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;



public class TabActivity extends android.app.TabActivity  implements AdWhirlInterface{

	private String str1;
	private AdView adView;
	private TabHost mTabHost;
	private AlertDialog.Builder finishDialog;
	
	private String adWhirlKey = "9ebc42f0a4584518a55be69c3651e4b3"; //adWhirl license key
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabbarscreen);
		
		
		mTabHost = getTabHost();

		setupTab(PageAppleActivity.class,"tab1", R.drawable.tab_apple_press);
		setupTab(PageFreeActivity.class,"tab2", R.drawable.tab_free);
		setupTab(PageUnoActivity.class,"tab3", R.drawable.tab_uno);
		setupTab(PageChinaTimesActivity.class,"tab4", R.drawable.tab_chinatimes);
		setupTab(PageEconomicActivity.class,"tab5", R.drawable.tab_eco);
		
		// delete rightest bar
//		View rightestTab = getTabWidget().getChildAt(4);
//		rightestTab.findViewById(R.id.tabSplitter).setVisibility(View.GONE);
        
//        View firstTab = getTabWidget().getChildAt(0);
//        firstTab.findViewById(R.id.tabDivider).setVisibility(View.VISIBLE);
        
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
		            ImageView image = (ImageView) tabView.findViewById(R.id.icon);
			        image.setImageResource(R.drawable.tab_apple_press);
		        }else if (tag.equals("tab2")) {
		            tabView = getTabWidget().getChildAt(1);
		            ImageView image = (ImageView) tabView.findViewById(R.id.icon);
			        image.setImageResource(R.drawable.tab_free_press);
		        }else if (tag.equals("tab3")) {
		            tabView = getTabWidget().getChildAt(2);
		            ImageView image = (ImageView) tabView.findViewById(R.id.icon);
			        image.setImageResource(R.drawable.tab_uno_press);
		        }else if (tag.equals("tab4")) {
		            tabView = getTabWidget().getChildAt(3);
		            ImageView image = (ImageView) tabView.findViewById(R.id.icon);
			        image.setImageResource(R.drawable.tab_chinatimes_press);
		        }else if (tag.equals("tab5")) {
		            tabView = getTabWidget().getChildAt(4);
		            ImageView image = (ImageView) tabView.findViewById(R.id.icon);
			        image.setImageResource(R.drawable.tab_eco_press);
		        }
		        
		    }       
		});

		
		setAdAdwhirl();
		
		
//		AdTask adTask = new AdTask();
//        adTask.execute();
		
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

        RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.adonView);
        
    	adwhirlLayout.setAdWhirlInterface(this);
	 	 	
	 	mainLayout.addView(adwhirlLayout);
		
		mainLayout.invalidate();
	}




	private void setupTab(Class<?> ccls, String name, Integer iconId) {
	    Intent intent = new Intent().setClass(this, ccls);


	    View tab = LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null);
	    ImageView image = (ImageView) tab.findViewById(R.id.icon);
//	    TextView text = (TextView) tab.findViewById(R.id.tabDivider);
	    if(iconId != null){
	        image.setImageResource(iconId);
	    }

	    TabSpec spec = mTabHost.newTabSpec(name).setIndicator(tab).setContent(intent);
	    mTabHost.addTab(spec);

	}
	
	private void clearTabStyles() {
	    for (int i = 0; i < getTabWidget().getChildCount(); i++) {
	    	View tab = getTabWidget().getChildAt(i);
	    	ImageView image = (ImageView) tab.findViewById(R.id.icon);
	        if(i==0){
	        	image.setImageResource(R.drawable.tab_apple);
	        }else if(i==1){
	        	image.setImageResource(R.drawable.tab_free);
	        }else if(i==2){
	        	image.setImageResource(R.drawable.tab_uno);
	        }else if(i==3){
	        	image.setImageResource(R.drawable.tab_chinatimes);
	        }else if(i==4){
	        	image.setImageResource(R.drawable.tab_eco);
	        }
	    }
	}
	
//	public void setAd() {
//    	// Create the adView
//    	Resources res = getResources();
//    	String admobKey = res.getString(R.string.admob_key);
//
//        adView = new AdView(this, AdSize.BANNER, admobKey);
//
//        // Lookup your LinearLayout assuming it's been given
//        // the attribute android:id="@+id/mainLayout"
//        LinearLayout layout = (LinearLayout)findViewById(R.id.ad_linearlayout);
//
//        // Add the adView to it
//        layout.addView(adView);
//
//        // Initiate a generic request to load it with an ad
//        adView.loadAd(new AdRequest());
//    }
    
//    class AdTask extends AsyncTask<Integer, Integer, String> {
//		@Override
//		protected String doInBackground(Integer... arg0) {
//			
//			return null;
//		}
//		
//		 @Override  
//	     protected void onPostExecute(String result) {
//			 setAd();
//			 super.onPostExecute(result);
//
//		 }
//    }
    
    @Override
    public void  onBackPressed  () {  
			finishDialog = new AlertDialog.Builder(this).setTitle("結束執行")
					.setMessage("是否離開即時新聞?")
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
							System.exit(0);
						}
					})
					.setNegativeButton("No", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
			finishDialog.show();          
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

}


