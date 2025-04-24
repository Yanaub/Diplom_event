package com.example.myapplication.ui.clases;

import java.util.ArrayList;
import java.util.List;

public class Event {
    String id, org;
    String name, info,place,data,time;
    int sost=0;
    List<String> help;

    public Event(String id, String org, String name, String info, String place, String data, String time, int sost, List<String> help) {
        this.id = id;
        this.org = org;
        this.name = name;
        this.info = info;
        this.place = place;
        this.data = data;
        this.time = time;
        this.sost = sost;
        this.help = help;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSost() {
        return sost;
    }

    public void setSost(int sost) {
        this.sost = sost;
    }

    public List<String> getHelp() {
        return help;
    }

    public void setHelp(ArrayList<String> help) {
        this.help = help;
    }




}
