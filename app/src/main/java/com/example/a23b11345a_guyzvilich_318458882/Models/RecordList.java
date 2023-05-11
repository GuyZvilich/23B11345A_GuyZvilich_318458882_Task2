package com.example.a23b11345a_guyzvilich_318458882.Models;

import java.util.ArrayList;

public class RecordList {
    private ArrayList<Record> records = new ArrayList<>();


    public RecordList(){

    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public RecordList setRecords(ArrayList<Record> records) {
        this.records = records;
        return this;
    }

    @Override
    public String toString() {
        return "RecordList{" +
                "records=" + records +
                '}';
    }


}
