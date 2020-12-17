package com.example.urbanmobility.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.urbanmobility.R;
import com.example.urbanmobility.databinding.ActivityMainBinding;
import com.example.urbanmobility.fragments.HomeFragment;
import com.example.urbanmobility.fragments.MapsFragment;
import com.example.urbanmobility.fragments.ResidencesFragment;
import com.example.urbanmobility.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = binding.bottomNavigation;

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.action_home:
                    selectedFragment = HomeFragment.newInstance();
                    break;
                case R.id.action_routes:
                    selectedFragment = ResidencesFragment.newInstance(getApplication());
                    break;
                case R.id.action_location:
                    selectedFragment = MapsFragment.newInstance();
                    break;
                case R.id.action_settings:
                    selectedFragment = SettingsFragment.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();
    }

    private void navigateToAddAddressActivity() {
        Intent addAddressActivityIntent = new Intent(this, AddAddressActivity.class);
        startActivity(addAddressActivityIntent);
    }
}
