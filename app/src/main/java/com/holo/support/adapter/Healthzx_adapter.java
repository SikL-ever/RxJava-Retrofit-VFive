package com.holo.support.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.holo.support.bean.HomepagerHealthzxBean;
import com.holo.support.scydemo.R;
import com.holo.support.util.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Healthzx_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HomepagerHealthzxBean> list=new ArrayList<>();
    private int a=0;
    private int b=1;
    private int c=2;
    public Healthzx_adapter(Context context) {
        this.context = context;
    }
    //类型选择
    @Override
    public int getItemViewType(int position) {
        int a = list.get(position).getThumbnail().split(";").length;
        if (a==1){
            return a;
        }else if (a==2){
            return b;
        }else{
            return c;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==a){
            View inflate = View.inflate(context, R.layout.duotiaomu_item, null);
            ViewHolderOne viewHolderOne = new ViewHolderOne(inflate);
            return viewHolderOne;
        }else if(viewType==b){
            View inflate = View.inflate(context, R.layout.duotiaomu_item1, null);
            ViewHolderTwo viewHolderTwo = new ViewHolderTwo(inflate);
            return viewHolderTwo;
        }else {
            View inflate = View.inflate(context, R.layout.duotiaomu_item1, null);
            ViewHolderThree viewHolderThree = new ViewHolderThree(inflate);
            return viewHolderThree;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderOne){
            String image = list.get(position).getThumbnail();
            String[] split = image.split(";");
            GridAdapter gridAdapter = new GridAdapter(context,split,list.get(position).getId());
            ((ViewHolderOne) holder).recyclerView.setLayoutManager(((ViewHolderOne) holder).linearLayoutManager);
            ((ViewHolderOne) holder).name.setText(list.get(position).getTitle());
            ((ViewHolderOne) holder).yys.setText(list.get(position).getSource());
            try {
                ((ViewHolderOne) holder).time.setText(DateUtils.dateTransformer(list.get(position).getReleaseTime(),DateUtils.DATE_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ((ViewHolderOne) holder).recyclerView.setAdapter(gridAdapter);
        }else if (holder instanceof ViewHolderTwo){
            String image = list.get(position).getThumbnail();
            String[] split = image.split(";");
            GridAdapter gridAdapter = new GridAdapter(context,split,list.get(position).getId());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            ((ViewHolderTwo) holder).recyclerView1.setLayoutManager(gridLayoutManager);
            ((ViewHolderTwo) holder).name.setText(list.get(position).getTitle());
            ((ViewHolderTwo) holder).yys.setText(list.get(position).getSource());
            try {
                ((ViewHolderTwo) holder).time.setText(DateUtils.dateTransformer(list.get(position).getReleaseTime(),DateUtils.DATE_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ((ViewHolderTwo) holder).recyclerView1.setAdapter(gridAdapter);
        }else if (holder instanceof ViewHolderThree){
            String image = list.get(position).getThumbnail();
            String[] split = image.split(";");
            GridAdapter gridAdapter = new GridAdapter(context,split,list.get(position).getId());
            ((ViewHolderThree) holder).recyclerView1.setLayoutManager(((ViewHolderThree) holder).gridLayoutManager1);
            ((ViewHolderThree) holder).name.setText(list.get(position).getTitle());
            ((ViewHolderThree) holder).yys.setText(list.get(position).getSource());
            try {
                ((ViewHolderThree) holder).time.setText(DateUtils.dateTransformer(list.get(position).getReleaseTime(),DateUtils.DATE_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ((ViewHolderThree) holder).recyclerView1.setAdapter(gridAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    //传入数据
    public void setData(List<HomepagerHealthzxBean> data) {
        if (data != null) {
            this.list = data;
        }
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder{
        private final LinearLayoutManager linearLayoutManager;
        RecyclerView recyclerView;
        TextView name,yys,time;
        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.image_recyclerView);
            linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            name = itemView.findViewById(R.id.text_name);
            yys = itemView.findViewById(R.id.yys);
            time= itemView.findViewById(R.id.time);
        }
    }
    public class ViewHolderTwo extends RecyclerView.ViewHolder{
        RecyclerView recyclerView1;
        TextView name,yys,time;
        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            recyclerView1 = itemView.findViewById(R.id.recyclerView1);
            name = itemView.findViewById(R.id.text_1);
            yys = itemView.findViewById(R.id.text_2);
            time = itemView.findViewById(R.id.text_3);
        }
    }
    public class ViewHolderThree extends RecyclerView.ViewHolder{
        private final GridLayoutManager gridLayoutManager1;
        RecyclerView recyclerView1;
        TextView name,yys,time;
        public ViewHolderThree(@NonNull View itemView) {
            super(itemView);
            recyclerView1 = itemView.findViewById(R.id.recyclerView1);
            gridLayoutManager1 = new GridLayoutManager(context, 3);
            name = itemView.findViewById(R.id.text_1);
            yys = itemView.findViewById(R.id.text_2);
            time = itemView.findViewById(R.id.text_3);
        }
    }

}
