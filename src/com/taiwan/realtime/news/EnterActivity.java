package com.taiwan.realtime.news;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class EnterActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_enter);
        
        new Handler().postDelayed(new Runnable (){
        	
   	     @Override
   	     public void run() {
   	    	 Intent new_intent = new Intent(EnterActivity.this,TabActivity.class);
   	    	 startActivity(new_intent);
   	         finish();            
   	     }}, 5000);
   	}
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}

