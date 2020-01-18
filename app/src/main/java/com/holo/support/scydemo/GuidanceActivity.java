package com.holo.support.scydemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.holo.support.adapter.GuidancePagerAdapter;
import com.holo.support.base.BaseActivity;
import com.holo.support.fragment.GuidacneFiveFragment;
import com.holo.support.fragment.GuidacneFourFragment;
import com.holo.support.fragment.GuidacneOneFragment;
import com.holo.support.fragment.GuidacneTwoFragment;
import com.holo.support.fragment.GuidanceThreeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuidanceActivity extends BaseActivity {


    @BindView(R.id.guidance_page)
    ViewPager guidancePage;
    @BindView(R.id.guidance_text)
    TextView guidanceText;
    @BindView(R.id.guidance_bt1)
    RadioButton guidanceBt1;
    @BindView(R.id.guidance_bt2)
    RadioButton guidanceBt2;
    @BindView(R.id.guidance_bt3)
    RadioButton guidanceBt3;
    @BindView(R.id.guidance_bt4)
    RadioButton guidanceBt4;
    @BindView(R.id.guidance_bt5)
    RadioButton guidanceBt5;
    @BindView(R.id.guidance_radio)
    RadioGroup guidanceRadio;

    private SharedPreferences guidancesp;
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_guidance;
    }

    @Override
    protected void initView() {
        //创建sp
        guidancesp = getSharedPreferences("guidance", MODE_PRIVATE);
        boolean bolguidance = guidancesp.getBoolean("bolguidance", false);
        if (bolguidance) {
            Intent intent = new Intent(GuidanceActivity.this,ShowActivity.class);
            startActivity(intent);
            finish();
        } else {
            list.add(new GuidacneOneFragment());
            list.add(new GuidacneTwoFragment());
            list.add(new GuidanceThreeFragment());
            list.add(new GuidacneFourFragment());
            list.add(new GuidacneFiveFragment());
            //创建适配器
            GuidancePagerAdapter adapter = new GuidancePagerAdapter(getSupportFragmentManager(), list);
            guidancePage.setAdapter(adapter);
            //滑动时间
            guidancePage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }
                @Override
                public void onPageSelected(int position) {
                    guidanceRadio.check(guidanceRadio.getChildAt(position).getId());
                    if (position==list.size()-1){
                        guidanceText.setVisibility(View.VISIBLE);
                    }else{
                        guidanceText.setVisibility(View.GONE);
                    }
                }
                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            guidanceRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i){
                        case R.id.guidance_bt1:
                            guidancePage.setCurrentItem(0,true);
                            break;
                        case R.id.guidance_bt2:
                            guidancePage.setCurrentItem(1,true);
                            break;
                        case R.id.guidance_bt3:
                            guidancePage.setCurrentItem(2,true);
                            break;
                        case R.id.guidance_bt4:
                            guidancePage.setCurrentItem(3,true);
                            break;
                        case R.id.guidance_bt5:
                            guidancePage.setCurrentItem(4,true);
                            break;
                    }
                }
            });
            //点击按钮进人
            guidanceText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(GuidanceActivity.this,ShowActivity.class);
                    SharedPreferences.Editor edit = guidancesp.edit();
                    edit.putBoolean("bolguidance",true);
                    edit.commit();
                    startActivity(intent);
                    finish();
                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (list != null) {
            list.clear();
        }
        finish();
    }
}
