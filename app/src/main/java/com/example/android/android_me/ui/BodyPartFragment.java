package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Fragment to display the body part of Android-Me
 *
 * @author Kaushik N Sanji
 */
public class BodyPartFragment extends Fragment {
    // COMPLETED (2) Create a new class called BodyPartFragment to display an image of an Android-Me body part
    // In this class, you'll need to implement an empty constructor and the onCreateView method

    /**
     * Default Constructor of the Fragment to instantiate {@link BodyPartFragment}
     */
    public BodyPartFragment() {
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflating the Android-Me Fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        //Finding the ImageView
        ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        // COMPLETED (3) Show the first image in the list of head images
        // Soon, you'll update this image display code to show any image you want

        //Setting the Image to the first asset in Head images list
        imageView.setImageResource(AndroidImageAssets.getHeads().get(0));

        //Returning the prepared layout
        return rootView;
    }

}
