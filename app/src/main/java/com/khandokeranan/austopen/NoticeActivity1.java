package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.annotations.concurrent.Background;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Future;

public class NoticeActivity1 extends AppCompatActivity {

    LinearLayout noticeLinear;
    private List<Notice> noticeList = new ArrayList<>();
    Document doc = null;

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice1);

        Log.d("anan-log", "START NOTICING");
        System.out.println("noticing");

        noticeLinear = (LinearLayout) findViewById(R.id.noticeLinearLayout);

//        String url = "https://www.aust.edu/notice";
//
//        Future<String> future = executorService.submit(() -> {
//            //Document doc = Jsoup.parse("<html><head> <title> hello </title> </head></html>");
//            Document doc = Jsoup.connect(url).get();
//            return doc.title();
//        });
//
//        String noticeText = null;
//        try {
//            noticeText = future.get();
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(noticeText);







        getHtmlFromWeb();
        //fetchDataFromUrl("https://www.aust.edu/notice/");
        //new FetchDataFromUrlTask().execute("https://www.aust.edu/notice");
    }

    @Background
    private void getHtmlFromWeb() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder stringBuilder = new StringBuilder();
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.aust.edu/notice")
                            .build();

                    // Make the HTTPS request
                    Response response = client.newCall(request).execute();
                    String htmlContent = response.body().string();
                    //System.out.println("Contents: ");
                    //System.out.println(htmlContent);
                    doc = Jsoup.parse(htmlContent);





                } catch (IOException e) {
                    System.out.println("error");
                    System.out.println(e.toString());
                }
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        int numNotices = doc.getElementsByClass("col-9 notice_text").size();
                        for (int idx = 0; idx < numNotices; idx++) {

                            Element titleElement = doc.getElementsByClass("col-9 notice_text").get(idx).getElementsByTag("a").first().getElementsByTag("h6").first();
                            Element descriptionElement = doc.getElementsByClass("col-9 notice_text").get(idx).getElementsByTag("p").first();
                            Element dayElement = doc.getElementsByClass("notice_date").get(idx).getElementsByTag("p").get(1);
                            Element monthElement = doc.getElementsByClass("notice_date").get(idx).getElementsByTag("p").first();
                            Element yearElement = doc.getElementsByClass("notice_date").get(idx).getElementsByTag("p").get(2);
                            Element linkElement = doc.getElementsByClass("col-9 notice_text").get(idx).getElementsByTag("a").first();
                          //  Element pdfElement = doc.getElementsByTag("iframe").first();


                            Notice notice = new Notice();
                            notice.setTitle(titleElement.text());
                            notice.setDescription(descriptionElement.text());
                            notice.setDay(dayElement.text());
                            notice.setMonth(monthElement.text());
                            notice.setYear(yearElement.text());
                            notice.setLink(linkElement.attr("href"));
                           // notice.setPdf(pdfElement.absUrl("src"));

                            View cardViewLayout = LayoutInflater.from(getApplicationContext()).inflate(R.layout.widget_notice1, null);
                            TextView noticeDate = cardViewLayout.findViewById(R.id.noticeDate);
                            TextView noticeTitle = cardViewLayout.findViewById(R.id.noticeTitle);
                            TextView noticeSubTitle = cardViewLayout.findViewById(R.id.noticeSubTitle);
                            LinearLayout noticeBtn = cardViewLayout.findViewById(R.id.noticeBtn);

                            noticeDate.setText(notice.day.trim() + "\n" + notice.month.trim());
                            noticeTitle.setText(notice.title);
                            noticeSubTitle.setText(notice.description);

                            noticeBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SharedPreferences sharedPreferences = getSharedPreferences("AUSTOPEN", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("notice_title", notice.title);
                                    editor.putString("notice_link", notice.link);
                                    editor.putString("notice_date", "Posted on " + notice.day + "/" + notice.month  + "/" + notice.year);
                                    editor.apply();
                                    Intent intent = new Intent(getApplicationContext(), NoticeActivity2.class);
                                    startActivity(intent);
                                }
                            });

                            noticeLinear.addView(cardViewLayout);
                        }


                    }
                });
            }
        }).start();
    }


    public void fetchDataFromUrl(String url) {
        try {
            Document doc = Jsoup.connect(url).followRedirects(true).get();

            Log.d("anan-log", doc.title());
            int numNotices = doc.getElementsByClass("col-9 notice_text").size();
            Log.d("anan-log", "NUM SIZE: " );
            for (int idx = 0; idx < numNotices; idx++) {
                Element titleElement = doc.getElementsByClass("col-9 notice_text").get(idx).getElementsByTag("a").first().getElementsByTag("h6").first();
                Element descriptionElement = doc.getElementsByClass("col-9 notice_text").get(idx).getElementsByTag("p").first();
                Element dayElement = doc.getElementsByClass("notice_date").get(idx).getElementsByTag("p").get(1);
                Element monthElement = doc.getElementsByClass("notice_date").get(idx).getElementsByTag("p").first();
                Element yearElement = doc.getElementsByClass("notice_date").get(idx).getElementsByTag("p").get(2);
                Element linkElement = doc.getElementsByClass("col-9 notice_text").get(idx).getElementsByTag("a").first();
                //Element pdfElement = doc.getElementsByTag("iframe").first();

                Notice notice = new Notice();
                notice.setTitle(titleElement.text());
                notice.setDescription(descriptionElement.text());
                notice.setDay(dayElement.text());
                notice.setMonth(monthElement.text());
                notice.setYear(yearElement.text());
                notice.setLink(linkElement.absUrl("href"));
                //notice.setPdf(pdfElement.absUrl("src"));

                View cardViewLayout = LayoutInflater.from(getApplicationContext()).inflate(R.layout.widget_notice1, null);
                TextView noticeDate = cardViewLayout.findViewById(R.id.noticeDate);
                TextView noticeTitle = cardViewLayout.findViewById(R.id.noticeTitle);
                TextView noticeSubTitle = cardViewLayout.findViewById(R.id.noticeSubTitle);
                LinearLayout noticeBtn = cardViewLayout.findViewById(R.id.noticeBtn);

                noticeDate.setText(notice.day + "\n" + notice.month);
                noticeTitle.setText(notice.title);
                noticeSubTitle.setText(notice.description);
                noticeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(notice.link));
                        startActivity(intent);
                    }
                });

                noticeLinear.addView(cardViewLayout);


                Log.d("anan-log", notice.toString());
            }


        } catch (Exception e) {
            Log.d("anan-log", e.toString());
            e.printStackTrace();
        }
    }


}