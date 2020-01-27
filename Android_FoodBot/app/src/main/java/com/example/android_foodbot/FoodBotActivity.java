package com.example.android_foodbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FoodBotActivity extends AppCompatActivity {
    private WebView mywebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_bot);
        mywebview = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = mywebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mywebview.loadUrl("http://10.0.2.2:8000");
        mywebview.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed(){
        if(mywebview.canGoBack()) {
            mywebview.goBack();
        } else
        {
            super.onBackPressed();
        }

    }

}