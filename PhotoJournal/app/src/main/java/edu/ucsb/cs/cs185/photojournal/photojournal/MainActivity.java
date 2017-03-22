package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
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
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import static edu.ucsb.cs.cs185.photojournal.photojournal.JournalManager.ITEMS;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int PICK_IMAGE_REQUEST = 9876;

    public static ArrayList<ImageManager> images = new ArrayList<>();
    public static boolean inListView = true;
    public static boolean loggedIn = false;

    public static ListFragment listFrag;
    public static CalendarFragment cFragment;
    public static boolean inDayView=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);


        if(!loggedIn) {
            loggedIn = true;
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(listFrag==null) {
            listFrag = new ListFragment();
        }
        if(cFragment==null){
            cFragment = new CalendarFragment();
        }
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, inListView?listFrag:cFragment).commit();

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
                ITEMS.add(new JournalManager.Journal(uri, image));
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

            if(listFrag==null) {
                listFrag = new ListFragment();
            }
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, listFrag).commit();
            //Log.d("LIST","going to list view");
        }
        else if (inListView && id == R.id.grid_view) {
            inListView = false;

            if(cFragment==null){
                cFragment = new CalendarFragment();
            }
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, cFragment).commit();
            TextView upload = (TextView)findViewById(R.id.upload);
            upload.setVisibility(View.INVISIBLE);
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

    @Override
    public void onBackPressed() {
        if (inDayView) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, cFragment).commit();
            inDayView=false;
        } else {
            super.onBackPressed();
        }
        
    }
}
