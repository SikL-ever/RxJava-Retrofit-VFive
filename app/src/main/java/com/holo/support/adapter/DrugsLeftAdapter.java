package com.holo.support.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.holo.support.bean.DrugsBean;
import com.holo.support.scydemo.R;

import java.util.ArrayList;
import java.util.List;

public class DrugsLeftAdapter extends RecyclerView.Adapter<DrugsLeftAdapter.ViewHolder> {
    private Context context;
    private List<DrugsBean> list=new ArrayList<>();
    public DrugsLeftAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.drugs_left, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.drugstext.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBack != null) {
                    callBack.callback(list.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setList(List<DrugsBean> list){
        this.list=list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView drugstext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drugstext=itemView.findViewById(R.id.drugs_left_text);
        }
    }
    //接口回调
    public interface  CallBack{
        void callback(int a);
    }
    public CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
