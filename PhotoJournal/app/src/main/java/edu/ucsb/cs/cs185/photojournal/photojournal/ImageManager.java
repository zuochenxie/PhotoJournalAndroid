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
        this.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae egestas leo, quis vulputate leo. Etiam efficitur condimentum augue at rhoncus. Donec in suscipit urna. Cras sit amet ligula urna. Vivamus rutrum, erat sit amet tincidunt elementum, libero sapien fermentum elit, sit amet sagittis massa enim eu est. In hac habitasse platea dictumst. Etiam velit justo, posuere non volutpat in, posuere et ex. Curabitur quis consequat dui, et tristique orci.\n\nAenean lacinia egestas suscipit. Vivamus at sem eget odio hendrerit tristique congue ut erat. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Morbi luctus erat elit, nec congue odio tristique ut. Quisque at metus id urna cursus blandit in eu sapien. Sed hendrerit tincidunt ullamcorper. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.\n\nVestibulum sem ex, imperdiet sed dolor ut, congue lobortis mauris. Praesent non lorem commodo lacus tristique tincidunt ut at est. Quisque congue ultricies turpis quis dignissim. Mauris vulputate mauris eu tincidunt tempor. Vivamus blandit, turpis ut condimentum venenatis, ipsum urna pellentesque nibh, at facilisis elit risus a diam. Quisque commodo lectus in ipsum suscipit cursus. Suspendisse vehicula ipsum at fermentum fermentum. Nullam leo nibh, euismod sit amet dapibus at, posuere sit amet ex. Donec faucibus est magna, eu euismod lectus ullamcorper eu. Curabitur porttitor pulvinar est, quis ultricies elit fringilla eu. Fusce vel ullamcorper nisi. Quisque tempus elit id ante placerat semper. Nulla sit amet semper tellus. Donec lectus mi, luctus ac ligula et, pharetra ornare justo. Suspendisse tristique metus a euismod euismod.";
        this.location="Santa Barbara";
        this.date = new Date();
    }
}
