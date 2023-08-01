package com.khandokeranan.austopen;
import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.khandokeranan.austopen.Functions;

import java.util.ArrayList;
import java.util.List;

public class FirestoreHelper {
    private FirebaseFirestore db;
    private CollectionReference usersCollectionRef;

    public FirestoreHelper() {
        // Initialize Firestore instance
        db = FirebaseFirestore.getInstance();
        // Get a reference to the "users" collection
        usersCollectionRef = db.collection("users");
    }

    public void checkIfEmailExists(final String email, final OnEmailCheckListener listener) {
        usersCollectionRef = db.collection("users");
        usersCollectionRef.whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                // Email exists in Firestore
                                listener.onEmailExists(querySnapshot.getDocuments().get(0));
                            } else {
                                // Email does not exist in Firestore
                                listener.onEmailNotExists();
                            }
                        } else {
                            // Error occurred while querying Firestore
                            listener.onQueryError(task.getException());
                        }
                    }
                });
    }

    public void addUserWithEmail(String email) {
        // Add a new document to the "users" collection with the provided email
        String nowTime = Functions.getCurrentDateTimeString();
        usersCollectionRef.add(new com.khandokeranan.austopen.User(email,nowTime, nowTime ));
    }

    // Interface to handle email existence check results
    public interface OnEmailCheckListener {
        void onEmailExists(DocumentSnapshot document);
        void onEmailNotExists();
        void onQueryError(Exception e);
    }



}
