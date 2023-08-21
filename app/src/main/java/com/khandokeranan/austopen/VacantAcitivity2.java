package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class VacantAcitivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacant_acitivity2);

       // String[] floors = new String[] {"1ST FLOOR", "2ND FLOOR", "3RD FLOOR", "4TH FLOOR", "5TH FLOOR", "6TH FLOOR", "7TH FLOOR", "8TH FLOOR", "9TH FLOOR"};
       // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_items, floors);

        AutoCompleteTextView floorText = (AutoCompleteTextView) findViewById(R.id.FloorDropDown);
       // floorText.setAdapter(arrayAdapter);
        floorText.setText("CSE (7TH FLOOR)");
        floorText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), floorText.getText().toString(), Toast.LENGTH_SHORT);

            }

        });

        CardView R7A01Btn = (CardView) findViewById(R.id.R7A01Btn);
        CardView R7A02Btn = (CardView) findViewById(R.id.R7A02Btn);
        CardView R7A03Btn = (CardView) findViewById(R.id.R7A03Btn);
        CardView R7A04Btn = (CardView) findViewById(R.id.R7A04Btn);
        CardView R7A05Btn = (CardView) findViewById(R.id.R7A05Btn);
        CardView R7A06Btn = (CardView) findViewById(R.id.R7A06Btn);
        CardView R7A07Btn = (CardView) findViewById(R.id.R7A07Btn);
        CardView R7B01Btn = (CardView) findViewById(R.id.R7B01Btn);
        CardView R7B02Btn = (CardView) findViewById(R.id.R7B02Btn);
        CardView R7B03Btn = (CardView) findViewById(R.id.R7B03Btn);
        CardView R7B04Btn = (CardView) findViewById(R.id.R7B04Btn);
        CardView R7B05Btn = (CardView) findViewById(R.id.R7B05Btn);
        CardView R7B06Btn = (CardView) findViewById(R.id.R7B06Btn);
        CardView R7B07Btn = (CardView) findViewById(R.id.R7B07Btn);
        CardView R7C06Btn = (CardView) findViewById(R.id.R7C06Btn);
        CardView R7C07Btn = (CardView) findViewById(R.id.R7C07Btn);
        R7A01Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vc1 = new Intent(getApplicationContext(), VacantAcitivity3.class);
                startActivity(vc1);
            }
        });


     }
}