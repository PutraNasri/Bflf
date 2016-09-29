package com.example.kinket.bflf;

/**
 * Created by addd on 9/29/2016.
 */

public class user {
    public String username;
    public String email;
    public String uid;

    public user() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public user(String username, String email, String userId) {
        this.username = username;
        this.email = email;
        this.uid=userId;
    }

}
