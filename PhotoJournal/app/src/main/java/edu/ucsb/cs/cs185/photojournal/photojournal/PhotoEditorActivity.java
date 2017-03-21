package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.ImageView;

import java.io.IOException;
import java.util.Calendar;

public class PhotoEditorActivity extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST = 1;
    private Uri uri;
//    BitMapManager images = new BitMapManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Upload a Photo");

        Calendar calMax = Calendar.getInstance();
        long calMaxMilli = calMax.getTimeInMillis();
        final DatePicker dp =  (DatePicker)this.findViewById(R.id.dp);
        dp.setMaxDate(calMaxMilli);

        Button import_photo = (Button) findViewById(R.id.photo_uploader_btn);
        Button upload_btn = (Button) findViewById(R.id.photo_upload_btn);

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

//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                  images.addImage(bitmap);


//                fragment = new ListFragment();
//                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
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
            Button imageView = (Button) findViewById(R.id.photo_uploader_btn);
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                BitmapDrawable bdrawable = new BitmapDrawable(getResources(),bitmap);

                imageView.setBackground(bdrawable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
