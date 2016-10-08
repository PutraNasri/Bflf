package com.example.kinket.bflf;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signin extends Activity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "EmailPassword";
    private EditText Email;
    private EditText Password;
    private TextView Nama;
    private TextView Email1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();
        Email = (EditText) findViewById(R.id.editemail);
        Password = (EditText) findViewById(R.id.editpass);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                  //  Nama.setText("");
                   // Email1.setText("");
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }

    public void daftar(View view) {
        Intent intent = new Intent(signin.this, signup.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void login(View view) {

        final String email = Email.getText().toString();
        final String password = Password.getText().toString();
        Nama = (TextView) findViewById(R.id.editnama);
        Email1 = (TextView) findViewById(R.id.email);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + email + task.isSuccessful());
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {


                            Intent intent = new Intent(signin.this, profil.class);
                            startActivity(intent);
                            finish();

                            // Name, email address, and profile photo Url
                           // String name = user.getDisplayName();
                           // String email = user.getEmail();
                           // String uid = user.getUid();
                            //Uri photoUrl = user.getPhotoUrl();

                            // The user's ID, unique to the Firebase project. Do NOT use this value to
                            // authenticate with your backend server, if you have one. Use
                            // FirebaseUser.getToken() instead.

                           // Nama.setText(name);
                          //  Email1.setText(email);



                        }


                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(signin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void out(View view) {
        FirebaseAuth.getInstance().signOut();
    }


}