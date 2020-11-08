package com.example.rentmanager.database;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

public class FirebaseDatabase {
    private static FirebaseDatabase instance;
    private FirebaseAuth firebaseAuth;
    private SharedPreferences sharedPreferences;

    private FirebaseDatabase() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseDatabase getInstance() {
        if(instance == null)
            instance = new FirebaseDatabase();
        return instance;
    }

    public Task<AuthResult> registerUser(String username, String password) {
        return firebaseAuth.createUserWithEmailAndPassword(username,password);
    }

    public Task<AuthResult> signInUser(String username, String password) {
        return firebaseAuth.signInWithEmailAndPassword(username,password);
    }

    public boolean checkIfUserIsSignedIn() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null) {
            return  true;
        }
        return false;
    }

    public void logoutUser() {
        firebaseAuth.signOut();
    }

    public Task<Void> changeUserEmail(String newEmail, String password) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        return user.updateEmail(newEmail);
    }

    public Task<Void> changeUserPassword(String password, String email) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        return user.updatePassword(password);
    }

}
