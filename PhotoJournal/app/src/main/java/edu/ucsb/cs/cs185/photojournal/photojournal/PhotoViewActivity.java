package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by joesong on 3/18/17.
 */

import static edu.ucsb.cs.cs185.photojournal.photojournal.MainActivity.images;

public class PhotoViewActivity extends AppCompatActivity{
    ImageView image;
    TextView title;
    TextView description;
    TextView date;
    public int imgIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_view);

        imgIndex = getIntent().getExtras().getInt("INDEX");

        image = (ImageView)findViewById(R.id.activityImage);
        title = (TextView)findViewById(R.id.entryTitle);
        description = (TextView)findViewById(R.id.entryDescription);
        date = (TextView)findViewById(R.id.entryDate);

        Bitmap img = images.get(imgIndex).bitmap;
        image.setImageBitmap(img);
        title.setText(images.get(imgIndex).title);
        description.setText(images.get(imgIndex).description);

    }

}
