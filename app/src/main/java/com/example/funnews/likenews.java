package com.example.funnews;

import java.util.ArrayList;

public class likenews {


    private String title;
    private String date;
    private String author_name;
    private String url;
    private String thumbnail_pic_s;
    private String thumbnail_pic_s02;
    private String thumbnail_pic_s03;
    private String shoucang;
    private ArrayList<likenews> likenews;

    public String getShoucang(){
        return shoucang;
    }
    public void setShoucang(String shoucang){
        this.shoucang =shoucang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02() {
        return thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }

    public ArrayList<likenews> getLikenews(){
        return likenews;
    }
    public String toString (){
        return
                        "title='"+title+'\''+'\n'+
                        "date='" +date+'\''+'\n'+
                        "author_name='" +author_name+'\''+'\n'+
                        "url='" +url+'\''+'\n'+
                        "thumbnail_pic_s='" +thumbnail_pic_s+'\''+'\n'+
                        "thumbnail_pic_s02='" +thumbnail_pic_s02+'\''+'\n'+
                        "thumbnail_pic_s03='" +thumbnail_pic_s03+'\''+'\n'+
                                "shoucang='" +shoucang+'\''+'\n';
    }
}
