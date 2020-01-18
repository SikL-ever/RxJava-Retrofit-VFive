package com.holo.support.base;

import com.holo.support.bean.Data;
import com.holo.support.mvp.model.MyAPI;
import com.holo.support.mvp.myview.MyView;
import com.holo.support.util.OkHttpUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public  abstract class MyPresenter {
    private MyView myView;

    public MyPresenter(MyView myView) {
        this.myView = myView;
    }

    public void getdata(final Object...arg){
        MyAPI myAPI = OkHttpUtil.getInstance().create(MyAPI.class);
        getModel(myAPI,arg)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable,Data>() {
                    @Override
                    public Data apply(Throwable o) throws Exception {
                        return new Data("5000",o.getMessage());
                    }
                })
                .subscribe(new Consumer<Data>() {
                    @Override
                    public void accept(Data o) throws Exception {
                        if (o.getStatus().equals("0000")){
                            myView.success(o.result);
                        }else{
                            myView.fail(o);
                        }
                    }
                });
    }
    protected abstract Observable getModel (MyAPI myAPI, Object...arg);
}
