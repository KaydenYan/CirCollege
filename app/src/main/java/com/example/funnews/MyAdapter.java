package com.example.funnews;

import android.content.ClipDescription;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.funnews.josnGet.toutiao;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {


    private ArrayList<toutiao> toutiaos ;
    private Context context;
    private int itemLayout;

    public MyAdapter(Context context, ArrayList<toutiao> toutiaos , int itemLayout) {

        this.toutiaos= toutiaos;
        this.context = context;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() {
        return toutiaos.size();
    }

    @Override
    public Object getItem(int position) {
        return toutiaos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(null == convertView){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(itemLayout, null);
            holder.itemTitle = convertView.findViewById(R.id.itemTitle);
            holder.itemAuthor = convertView.findViewById(R.id.itemAuthor);
            holder.itemDate =convertView.findViewById(R.id.itemDate);
            holder.itemImg =convertView.findViewById(R.id.itemImg);
            convertView.setTag(holder);
//            convertView.setTag(1, convertView);
//            convertView.setTag(2, convertView);
            convertView.setTag(R.id.itemTitle,convertView);
            convertView.setTag(R.id.itemAuthor, convertView);
            convertView.setTag(R.id.itemDate, convertView);
            convertView.setTag(R.id.itemImg,convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
//            convertView.getTag(1);
//            convertView.getTag(2);
            convertView.getTag(R.id.itemTitle);
            convertView.getTag(R.id.itemAuthor);
            convertView.getTag(R.id.itemDate);
            convertView.getTag(R.id.itemImg);
        }

        toutiao stu = toutiaos.get(position);

        holder.itemTitle.setText(stu.getTitle());
        holder.itemAuthor.setText(stu.getAuthor_name());
        holder.itemDate.setText(stu.getDate());

        return convertView;
    }
    private void initViews(toutiao toutiaos, ViewHolder holder) {//初始化数据

        holder.itemImg.setTag(toutiaos.getThumbnail_pic_s());
        holder.itemTitle.setText(toutiaos.getTitle());
        holder.itemAuthor.setText(toutiaos.getAuthor_name());
        holder.itemDate.setText(toutiaos.getDate());

    }


    private ClipDescription getIntent() {
        return null;
    }

    final static class ViewHolder{

        private ImageView itemImg;
        private TextView itemTitle;
        private TextView itemDate;
        private TextView itemAuthor;
    }
}
