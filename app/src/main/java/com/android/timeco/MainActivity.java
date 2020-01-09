package com.android.timeco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;

import com.android.timeco.Model.User;
import com.android.timeco.View.LoginFragment;
import com.android.timeco.View.WebFragment;

/**
 * Main Activity where the fragments are drawn
 */
public class MainActivity extends AppCompatActivity {

    final String FILE_NAME = "Users.bin";
    FragmentManager fm;
    AccessData accessData;
    static public User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accessData = AccessData.get();
        accessData.initializeFile(this, FILE_NAME);

        // Draw the first fragment
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.MainActivity, new LoginFragment()).commit();
    }
}
