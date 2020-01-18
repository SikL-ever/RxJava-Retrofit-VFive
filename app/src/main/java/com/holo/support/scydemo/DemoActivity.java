package com.holo.support.scydemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.holo.support.bean.DemoBean;
import com.holo.support.dao.DaoMaster;
import com.holo.support.dao.DemoBeanDao;
import com.holo.support.util.FlowLayout1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DemoActivity extends AppCompatActivity {

    @BindView(R.id.demo_edit)
    EditText demoEdit;
    @BindView(R.id.demo_sousuo)
    TextView demoSousuo;
    @BindView(R.id.demo_flow)
    FlowLayout1 demoFlow;
    @BindView(R.id.remo_demo_view)
    ImageView remoDemoView;
    @BindView(R.id.finist_image)
    ImageView finistImage;
    private DemoBeanDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);
        dao = DaoMaster.newDevSession(this, DemoBeanDao.TABLENAME).getDemoBeanDao();
        List<DemoBean> demoBeans = dao.loadAll();
        if (demoBeans == null) {

        } else {
            for (int i = 0; i < demoBeans.size(); i++) {
                demoFlow.addTextView(demoBeans.get(i).getText());
            }
        }
        finistImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        demoSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = demoEdit.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(DemoActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    //有数据
                    demoEdit.setText("");
                    demoFlow.addTextView(s);
                    dao.insertOrReplaceInTx(new DemoBean(s));
                }
            }
        });
        //删除所有
        remoDemoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demoFlow.remoview();
                dao.deleteAll();
            }
        });
        demoFlow.setOnViewRemoListener(new FlowLayout1.OnViewRemoListener() {
            @Override
            public void onDelete(String a) {
                dao.delete(new DemoBean(a));
            }
        });
    }
}
