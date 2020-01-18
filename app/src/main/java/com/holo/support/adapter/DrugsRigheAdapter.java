package com.holo.support.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.holo.support.bean.DrugsRightBean;
import com.holo.support.scydemo.R;

import java.util.ArrayList;
import java.util.List;

public class DrugsRigheAdapter extends RecyclerView.Adapter<DrugsRigheAdapter.ViewHolder> {
    private Context context;
    private List<DrugsRightBean> list=new ArrayList<>();
    public DrugsRigheAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.drugst_right, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getPicture()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setList(List<DrugsRightBean> list){
        this.list=list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.drugs_right_image);
            text=itemView.findViewById(R.id.drugs_right_text);
        }
    }
}
