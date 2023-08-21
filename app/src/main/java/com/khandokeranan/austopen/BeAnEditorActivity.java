package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BeAnEditorActivity extends AppCompatActivity {
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_an_editor);
        user = FirebaseAuth.getInstance().getCurrentUser();


        TextView textView = (TextView) findViewById(R.id.username_text_view);
        textView.setText("Hello ");
    }
}