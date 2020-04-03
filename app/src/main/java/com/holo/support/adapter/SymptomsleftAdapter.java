package com.holo.support.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.holo.support.bean.HomepagerWzzxBean;
import com.holo.support.scydemo.R;

import java.util.ArrayList;
import java.util.List;

public class SymptomsleftAdapter extends RecyclerView.Adapter<SymptomsleftAdapter.ViewHolder> {
    private Context context;
    private List<HomepagerWzzxBean> list=new ArrayList<>();
    public SymptomsleftAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SymptomsleftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.symptoms_left, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomsleftAdapter.ViewHolder holder, int position) {
        holder.symptoms_text.setText(list.get(position).getDepartmentName());
        if (getthisPostion()==position){
            holder.symptoms_text.setBackgroundColor(Color.WHITE);
            holder.symptoms_view.setBackgroundColor(Color.parseColor("#3087ea"));
        }else{
            holder.symptoms_text.setBackgroundColor(Color.parseColor("#f2f2f2"));
            holder.symptoms_view.setBackgroundColor(Color.parseColor("#f2f2f2"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBack != null) {
                    callBack.callback(list.get(position).id);
                }
                onrecyclerviewlistener.onClick(position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(List<HomepagerWzzxBean> list){
      this.list=list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView symptoms_text;
        View symptoms_view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            symptoms_text = itemView.findViewById(R.id.symptoms_text);
            symptoms_view = itemView.findViewById(R.id.symptoms_view);
        }
    }
    //
    private  int thisPosition;
    private int getthisPostion(){
        return thisPosition;
    }
    public void setthisPostion(int thisPosition){
        this.thisPosition=thisPosition;
    }
    //定义不同的接口
    private OnItemClickListener  onrecyclerviewlistener;

    public void setOnrecyclerviewlistener(OnItemClickListener onrecyclerviewlistener) {
        this.onrecyclerviewlistener = onrecyclerviewlistener;
    }
    public interface OnItemClickListener{
        void onClick(int position);
    }

    //添加接口回调
    private  CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack{
        void callback(int a);
    }



}
