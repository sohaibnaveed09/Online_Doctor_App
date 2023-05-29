package com.example.onlinedoctor;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    NavigationView side_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);

        bottomNavigationView=findViewById(R.id.bottom_nav_view);
        side_bar=findViewById(R.id.side_nav_view);

        drawerLayout=(DrawerLayout) findViewById(R.id.Drawerable);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView,new HomeFragment());
        fragmentTransaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.homeFragment:
                        fragmentTransaction.replace(R.id.fragmentContainerView,new HomeFragment());
                        break;
                    case R.id.addFragment:
                        fragmentTransaction.replace(R.id.fragmentContainerView,new AddFragment());
                        break;
                    case R.id.profileFragment:
                        fragmentTransaction.replace(R.id.fragmentContainerView,new ProfileFragment());
                        break;
                }
                fragmentTransaction.commit();
                return true;

            }
        });

        side_bar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.profile_side:
                        drawerLayout.closeDrawers();
                        fragmentTransaction.replace(R.id.fragmentContainerView,new ProfileFragment());
                        fragmentTransaction.commit();
                        break;
                    case R.id.settings_side:
                        drawerLayout.closeDrawers();
                        Toast.makeText(HomePage.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Signout_side:
                        drawerLayout.closeDrawers();
                        SharedPreferences myPrefs = getSharedPreferences("MY", MODE_PRIVATE);
                        SharedPreferences.Editor editor = myPrefs.edit();
                        editor.clear();
                        editor.commit();
                        AppState.getSingleInstance().setLoggingOut(true);
                        Log.d(TAG, "Now log out and start the activity login");
                        Intent intent = new Intent(HomePage.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                }

                return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(toggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}