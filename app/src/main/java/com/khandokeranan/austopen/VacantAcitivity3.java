package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VacantAcitivity3 extends AppCompatActivity {
    String htmlContent = "okay";
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacant_acitivity3);
        String department = "CSE";
        String room = getIntent().getStringExtra("room");
        url = "https://khandokeranan.com/aust/apis/vacancy.php?department="+ department + "&room_no=" + room;


        TextView roomNo = (TextView) findViewById(R.id.roomNo);
        roomNo.setText(room + " FREE TIME");
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    Response response = client.newCall(request).execute();
                    htmlContent = response.body().string();


                } catch (IOException e) {
                    System.out.println("error");
                    System.out.println(e.toString());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();

                        // Define the type of map using TypeToken
                        Type type = new TypeToken<Map<String, List<String>>>() {}.getType();

                        // Parse JSON into a Map
                        Map<String, List<String>> scheduleMap = gson.fromJson(htmlContent, type);

                        // Print the parsed schedule map
                        for (Map.Entry<String, List<String>> entry : scheduleMap.entrySet()) {
                            String day = entry.getKey();
                            List<String> timings = entry.getValue();
                            switch (day){
                                case "Sunday":
                                    FlexboxLayout sundayLayout = (FlexboxLayout) findViewById(R.id.sundayFlexBox);
                                    for (int i =0; i< timings.size(); i++) {
                                        View textView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.vacancy_textview, null);
                                        TextView timeText = textView.findViewById(R.id.timeText);
                                        timeText.setText(timings.get(i));
                                        sundayLayout.addView(textView);
                                    }
                                    break;
                                case "Monday":
                                    FlexboxLayout mondayLayout = (FlexboxLayout) findViewById(R.id.mondayFlexBox);
                                    for (int i =0; i< timings.size(); i++) {
                                        View textView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.vacancy_textview, null);
                                        TextView timeText = textView.findViewById(R.id.timeText);
                                        timeText.setText(timings.get(i));
                                        mondayLayout.addView(textView);
                                    }
                                    break;
                                case "Tuesday":
                                    FlexboxLayout tuesdayLayout = (FlexboxLayout) findViewById(R.id.tuesdayFlexBox);
                                    for (int i =0; i< timings.size(); i++) {
                                        View textView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.vacancy_textview, null);
                                        TextView timeText = textView.findViewById(R.id.timeText);

                                        timeText.setText(timings.get(i));
                                        tuesdayLayout.addView(textView);
                                    }
                                    break;
                                case "Wednesday":
                                    FlexboxLayout wednesdayLayout = (FlexboxLayout) findViewById(R.id.wednesdayFlexBox);
                                    for (int i =0; i< timings.size(); i++) {
                                        View textView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.vacancy_textview, null);
                                        TextView timeText = textView.findViewById(R.id.timeText);
                                        timeText.setText(timings.get(i));
                                        wednesdayLayout.addView(textView);
                                    }
                                    break;
                                case "Thursday":
                                FlexboxLayout thursdayLayout = (FlexboxLayout) findViewById(R.id.thursdayFlexBox);
                                for (int i =0; i< timings.size(); i++) {
                                    View textView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.vacancy_textview, null);
                                    TextView timeText = textView.findViewById(R.id.timeText);
                                    timeText.setText(timings.get(i));
                                    thursdayLayout.addView(textView);
                                }
                                break;



                            }
                            //System.out.println("Timings: " + timings);
                        }

                        //Log.d("3213132", "Contents" + htmlContent);
                    }
                });
            }
        }).start();

    }
}