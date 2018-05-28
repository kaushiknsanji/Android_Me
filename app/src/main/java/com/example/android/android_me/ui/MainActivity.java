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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity
        implements MasterListFragment.OnImageClickListener{

    //Index of the Images of each body part being clicked/selected
    //Default value will be 0
    private int selectedHeadIndex;
    private int selectedBodyIndex;
    private int selectedLegIndex;

    //Total images per part
    private static final int TOTAL_IMAGES_PER_PART = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        // COMPLETED (2) Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments

        //Since the number of images per part is 12, and we have three such sets of images
        //dividing by this number will result in 0 for head part; 1 - body part; 2 - leg part
        int bodyPartIndex = position/TOTAL_IMAGES_PER_PART;

        //Evaluating the subset index of the image being clicked
        int subsetIndex = position - TOTAL_IMAGES_PER_PART * bodyPartIndex;

        switch (bodyPartIndex){
            case 0:
                //For the head part
                selectedHeadIndex = subsetIndex;
                break;
            case 1:
                //For the body part
                selectedBodyIndex = subsetIndex;
                break;
            case 2:
                //For the leg part
                selectedLegIndex = subsetIndex;
                break;
        }

        // COMPLETED (3) Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
        Bundle bundle = new Bundle(3);
        bundle.putInt(AndroidMeActivity.BUNDLE_HEAD_INDEX, selectedHeadIndex);
        bundle.putInt(AndroidMeActivity.BUNDLE_BODY_INDEX, selectedBodyIndex);
        bundle.putInt(AndroidMeActivity.BUNDLE_LEG_INDEX, selectedLegIndex);

        // COMPLETED (4) Get a reference to the "Next" button and launch the intent when this button is clicked
        final Intent secondActivityIntent = new Intent(this, AndroidMeActivity.class);
        secondActivityIntent.putExtras(bundle);

        //'Next' Button launches a new AndroidMeActivity
        Button nextButton = (Button) findViewById(R.id.button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(secondActivityIntent);
            }
        });
    }

}
