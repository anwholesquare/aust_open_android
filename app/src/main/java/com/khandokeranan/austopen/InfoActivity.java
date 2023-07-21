package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

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

    }
}