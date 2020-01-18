package com.holo.support.scydemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.holo.support.base.BaseActivity;
import com.holo.support.bean.Data;
import com.holo.support.bean.LoginBean;
import com.holo.support.mvp.myview.MyView;
import com.holo.support.mvp.presenter.LoginPresenter;
import com.holo.support.util.LoginDaoUtil;
import com.holo.support.util.MyDialog;
import com.holo.support.util.RsaCoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_bt)
    Button loginBt;
    @BindView(R.id.login_name)
    EditText loginName;
    @BindView(R.id.login_pass)
    EditText loginPass;
    @BindView(R.id.passhideshow)
    ImageView passhideshow;
    private LoginPresenter loginPresenter;
    private Boolean pasVisiibile=true;
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        loginPresenter = new LoginPresenter(new getlogin());
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog.getDialog(LoginActivity.this);
                //获取账号密码
                String name = loginName.getText().toString();
                String pass = loginPass.getText().toString();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    boolean email = isEmail(name);//判断是否是邮箱
                    if (email){
                        try {
                            String s = RsaCoder.encryptByPublicKey(pass);
                            loginPresenter.getdata(name,s);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "邮箱格式不对", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        //密码显示隐藏
        Glide.with(LoginActivity.this).load(R.drawable.login_icon_hide_password_n).into(passhideshow);
        passhideshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pasVisiibile){//密码隐藏
                    Glide.with(LoginActivity.this).load(R.drawable.login_icon_hide_password_n).into(passhideshow);
                    loginPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pasVisiibile=false;
                }else{//密码显示
                    Glide.with(LoginActivity.this).load(R.drawable.login_icon_show_password).into(passhideshow);
                    loginPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pasVisiibile=true;
                }
            }
        });
    }
    class getlogin implements MyView<LoginBean> {
        @Override
        public void success(LoginBean data) {
            //在这里进行存储登录的信息
            LoginDaoUtil.getInstance().AddDao(data,LoginActivity.this);
            //登录成功进行页面
            /*Intent intent = new Intent(LoginActivity.this,ShowActivity.class);
            startActivity(intent);*/
            finish();
        }
        @Override
        public void fail(Data data) {
        }
    }
    //判断邮箱
    /*** 验证邮箱输入是否合法* * @param strEmail* @return*/
    public static boolean isEmail(String strEmail) {//
        // String strPattern =// "^(
        // [a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        String strPattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }
}
