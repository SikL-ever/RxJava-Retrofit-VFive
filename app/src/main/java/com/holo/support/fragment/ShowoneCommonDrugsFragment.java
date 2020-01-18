package com.holo.support.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.holo.support.adapter.DrugsLeftAdapter;
import com.holo.support.adapter.DrugsRigheAdapter;
import com.holo.support.base.BaseFragment;
import com.holo.support.bean.Data;
import com.holo.support.bean.DrugsBean;
import com.holo.support.bean.DrugsRightBean;
import com.holo.support.mvp.myview.MyView;
import com.holo.support.mvp.presenter.DrugsLeftPrenter;
import com.holo.support.mvp.presenter.DrugsRightPresenter;
import com.holo.support.scydemo.R;

import java.util.List;

import butterknife.BindView;

//常见药品
public class ShowoneCommonDrugsFragment extends BaseFragment {
    @BindView(R.id.showonedrugs_onerecycler)
    RecyclerView showonedrugsOnerecycler;
    @BindView(R.id.showonedrugs_tworecycler)
    RecyclerView showonedrugsTworecycler;
    private DrugsLeftPrenter drugsLeftPrenter;
    private DrugsLeftAdapter drugsLeftAdapter;
    private DrugsRightPresenter drugsRightPresenter;
    private DrugsRigheAdapter drugsRigheAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.showone_common_drugs_fragment;
    }

    @Override
    protected void initView() {
        //左边
        drugsLeftPrenter = new DrugsLeftPrenter(new drugsleft());
        drugsLeftAdapter = new DrugsLeftAdapter(getContext());
        showonedrugsOnerecycler.setAdapter(drugsLeftAdapter);
        showonedrugsOnerecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        drugsLeftAdapter.setCallBack(new DrugsLeftAdapter.CallBack() {
            @Override
            public void callback(int a) {
                drugsRightPresenter.getdata(a,1,20);
            }
        });
        //右边
        drugsRightPresenter = new DrugsRightPresenter(new drugsright());
        drugsRigheAdapter = new DrugsRigheAdapter(getContext());
    }
    //左边
    class drugsleft implements MyView<List<DrugsBean>>{
        @Override
        public void success(List<DrugsBean> data) {
            drugsLeftAdapter.setList(data);
            drugsLeftAdapter.notifyDataSetChanged();
            drugsRightPresenter.getdata(data.get(0).getId(),1,20);
        }
        @Override
        public void fail(Data data) {
        }
    }
    //右边
    class drugsright implements MyView<List<DrugsRightBean>>{
        @Override
        public void success(List<DrugsRightBean> data) {
            drugsRigheAdapter.setList(data);
            drugsRigheAdapter.notifyDataSetChanged();
        }
        @Override
        public void fail(Data data) {

        }
    }



}
