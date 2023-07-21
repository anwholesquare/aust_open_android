package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class NoticeActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice1);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.noticeXBtn);
        linearLayout.setOnClickListener(v -> {
            Intent vc1 = new Intent(getApplicationContext(), NoticeActivity2.class);
            startActivity(vc1);
        });



    }
}