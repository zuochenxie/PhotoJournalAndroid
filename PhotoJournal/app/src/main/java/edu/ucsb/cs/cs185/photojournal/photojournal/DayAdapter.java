package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rexluan on 3/19/17.
 */

public class DayAdapter extends BaseAdapter {
    public Context mContext;
    public int mDate;
    public DayAdapter(Context context, int date) {
        mContext=context;
        mDate=date;
    }

    @Override
    public int getCount() {
        return JournalManager.dateMap.get(mDate).size();
    }

    @Override
    public Object getItem(int position) {
        return JournalManager.dateMap.get(mDate).get(position).bitmap;
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
        ImageView thumbnail = (ImageView)convertView.findViewById(R.id.thumbnail);
        if (thumbnail != null) {
            thumbnail.setImageBitmap((Bitmap)getItem(position));
        }
        TextView imgDate = (TextView)convertView.findViewById(R.id.image_date);
        if (imgDate!= null) {
            imgDate.setText("");
            imgDate.setAlpha(0f);
        }

        return convertView;
    }
}
