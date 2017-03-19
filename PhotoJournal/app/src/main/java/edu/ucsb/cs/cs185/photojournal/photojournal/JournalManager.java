package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rexluan on 3/14/17.
 */
public class JournalManager {
    private static JournalManager ourInstance = new JournalManager();
    public static JournalManager getInstance() {
        return ourInstance;
    }

    public static final List<Journal> ITEMS = new ArrayList<Journal>();
    public static JournalAdapter adapter = new JournalAdapter();

    private JournalManager() {
    }

    public static void addItem(Journal item){
        ITEMS.add(item);
        adapter.notifyDataSetChanged();
    }

    public static void removeItem(Journal item){
        ITEMS.remove(item);
        adapter.notifyDataSetChanged();
    }

    public static class Journal{
        public Uri uri;
        public Bitmap image;
        public String caption;
        public Date date;

        public Journal(Uri uri, Bitmap _image){
            this.uri = uri;
            image = _image;
            caption = "";
            date = new Date();
        }
    }
}
