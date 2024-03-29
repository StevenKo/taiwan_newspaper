package com.taiwan.realtime.news;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.google.ads.AdView;
import com.google.analytics.tracking.android.EasyTracker;
import com.taiwan.imageload.ImageLoader;
import com.taiwan.imageload.ImageLoader2;
import com.taiwan.news.api.NewsAPI;
import com.taiwan.news.entity.News;
import com.vpon.adon.android.VponDestroy;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class PageNewsDetailActivity extends Activity implements AdWhirlInterface{
	
	private ProgressDialog progressDialog   = null;
	
	private TextView textNewsSource;
	private TextView textNewsTitle;
//	private TextView textNewsContent;
	private TextView textNewsDatetime;
	
	private ScrollView newsScrollView;
	private ImageLoader2 imageLoader;
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
//	private int contentTextSize;
	private LinearLayout titleLayout;
	
	private ArrayList<News> newCategoryNews;
	
	private String adWhirlKey = "9ebc42f0a4584518a55be69c3651e4b3"; //adWhirl license key
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_news_detail);
        textNewsSource = (TextView) findViewById (R.id.text_news_source);
        titleLayout = (LinearLayout) findViewById (R.id.linear_source);
        
        if(isOnline()){    
	        myBundle = this.getIntent().getExtras(); 
	        newsIDs = myBundle.getIntArray("NewsIDs");
	        sourceInt = myBundle.getInt("SourceInt");
	        categoryInt = myBundle.getInt("CategoryInt");
	        newsPosition = myBundle.getInt("NewsPosition");
	        pageNum = myBundle.getInt("PageNum");
	        
	        changeTitleBanner();
//	        contentTextSize = dip2px(this, 10);
	        pourNewsIDs();
	        
	        if(newsIDsArray.size() == 1){
	        	new loadNewsIDsTask().execute();
	        }
	        
	        new LoadNewsTask().execute();
        }else{
        	Toast.makeText(getApplicationContext(), "無網路連線", Toast.LENGTH_SHORT).show();
        	finish();
        }
        
        setAdAdwhirl();
        
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
    
    private void changeTitleBanner() {
		if(sourceInt == 1){
			titleLayout.setBackgroundResource(R.drawable.banner_apple);
		}else if(sourceInt == 2){
			titleLayout.setBackgroundResource(R.drawable.banner_free);
		}else if(sourceInt == 3){
			titleLayout.setBackgroundResource(R.drawable.banner_uno);
		}else if(sourceInt == 4){
			titleLayout.setBackgroundResource(R.drawable.banner_chinatimes);
		}else if(sourceInt == 5){
			titleLayout.setBackgroundResource(R.drawable.banner_eco);
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

        RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.adonViewdetail);
        
    	adwhirlLayout.setAdWhirlInterface(this);
	 	 	
	 	mainLayout.addView(adwhirlLayout);
		
		mainLayout.invalidate();
	}
    
    private void pourNewsIDs() {
		// TODO Auto-generated method stub
		for(int i=0; i< newsIDs.length;i++){
			newsIDsArray.add(newsIDs[i]);
		}
	}
    
    public static int dip2px(Context context, float dpValue) {
    	  final float scale = context.getResources().getDisplayMetrics().density;
    	  return (int) (dpValue * scale + 0.5f);
    	}

	private class LoadNewsTask extends AsyncTask<Void, Void, Void> {
    	
    	@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = ProgressDialog.show(PageNewsDetailActivity.this, null, null);
            progressDialog.setCancelable(true);

        }
    	
		@Override
		protected Void doInBackground(Void... params) {

			thisNews = NewsAPI.getNewsDetail(newsIDsArray.get(newsPosition));
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if(progressDialog.isShowing()){
		    	progressDialog.dismiss();
		    }
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
            progressDialog.setCancelable(true);
        }
    	
		@Override
		protected Void doInBackground(Void... params) {
			
			thisNews = NewsAPI.getNewsDetail(newsIDsArray.get(newsPosition));
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			 if(progressDialog.isShowing()){
			    	progressDialog.dismiss();
			 }
			if(thisNews!= null){
				newsScrollView.fullScroll(ScrollView.FOCUS_UP);
				setUIs();
			}else{
				Toast.makeText(getApplicationContext(), "無網路連線", Toast.LENGTH_SHORT).show();
	        	finish();
			}
		}
	}
    
    private void findUIs() {
		// TODO Auto-generated method stub
    	
    	textNewsTitle = (TextView) findViewById (R.id.text_news_title);
//    	textNewsContent = (TextView) findViewById (R.id.text_news_content);
    	textNewsDatetime = (TextView) findViewById (R.id.text_news_datetime);
    	newsScrollView =(ScrollView) findViewById (R.id.news_detail_scrollview);
    	newsDetailImages =(LinearLayout) findViewById (R.id.news_detail_images);
    	buttonUp = (Button) findViewById (R.id.button_up);
    	buttonDown = (Button) findViewById (R.id.button_down);
    	
    	buttonUp.setOnClickListener(new OnClickListener(){  
            public void onClick(View v) {
            	
            	if(isOnline()){          	
	            	if(!progressDialog.isShowing()){
		            	if(newsPosition == 0){
		            		Toast.makeText(getApplicationContext(), "無上一則", Toast.LENGTH_SHORT).show();
		            	}else{
		            		clearLayoutImages();
			            	newsPosition = newsPosition - 1;
			            	new UpdateNewsTask().execute();
		            	}
	            	}
            	}else{
            		Toast.makeText(getApplicationContext(), "無網路連線", Toast.LENGTH_SHORT).show();
                	finish();
            	}
            }

			
        });
    	
    	buttonDown.setOnClickListener(new OnClickListener(){  
            public void onClick(View v) {
            	if(isOnline()){
	            	if(!progressDialog.isShowing()){
		            	if(newsPosition + 1 == newsIDsArray.size()){
		            		Toast.makeText(getApplicationContext(), "無下一則", Toast.LENGTH_SHORT).show();
		            	}else{
		            		clearLayoutImages();
			            	newsPosition = newsPosition + 1;
			            	new UpdateNewsTask().execute();
		            	}
	            	}
	            	
	            	if(LoadOrNot && newsIDsArray.size()- newsPosition < 5){
	            		new loadNewsIDsTask().execute();
	            	}
            	}else{
            		Toast.makeText(getApplicationContext(), "無網路連線", Toast.LENGTH_SHORT).show();
                	finish();
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
				if(newsIDsArray.size() != 1){
					pageNum = pageNum+1;
				}else{
				  
				}
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
					
					if(newsIDsArray.size()==1){
						for(int i= 1 ; i< newCategoryNews.size(); i++){
							newsIDsArray.add(newCategoryNews.get(i).getId()); //if newsIDsArray only one, skip one for promo news
						}
					}else{
						for(int i= 0 ; i< newCategoryNews.size(); i++){
							newsIDsArray.add(newCategoryNews.get(i).getId()); //if newsIDsArray more than one, do normal
						}
					}
				}
				LoadingOrNot = false;
			}
			
			super.onPostExecute(result);			
		}
	}
    
    
    
    private void setUIs() {
		// TODO Auto-generated method stub
    	
//    	float scale = getResources().getDisplayMetrics().density;
//    	float textSize;
//    	float dateSize;
//    	
//    	Display display = getWindowManager().getDefaultDisplay();
//	    DisplayMetrics outMetrics = new DisplayMetrics ();
//	    display.getMetrics(outMetrics);
//
//	    float density  = getResources().getDisplayMetrics().density;
//	    float dpHeight = outMetrics.heightPixels / density;
//	    float dpWidth  = outMetrics.widthPixels / density;
//	    
//	    if (dpWidth > 400){
//	    	textSize = 1/scale *31;
//	    	dateSize = 1/scale *29;
//	    }else{
//	    	textSize = 1/scale *40;
//	    	dateSize = 1/scale *35;    	
//	    }
    	
    	Display display = getWindowManager().getDefaultDisplay(); 
    	int width = display.getWidth();  // deprecated
    	int height = display.getHeight();  // deprecated
    	
    	
        textNewsSource.setTextSize(getResources().getDimension(R.dimen.text_size));
        textNewsTitle.setTextSize(getResources().getDimension(R.dimen.text_size));
        textNewsDatetime.setTextSize(getResources().getDimension(R.dimen.text_size));
        
    	textNewsSource.setText(thisNews.getCategoryName());
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
    	textNewsContent.setTextColor(getResources().getColor(R.color.news_detail_content));
    	textNewsContent.setTextSize(getResources().getDimension(R.dimen.text_size));
    	textNewsContent.setText(thisNews.getContent());
    	
    	if (thisNews.getPictures().size()>0){
    		
    		imageLoader = new ImageLoader2(PageNewsDetailActivity.this, 150);
    		
    		for(int i=0; i< thisNews.getPictures().size(); i++){
    			
    			ImageView iv = new ImageView(this);
    			newsDetailImages.addView(iv);
//    			LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(width/2, ViewGroup.LayoutParams.WRAP_CONTENT);
//    			LinearLayout.LayoutParams imageParams = (LinearLayout.LayoutParams)iv.getLayoutParams();
//    			imageParams.setMargins(5, 5, 5, 5);
//    			iv.setLayoutParams(imageParams);
    			imageLoader.DisplayImage(thisNews.getPictures().get(i).getUrl(), iv, width/5*3);		
    			
    			TextView textImage = new TextView(this);
    			newsDetailImages.addView(textImage);
    			LinearLayout.LayoutParams imageTextParams = (LinearLayout.LayoutParams)textImage.getLayoutParams();
    			imageTextParams.gravity = Gravity.CENTER;
    			imageTextParams.setMargins(5, 5, 5, 5);
    			textImage.setGravity(Gravity.CENTER);
    	    	textNewsContent.setLayoutParams(imageTextParams);
    	    	textImage.setTextSize(getResources().getDimension(R.dimen.text_size));
    	    	textImage.setTextColor(android.graphics.Color.GRAY);
    	    	textImage.setText(thisNews.getPictures().get(i).getIntro());
    				
    		}
    		
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
    
    @Override
    public void  onBackPressed  () {  
	    if(progressDialog.isShowing()){
	    	progressDialog.dismiss();
	    }else{
	    	finish();
	    }     
    }
    
    
}
