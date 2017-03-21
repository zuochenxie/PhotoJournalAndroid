package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import static edu.ucsb.cs.cs185.photojournal.photojournal.JournalManager.ITEMS;
import static edu.ucsb.cs.cs185.photojournal.photojournal.JournalManager.Journal;

/**
 * Created by joesong on 3/18/17.
 */

public class ListFragment extends Fragment{
    public static ImageAdapter adapter;

    public ListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        final ListView imageView = (ListView) rootView.findViewById(R.id.image_list);
        adapter = new ImageAdapter(getActivity().getApplicationContext(), ITEMS);
        imageView.setAdapter(adapter);

        imageView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x, long y){
                Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("INDEX", x);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
