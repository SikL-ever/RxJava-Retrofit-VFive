package com.holo.support.mvp.presenter;

import com.holo.support.base.MyPresenter;
import com.holo.support.mvp.model.MyAPI;
import com.holo.support.mvp.myview.MyView;

import io.reactivex.Observable;

public class LoginPresenter extends MyPresenter {
    public LoginPresenter(MyView myView) {
        super(myView);
    }

    @Override
    protected Observable getModel(MyAPI myAPI, Object... arg) {
        return myAPI.getlogin((String) arg[0],(String) arg[1]);
    }
}