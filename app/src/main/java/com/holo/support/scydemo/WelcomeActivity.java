package com.holo.support.scydemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.holo.support.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.welcome_text)
    TextView welcomeText;
    private SharedPreferences welsp;
    private int page=3;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            page--;
            welcomeText.setText(page+"S");
            if (page==0){
                SharedPreferences.Editor edit = welsp.edit();
                edit.putBoolean("bolwelcome",true);
                edit.commit();
                //跳转
                Intent intent=new Intent(WelcomeActivity.this,GuidanceActivity.class);
                handler.removeMessages(0);
                startActivity(intent);
                finish();
            }else{
                handler.sendEmptyMessageDelayed(0,1000);
            }
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        //创建sp
        welsp = getSharedPreferences("wel", MODE_PRIVATE);
        //获取值
        boolean bolwelcome = welsp.getBoolean("bolwelcome", false);
        if (bolwelcome){
            Intent intent=new Intent(WelcomeActivity.this,GuidanceActivity.class);
            startActivity(intent);
            finish();
        }else{
            handler.sendEmptyMessageDelayed(0,1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
