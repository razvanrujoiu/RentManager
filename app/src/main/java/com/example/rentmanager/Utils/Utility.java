package com.example.rentmanager.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.Context.MODE_PRIVATE;

public class Utility {

    public static String hashPassword(String password) {
        String passwordHash = "";
        try {
            byte[] passwordByteArr = password.getBytes(StandardCharsets.UTF_8);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] passwordHashByteArr = messageDigest.digest(passwordByteArr);
            passwordHash = Base64.encodeToString(passwordHashByteArr, 0);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return passwordHash;
    }


    public static void storeUserIdToSharedPreferences(Context context, long userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("userId", userId);
        editor.apply();
    }
}
