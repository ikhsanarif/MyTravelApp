package com.example.mytravelapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.mytravelapp.R;
import com.example.mytravelapp.fragments.HomeFragment;
import com.example.mytravelapp.fragments.MapFragment;
import com.example.mytravelapp.fragments.PhotosFragment;
import com.example.mytravelapp.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    FragmentManager fragmentManager;
    HomeFragment homeFragment;
    PhotosFragment photosFragment;
    MapFragment mapFragment;
    ProfileFragment profileFragment;
    BottomNavigationView bottomNavigationView;
    Fragment activeFragment;
    FloatingActionButton btnAddPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(MainActivity.this);

        btnAddPost = (FloatingActionButton) findViewById(R.id.btnAddPost);
        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddPostActivity.class));
            }
        });

        fragmentManager = getSupportFragmentManager();

        photosFragment = new PhotosFragment();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerMain, photosFragment)
                .hide(photosFragment)
                .commit();

        mapFragment = new MapFragment();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerMain, mapFragment)
                .hide(mapFragment)
                .commit();

        profileFragment = new ProfileFragment();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerMain, profileFragment)
                .hide(profileFragment)
                .commit();

        homeFragment = new HomeFragment();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerMain, homeFragment)
                .commit();

        activeFragment = homeFragment;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.home:
                fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
                activeFragment = homeFragment;
                break;

            case R.id.photos:
                fragmentManager.beginTransaction().hide(activeFragment).show(photosFragment).commit();
                activeFragment = photosFragment;
                break;

            case R.id.map:
                fragmentManager.beginTransaction().hide(activeFragment).show(mapFragment).commit();
                activeFragment = mapFragment;
                break;
            case  R.id.profile:
                fragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit();
                activeFragment = profileFragment;
                break;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}