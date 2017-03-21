package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by joesong on 3/18/17.
 */

import java.text.Format;
import java.text.SimpleDateFormat;

import static edu.ucsb.cs.cs185.photojournal.photojournal.MainActivity.images;

public class PhotoViewActivity extends AppCompatActivity{
    ImageView image;
    TextView title;
    TextView description;
    TextView date;
    TextView location;
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
        location = (TextView)findViewById(R.id.entryLocation);

        Bitmap img = images.get(imgIndex).bitmap;
        image.setImageBitmap(img);
        title.setText(images.get(imgIndex).title);
        description.setText(images.get(imgIndex).description);
        location.setText(images.get(imgIndex).location);

        Format formatter = new SimpleDateFormat("MM/dd/yyyy");
        String s = formatter.format(images.get(imgIndex).date);
        date.setText(s);

        ScrollView view = (ScrollView) findViewById(R.id.photo_view_scroll);
        view.setOnTouchListener(new OnSwipeTouchListener(PhotoViewActivity.this){
            public void onSwipeRight() {
                Toast.makeText(PhotoViewActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(PhotoViewActivity.this, "left", Toast.LENGTH_SHORT).show();
            }
        });
    }





}
