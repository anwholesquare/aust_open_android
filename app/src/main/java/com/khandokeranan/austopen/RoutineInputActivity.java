package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class RoutineInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_input);

        WebView inputWebview = (WebView) findViewById(R.id.inputWebview);
        inputWebview.loadUrl(getIntent().getStringExtra("weblink"));
    }
}