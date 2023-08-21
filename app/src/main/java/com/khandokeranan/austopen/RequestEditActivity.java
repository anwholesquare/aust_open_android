package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class RequestEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_edit);

        CardView signInBtn = (CardView) findViewById(R.id.signInButton);
        signInBtn.setOnClickListener(v -> {
            EditText username = (EditText) findViewById(R.id.usernameEditText);
            EditText password = (EditText) findViewById(R.id.passwordEditText);
            String usernameStr = username.getText().toString();
            String passwordStr = password.getText().toString();
            if(usernameStr.isEmpty()||passwordStr.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please enter your username and password",Toast.LENGTH_SHORT).show();
            }
            else{
                String url = "https://khandokeranan.com/aust/login.php?user="+usernameStr+"&pass="+passwordStr;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}