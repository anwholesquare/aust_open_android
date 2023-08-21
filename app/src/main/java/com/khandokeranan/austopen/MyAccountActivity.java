package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyAccountActivity extends AppCompatActivity {
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        user = FirebaseAuth.getInstance().getCurrentUser();

        TextView userName = (TextView) findViewById(R.id.username);
        userName.setText(user.getEmail());

        CardView signOutBtn = (CardView) findViewById(R.id.logoutBtn);
        signOutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent vc1 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(vc1);
        });

        CardView beAnEditorBtn = (CardView) findViewById(R.id.beEditorBtn);
        beAnEditorBtn.setOnClickListener(v -> {
            Intent vc1 = new Intent(getApplicationContext(), BeAnEditorActivity.class);
            startActivity(vc1);
        });

        CardView requestAnEditBtn = (CardView) findViewById(R.id.requestEditBtn);
        requestAnEditBtn.setOnClickListener(v -> {
            Intent vc1 = new Intent(getApplicationContext(), RequestEditActivity.class);
            startActivity(vc1);
        });




    }
}