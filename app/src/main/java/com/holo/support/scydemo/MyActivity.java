package com.holo.support.scydemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.holo.support.base.BaseActivity;
import com.holo.support.util.LoginDaoUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyActivity extends BaseActivity {

    @BindView(R.id.my_user_finsh)
    ImageView myUserFinsh;
    @BindView(R.id.my_user_image)
    ImageView myUserImage;
    @BindView(R.id.my_user_name)
    TextView myUserName;
    @BindView(R.id.my_user_setting)
    LinearLayout myUserSetting;
    private View inflate;
    private PopupWindow popupWindow;
    private TextView popupcamera, popupphoto, popupfinsh;

    @Override
    protected int getLayout() {
        return R.layout.activity_my;
    }

    @Override
    protected void initView() {
        //popupwindow
        inflate = View.inflate(MyActivity.this, R.layout.updata_head, null);
        popupcamera = inflate.findViewById(R.id.paizhao);
        popupphoto = inflate.findViewById(R.id.xiangce);
        popupfinsh = inflate.findViewById(R.id.updata_finish);
        myUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //穿件popupwindow
                popupWindow = new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
                // 在底部显示
                popupWindow.showAtLocation(MyActivity.this.findViewById(R.id.my_user_all),
                        Gravity.BOTTOM, 0, 0);
            }
        });
        //拍照
        popupcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uri;
                if (Build.VERSION.SDK_INT >= 24) {
                    uri = FileProvider.getUriForFile(MyActivity.this, "com.holo.support.scydemo.fileprovider", new File(Environment
                            .getExternalStorageDirectory(), "image.jpg"));//通过FileProvider创建一个content类型的Uri
                } else {
                    uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));//7.0以下打开相机拍照的方法
                }
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, 200);
                popupWindow.dismiss();
            }
        });
        //xiangce
        popupphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳入相册拿取图片
                // 创建相册的意图对象
                Intent intent = new Intent(Intent.ACTION_PICK);
                // 设置支持的图片格式
                intent.setType("image/*");
                // 开启相册页面
                startActivityForResult(intent, 100);
            }
        });
        //finish
        popupfinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }
    //设置页面

    @OnClick(R.id.my_user_setting)
    public void myuserseting(){
        Intent intent = new Intent(MyActivity.this,MyUserSetingActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        List<String> dao = LoginDaoUtil.getInstance().findDao(MyActivity.this);
        if (dao != null) {
            Glide.with(MyActivity.this).load(dao.get(2)).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myUserImage);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //相机回来的值
        if (requestCode == 200 && resultCode == RESULT_OK) {
            File temp = new File(Environment.getExternalStorageDirectory()
                    + "/image.jpg");
            Glide.with(MyActivity.this).load(temp).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myUserImage);
        }
        //相册取回来的值
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri url = data.getData();
            //剪裁
            crop(url);
        }
        if (requestCode == 99 && resultCode == RESULT_OK) {
            //取出剪裁图片
            Bitmap bitmap = data.getParcelableExtra("data");
            //setPicToView(bitmap);//保存在SD卡中
            //ABitmap是一个bitmap转换url类型的工具类,主要原因是上传服务器无法使用bitmap类型只能用file类型或url类型
            //这里不请求服务器不用使用
            //ABitMap aBitMap = new ABitMap();
            Glide.with(MyActivity.this).load(bitmap).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myUserImage);
            popupWindow.dismiss();
        }
        popupWindow.dismiss();
    }

    //剪裁图片
    private void crop(Uri uri) {
        //通过隐式跳转进入到裁剪页面
        Intent intent = new Intent("com.android.camera.action.CROP");
        //设置裁剪的数据
        intent.setDataAndType(uri, "image/*");
        //设置裁剪的属性
        intent.putExtra("crop", "true");//设置是否支持裁剪
        intent.putExtra("aspectX", 1);//设置裁剪框的比例
        intent.putExtra("aspectY", 1);//设置裁剪框的比例
        intent.putExtra("outputX", 250);//设置输出图片的大小
        intent.putExtra("outputY", 250);//设置输出图片的大小
        intent.putExtra("return-data", true);//设置是否返回数据
        //回传数据
        startActivityForResult(intent, 99);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
