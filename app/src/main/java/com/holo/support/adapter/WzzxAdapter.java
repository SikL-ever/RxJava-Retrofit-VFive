package com.holo.support.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.holo.support.bean.HomepagerWzzxBean;
import com.holo.support.scydemo.R;

import java.util.ArrayList;
import java.util.List;

public class WzzxAdapter extends RecyclerView.Adapter<WzzxAdapter.ViewHolder> {
    private Context context;
    private List<HomepagerWzzxBean> data=new ArrayList<>();

    public WzzxAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.show_wzzx_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomepagerWzzxBean wzzxBean = data.get(position);
        holder.textView.setText(wzzxBean.departmentName);
        Glide.with(context).load(wzzxBean.pic).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<HomepagerWzzxBean> data) {
        if (data != null) {
            this.data = data;
        }
    }

    public List<HomepagerWzzxBean> getData() {
        return data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.wzzx_item_name);
            imageView=itemView.findViewById(R.id.wzzx_item_image);
        }
    }
}
