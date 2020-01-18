package com.holo.support.scydemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.holo.support.bean.HomepagerBanner;
import com.holo.support.bean.Data;
import com.holo.support.mvp.myview.MyView;
import com.holo.support.mvp.presenter.BannerPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt1)
    Button bt1;
    private BannerPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //创建p层
        showPresenter = new BannerPresenter(new Bannerdata());
        showPresenter.getdata();
    }

    public class Bannerdata implements MyView<List<HomepagerBanner>> {
        @Override
        public void success(List<HomepagerBanner> data) {
            Log.i("aaa", "success: " + data.toString());
        }

        @Override
        public void fail(Data data) {
        }
    }

}
