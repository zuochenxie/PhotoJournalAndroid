package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int PICK_IMAGE_REQUEST = 9876;

    public static ArrayList<ImageManager> images = new ArrayList<>();
    public static boolean inListView = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            /*
            public void onClick(View view) {
                Intent imgPickingIntent = new Intent();
                imgPickingIntent.setType("image/*");
                imgPickingIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(imgPickingIntent, PICK_IMAGE_REQUEST);

            }
        });

        CalendarFragment cFragment = new CalendarFragment();
        getFragmentManager().beginTransaction().replace(R.id.content_main, cFragment).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        try{
            Uri uri = data.getData();
            Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            JournalManager.Journal journal = new JournalManager.Journal(image);
            JournalManager.addItem(journal);
            //Log.d("MainActivity", "onActivityResult: image selected");
        } catch (Exception e){
            Log.e("onActivityResult", e.toString());
        }
        */

            public void onClick(View view){
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment listFrag = new ListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, listFrag).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        if (requestCode == PICK_IMAGE_REQUEST) {
            // came back from second activity
            Uri uri = intent.getData();
            try{
                Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                images.add(new ImageManager(uri, image));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.photo_editor) {
            Intent intent = new Intent(this, PhotoEditorActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (!inListView && id == R.id.list_view) {
            inListView = true;
            /*
            if(listFragment==null){
                listFragment = new ListFragment();
            }
            getFragmentManager().beginTransaction().replace(R.id.viewContainer, listFragment).commit();
            */
            //Log.d("LIST","going to list view");
        }
        else if (inListView && id == R.id.grid_view) {
            inListView = false;
            /*
            if(gridFragment==null){
                gridFragment = new GridFragment();
            }
            getFragmentManager().beginTransaction().replace(R.id.viewContainer, gridFragment).commit();
            */
            //Log.d("GRID","going to grid view");
        }
        else if (id==R.id.logout){
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
