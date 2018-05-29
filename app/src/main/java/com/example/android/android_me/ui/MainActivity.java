/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity
        implements MasterListFragment.OnImageClickListener, View.OnClickListener{

    // Variables to store the values for the list index of the selected images
    // The default value will be index = 0
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    // COMPLETED (3) Create a variable to track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mIsTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // COMPLETED (4) If you are making a two-pane display, add new BodyPartFragments to create an initial Android-Me image
        // Also, for the two-pane display, get rid of the "Next" button in the master list fragment
        if(findViewById(R.id.android_me_linear_layout) != null){
            //When it is a Two pane layout, the linear layout for the BodyPartFragments will be present
            mIsTwoPane = true;

            //Hiding the Next Button as it is not required
            findViewById(R.id.next_button).setVisibility(View.GONE);

            //Setting the number of columns in a Grid to 2 columns
            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            // Only create new fragments when there is no previously saved state
            if(savedInstanceState == null){

                // Create a new Head BodyPartFragment
                BodyPartFragment headFragment = new BodyPartFragment();
                // Set the list of image id's for the head fragment
                headFragment.setImageIds(AndroidImageAssets.getHeads());
                // Set the position to the selected index in the list
                headFragment.setListIndex(headIndex);

                // Create a new Body - BodyPartFragment
                BodyPartFragment bodyFragment = new BodyPartFragment();
                // Set the list of image id's for the body fragment
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                // Set the position to the selected index in the list
                bodyFragment.setListIndex(bodyIndex);

                // Create a new Leg - BodyPartFragment
                BodyPartFragment legFragment = new BodyPartFragment();
                // Set the list of image id's for the leg fragment
                legFragment.setImageIds(AndroidImageAssets.getLegs());
                // Set the position to the selected index in the list
                legFragment.setListIndex(legIndex);

                // Add the fragments to its respective container using a FragmentManager and a Transaction
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .add(R.id.body_container, bodyFragment)
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }

        } else {
            //It is a single pane layout when the linear layout for the BodyPartFragments is absent
            mIsTwoPane = false;

            //Attach the OnClickListener for the Next Button
            findViewById(R.id.next_button).setOnClickListener(this);
        }
    }

    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        // COMPLETED (5) Handle the two-pane case and replace existing fragments right when a new image is selected from the master list
        // The two-pane case will not need a Bundle or Intent since a new activity will not be started;
        // This is all happening in this MainActivity and one fragment will be replaced at a time


        // Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments

        // bodyPartNumber will be = 0 for the head fragment, 1 for the body, and 2 for the leg fragment
        // Dividing by 12 gives us these integer values because each list of images resources has a size of 12
        int bodyPartNumber = position /12;

        // Store the correct list index no matter where in the image list has been clicked
        // This ensures that the index will always be a value between 0-11
        int listIndex = position - 12*bodyPartNumber;

        // Retrieving the FragmentManager to find the Fragments for Two Pane layout
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        // Set the currently displayed item for the correct body part fragment
        switch(bodyPartNumber) {
            case 0:
                headIndex = listIndex;
                if(mIsTwoPane){
                    BodyPartFragment headFragment = (BodyPartFragment) supportFragmentManager.findFragmentById(R.id.head_container);
                    headFragment.showListIndex(headIndex);
                }
                break;
            case 1:
                bodyIndex = listIndex;
                if(mIsTwoPane){
                    BodyPartFragment bodyFragment = (BodyPartFragment) supportFragmentManager.findFragmentById(R.id.body_container);
                    bodyFragment.showListIndex(bodyIndex);
                }
                break;
            case 2:
                legIndex = listIndex;
                if(mIsTwoPane){
                    BodyPartFragment legFragment = (BodyPartFragment) supportFragmentManager.findFragmentById(R.id.leg_container);
                    legFragment.showListIndex(legIndex);
                }
                break;
            default: break;
        }

    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.next_button){
            //When the Next Button is clicked

            // Put the image index information in a Bundle and
            // attach it to an Intent that will launch an AndroidMeActivity
            Bundle b = new Bundle();
            b.putInt("headIndex", headIndex);
            b.putInt("bodyIndex", bodyIndex);
            b.putInt("legIndex", legIndex);

            // Attach the Bundle to an intent
            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(b);

            // Launch a new AndroidMeActivity
            startActivity(intent);
        }
    }

}
