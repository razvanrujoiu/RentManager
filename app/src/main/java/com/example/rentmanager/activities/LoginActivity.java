package com.example.rentmanager.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.rentmanager.R;
import com.example.rentmanager.database.FirebaseDatabase;
import com.example.rentmanager.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {
    public static String TAG = "LOGIN ACTIVITY";
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(FirebaseDatabase.getInstance().checkIfUserIsSignedIn()) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        binding.registerTextView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
            startActivity(intent);
        });
        binding.loginButton.setOnClickListener(signInUser());

    }



    private View.OnClickListener signInUser() {
        return view -> {
            String email = binding.emailLogin.getText().toString();
            String password = binding.passwordLogin.getText().toString();
            if(!checkCredentials(email,password)) return;
            FirebaseDatabase.getInstance().signInUser(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        binding.emailLogin.setError(getString(R.string.bad_credentials_error));
                        binding.passwordLogin.setError(getString(R.string.bad_credentials_error));
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        binding.emailLogin.setError(getString(R.string.bad_credentials_error));
                        binding.passwordLogin.setError(getString(R.string.bad_credentials_error));
                    }
                    catch (Exception e) {
                        Log.e(TAG,e.getMessage());
                    }
                }
            });
        };
    }

    private boolean checkCredentials(String username, String password) {
        if(username.isEmpty()) {
            binding.emailLogin.setError(getString(R.string.empty_email_error));
            return false;
        }
        if(password.isEmpty()) {
            binding.passwordLogin.setError(getString(R.string.empty_password_error));
            return false;
        }
        return true;
    }
}