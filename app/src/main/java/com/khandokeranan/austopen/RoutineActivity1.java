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

public class RoutineActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine1);

        CardView routineCard = (CardView) findViewById(R.id.checkButton);
        routineCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vc1 = new Intent(getApplicationContext(), RoutineActivity2.class);
                startActivity(vc1);
            }
        });


        String[] department = new String[] {"CSE", "EEE", "ME", "Arch", "TEX", "IPE"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_items, department);

        AutoCompleteTextView deptText = (AutoCompleteTextView) findViewById(R.id.DepartmentDropDown);
        deptText.setAdapter(arrayAdapter);
        deptText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), deptText.getText().toString(), Toast.LENGTH_SHORT);

            }

        });

        String[] year = new String[] {"1st", "2nd", "3rd", "4th"};
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_items, year);

        AutoCompleteTextView yearText = (AutoCompleteTextView) findViewById(R.id.yearDropDown);
        yearText.setAdapter(yearAdapter);
        yearText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), yearText.getText().toString(), Toast.LENGTH_SHORT);

            }

        });

        String[] semester = new String[] {"1st","2nd"};
        ArrayAdapter<String> semAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_items, semester);

        AutoCompleteTextView semText = (AutoCompleteTextView) findViewById(R.id.semesterDropDown);
        semText.setAdapter(semAdapter);
        semText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), semText.getText().toString(), Toast.LENGTH_SHORT);

            }

        });
        String[] section = new String[] {"A","B","C","D"};
        ArrayAdapter<String> secAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_items, section);

        AutoCompleteTextView secText = (AutoCompleteTextView) findViewById(R.id.sectionDropDown);
        secText.setAdapter(secAdapter);
        secText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), secText.getText().toString(), Toast.LENGTH_SHORT);

            }

        });
        String[] group = new String[] {"1", "2"};
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_items, group);

        AutoCompleteTextView groupText = (AutoCompleteTextView) findViewById(R.id.groupDropDown);
        groupText.setAdapter(groupAdapter);
        groupText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), groupText.getText().toString(), Toast.LENGTH_SHORT);

            }

        });
    }

}