package com.holo.support.fragment;

import android.graphics.Color;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.holo.support.adapter.WardmateAdapter;
import com.holo.support.adapter.WardmatelistAdapter;
import com.holo.support.base.BaseFragment;
import com.holo.support.bean.Data;
import com.holo.support.bean.WardmateListBean;
import com.holo.support.bean.WardmateTopBean;
import com.holo.support.mvp.myview.MyView;
import com.holo.support.mvp.presenter.WardmateListPresenter;
import com.holo.support.mvp.presenter.WardmatetopPresenter;
import com.holo.support.scydemo.R;

import java.util.List;

import butterknife.BindView;

public class ShowTwoFragment extends BaseFragment {

    @BindView(R.id.top_recycle)
    RecyclerView topRecycle;
    @BindView(R.id.content_recycle)
    RecyclerView contentRecycle;
    private WardmatetopPresenter wardmatetopPresenter;
    private WardmateAdapter wardmateAdapter;
    private WardmateListPresenter wardmateListPresenter;
    private WardmatelistAdapter listadapter;

    @Override
    protected int getLayoutId() {
        return R.layout.showtwofragment;
    }

    @Override
    protected void initView() {
        //解决recycler滑动冲突
        topRecycle.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        contentRecycle.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        wardmateAdapter = new WardmateAdapter(getContext());
        listadapter = new WardmatelistAdapter(getContext());
        wardmatetopPresenter = new WardmatetopPresenter(new topdata());
        wardmateListPresenter = new WardmateListPresenter(new listdata());
        wardmatetopPresenter.getdata();
        wardmateListPresenter.getdata(7,1,10);
        contentRecycle.setAdapter(listadapter);
        //顶部适配器点击事件
        wardmateAdapter.setCallBackItem(new WardmateAdapter.CallBackItem() {
            @Override
            public void calldata(int data) {
                wardmateListPresenter.getdata(data,1,10);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    //顶部recycler
    class topdata implements MyView<List<WardmateTopBean>>{
        @Override
        public void success(List<WardmateTopBean> data) {
            data.get(0).textColor= Color.parseColor("#3087ea");
            wardmateAdapter.setData(data);
            topRecycle.setAdapter(wardmateAdapter);
        }
        @Override
        public void fail(Data data) {
        }
    }
    //列表recycler
    class listdata implements MyView<List<WardmateListBean>>{
        @Override
        public void success(List<WardmateListBean> data) {
            listadapter.deleat();
            listadapter.setData(data);
            listadapter.notifyDataSetChanged();
        }
        @Override
        public void fail(Data data) {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wardmatetopPresenter != null) {
            wardmatetopPresenter=null;
        }
    }
}
