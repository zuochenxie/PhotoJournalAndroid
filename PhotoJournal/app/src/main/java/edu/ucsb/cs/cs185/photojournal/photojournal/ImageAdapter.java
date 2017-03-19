package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static edu.ucsb.cs.cs185.photojournal.photojournal.MainActivity.images;

/**
 * Created by joesong on 3/18/17.
 */

public class ImageAdapter extends ArrayAdapter<ImageManager>{
    private ArrayList<ImageManager> images;

    public ImageAdapter(Context context, ArrayList<ImageManager> images){
        super(context, 0, images);
        this.images = images;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageManager image = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_image, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.list_img);
        imageView.setImageBitmap(image.bitmap);

        TextView date = (TextView) convertView.findViewById(R.id.img_date);
        Format formatter = new SimpleDateFormat("MM/dd/yyyy");
        String s = formatter.format(image.date);
        date.setText(s);



        return convertView;
    }

    @Override
    public int getCount(){
        return images.size();
    }

    @Override
    public ImageManager getItem(int position){
        return images.get(position);
    }

    @Override
    public long getItemId(int position){
        return images.indexOf(getItem(position));
    }
}
