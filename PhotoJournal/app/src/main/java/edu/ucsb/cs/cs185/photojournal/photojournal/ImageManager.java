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
        this.title = "Lorum Ipsum";
        this.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\nSed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.";
        this.location="Santa Barbara";
        this.date = new Date();
    }
}
