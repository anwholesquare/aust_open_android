package com.khandokeranan.austopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.khandokeranan.austopen.databinding.ActivityRoutine2Binding;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class RoutineActivity2 extends AppCompatActivity {
    ActivityRoutine2Binding binding;
    //retrive image from firebase
    private ImageView imageView;
    private ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix = new Matrix();
    private float scaleFactor = 1.0f;
    private float lastTouchX;
    private float lastTouchY;
    private float posX = 0;
    private float posY = 0;
    private boolean isDragging = false;

    private TextView textView;
    private StorageReference storageReference;


    String finderVal;
    String deptVal, yearVal, semVal, secVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve data from RoutineActivity1.java
        finderVal = getIntent().getStringExtra("finderVal");
        deptVal = getIntent().getStringExtra("deptVal");
        yearVal = getIntent().getStringExtra("yearVal");
        semVal = getIntent().getStringExtra("semVal");
        secVal = getIntent().getStringExtra("secVal");
        binding = ActivityRoutine2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.routineTitle.setText(deptVal + " " + yearVal + "." + semVal + " " + secVal+" Section's" + " Routine");

        imageView = findViewById(R.id.routineImage);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastTouchX = event.getX();
                        lastTouchY = event.getY();
                        isDragging = false;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (scaleFactor > 1.0f) {
                            float deltaX = event.getX() - lastTouchX;
                            float deltaY = event.getY() - lastTouchY;

                            if (!isDragging) {
                                isDragging = true;
                            } else {
                                posX += deltaX;
                                posY += deltaY;

                                matrix.postTranslate(deltaX, deltaY);
                                imageView.setImageMatrix(matrix);
                            }

                            lastTouchX = event.getX();
                            lastTouchY = event.getY();
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        isDragging = false;
                        break;
                }

                return true;
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference("routine_images/" + finderVal + ".png");
        Log.d("habib", "onCreate: " + storageReference);
        try {
            File localFile = File.createTempFile("images", "png");
            storageReference.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                binding.routineImage.setImageBitmap(bitmap);
                binding.routineImage.setRotation(90);
            }).addOnFailureListener(exception -> {
                binding.warningText.setText("No Routine Found. Please contact with your class representative.");
                Toast.makeText(this,"Routine Not Found", Toast.LENGTH_LONG);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish(); // This will close the current activity and return to the previous activity
                    }
                }, 5000);


            });
        } catch (IOException e) {
            Log.d("habib", "onCreateFail: " + e.getMessage());
        }


        CardView subscribeButton = (CardView) findViewById(R.id.subscribeButton);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deptVal = getIntent().getStringExtra("deptVal");
                yearVal = getIntent().getStringExtra("yearVal");
                semVal = getIntent().getStringExtra("semVal");
                secVal = getIntent().getStringExtra("secVal");

                SharedPreferences sharedPreferences = getSharedPreferences("AUSTOPEN", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("deptVal", deptVal);
                editor.putString("yearVal", yearVal);
                editor.putString("semVal",  semVal);
                editor.putString("secVal",  secVal);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), RoutineActivity1.class);
                startActivity(intent);
            }
        });
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));

            matrix.setScale(scaleFactor, scaleFactor);
            imageView.setImageMatrix(matrix);
            return true;
        }
    }

}