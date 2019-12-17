package com.example.funnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.funnews.josnGet.toutiao;

import java.util.ArrayList;

public class like_MyAdapter extends BaseAdapter {
    private final int TYPE_COUNT = 3;

    private final int TYPE_ONE = 0;

    private final int TYPE_TWO = 1;
    private final int TYPE_THREE =2;


    private ArrayList<likenews> likenews ;
    private Context context;
    private int itemLayout;
    private int currentType;
    private LayoutInflater inflater;
    public like_MyAdapter(Context context, ArrayList<likenews> likenews) {

        this.likenews= likenews;
        this.context = context;
        inflater =LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return likenews.size();
    }

    @Override
    public Object getItem(int position) {
        return likenews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    public View getView(int position, View convertView, ViewGroup parent) {

        currentType =getItemViewType(position);
        if(currentType ==TYPE_ONE){
            like_MyAdapter.ViewHolder holder =null;
            if(null == convertView){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.newsitems, null);
                holder.itemTitle = convertView.findViewById(R.id.itemTitle);
                holder.itemAuthor = convertView.findViewById(R.id.itemAuthor);
                holder.itemDate =convertView.findViewById(R.id.itemDate);

                convertView.setTag(holder);
//            convertView.setTag(1, convertView);
//            convertView.setTag(2, convertView);

            } else {
                holder = (like_MyAdapter.ViewHolder) convertView.getTag();
//            convertView.getTag(1);
//            convertView.getTag(2);

            }

            likenews stu = likenews.get(position);

            holder.itemTitle.setText(stu.getTitle());
            holder.itemAuthor.setText(stu.getAuthor_name());
            holder.itemDate.setText(stu.getDate());
            holder.itemImg =convertView.findViewById(R.id.itemImg);
            Glide.with(context).load(stu.getThumbnail_pic_s()).into(holder.itemImg);


        }else if(currentType ==TYPE_TWO){
            like_MyAdapter.ViewHolder1 holder1 =null;
            if(null == convertView){
                holder1 = new ViewHolder1();
                convertView = LayoutInflater.from(context).inflate(R.layout.newsitems2, null);
                holder1.itemTitle = convertView.findViewById(R.id.itemTitle);
                holder1.itemAuthor = convertView.findViewById(R.id.itemAuthor);
                holder1.itemDate =convertView.findViewById(R.id.itemDate);

                convertView.setTag(holder1);
//            convertView.setTag(1, convertView);
//            convertView.setTag(2, convertView);

            } else {
                holder1 = (like_MyAdapter.ViewHolder1) convertView.getTag();
//            convertView.getTag(1);
//            convertView.getTag(2);

            }

            likenews stu = likenews.get(position);

            holder1.itemTitle.setText(stu.getTitle());
            holder1.itemAuthor.setText(stu.getAuthor_name());
            holder1.itemDate.setText(stu.getDate());
            holder1.itemImg =convertView.findViewById(R.id.itemImg);
            Glide.with(context).load(stu.getThumbnail_pic_s()).into(holder1.itemImg);
            holder1.itemImg2 =convertView.findViewById(R.id.itemImg2);
            Glide.with(context).load(stu.getThumbnail_pic_s02()).into(holder1.itemImg2);
            holder1.itemImg3 =convertView.findViewById(R.id.itemImg3);
            Glide.with(context).load(stu.getThumbnail_pic_s03()).into(holder1.itemImg3);


        }
        else if(currentType ==TYPE_THREE){
            like_MyAdapter.ViewHolder2 holder2 =null;
            if(null == convertView){
                holder2 = new ViewHolder2();
                convertView = LayoutInflater.from(context).inflate(R.layout.newsitems3, null);
                holder2.itemTitle = convertView.findViewById(R.id.itemTitle);
                holder2.itemAuthor = convertView.findViewById(R.id.itemAuthor);
                holder2.itemDate =convertView.findViewById(R.id.itemDate);

                convertView.setTag(holder2);
//            convertView.setTag(1, convertView);
//            convertView.setTag(2, convertView);

            } else {
                holder2 = (like_MyAdapter.ViewHolder2) convertView.getTag();
//            convertView.getTag(1);
//            convertView.getTag(2);

            }

            likenews stu = likenews.get(position);

            holder2.itemTitle.setText(stu.getTitle());
            holder2.itemAuthor.setText(stu.getAuthor_name());
            holder2.itemDate.setText(stu.getDate());
            holder2.itemImg =convertView.findViewById(R.id.itemImg);
            Glide.with(context).load(stu.getThumbnail_pic_s()).into(holder2.itemImg);
            holder2.itemImg3 =convertView.findViewById(R.id.itemImg3);
            Glide.with(context).load(stu.getThumbnail_pic_s02()).into(holder2.itemImg3);
        }
        return convertView;
    }


    private void initViews(toutiao toutiaos, like_MyAdapter.ViewHolder holder) {//初始化数据

        holder.itemImg.setTag(toutiaos.getThumbnail_pic_s());
        holder.itemTitle.setText(toutiaos.getTitle());
        holder.itemAuthor.setText(toutiaos.getAuthor_name());
        holder.itemDate.setText(toutiaos.getDate());


    }
    private void initViews1(toutiao toutiaos, like_MyAdapter.ViewHolder1 holder1){
        holder1.itemImg.setTag(toutiaos.getThumbnail_pic_s());
        holder1.itemImg2.setTag(toutiaos.getThumbnail_pic_s02());
        holder1.itemImg3.setTag(toutiaos.getThumbnail_pic_s03());
        holder1.itemTitle.setText(toutiaos.getTitle());
        holder1.itemAuthor.setText(toutiaos.getAuthor_name());
        holder1.itemDate.setText(toutiaos.getDate());
    }
    private void initViews2(toutiao toutiaos, like_MyAdapter.ViewHolder2 holder2){
        holder2.itemImg.setTag(toutiaos.getThumbnail_pic_s());
        holder2.itemImg3.setTag(toutiaos.getThumbnail_pic_s02());
        holder2.itemTitle.setText(toutiaos.getTitle());
        holder2.itemAuthor.setText(toutiaos.getAuthor_name());
        holder2.itemDate.setText(toutiaos.getDate());
    }



    final static class ViewHolder{

        public ImageView itemImg;
        public TextView itemTitle;
        public TextView itemDate;
        public TextView itemAuthor;
    }
    final static class ViewHolder1{
        public ImageView itemImg;
        public ImageView itemImg2;
        public ImageView itemImg3;
        public TextView itemTitle;
        public TextView itemDate;
        public TextView itemAuthor;

    }
    final static class ViewHolder2{
        public ImageView itemImg;
        public ImageView itemImg3;
        public TextView itemTitle;
        public TextView itemDate;
        public TextView itemAuthor;

    }
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    public int getItemViewType (int position){
        if(position!=0 &&position%3==0 ){
            return 0;
        }else if(position%3==1)
        {
            return 1;
        }else {
            return 2;
        }
    }
}
