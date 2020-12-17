package com.example.urbanmobility.database.Firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.urbanmobility.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    public UploadTask updateProfilePhotoIntoStorage(Bitmap profilePhoto) {
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
        try {
            StorageReference storageReference = firebaseStorage.getReference();
            StorageReference profilePhotoForUser = storageReference.child(user.getUid() + "/photo.jpg");
            GlideApp.with(context)
                    .load(profilePhotoForUser)
                    .placeholder(R.drawable.person)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
