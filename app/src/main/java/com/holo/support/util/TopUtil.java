package com.holo.support.util;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.holo.support.scydemo.R;

public class TopUtil extends RelativeLayout {
    private ImageView image;
    private TextView textview;
    private RelativeLayout relativeLayout;
    public TopUtil(Context context) {
        super(context);
        initView(context);
    }

    public TopUtil(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TopUtil(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    //初始化
    private void initView(final Context context){
        View view=View.inflate(context, R.layout.top,this);
        image=view.findViewById(R.id.top_image);
        textview=view.findViewById(R.id.top_text);
        relativeLayout=view.findViewById(R.id.rela);
        //返回
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)context).finish();
            }
        });
    }
    //设置标题
    public void setTitle(String text){
        if (text==null){
           return;
        }
        textview.setText(text);
    }
    //设置背景颜色
    public void setRelar(int color){
        relativeLayout.setBackgroundColor(color);
    }
}
