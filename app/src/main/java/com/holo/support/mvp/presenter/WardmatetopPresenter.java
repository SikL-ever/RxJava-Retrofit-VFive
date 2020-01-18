package com.holo.support.mvp.presenter;

import com.holo.support.base.MyPresenter;
import com.holo.support.mvp.model.MyAPI;
import com.holo.support.mvp.myview.MyView;

import io.reactivex.Observable;

public class WardmatetopPresenter extends MyPresenter {

    public WardmatetopPresenter(MyView myView) {
        super(myView);
    }

    @Override
    protected Observable getModel(MyAPI myAPI, Object... arg) {
        return myAPI.wardmatetop();
    }
}
