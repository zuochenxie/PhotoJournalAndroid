package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static edu.ucsb.cs.cs185.photojournal.photojournal.JournalManager.ITEMS;
import static edu.ucsb.cs.cs185.photojournal.photojournal.JournalManager.Journal;

public class PhotoEditorActivity extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST = 1;
    private Uri uri;
    private PhotoEditorActivity photoview = this;
    private Bitmap image = null;
    Bundle extras;
    public int imgIndex;
    EditText location;
    EditText caption;
    EditText title;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Upload a Photo");
        location = (EditText) findViewById(R.id.photo_location);
        caption = (EditText) findViewById(R.id.photo_caption);
        title = (EditText) findViewById(R.id.photo_title);
        imageView = (ImageView) findViewById(R.id.photo_uploader_btn);

        Calendar calMax = Calendar.getInstance();
        Calendar calMin = Calendar.getInstance();
        calMin.add(Calendar.YEAR,-17);

        long calMinMilli = calMin.getTimeInMillis();
        long calMaxMilli = calMax.getTimeInMillis();

        final DatePicker dp =  (DatePicker)this.findViewById(R.id.dp);
        dp.setMinDate(calMinMilli);
        dp.setMaxDate(calMaxMilli);

        ImageView import_photo = (ImageView) findViewById(R.id.photo_uploader_btn);
        Button upload_btn = (Button) findViewById(R.id.photo_upload_btn);

        extras = getIntent().getExtras();
        if (extras != null) {
            Date date_ = ITEMS.get(imgIndex).date;
            Calendar cal = Calendar.getInstance();
            cal.setTime(date_);

            imgIndex = extras.getInt("photo");
            image = ITEMS.get(imgIndex).bitmap;
            imageView.setImageBitmap(image);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            title.setText(ITEMS.get(imgIndex).title, TextView.BufferType.EDITABLE);
            location.setText(ITEMS.get(imgIndex).location, TextView.BufferType.EDITABLE);
            caption.setText(ITEMS.get(imgIndex).description, TextView.BufferType.EDITABLE);
            dp.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }


        import_photo.setOnClickListener(new View.OnClickListener() {
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

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day = dp.getDayOfMonth();
                int month = dp.getMonth();
                int year = dp.getYear();
                calendar.set(year, month, day);

                if(image!=null){
                    //Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    if(extras != null){
//                        ITEMS.remove(imgIndex);
                        ITEMS.get(imgIndex).bitmap = image;
                        ITEMS.get(imgIndex).title = title.getText().toString();
                        ITEMS.get(imgIndex).location = location.getText().toString();
                        ITEMS.get(imgIndex).description = caption.getText().toString();
                        calendar.set(year, month, day);
                        ITEMS.get(imgIndex).date = calendar.getTime();
                    }else {

                        Journal newImage = new Journal(uri, image);


                        newImage.location = location.getText().toString();
                        newImage.description = caption.getText().toString();
                        newImage.title = title.getText().toString();

                        newImage.date = calendar.getTime();

                        JournalManager.addItem(newImage);

                    }
//                    Intent intent = new Intent(photoview, MainActivity.class);
//                    startActivity(intent);

                    photoview.finish();

                } else{
                    Toast.makeText(photoview, "Please upload your image!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();

            try{
                image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(image);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
