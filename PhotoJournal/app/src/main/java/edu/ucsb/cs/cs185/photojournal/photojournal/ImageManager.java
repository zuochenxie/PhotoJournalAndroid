package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by joesong on 3/18/17.
 */

public class ImageManager {
    public Uri uri;
    public Bitmap bitmap;

    public ImageManager(Uri uri, Bitmap bitmap){
        this.uri = uri;
        this.bitmap = bitmap;
    }
}
