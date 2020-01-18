package com.holo.support.mvp.myview;

import com.holo.support.bean.Data;

public interface MyView<T> {
    void success(T data);
    void fail(Data data);
}
