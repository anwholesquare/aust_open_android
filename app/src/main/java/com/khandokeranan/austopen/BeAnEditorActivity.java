package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BeAnEditorActivity extends AppCompatActivity {
    String deptVal, yearVal, semVal, secVal,finderVal;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_an_editor);
        user = FirebaseAuth.getInstance().getCurrentUser();





        TextView textView = (TextView) findViewById(R.id.username_text_view);
        textView.setText("Hello "+user.getDisplayName());


        CardView submissionCard = (CardView) findViewById(R.id.submissionButton);
submissionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.requestRoleText);
                String requestRole = editText.getText().toString();

                if(deptVal == null || yearVal == null || semVal == null || secVal == null || requestRole.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "We received your request. Get back to you soon!", Toast.LENGTH_SHORT).show();
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

        String[] group = new String[] {"1","2"};
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_items, group);
        AutoCompleteTextView groupText = (AutoCompleteTextView) findViewById(R.id.groupDropDown);
        groupText.setAdapter(groupAdapter);
        groupText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), groupText.getText().toString(), Toast.LENGTH_SHORT);
                finderVal = groupText.getText().toString();
            }

        });



    }
}