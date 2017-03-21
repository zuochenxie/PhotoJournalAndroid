package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.graphics.Bitmap;
import android.net.Uri;

import java.util.Date;

/**
 * Created by joesong on 3/18/17.
 */

public class ImageManager {
    public Uri uri;
    public Bitmap bitmap;
    public String title;
    public String description;
    public String location;
    public Date date;

    public ImageManager(Uri uri, Bitmap bitmap){
        this.uri = uri;
        this.bitmap = bitmap;

    }

    public void setInfo(String title, String description, String location, Date date){
        this.title = title;
        this.description = description;
        this.location= location;
        this.date = date;
    }
}
