package com.holo.support.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.holo.support.bean.WardmateListBean;
import com.holo.support.bean.WardmateTopBean;
import com.holo.support.scydemo.R;

import java.util.ArrayList;
import java.util.List;

public class WardmatelistAdapter extends RecyclerView.Adapter<WardmatelistAdapter.ViewHolder> {
    private Context context;
    private List<WardmateListBean> list=new ArrayList<>();
    public WardmatelistAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WardmatelistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.ward_list_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull WardmatelistAdapter.ViewHolder holder, int position) {
        holder.titles.setText(list.get(position).getTitle()+"");
        holder.times.setText(list.get(position).getReleaseTime()+"");
        holder.h_num.setText(list.get(position).getAmount()+"");
        holder.contents.setText(list.get(position).getDetail());
        holder.jianyi.setText(list.get(position).getCollectionNum()+"");
        holder.advice.setText(list.get(position).getCommentNum()+"");

        int amount = list.get(position).getAmount();
        if(amount>0){
            holder.h.setVisibility(View.VISIBLE);
        }else{
            holder.h.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //添加数据
    public void setData(List<WardmateListBean> data) {
        if (data != null) {
           this.list=data;
        }
    }
    //清空
    public void deleat() {
        list.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titles;
        TextView times;
        TextView contents;
        TextView h_num;
        RelativeLayout h;
        TextView jianyi;
        TextView advice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titles = itemView.findViewById(R.id.titles);
            times = itemView.findViewById(R.id.times);
            contents = itemView.findViewById(R.id.contents);
            h_num = itemView.findViewById(R.id.H_num);
            h = itemView.findViewById(R.id.H);
            jianyi = itemView.findViewById(R.id.jainyi);
            advice = itemView.findViewById(R.id.advise);
        }
    }

}
