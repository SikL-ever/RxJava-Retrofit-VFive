package com.holo.support.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.holo.support.scydemo.R;
import com.holo.support.uitlview.MyProgressDialog;

public class MyDialog {
    private static MyDialog dialog;
    private Handler handler;
    private int hanint=10;
    private MyProgressDialog myProgressDialog;

    public MyDialog(Context context){
        initdialog(context);
    }
    public static MyDialog getDialog(Context context){
        if (dialog == null) {
            dialog=new MyDialog(context);
        }
        return dialog;
    }
    private void initdialog(Context context) {
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                hanint--;
                if (hanint==0){
                    handler.removeMessages(0);
                    myProgressDialog.dismiss();
                    hanint=10;
                }else{
                    handler.sendEmptyMessageDelayed(0,1000);
                }
            }
        };
        myProgressDialog = new MyProgressDialog(context, R.style.CustomDialog);//样式
        myProgressDialog.setCanceledOnTouchOutside(false);
        myProgressDialog.show();
        handler.sendEmptyMessageDelayed(0,1000);
    }
}
