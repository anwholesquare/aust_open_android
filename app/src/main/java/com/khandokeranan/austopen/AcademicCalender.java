package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.jsoup.nodes.Document;


public class AcademicCalender extends AppCompatActivity {
    Document doc;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calender);
        webView = (WebView) findViewById(R.id.pdfWebView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        String link = "https://drive.google.com/file/d/1dbvyfOm5cT9ARHaoExbatT-DqCTcV5gJ/view";
        webView.loadUrl(link);
    }
}