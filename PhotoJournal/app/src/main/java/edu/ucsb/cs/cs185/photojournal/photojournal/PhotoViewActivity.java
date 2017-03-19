package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

/**
 * Created by joesong on 3/18/17.
 */

import static edu.ucsb.cs.cs185.photojournal.photojournal.MainActivity.images;

public class PhotoViewActivity extends AppCompatActivity{
    ImageView image;
    public int imgIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_view);

        imgIndex = getIntent().getExtras().getInt("INDEX");

        image = (ImageView)findViewById(R.id.activityImage);
        Bitmap img = images.get(imgIndex).bitmap;
        image.setImageBitmap(img);
    }

}
