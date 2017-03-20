package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;

public class PhotoEditorFragment extends Fragment {
    private int PICK_IMAGE_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_editor, container, false);

//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
//        appCompatActivity.setSupportActionBar(toolbar);
//
//        appCompatActivity.getActionBar().setHomeButtonEnabled(true);
//        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Button uploader = (Button) getView().findViewById(R.id.button5);
        uploader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });
    }

}
