package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.content.Intent;
import android.view.View;

public class VacantAcitivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacant_acitivity1);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.f9thBtn);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vc2 = new Intent(getApplicationContext(), VacantAcitivity2.class);
                startActivity(vc2);
            }
        });
    }
}