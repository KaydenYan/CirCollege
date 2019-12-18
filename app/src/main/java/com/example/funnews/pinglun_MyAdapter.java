package com.example.funnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class pinglun_MyAdapter extends BaseAdapter {


    private ArrayList<pinglun_sjk> pingluns ;
    private Context context;
    private int itemLayout;
    private int currentType;
    private LayoutInflater inflater;
    public pinglun_MyAdapter(Context context, ArrayList<pinglun_sjk> pingluns) {

        this.pingluns= pingluns;
        this.context = context;
        inflater =LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return pingluns.size();
    }

    @Override
    public Object getItem(int position) {
        return pingluns.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        currentType = getItemViewType(position);

        pinglun_MyAdapter.ViewHolder holder = null;
        if (null == convertView) {
            holder = new pinglun_MyAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.comss, null);
            holder.commuser = convertView.findViewById(R.id.commuser);
            holder.commcon = convertView.findViewById(R.id.commcon);
            convertView.setTag(holder);
//            convertView.setTag(1, convertView);
//            convertView.setTag(2, convertView);

        } else {
            holder = (pinglun_MyAdapter.ViewHolder) convertView.getTag();
//            convertView.getTag(1);
//            convertView.getTag(2);
        }

        pinglun_sjk stu = pingluns.get(position);

        holder.commuser.setText(stu.getAuthor_name());
        holder.commcon.setText(stu.getPinglun());


        return convertView;
    }

    private void initViews(pinglun_sjk pingluns, pinglun_MyAdapter.ViewHolder holder) {//初始化数据

        holder.commuser.setTag(pingluns.getAuthor_name());
        holder.commcon.setText(pingluns.getPinglun());



    }
    public class ViewHolder {
        public TextView commuser;
        public TextView commcon;
    }
}
