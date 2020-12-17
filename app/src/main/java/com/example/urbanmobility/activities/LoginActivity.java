package com.example.urbanmobility.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.urbanmobility.R;
import com.example.urbanmobility.Utils.Utility;
import com.example.urbanmobility.database.DatabaseClient;
import com.example.urbanmobility.database.Firebase.FirebaseDatabase;
import com.example.urbanmobility.databinding.ActivityLoginBinding;
import com.example.urbanmobility.models.User;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {
    public static String TAG = "LOGIN ACTIVITY";
    ActivityLoginBinding binding;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
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
                    GetUserFromDatabaseByEmail getUserFromDatabaseByEmail = new GetUserFromDatabaseByEmail();
                    getUserFromDatabaseByEmail.execute(email, password);
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


    class GetUserFromDatabaseByEmail extends AsyncTask<String,Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            User databaseResult = DatabaseClient.getInstance(getApplicationContext())
                    .getRentManagerDatabase()
                    .userDao()
                    .getUserByMail(strings[0]);
            if (databaseResult == null) {
                User user = new User();
                user.setEmailAddress(strings[0]);
                user.setUserPassword(Utility.hashPassword(strings[1]));
                long userId = DatabaseClient.getInstance(getApplicationContext())
                        .getRentManagerDatabase()
                        .userDao()
                        .insertUser(user);
                Utility.storeUserIdToSharedPreferences(getApplicationContext(), userId);

            } else {
                Utility.storeUserIdToSharedPreferences(getApplicationContext(), databaseResult.getUserId());
            }
            return null;
        }
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