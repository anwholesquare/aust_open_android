package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;

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

        String[] floors = new String[] {"1ST FLOOR", "2ND FLOOR", "3RD FLOOR", "4TH FLOOR", "5TH FLOOR", "6TH FLOOR", "7TH FLOOR", "8TH FLOOR", "9TH FLOOR"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_items, floors);

        AutoCompleteTextView floorText = (AutoCompleteTextView) findViewById(R.id.FloorDropDown);
        floorText.setAdapter(arrayAdapter);
        floorText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), floorText.getText().toString(), Toast.LENGTH_SHORT);
            }
        });
     }
}