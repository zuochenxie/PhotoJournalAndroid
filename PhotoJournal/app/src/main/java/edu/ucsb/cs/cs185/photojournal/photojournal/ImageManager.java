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
    public Date date;

    public ImageManager(Uri uri, Bitmap bitmap){
        this.uri = uri;
        this.bitmap = bitmap;
        this.title = "The Time I Ate a Whole Goat";
        this.description = "There was a goat on my plate and I just ate the whole thing. I'm not sure if it was alive or not but you know it was a goat so I ate it because it was quite tasty. Yes goats are good food, yum, goats.\n Have you ever eaten a goat before? I'm not sure me neither. I know this photo says I ate a goat once, but that's a lie. I've never eaten a goat. I'm sorry to mislead you.";
        this.date = new Date();
    }
}
