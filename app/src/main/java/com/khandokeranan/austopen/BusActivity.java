package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class BusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        CardView surmaBtn = (CardView) findViewById(R.id.surmaBtn);
        surmaBtn.setOnClickListener(v -> {
            String url = "https://m.me/j/AbY8Zdps6C0yu6Cc/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
        CardView jamunaBtn = (CardView) findViewById(R.id.jamunaBtn);
        jamunaBtn.setOnClickListener(v -> {
            String url = "https://www.messenger.com/t/4787755037945490";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
        CardView karnafuliBtn = (CardView) findViewById(R.id.kornofuliBtn);
        karnafuliBtn.setOnClickListener(v -> {
            String url = "https://www.messenger.com/t/2706778839410669";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });


    }
}