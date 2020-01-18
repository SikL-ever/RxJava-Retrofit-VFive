package com.holo.support.mvp.presenter;

import com.holo.support.base.MyPresenter;
import com.holo.support.mvp.model.MyAPI;
import com.holo.support.mvp.myview.MyView;

import io.reactivex.Observable;

public class HomePagerHealthzxPresenter extends MyPresenter {

    public HomePagerHealthzxPresenter(MyView myView) {
        super(myView);
    }

    @Override
    protected Observable getModel(MyAPI myAPI, Object... arg) {
        return myAPI.healthzxshow((int) arg[0],(int)arg[1],(int)arg[2]);
    }
}
