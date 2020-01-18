package com.holo.support.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.holo.support.bean.WardmateTopBean;
import com.holo.support.scydemo.R;

import java.util.ArrayList;
import java.util.List;

public class WardmateAdapter extends RecyclerView.Adapter<WardmateAdapter.ViewHolder> {
    private Context context;
    private List<WardmateTopBean> list=new ArrayList<>();
    public WardmateAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WardmateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.wardmate_topitem, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull WardmateAdapter.ViewHolder holder, int position) {
        holder.wardmate_top_item.setText(list.get(position).departmentName);
        //条目变颜色
        holder.wardmate_top_item.setTextColor(list.get(position).textColor);
        //条目点击
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBackItem != null) {
                    callBackItem.calldata(list.get(position).getId());
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).textColor= Color.BLACK;
                    }
                    list.get(position).textColor=Color.parseColor("#3087ea");
                    notifyDataSetChanged();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<WardmateTopBean> data) {
        if (data != null) {
           this.list=data;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView wardmate_top_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wardmate_top_item=itemView.findViewById(R.id.wardmate_top_item);
        }
    }
    //创建一个接口回调
    private CallBackItem  callBackItem;
    public interface CallBackItem{
        void calldata(int data);
    }

    public void setCallBackItem(CallBackItem callBackItem) {
        this.callBackItem = callBackItem;
    }
}
