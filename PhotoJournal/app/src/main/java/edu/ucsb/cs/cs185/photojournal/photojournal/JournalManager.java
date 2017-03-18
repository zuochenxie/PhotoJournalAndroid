package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by rexluan on 3/14/17.
 */
public class JournalManager {
    private static JournalManager ourInstance = new JournalManager();
    public static JournalManager getInstance() {
        return ourInstance;
    }

    public static final List<Journal> ITEMS = new ArrayList<Journal>();
    public static final Map<Date, ArrayList<Journal>> DATEMAP = new TreeMap<Date, ArrayList<Journal>>();
    public static JournalAdapter adapter = new JournalAdapter();
    public static ArrayList<ArrayList<Journal>> mList;

    private JournalManager() {
    }

    public static void addItem(Journal item){
        ITEMS.add(item);
        if(DATEMAP.get(item.date)==null){
            ArrayList<Journal> newDay = new ArrayList<Journal>();
            newDay.add(item);
            DATEMAP.put(item.date, newDay);
        }
        else {
            DATEMAP.get(item.date).add(item);
        }
        adapter.notifyDataSetChanged();
    }

    public static void removeItem(Journal item){
        ITEMS.remove(item);
        DATEMAP.get(item.date).remove(item);
        if(DATEMAP.get(item.date).size()==0){
            DATEMAP.remove(item.date);
        }
        adapter.notifyDataSetChanged();
    }

    public static ArrayList<ArrayList<Journal>> getJournalsInMonth(int year, int month){
        ArrayList<ArrayList<Journal>> dList = new ArrayList<ArrayList<Journal>>();
        for (Map.Entry<Date, ArrayList<Journal>> entry: DATEMAP.entrySet()) {
            Date myDate = entry.getKey();
            if(myDate.getYear()==year&&myDate.getMonth()==month){
                dList.add(entry.getValue());
            }
        }
        return dList;
    }

    public static class Journal{
        public Bitmap image;
        public String caption;
        public Date date;

        public Journal(Bitmap _image){
            image = _image;
            caption = "";
            date = new Date();
        }
        public Journal(Bitmap _image, String _caption, Date _date){
            image = _image;
            caption = _caption;
            date = _date;
        }


    }

}
