package com.example.a23b11345a_guyzvilich_318458882.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a23b11345a_guyzvilich_318458882.Interfaces.CallBack_sendCoordinates;
import com.example.a23b11345a_guyzvilich_318458882.Models.Record;
import com.example.a23b11345a_guyzvilich_318458882.R;
import com.example.a23b11345a_guyzvilich_318458882.Utilities.MySP;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class RecordFragment extends Fragment  {

    private ArrayList<Record> list;
    private double xCoordinate;
    private double yCoordinate;
    private String difficultyLevel;
    private int score;
    private boolean afterGame;
    private CallBack_sendCoordinates callBack_sendCoordinates;
    public RecordFragment(double xCoordinate,double yCoordinate, String difficultyLevel, int score,boolean afterGame)
    {
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
        this.difficultyLevel=difficultyLevel;
        this.score=score;
        this.afterGame=afterGame;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = (ListView)view.findViewById(R.id.record_FRAG_list);
        loadRecords();
        if(afterGame)
            endGame();
        else
            saveRecords(list);
        ArrayAdapter<Record> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(callBack_sendCoordinates!=null) {
                    callBack_sendCoordinates.userCoordinatesChosen(list.get(position).getxCoordinate(), list.get(position).getyCoordinate());
                }
            }
        });

    }

    public void loadRecords()
    {
        String fromSp = MySP.getInstance().getString("23b-11345A-GUYZVILICH-318458882","");
        list = new ArrayList<>();
        if (!fromSp.isEmpty()) {
            Gson gson = new Gson();
            list = new Gson().fromJson(fromSp, new TypeToken<ArrayList<Record>>() {
            }.getType());
        }
    }
    public void saveRecords(ArrayList<Record> list)
    {
        String recordListJson = new Gson().toJson(list);
        MySP.getInstance().putString("23b-11345A-GUYZVILICH-318458882",recordListJson);
    }
    public void setCallback(CallBack_sendCoordinates callBack_sendCoordinates)
    {
        this.callBack_sendCoordinates = callBack_sendCoordinates;
    }
    private void endGame() {
        Record tempRecord = new Record(difficultyLevel,xCoordinate,yCoordinate,score);
        list.add(tempRecord);
        Collections.sort(list, new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return Integer.compare(o1.getScore(),o2.getScore());
            }
        });
        if(list.size()>10)
        {
            list.remove(10);
        }
        Collections.reverse(list);
        saveRecords(list);
    }

}