package com.holo.support.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.holo.support.bean.SymptomsrightBean;
import com.holo.support.scydemo.R;

import java.util.ArrayList;
import java.util.List;

public class SymptomsRightAdapter extends RecyclerView.Adapter<SymptomsRightAdapter.ViewHolder> {
    private Context comtext;
    private List<SymptomsrightBean> list=new ArrayList<>();
    public SymptomsRightAdapter(Context comtext) {
        this.comtext = comtext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(comtext, R.layout.symptoms_right, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Symptomstext.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  void setList(List<SymptomsrightBean> list){
        this.list=list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Symptomstext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Symptomstext=itemView.findViewById(R.id.symptoms_right_text);
        }
    }
}
