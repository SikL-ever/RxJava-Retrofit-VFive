package com.holo.support.scydemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.holo.support.base.BaseActivity;
import com.holo.support.util.LoginDaoUtil;
import com.holo.support.util.TopUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ sichangyong
 * @ 创建日期 2020/4/3 15:52
 * 内容:设置页面
 */
public class MyUserSetingActivity extends BaseActivity {

    @BindView(R.id.user_seting_top)
    TopUtil userSetingTop;
    @BindView(R.id.user_seting_image)
    ImageView userSetingImage;
    @BindView(R.id.user_seting_name)
    TextView userSetingName;
    @BindView(R.id.user_seting_up_pass)
    RelativeLayout userSetingUpPass;
    @BindView(R.id.user_seting_clear_cache)
    RelativeLayout userSetingClearCache;
    @BindView(R.id.user_seting_screen)
    RelativeLayout userSetingScreen;
    @BindView(R.id.user_seting_versions)
    RelativeLayout userSetingVersions;
    @BindView(R.id.user_seting_help_center)
    RelativeLayout userSetingHelpCenter;
    @BindView(R.id.user_seting_about_me)
    RelativeLayout userSetingAboutMe;
    @BindView(R.id.user_seting_invite_friends)
    RelativeLayout userSetingInviteFriends;
    @BindView(R.id.user_seting_logout)
    RelativeLayout userSetingLogout;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_user_seting;
    }

    @Override
    protected void initView() {
        //设置title
        userSetingTop.setTitle("设置页面");
    }

    //onResume
    @Override
    protected void onResume() {
        super.onResume();
        //进行获取数据库的值然后进行设置
        List<String> dao = LoginDaoUtil.getInstance().findDao(this);
        if (dao==null){
            userSetingName.setText("请先登录");
            return;
        }
        Glide.with(this).load(dao.get(2)).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(userSetingImage);//设置头像

    }
    //这是一个很多的设置
    @OnClick({R.id.user_seting_image, R.id.user_seting_name, R.id.user_seting_up_pass,
            R.id.user_seting_clear_cache, R.id.user_seting_screen, R.id.user_seting_versions,
            R.id.user_seting_help_center, R.id.user_seting_about_me, R.id.user_seting_invite_friends,
            R.id.user_seting_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_seting_image:
                break;
            case R.id.user_seting_name:
                break;
            case R.id.user_seting_up_pass:
                break;
            case R.id.user_seting_clear_cache:
                break;
            case R.id.user_seting_screen:
                break;
            case R.id.user_seting_versions:
                break;
            case R.id.user_seting_help_center:
                break;
            case R.id.user_seting_about_me:
                break;
            case R.id.user_seting_invite_friends:
                break;
            case R.id.user_seting_logout://退出登录的操作,进行清理数据库,通知远端退出登录操作

                break;
        }
    }
}
