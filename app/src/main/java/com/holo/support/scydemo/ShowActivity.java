package com.holo.support.scydemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.holo.support.base.BaseActivity;
import com.holo.support.fragment.ShowOneFragment;
import com.holo.support.fragment.ShowThreeFragment;
import com.holo.support.fragment.ShowTwoFragment;
import com.holo.support.util.LoginDaoUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity {

    @BindView(R.id.user_top_image)
    ImageView userTopImage;
    @BindView(R.id.user_top_message)
    ImageView userTopMessage;
    @BindView(R.id.toplayout)
    RelativeLayout toplayout;
    @BindView(R.id.show_fragment)
    FrameLayout showFragment;
    @BindView(R.id.show_home_bt)
    RadioButton showHomeBt;
    @BindView(R.id.show_xin_one)
    RadioButton showXinOne;
    @BindView(R.id.show_xin_two)
    RadioButton showXinTwo;
    @BindView(R.id.show_void_bt)
    RadioButton showVoidBt;
    @BindView(R.id.show_radio)
    RadioGroup showRadio;
    @BindView(R.id.show_botton_bt)
    ImageView showBottonBt;
    @BindView(R.id.show_botton_layout)
    RelativeLayout showBottonLayout;
    private FragmentManager manager;
    private ShowOneFragment showOneFragment;
    private ShowTwoFragment showTwoFragment;
    private ShowThreeFragment showThreeFragment;
    private FragmentTransaction fragmentTransaction;
    int page=3;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            page--;
            handler.sendEmptyMessageDelayed(0,1000);
            if (showVoidBt.isChecked()==false){
                showRadio.setVisibility(View.VISIBLE);
                showBottonLayout.setVisibility(View.GONE);
                if (showHomeBt.isChecked()==true){
                    toplayout.setVisibility(View.GONE);
                }else{
                    toplayout.setVisibility(View.GONE);
                }
                page=3;
                handler.removeMessages(0);
            }else{
                if (page==0){
                    showRadio.setVisibility(View.GONE);
                    toplayout.setVisibility(View.GONE);
                    showBottonLayout.setVisibility(View.VISIBLE);
                    handler.removeMessages(0);
                    page=3;
                }
            }
        }
    };
    @Override
    protected int getLayout() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        manager = getSupportFragmentManager();
        showOneFragment = new ShowOneFragment();
        showTwoFragment = new ShowTwoFragment();
        showThreeFragment = new ShowThreeFragment();
        manager.beginTransaction()
                .add(R.id.show_fragment,showOneFragment)
                .add(R.id.show_fragment,showTwoFragment)
                .add(R.id.show_fragment,showThreeFragment)
                .show(showOneFragment)
                .hide(showTwoFragment)
                .hide(showThreeFragment)
                .commit();
        showRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                fragmentTransaction = manager.beginTransaction();
                switch (i){
                    case R.id.show_home_bt:
                        fragmentTransaction.show(showOneFragment).hide(showTwoFragment).hide(showThreeFragment).commit();
                        showXinOne.setVisibility(View.VISIBLE);
                        showXinTwo.setVisibility(View.GONE);
                        toplayout.setVisibility(View.GONE);
                        break;
                    case R.id.show_xin_one:
                        fragmentTransaction.show(showTwoFragment).hide(showOneFragment).hide(showThreeFragment).commit();
                        showXinOne.setVisibility(View.GONE);
                        showXinTwo.setVisibility(View.VISIBLE);
                        toplayout.setVisibility(View.GONE);
                        break;
                    case R.id.show_xin_two:
                        //这个里面写的跳转发布圈子
                        break;
                    case R.id.show_void_bt:
                        fragmentTransaction.show(showThreeFragment).hide(showTwoFragment).hide(showOneFragment).commit();
                        showXinOne.setVisibility(View.VISIBLE);
                        showXinTwo.setVisibility(View.GONE);
                        //隐藏底部导航浪,发送hanlde
                        showRadio.setVisibility(View.GONE);
                        showBottonLayout.setVisibility(View.VISIBLE);
                        toplayout.setVisibility(View.GONE);
                        break;
                }
            }
        });

        showBottonBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toplayout.setVisibility(View.VISIBLE);
                showBottonLayout.setVisibility(View.GONE);
                showRadio.setVisibility(View.VISIBLE);
                handler.sendEmptyMessageDelayed(0,1000);
            }
        });
        //头像点击设置
        userTopImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> dao = LoginDaoUtil.getInstance().findDao(ShowActivity.this);
                if (dao == null) {
                    Intent intent = new Intent(ShowActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(ShowActivity.this, MyActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    //-----------------------------------------------两次点击退出
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
