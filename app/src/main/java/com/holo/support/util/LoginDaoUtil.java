package com.holo.support.util;

import android.content.Context;

import com.holo.support.bean.LoginBean;
import com.holo.support.dao.DaoMaster;
import com.holo.support.dao.LoginBeanDao;

import java.util.ArrayList;
import java.util.List;

public class LoginDaoUtil {
    private static LoginDaoUtil daoUtil;
    private LoginBeanDao dao;

    public static LoginDaoUtil getInstance(){
        if (daoUtil==null){
            daoUtil = new LoginDaoUtil();
        }
        return daoUtil;
    }
    private LoginDaoUtil(){

    }
    //数据库添加数据
    public void AddDao(LoginBean loginBean, Context context){
        dao= DaoMaster.newDevSession(context, LoginBeanDao.TABLENAME).getLoginBeanDao();
        List<LoginBean> loginBeans = dao.loadAll();
        for (int i=0;i<loginBeans.size();i++){
            if (loginBeans.get(i).ttt==1){
                loginBeans.get(i).ttt=2;
            }
        }
        loginBean.ttt=1;
        dao.insertOrReplaceInTx(loginBean);
        dao.insertOrReplaceInTx(loginBeans);
    }
    //数据库查找数据
    public List<String> findDao(Context context){
        if (dao == null) {
            dao= DaoMaster.newDevSession(context, LoginBeanDao.TABLENAME).getLoginBeanDao();
        }
        List<LoginBean> loginBeans = dao.loadAll();
        for (int i = 0; i < loginBeans.size(); i++) {
            LoginBean loginBean = loginBeans.get(i);
            if (loginBean.ttt==1){
                List<String> list=new ArrayList<>();
                list.add(loginBean.id);
                list.add(loginBean.sessionId);
                list.add(loginBean.headPic);
                list.add(loginBean.nickName);
                return list;
            }
        }
        return null;
    }

}
