package com.example.funnews;

import java.util.ArrayList;

public class pinglun_sjk {
    private String author_name;
    private String pinglun;
    private ArrayList<pinglun_sjk> pinglun_sjks;


    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getPinglun() {
        return pinglun;
    }

    public void setPinglun(String pinglun) {
        this.pinglun = pinglun;
    }
    public ArrayList<pinglun_sjk> getPinglun_sjk(){
        return pinglun_sjks;
    }
    public String toString(){
        return
                "author_name='"+author_name+'\''+'\n'+
                        "pinglun='"+pinglun+'\''+'\n';
    }
}
