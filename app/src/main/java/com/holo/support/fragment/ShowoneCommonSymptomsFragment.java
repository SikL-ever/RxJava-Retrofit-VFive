package com.holo.support.fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.holo.support.adapter.SymptomsRightAdapter;
import com.holo.support.adapter.SymptomsleftAdapter;
import com.holo.support.base.BaseFragment;
import com.holo.support.bean.Data;
import com.holo.support.bean.HomepagerWzzxBean;
import com.holo.support.bean.SymptomsrightBean;
import com.holo.support.mvp.myview.MyView;
import com.holo.support.mvp.presenter.CommoneStmptomsPresneter;
import com.holo.support.mvp.presenter.SymptomsRightPresenter;
import com.holo.support.scydemo.R;

import java.util.List;

import butterknife.BindView;

//常见病症
public class ShowoneCommonSymptomsFragment extends BaseFragment {
    @BindView(R.id.symptoms_left)
    RecyclerView symptomsLeft;
    @BindView(R.id.symptoms_right)
    RecyclerView symptomsRight;
    private CommoneStmptomsPresneter commoneStmptomsPresneter;
    private SymptomsleftAdapter symptomsleftAdapter;
    private SymptomsRightPresenter symptomsRightPresenter;
    private SymptomsRightAdapter symptomsRightAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.showone_common_symptoms_fargment;
    }

    @Override
    protected void initView() {
        //进行请求数据
        //左边
        commoneStmptomsPresneter = new CommoneStmptomsPresneter(new symptmsleft());
        commoneStmptomsPresneter.getdata();
        symptomsleftAdapter = new SymptomsleftAdapter(getContext());
        symptomsLeft.setAdapter(symptomsleftAdapter);
        symptomsLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        symptomsleftAdapter.setCallBack(new SymptomsleftAdapter.CallBack() {
            @Override
            public void callback(int a) {
                symptomsRightPresenter.getdata(a);
            }
        });
        symptomsleftAdapter.setOnrecyclerviewlistener(new SymptomsleftAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                symptomsleftAdapter.setthisPostion(position);
                symptomsleftAdapter.notifyDataSetChanged();
            }
        });
        //右边
        symptomsRightPresenter = new SymptomsRightPresenter(new symptomsright());
        symptomsRightAdapter = new SymptomsRightAdapter(getContext());
        symptomsRight.setAdapter(symptomsRightAdapter);
        symptomsRight.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

    //左边
    class symptmsleft implements MyView<List<HomepagerWzzxBean>> {
        @Override
        public void success(List<HomepagerWzzxBean> data) {
            symptomsleftAdapter.getList(data);
            symptomsleftAdapter.notifyDataSetChanged();
            symptomsRightPresenter.getdata(data.get(0).id);
        }
        @Override
        public void fail(Data data) {
        }
    }
    //右边
    class symptomsright implements MyView<List<SymptomsrightBean>>{
        @Override
        public void success(List<SymptomsrightBean> data) {
            symptomsRightAdapter.setList(data);
            symptomsRightAdapter.notifyDataSetChanged();
        }
        @Override
        public void fail(Data data) {
        }
    }
}
