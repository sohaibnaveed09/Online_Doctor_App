package com.example.onlinedoctor;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Doctor_DashBoard extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    NavigationView side_bar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_doctor_dash_board);

        side_bar=findViewById(R.id.side_nav_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.Drawerable);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        side_bar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.profile_side:
                        drawerLayout.closeDrawers();
                        Toast.makeText(Doctor_DashBoard.this, "Profile of Doctor", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.settings_side:
                        drawerLayout.closeDrawers();
                        Toast.makeText(Doctor_DashBoard.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Signout_side:
                        drawerLayout.closeDrawers();
                        SharedPreferences myPrefs = getSharedPreferences("MY", MODE_PRIVATE);
                        SharedPreferences.Editor editor = myPrefs.edit();
                        editor.clear();
                        editor.commit();
                        AppState.getSingleInstance().setLoggingOut(true);
                        Log.d(TAG, "Now log out and start the activity login");
                        Intent intent = new Intent(Doctor_DashBoard.this, LoginActivity.class);
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