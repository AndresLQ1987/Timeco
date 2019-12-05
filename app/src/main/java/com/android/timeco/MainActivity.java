package com.android.timeco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.android.timeco.View.LoginFragment;

/**
 * Main Activity where the fragments are drawn
 */
public class MainActivity extends AppCompatActivity {

    FragmentManager fm;
    AccessData accessData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Draw the first fragment
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.MainActivity, new LoginFragment()).commit();
    }
}
