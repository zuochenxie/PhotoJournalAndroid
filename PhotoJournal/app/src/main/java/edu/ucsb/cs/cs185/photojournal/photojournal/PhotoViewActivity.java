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

import static edu.ucsb.cs.cs185.photojournal.photojournal.JournalManager.ITEMS;
import static edu.ucsb.cs.cs185.photojournal.photojournal.JournalManager.Journal;

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
        boolean d = getIntent().getExtras().getBoolean("date");
        int pos = getIntent().getExtras().getInt("pos");


        image = (ImageView)findViewById(R.id.activityImage);
        title = (TextView)findViewById(R.id.entryTitle);
        description = (TextView)findViewById(R.id.entryDescription);
        date = (TextView)findViewById(R.id.entryDate);
        location = (TextView)findViewById(R.id.entryLocation);

        Journal journal;
        if(d){
            journal=JournalManager.dateMap.get(DayFragment.today).get(pos);
        }
        else{
            journal=ITEMS.get(imgIndex);
        }
        Bitmap img = journal.bitmap;
        image.setImageBitmap(img);
        title.setText(journal.title);
        description.setText(journal.description);
        location.setText(journal.location);

        Format formatter = new SimpleDateFormat("MM/dd/yyyy");
        String s = formatter.format(journal.date);
        date.setText(s);

        ScrollView view = (ScrollView) findViewById(R.id.photo_view_scroll);
        view.setOnTouchListener(new OnSwipeTouchListener(PhotoViewActivity.this){
            public void onSwipeRight() {
                if(imgIndex < ITEMS.size()-1) {
                    imgIndex += 1;
                    Bitmap img = ITEMS.get(imgIndex).bitmap;
                    image.setImageBitmap(img);
                    title.setText(ITEMS.get(imgIndex).title);
                    description.setText(ITEMS.get(imgIndex).description);
                    location.setText(ITEMS.get(imgIndex).location);

                    Format formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String s = formatter.format(ITEMS.get(imgIndex).date);
                    date.setText(s);
                }
            }
            public void onSwipeLeft() {
                if(imgIndex>0) {
                    imgIndex -= 1;
                    Bitmap img = ITEMS.get(imgIndex).bitmap;
                    image.setImageBitmap(img);
                    title.setText(ITEMS.get(imgIndex).title);
                    description.setText(ITEMS.get(imgIndex).description);
                    location.setText(ITEMS.get(imgIndex).location);

                    Format formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String s = formatter.format(ITEMS.get(imgIndex).date);
                    date.setText(s);
                }
            }
        });
    }





}
