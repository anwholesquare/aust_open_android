package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.net.Uri;


public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        CardView teacherBtn = (CardView) findViewById(R.id.teacherInfoBtn);
        teacherBtn.setOnClickListener(v -> {
            Intent vc1 = new Intent(getApplicationContext(), TeacherInfoActivity.class);
            startActivity(vc1);
        });

        CardView academicBtn = (CardView) findViewById(R.id.AUSTCalenderBtn);
        academicBtn.setOnClickListener(v -> {
            Intent vc1 = new Intent(getApplicationContext(), AcademicCalender.class);
            startActivity(vc1);
        });

        CardView researchBtn = (CardView) findViewById(R.id.AUSTResearchBtn);
        researchBtn.setOnClickListener(v -> {
            String url = "https://aust.edu/research_and_publications";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);

        });

        CardView facebookBtn = (CardView) findViewById(R.id.facebookPageBtn);
        facebookBtn.setOnClickListener(v -> {
            String url = "https://www.facebook.com/AUST.BD/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);

        });

        CardView eduRankBtn = (CardView) findViewById(R.id.eduRankBtn);
        eduRankBtn.setOnClickListener(v -> {
            //Launch a webLink to the eduRank website
            String url = "https://edurank.org/uni/ahsanullah-university-of-science-and-technology/rankings/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });



    }
}