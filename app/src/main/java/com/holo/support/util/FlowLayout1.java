package com.holo.support.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.holo.support.scydemo.R;

/**
 * @author dingtao
 * @date 2018/11/29 10:36
 * 效果参照：https://www.jianshu.com/p/05954091c650
 */
public class FlowLayout1 extends FrameLayout {

    
    private int space;
    private int textSize;
    private String bg;

    public FlowLayout1(Context context) {
        super(context);
    }

    public FlowLayout1(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.flow);
        space=(int)array.getDimension(R.styleable.flow_space,10);
        textSize=(int)array.getDimension(R.styleable.flow_textSize,30);
        bg = array.getString(R.styleable.flow_bg);
        Log.i("dt","背景："+bg);
    }

    public FlowLayout1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.flow);
        space=(int)array.getDimension(R.styleable.flow_space,10);
        textSize=(int)array.getDimension(R.styleable.flow_textSize,30);
        bg = array.getString(R.styleable.flow_bg);
        Log.i("dt","背景："+bg);
    }



    //效果参照：https://www.jianshu.com/p/05954091c650
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        Log.i("dt", space + "间距");

        int width = getWidth();//获取控件宽度
        int hWidth = 0;//记录每行控件累加的宽度

        int rows = 0;//行数

        for (int i = 0; i < getChildCount(); i++) {

            final TextView view = (TextView) getChildAt(i);
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*if (flowViewClickListener != null) {
                        flowViewClickListener.flowViewClick((TextView) view);
                    }*/
                    if (onViewRemoListener!=null){
                        onViewRemoListener.onDelete(view.getText().toString());
                        //在这里进行删除
                        removeView(v);
                    }
                }
            });
            int childWidth = view.getWidth();
            int childHeight = view.getHeight();

            hWidth = hWidth + (childWidth + space);
            if (hWidth > width) {//如果累加宽度大于控件宽度，则换行
                rows++;
                hWidth = childWidth + space;//超过之后，直接等于子控件的宽度
            }
            view.layout(hWidth - childWidth, rows * childHeight + (rows + 1) * space,
                    hWidth, (rows + 1) * childHeight + (rows + 1) * space);
        }
    }
	
	private FlowViewClickListener flowViewClickListener;
    public void setFlowViewClickListener(FlowViewClickListener flowViewClickListener) {
        this.flowViewClickListener = flowViewClickListener;

    }
	public interface FlowViewClickListener {
        void flowViewClick(TextView view);
    }

    public void addTextView(String s) {
        TextView textView = (TextView) View.inflate(getContext(), R.layout.flow_item,null);
        textView.setText(s);
        //布局宽高自适应
        LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);//控件设置上布局参数
        addView(textView);
    }
    //删除所有的
    public void remoview(){
        removeAllViews();
    }
    //创建 接口回调3
    private OnViewRemoListener onViewRemoListener;
    public interface  OnViewRemoListener{
        void onDelete(String a);
    }
    public void setOnViewRemoListener(OnViewRemoListener onViewRemoListener) {
        this.onViewRemoListener = onViewRemoListener;
    }
}
