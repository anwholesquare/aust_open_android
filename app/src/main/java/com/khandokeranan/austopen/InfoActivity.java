package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
            //String link = sharedPreferences.getString("notice_link", "https://drive.google.com/file/d/1dbvyfOm5cT9ARHaoExbatT-DqCTcV5gJ/view");
            Log.d("habib", "researchBtn clicked");

        });

        CardView facebookBtn = (CardView) findViewById(R.id.facebookPageBtn);
        facebookBtn.setOnClickListener(v -> {

        });

        CardView eduRankBtn = (CardView) findViewById(R.id.eduRankBtn);
        eduRankBtn.setOnClickListener(v -> {

        });



    }
}