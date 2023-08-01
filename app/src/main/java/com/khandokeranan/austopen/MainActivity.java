package com.khandokeranan.austopen;

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
            userName.setText("HEY " + user.getDisplayName().toUpperCase());
        }else {
            Intent vc1 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(vc1);
        }

        //
        TextView signOutBtn = (TextView) findViewById(R.id.signoutBtn);
        signOutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent vc1 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(vc1);
        });


        LinearLayout linearLayout = findViewById(R.id.featuredLinearLayout);
        Query query = FirebaseFirestore.getInstance().collection("featured").orderBy("timestamp", Query.Direction.DESCENDING).limit(10);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String imageURL = document.getString("image");
                        String link = document.getString("link");
                        String title = document.getString("title");

                        View cardViewLayout = LayoutInflater.from(getApplicationContext()).inflate(R.layout.widget_featured_feed, null);
                        ImageView featuredImgView = cardViewLayout.findViewById(R.id.featured_feed_image);
                        featuredImgView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                                startActivity(intent);
                            }
                        });

                        Glide.with(getApplicationContext())
                                .load(imageURL)
                                .apply(new RequestOptions().placeholder(R.drawable.routine))
                                .into(featuredImgView);

                        TextView featuredTextView = cardViewLayout.findViewById(R.id.featured_feed_text);
                        featuredTextView.setText(title);
                        linearLayout.addView(cardViewLayout);
                    }
                } else {
                    Log.w("ananlog", "Error getting documents.", task.getException());
                }
            }
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