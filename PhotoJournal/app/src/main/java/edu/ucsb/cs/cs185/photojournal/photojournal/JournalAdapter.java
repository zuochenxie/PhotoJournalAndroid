package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rexluan on 3/14/17.
 */

public class JournalAdapter extends BaseAdapter {
    public Context mContext;
    private ArrayList<ArrayList<JournalManager.Journal>> jArray;

    public JournalAdapter(Context context){
        mContext = context;

    }

    @Override
    public int getCount() {
        return JournalManager.dateMap.size();
    }


    @Override
    public Object getItem(int position) {
        jArray = new ArrayList<ArrayList<JournalManager.Journal>>(JournalManager.dateMap.values());
        return jArray.get(position).get(0);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false);
        }

        JournalManager.Journal item = (JournalManager.Journal)getItem(position);
        ImageView thumbnail = (ImageView)convertView.findViewById(R.id.thumbnail);
        if (thumbnail != null) {
            thumbnail.setImageBitmap(item.bitmap);
        }
        TextView imgDate = (TextView)convertView.findViewById(R.id.image_date);
        if (imgDate!= null) {
            imgDate.setText(item.date.getDate()+"");
            imgDate.setAlpha(1f);
        }

        return convertView;
    }
}
