package com.example.android.android_me.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;

/**
 * Activity responsible for displaying a master list of all the images
 *
 * @author Kaushik N Sanji
 */
public class MainActivity extends AppCompatActivity {
    // COMPLETED (4) Create a new Activity named MainActivity and a corresponding layout file that displays a MasterListFragment
    // Remember, to display a static fragment in a layout file, use the <fragment> tag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
