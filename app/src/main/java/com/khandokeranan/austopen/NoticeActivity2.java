package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;


public class NoticeActivity2 extends AppCompatActivity {
    Document doc;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice2);

        webView = (WebView) findViewById(R.id.pdfWebView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());


        SharedPreferences sharedPreferences = getSharedPreferences("AUSTOPEN", Context.MODE_PRIVATE);
        String title = sharedPreferences.getString("notice_title", "");
        String link = sharedPreferences.getString("notice_link", "");
        String date = sharedPreferences.getString("notice_date", "");

        TextView TitleText = (TextView) findViewById(R.id.noticeTitle);
        TextView DateText = (TextView) findViewById(R.id.noticeDate);

        TitleText.setText(title);
        DateText.setText(date);


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                     doc = Jsoup.connect(link).followRedirects(true).get();
                } catch (IOException e) {
                    //TODO: NO INTERNET PAGE
                }

                Element pdfElement = doc.getElementsByTag("iframe").first();
                //Log.d("anan-log", pdfElement.attr("src"));

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        webView.loadUrl( pdfElement.attr("src"));
                    }
                });


            }


        }).start();







    }


}