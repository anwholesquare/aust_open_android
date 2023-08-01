package com.khandokeranan.austopen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private ImageView googleLoginBtn;

    private GoogleSignInClient clint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        googleLoginBtn = (ImageView) findViewById(R.id.googleLoginBtn);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) //get the default web client id
                .requestEmail()
                .build();
        clint = GoogleSignIn.getClient(this, options);
        googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = clint.getSignInIntent();
//                startActivityForResult(i, 1234);
                signOutAndStartGoogleSignIn();
            }
        });


        TextView guestLoginBtn = (TextView) findViewById(R.id.guestLoginBtn);
        guestLoginBtn.setOnClickListener(v -> {
            Intent vc1 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(vc1);
        });
    }
    //Google sign in with @aust.edu
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1234){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class); //get the account
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null); //get the credential
                FirebaseAuth.getInstance().signInWithCredential(credential) //sign in with credential
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //if sign in is successful
                                if(task.isSuccessful()){
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    String email = user.getEmail();
                                    if(email.contains("@aust.edu")){FirestoreHelper firestoreHelper = new FirestoreHelper();
                                        firestoreHelper.checkIfEmailExists(email, new FirestoreHelper.OnEmailCheckListener() {
                                            @Override
                                            public void onEmailExists(DocumentSnapshot document) {
                                                Intent vc1 = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(vc1);
                                            }
                                            @Override
                                            public void onEmailNotExists() {
                                                firestoreHelper.addUserWithEmail(email);
                                                Intent vc1 = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(vc1);
                                            }
                                            @Override
                                            public void onQueryError(Exception e) {
                                                Intent vc1 = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(vc1);
                                            }
                                        });


                                    }
                                    else{
                                        Toast.makeText(LoginActivity.this, "Please use your aust email\nOr Login as a Guest", Toast.LENGTH_SHORT).show();
                                        FirebaseAuth.getInstance().signOut();
                                    }
                                }
                                else{
                                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            catch (Exception e){
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Intent vc1 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(vc1);
        }
    }


    private void signOutAndStartGoogleSignIn() {
        clint.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // After signing out, revoke access to clear the Google Sign-In cache
                        clint.revokeAccess()
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        // Start Google Sign-In flow
                                        Intent i = clint.getSignInIntent();
                                        startActivityForResult(i, 1234);
                                    }
                                });
                    }
                });
    }
}