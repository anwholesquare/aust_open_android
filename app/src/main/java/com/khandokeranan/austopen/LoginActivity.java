package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView guestLoginBtn = (TextView) findViewById(R.id.guestLoginBtn);
        guestLoginBtn.setOnClickListener(v -> {
            Intent vc1 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(vc1);
        });
    }
}