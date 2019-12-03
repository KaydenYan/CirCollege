package com.example.funnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.funnews.josnGet.toutiao;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {


    private List<toutiao> toutiaos ;
    private Context context;
    private int itemLayout;

    public MyAdapter(Context context, List<toutiao> toutiaos , int itemLayout) {
        List<toutiao> toutiaos1 =new ArrayList<>();
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
            convertView.setTag(holder);
//            convertView.setTag(1, convertView);
//            convertView.setTag(2, convertView);
            convertView.setTag(R.id.itemTitle,convertView);
            convertView.setTag(R.id.itemAuthor, convertView);
            convertView.setTag(R.id.itemDate, convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
//            convertView.getTag(1);
//            convertView.getTag(2);
            convertView.getTag(R.id.itemTitle);
            convertView.getTag(R.id.itemAuthor);
            convertView.getTag(R.id.itemDate);
        }

        toutiao stu = toutiaos.get(position);
        holder.itemTitle.setText(stu.getAuthor_name());
        holder.itemAuthor.setText(stu.getTitle());
        holder.itemDate.setText(stu.getDate());

        return convertView;
    }
    final static class ViewHolder{

        private TextView itemTitle;
        private TextView itemDate;
        private TextView itemAuthor;
    }
}
