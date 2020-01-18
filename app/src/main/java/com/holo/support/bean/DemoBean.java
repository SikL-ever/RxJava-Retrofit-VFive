package com.holo.support.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class DemoBean {
    @Id
    String text;

    @Generated(hash = 389031273)
    public DemoBean(String text) {
        this.text = text;
    }

    @Generated(hash = 2085635340)
    public DemoBean() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

