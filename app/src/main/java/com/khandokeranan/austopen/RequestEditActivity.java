package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

public class RequestEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_edit);

        CardView signInBtn = (CardView) findViewById(R.id.signInButton);
        signInBtn.setOnClickListener(v -> {
        });



    }
}