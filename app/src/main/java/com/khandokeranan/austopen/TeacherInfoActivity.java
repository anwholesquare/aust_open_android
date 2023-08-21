package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
public class TeacherInfoActivity extends AppCompatActivity {
    String departmentName = "CSE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        String[] department = new String[] {"CSE", "EEE", "ME", "Arch", "TEX", "IPE"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_items, department);

        AutoCompleteTextView deptText = (AutoCompleteTextView) findViewById(R.id.DepartmentDropDown);
        deptText.setAdapter(arrayAdapter);
        deptText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), deptText.getText().toString(), Toast.LENGTH_SHORT);
                departmentName = deptText.getText().toString().toLowerCase();
            }

        });
        LinearLayout teacherList = (LinearLayout) findViewById(R.id.teacherInfoLayout);
        Query query = FirebaseFirestore.getInstance().collection("teacher");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        String teacherName = document.getString("name");
                        String teacherDesignation = document.getString("designation");
                        String phone = document.getString("phone");





                        View teacherCard = LayoutInflater.from(getApplicationContext()).inflate(R.layout.widget_teacher_info, null);
                        TextView name = (TextView) teacherCard.findViewById(R.id.teacher_name);
                        TextView designation = (TextView) teacherCard.findViewById(R.id.teacher_designation);
                        ImageView callBtn = (ImageView) teacherCard.findViewById(R.id.teacher_call_btn);
                        callBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                String uri = "tel:" + phone.trim();
                                intent.setData(Uri.parse(uri));
                                startActivity(intent);
                            }
                        });


                        name.setText(teacherName);
                        designation.setText(teacherDesignation);
                        teacherList.addView(teacherCard);
                    }
                }
            }
        });
        CardView checkBtn = (CardView) findViewById(R.id.checkButton);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherList.removeAllViews();
                Query query = FirebaseFirestore.getInstance().collection("teacher").whereEqualTo("department", departmentName);
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d("habib", "task is successful");
                        if(task.isSuccessful()){
                            Log.d("habib", "task is successful");
                            for(QueryDocumentSnapshot document : task.getResult()){
                                String teacherName = document.getString("name");
                                String teacherDesignation = document.getString("designation");
                                String phone = document.getString("phone");
                                Log.d("habib", teacherName + " " + teacherDesignation + " " + phone);
                                View teacherCard = LayoutInflater.from(getApplicationContext()).inflate(R.layout.widget_teacher_info, null);
                                TextView name = (TextView) teacherCard.findViewById(R.id.teacher_name);
                                TextView designation = (TextView) teacherCard.findViewById(R.id.teacher_designation);
                                ImageView callBtn = (ImageView) teacherCard.findViewById(R.id.teacher_call_btn);
                                callBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(Intent.ACTION_DIAL);
                                        String uri = "tel:" + phone.trim();
                                        intent.setData(Uri.parse(uri));
                                        startActivity(intent);
                                    }
                                });
                                name.setText(teacherName);
                                designation.setText(teacherDesignation);
                                teacherList.addView(teacherCard);
                            }
                        }
                    }
                });
            }
        });

    }

}