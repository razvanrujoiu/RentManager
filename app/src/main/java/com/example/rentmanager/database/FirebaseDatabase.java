package com.example.rentmanager.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class FirebaseDatabase {
    private static FirebaseDatabase instance;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private SharedPreferences sharedPreferences;

    private FirebaseDatabase() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
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

    public UploadTask updateProfilePhotoIntoStorogage(Bitmap profilePhoto) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference profilePhotoForUser = storageReference.child(user.getUid() + "/photo.jpg");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        profilePhoto.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();
        return profilePhotoForUser.putBytes(data);
    }

    public void downloadProfilePhotoFromStorage(ImageView imageView,Context context) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference profilePhotoForUser = storageReference.child(user.getUid() + "/photo.jpg");
        profilePhotoForUser.getDownloadUrl().addOnCompleteListener(task -> {
            try {
                Glide.with(context)
                        .load(task.getResult().toString())
                        .into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}
