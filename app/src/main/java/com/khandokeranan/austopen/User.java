package com.khandokeranan.austopen;
public class User {
    public String email;
    public String last_active;
    public String created_date;

    public User() {
        // Empty constructor required for Firestore
    }

    public User(String email, String last_active, String created_date ) {
        this.email = email;
        this.last_active = last_active;
        this.created_date = created_date;
    }

    // Add getters and setters as needed
}
