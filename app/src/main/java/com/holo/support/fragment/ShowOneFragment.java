package com.holo.support.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.holo.support.adapter.Healthzx_adapter;
import com.holo.support.adapter.WzzxAdapter;
import com.holo.support.base.BaseFragment;
import com.holo.support.bean.HomepagerBanner;
import com.holo.support.bean.Data;
import com.holo.support.bean.HomepagerHealthzxBean;
import com.holo.support.bean.HomepagerWzzxBean;
import com.holo.support.mvp.myview.MyView;
import com.holo.support.mvp.presenter.BannerPresenter;
import com.holo.support.mvp.presenter.HomePagerHealthzxPresenter;
import com.holo.support.mvp.presenter.HomePagerWzzxPresenter;
import com.holo.support.scydemo.DemoActivity;
import com.holo.support.scydemo.LoginActivity;
import com.holo.support.scydemo.MyActivity;
import com.holo.support.scydemo.R;
import com.holo.support.scydemo.Show_Disease_DrugActivity;
import com.holo.support.util.LoginDaoUtil;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShowOneFragment extends BaseFragment {

    @BindView(R.id.show_one_bt)
    TextView showOneBt;
    @BindView(R.id.one_user_image)
    ImageView oneUserImage;
    @BindView(R.id.show_banner)
    FlyBanner showBanner;
    @BindView(R.id.show_scrollview)
    ScrollView showScrollview;
    @BindView(R.id.show_Interrogation_consulting_recycler)
    RecyclerView showInterrogationConsultingRecycler;
    @BindView(R.id.health_recycler)
    RecyclerView healthRecycler;
    @BindView(R.id.show_drug)
    LinearLayout showdrug;
    @BindView(R.id.show_disease)
    LinearLayout showdisease;
    private BannerPresenter bannerPresenter;
    private HomePagerWzzxPresenter wzzxPresenter;
    private WzzxAdapter wzzxAdapter;
    private HomePagerHealthzxPresenter healthzxPresenter;
    private Healthzx_adapter healthzx_adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.showonefragment;
    }

    @Override
    protected void initView() {
        //解决滑动冲突
        showInterrogationConsultingRecycler.setNestedScrollingEnabled(false);
        healthRecycler.setNestedScrollingEnabled(false);
        //设置用户头像
        if (LoginDaoUtil.getInstance().findDao(getContext()) == null) {
            Glide.with(getContext()).load(R.drawable.register_icon_invitatiion_code_n).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(oneUserImage);
        } else {
            List<String> dao = LoginDaoUtil.getInstance().findDao(getContext());
            Glide.with(getContext()).load(dao.get(2)).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(oneUserImage);
        }
        //搜索点击跳转
        showOneBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DemoActivity.class);
                getActivity().startActivity(intent);
            }
        });
        //头像点击
        oneUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (LoginDaoUtil.getInstance().findDao(getContext()) == null) {
                    //跳转到登录页面
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    getActivity().startActivity(intent);
                } else {
                    //跳转到用户详情页面ss

                }*/
                Intent intent = new Intent(getContext(), MyActivity.class);
                startActivity(intent);
            }
        });
        showBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //banner点击操作
            }
        });
        //所有适配器管理类
        adapterdata();
        //常见病症
        showdisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Show_Disease_DrugActivity.class);
                intent.putExtra("code",1);
                startActivity(intent);
            }
        });
        //常见药品
        showdrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Show_Disease_DrugActivity.class);
                intent.putExtra("code",2);
                startActivity(intent);
            }
        });
    }

    //适配器管理类
    private void adapterdata() {
        if (wzzxAdapter == null) {
            wzzxAdapter = new WzzxAdapter(getContext());//我的咨询
        }
        if (healthzx_adapter == null) {
            healthzx_adapter = new Healthzx_adapter(getContext());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        //banner请求
        bannerPresenter = new BannerPresenter(new BannerData());
        wzzxPresenter = new HomePagerWzzxPresenter(new WzzxData());//问诊咨询请求
        healthzxPresenter = new HomePagerHealthzxPresenter(new HealthzxData());
    }

    @Override
    public void onResume() {
        super.onResume();
        //所有适配器管理类
        adapterdata();
        //请求
        bannerPresenter.getdata();//banner
        wzzxPresenter.getdata();//我的咨询
        healthzxPresenter.getdata(1, 1, 10);//健康咨询
    }

    //问诊咨询请求
    class WzzxData implements MyView<List<HomepagerWzzxBean>> {
        @Override
        public void success(List<HomepagerWzzxBean> data) {
            if (data == null) {
                return;
            }
            wzzxAdapter.setData(data);
            showInterrogationConsultingRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 4));
            showInterrogationConsultingRecycler.setAdapter(wzzxAdapter);
            wzzxAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(Data data) {
        }
    }

    //banner请求
    class BannerData implements MyView<List<HomepagerBanner>> {
        @Override
        public void success(List<HomepagerBanner> data) {
            if (data == null) {
                return;
            }
            List<String> list = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                list.add(data.get(i).getImageUrl());
            }
            showBanner.setImagesUrl(list);
        }

        @Override
        public void fail(Data data) {
        }
    }

    //健康咨询
    class HealthzxData implements MyView<List<HomepagerHealthzxBean>> {
        @Override
        public void success(List<HomepagerHealthzxBean> data) {
            healthzx_adapter.setData(data);
            healthzx_adapter.notifyDataSetChanged();
            healthRecycler.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
            healthRecycler.setAdapter(healthzx_adapter);
        }

        @Override
        public void fail(Data data) {
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bannerPresenter != null) {
            bannerPresenter = null;
        }
        if (wzzxPresenter != null) {
            wzzxPresenter = null;
        }
    }
}
