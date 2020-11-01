package com.example.rentmanager.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rentmanager.R;
import com.example.rentmanager.database.FirebaseDatabase;
import com.example.rentmanager.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterActivity extends AppCompatActivity {
    public static String TAG = "REGISTER ACTIVITY";
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.registerButton.setOnClickListener(registerUser());
    }

    public View.OnClickListener registerUser() {
        return view -> {
            String username = binding.email.getText().toString();
            String password = binding.password.getText().toString();
            String reppassword = binding.password.getText().toString();
            if (!checkCredentials(username, password, reppassword)) return;
            FirebaseDatabase.getInstance().registerUser(username,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Register was successful!",Toast.LENGTH_LONG).show();
                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        finish();
                    },2000);
                } else {
                    handleFirebaseExceptions(task);
                }
            });
        };
    }

    private boolean checkCredentials(String username, String password, String reppassword) {
        if(username.isEmpty()) {
            binding.email.setError(getString(R.string.empty_email_error));
            return false;
        }
        if(password.isEmpty()) {
            binding.password.setError(getString(R.string.empty_password_error));
            return false;
        }
        if(reppassword.isEmpty()) {
            binding.reppassword.setError(getString(R.string.empty_password_error));
            return false;
        }
        if(!password.equals(reppassword)) {
            binding.password.setError(getString(R.string.passwords_no_match_error));
            binding.password.requestFocus();
            binding.reppassword.setError(getString(R.string.passwords_no_match_error));
            return false;
        }
        return true;
    }

    private void handleFirebaseExceptions(Task<AuthResult> task) {
        try {
            throw task.getException();
        } catch (FirebaseAuthWeakPasswordException e) {
            binding.password.setError(getString(R.string.weak_password_error));
            binding.password.requestFocus();
            binding.reppassword.setError(getString(R.string.weak_password_error));
        } catch (FirebaseAuthInvalidCredentialsException e) {
            binding.email.setError(getString(R.string.invalid_email_error));
            binding.email.requestFocus();
        } catch (FirebaseAuthUserCollisionException e) {
            binding.email.setError(getString(R.string.email_exists_error));
            binding.email.requestFocus();
        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
        }
    }


}