package edu.ucsb.cs.cs185.photojournal.photojournal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public DayAdapter dAdapter;
    public static int today=(new Date()).getDate();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DayFragment() {
        // Required empty public constructor
    }
    public DayFragment(int d){
        today = d;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DayFragment newInstance(String param1, String param2) {
        DayFragment fragment = new DayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_day, container, false);
        dAdapter = new DayAdapter(getContext());
        JournalManager.dAdapter = dAdapter;
        final TextView textView = (TextView)rootView.findViewById(R.id.title_day);
        textView.setText(CalendarFragment.months[CalendarFragment.currentMonth] + " " + today + ", " + (CalendarFragment.currentYear+1900));
        TextView left = (TextView)rootView.findViewById(R.id.leftArrow2);
        left.setText("<");
        TextView right = (TextView)rootView.findViewById(R.id.rightArrow2);
        right.setText(">");
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> dList = new ArrayList<Integer>(JournalManager.dateMap.keySet());
                Log.d("leftClick",dList.toString());
                int idx=dList.indexOf(today);
                //Log.d("leftClick",""+idx);

                if(idx>0){
                    today=dList.get(idx-1);
                    textView.setText(CalendarFragment.months[CalendarFragment.currentMonth] + " " + today + ", " + (CalendarFragment.currentYear+1900));
                    dAdapter.notifyDataSetChanged();
                }
                else{
                    ArrayList<Date> monthList = new ArrayList<Date>(JournalManager.monthSet);
                    Date nowMonth = new Date(CalendarFragment.currentYear, CalendarFragment.currentMonth, 1);
                    int idx2=-1;
                    for(int i=0;i<monthList.size();i++){
                        if(monthList.get(i).getYear()==CalendarFragment.currentYear&&monthList.get(i).getMonth()==CalendarFragment.currentMonth){
                            idx2=i;
                            break;
                        }
                    }
                    if(idx2>0){
                        Date lastMonth = monthList.get(idx2-1);
                        CalendarFragment.currentYear=lastMonth.getYear();
                        CalendarFragment.currentMonth=lastMonth.getMonth();
                        JournalManager.dateMap=JournalManager.getJournalsInMonth(CalendarFragment.currentYear,CalendarFragment.currentMonth);
                        dList = new ArrayList<Integer>(JournalManager.dateMap.keySet());
                        today = dList.get(dList.size()-1);
                        textView.setText(CalendarFragment.months[CalendarFragment.currentMonth] + " " + today + ", " + (CalendarFragment.currentYear+1900));
                        JournalManager.jAdapter.notifyDataSetChanged();
                        dAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> dList = new ArrayList<Integer>(JournalManager.dateMap.keySet());
                Log.d("rightClick",dList.toString());
                int idx=dList.indexOf(today);
                //Log.d("rightClick",""+idx);

                if(idx>-1&&idx<dList.size()-1){
                    today=dList.get(idx+1);
                    textView.setText(CalendarFragment.months[CalendarFragment.currentMonth] + " " + today + ", " + (CalendarFragment.currentYear+1900));
                    dAdapter.notifyDataSetChanged();
                }
                else{
                    ArrayList<Date> monthList = new ArrayList<Date>(JournalManager.monthSet);
                    Date nowMonth = new Date(CalendarFragment.currentYear, CalendarFragment.currentMonth, 1);
                    int idx2=monthList.size();
                    for(int i=0;i<monthList.size();i++){
                        if(monthList.get(i).getYear()==CalendarFragment.currentYear&&monthList.get(i).getMonth()==CalendarFragment.currentMonth){
                            idx2=i;
                            break;
                        }
                    }
                    if(idx2<monthList.size()-1){
                        Date nextMonth = monthList.get(idx2+1);
                        CalendarFragment.currentYear= nextMonth.getYear();
                        CalendarFragment.currentMonth= nextMonth.getMonth();
                        JournalManager.dateMap=JournalManager.getJournalsInMonth(CalendarFragment.currentYear,CalendarFragment.currentMonth);
                        dList = new ArrayList<Integer>(JournalManager.dateMap.keySet());
                        today = dList.get(0);
                        textView.setText(CalendarFragment.months[CalendarFragment.currentMonth] + " " + today + ", " + (CalendarFragment.currentYear+1900));
                        JournalManager.jAdapter.notifyDataSetChanged();
                        dAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        GridView gridView = (GridView)rootView.findViewById(R.id.gridView2);
        gridView.setAdapter(dAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // go to imageViewActivity
                Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
                intent.putExtra("date", true);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });



        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
