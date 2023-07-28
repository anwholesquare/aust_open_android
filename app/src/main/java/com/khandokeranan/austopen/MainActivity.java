package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = FirebaseAuth.getInstance().getCurrentUser();

        // User is logged in already
        if(user != null){
            TextView userName = (TextView) findViewById(R.id.username);
            userName.setText("HEY " + user.getDisplayName());
        }

        //
        TextView signOutBtn = (TextView) findViewById(R.id.signoutBtn);
        signOutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent vc1 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(vc1);
        });

        CardView vacantBtn = (CardView) findViewById(R.id.vacantBtn);
        vacantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vc1 = new Intent(getApplicationContext(), VacantAcitivity1.class);
                startActivity(vc1);
            }
        });


        CardView noticeBtn = (CardView) findViewById(R.id.noticeBtn);
        noticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vc1 = new Intent(getApplicationContext(), NoticeActivity1.class);
                startActivity(vc1);
            }
        });


        CardView infoBtn = (CardView) findViewById(R.id.infoBtn);
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vc1 = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(vc1);
            }
        });



        CardView routineBtn = (CardView) findViewById(R.id.routineBtn);
        routineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vc1 = new Intent(getApplicationContext(), RoutineActivity1.class);
                startActivity(vc1);
            }
        });



        CardView busBtn = (CardView) findViewById(R.id.busBtn);
        busBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vc1 = new Intent(getApplicationContext(), BusActivity.class);
                startActivity(vc1);
            }
        });



        CardView accBtn = (CardView) findViewById(R.id.accBtn);
        accBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vc1 = new Intent(getApplicationContext(), VacantAcitivity1.class);
                startActivity(vc1);
            }
        });
    }
}