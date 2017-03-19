package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

/**
 * Created by joesong on 3/18/17.
 */

public class PhotoView extends AppCompatActivity{
    ImageView image;
    public int imgIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_view);
    }

}
