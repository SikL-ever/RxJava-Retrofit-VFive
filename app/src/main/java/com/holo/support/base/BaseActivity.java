package com.holo.support.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.holo.support.scydemo.R;
import com.holo.support.uitlview.MyProgressDialog;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    public MyProgressDialog myProgressDialog;
    private int hanint=10;
    private Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());//设置布局
        ButterKnife.bind(this);//绑定布局
        initView();//操作数据
        //这里会有一个初始化加载框
        //这里一个网络监听

    }
    protected abstract int getLayout();
    protected abstract void initView();



}
