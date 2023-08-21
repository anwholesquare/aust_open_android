package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import org.w3c.dom.Text;

public class RoutineActivity1 extends AppCompatActivity {
    String deptVal, yearVal, semVal, secVal,finderVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine1);

        CardView routineCard = (CardView) findViewById(R.id.checkButton);
        routineCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vc1 = new Intent(getApplicationContext(), RoutineActivity2.class);
                if(deptVal == null || yearVal == null || semVal == null || secVal == null){
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                finderVal = deptVal + yearVal + semVal + secVal;
                finderVal = finderVal.toLowerCase();
                Log.d("habib", "onClick: " + finderVal);
                vc1.putExtra("finderVal", finderVal);
                vc1.putExtra("deptVal", deptVal);
                vc1.putExtra("yearVal", yearVal);
                vc1.putExtra("semVal", semVal);
                vc1.putExtra("secVal", secVal);
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
                deptVal = deptText.getText().toString();
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
                yearVal = yearText.getText().toString();
                yearVal = yearVal.substring(0,1);

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
                semVal = semText.getText().toString();
                semVal = semVal.substring(0,1);
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
                secVal = secText.getText().toString();
            }

        });

        LinearLayout subsLayout = (LinearLayout)  findViewById(R.id.subsLayout);
        TextView subtext = (TextView) findViewById(R.id.subsText);
        CardView unsubBtn = (CardView) findViewById(R.id.unsubBtn);
        SharedPreferences sharedPreferences = getSharedPreferences("AUSTOPEN", Context.MODE_PRIVATE);
        String deptSub = sharedPreferences.getString("deptVal", "-1");
        String yearSub = sharedPreferences.getString("yearVal", "-1");
        String semesterSub = sharedPreferences.getString("semVal", "-1");
        String sectionSub = sharedPreferences.getString("secVal", "-1");
        if (deptSub == "-1") {
            subsLayout.removeAllViews();
        }else {
            subtext.setText("SUBSCRIBED ROUTINE: " + deptSub + " " + yearSub + "." + semesterSub + " " + sectionSub );
            unsubBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subsLayout.removeAllViews();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("deptVal", "-1");
                    editor.putString("yearVal", "-1");
                    editor.putString("semVal",  "-1");
                    editor.putString("secVal",  "-1");
                    editor.apply();
                }
            });
        }


    }

}