package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
    Button edit;
    Button delete;
    public int imgIndex;
    Journal journal;
    private PhotoViewActivity photoview = this;

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
        edit = (Button)findViewById(R.id.edit_btn);
        delete = (Button)findViewById(R.id.delete_btn);

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

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(photoview, PhotoEditorActivity.class);
                intent.putExtra("photo", imgIndex);
                startActivity(intent);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ITEMS.remove(imgIndex);

                photoview.finish();
            }
        });



    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        Bitmap img = journal.bitmap;
        image.setImageBitmap(img);
        title.setText(journal.title);
        description.setText(journal.description);
        location.setText(journal.location);

        Format formatter = new SimpleDateFormat("MM/dd/yyyy");
        String s = formatter.format(journal.date);
        date.setText(s);
    }

}
