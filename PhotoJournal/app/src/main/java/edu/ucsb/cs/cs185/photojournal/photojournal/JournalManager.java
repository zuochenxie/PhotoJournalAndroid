package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.graphics.Bitmap;
import android.util.Log;

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
    //public static final Map<Date, ArrayList<Journal>> DATEMAP = new TreeMap<Date, ArrayList<Journal>>(); // month journals
    public static JournalAdapter jAdapter;
    public static DayAdapter dAdapter;
    public static TreeMap<Integer, ArrayList<Journal>> dateMap;

    private JournalManager() {
    }

    public static void addItem(Journal item) {
        ITEMS.add(item);

        Date date = item.date;
        if (date.getYear() == CalendarFragment.currentYear && date.getMonth() == CalendarFragment.currentMonth) {
            if (dateMap.get(date.getDate()) == null) {
                ArrayList<Journal> list = new ArrayList<Journal>();
                list.add(item);
                dateMap.put(date.getDate(), list);
                jAdapter.notifyDataSetChanged();
            } else {
                dateMap.get(date.getDate()).add(item);
                if (dAdapter != null && date.getDate() == DayFragment.today) {
                    dAdapter.notifyDataSetChanged();
                }
            }
        }
    }
    public static void removeItem(Journal item){
        ITEMS.remove(item);
        if(item.date.getYear()==CalendarFragment.currentYear&&item.date.getMonth()==CalendarFragment.currentMonth) {
            int date = item.date.getDate();
            dateMap.get(date).remove(item);
            if(dateMap.get(date).size()==0){
                dateMap.remove(date);
            }
            jAdapter.notifyDataSetChanged();
            if(dAdapter!=null&&item.date.getDate()==DayFragment.today) {
                dAdapter.notifyDataSetChanged();
            }
        }
    }

    public static TreeMap<Integer,ArrayList<Journal>> getJournalsInMonth(int year, int month){
        TreeMap<Integer, ArrayList<Journal>> dMap = new TreeMap<Integer, ArrayList<Journal>>();
        for(int i=0;i<ITEMS.size();i++){
            Date date = ITEMS.get(i).date;
            if(date.getYear()==CalendarFragment.currentYear&&date.getMonth()==CalendarFragment.currentMonth){
                if(dMap.get(date.getDate())==null){
                    ArrayList<Journal> list = new ArrayList<Journal>();
                    list.add(ITEMS.get(i));
                    dMap.put(date.getDate(),list);
                }
                else{
                    dMap.get(date.getDate()).add(ITEMS.get((i)));
                }
            }
        }
        return dMap;
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
        public Journal(Bitmap _image, String _caption, int y, int m, int d){
            image = _image;
            caption = _caption;
            date = new Date(y,m,d);
        }

    }

}
