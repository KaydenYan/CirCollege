package com.example.funnews;

import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
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

            convertView.setTag(holder);
//            convertView.setTag(1, convertView);
//            convertView.setTag(2, convertView);

        } else {
            holder = (ViewHolder) convertView.getTag();
//            convertView.getTag(1);
//            convertView.getTag(2);

        }

        toutiao stu = toutiaos.get(position);

        holder.itemTitle.setText(stu.getTitle());
        holder.itemAuthor.setText(stu.getAuthor_name());
        holder.itemDate.setText(stu.getDate());
        holder.itemImg =convertView.findViewById(R.id.itemImg);
        Glide.with(context).load(stu.getThumbnail_pic_s()).into(holder.itemImg);

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
    public void getImage(Context context, String itemImg, final ImageView imageView) {

        /**
         * 检测图片的Tag值 ,如果根请求的地址相同 才做图片的网络请求.
         */
        if (imageView.getTag().toString().equals(itemImg)) {
            RequestQueue mQueue = Volley.newRequestQueue(context);
            ImageRequest imageRequest = new ImageRequest(itemImg,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            imageView.setImageBitmap(response);//将返回的Bitmap显示子啊ImageView上
                        }
                    }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            mQueue.add(imageRequest);
        }
    }
}