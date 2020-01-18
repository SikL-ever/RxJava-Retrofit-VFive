package com.holo.support.adapter;

import android.content.Context;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBack != null) {
                    callBack.callback(list.get(position).id);
                }
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            symptoms_text = itemView.findViewById(R.id.symptoms_text);
        }
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
