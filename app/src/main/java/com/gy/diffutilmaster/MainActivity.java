package com.gy.diffutilmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DiffAdapter adapter;

    private Button btn_exchange;
    DiffUtil.DiffResult diffResult;
    List<DataBean> oldData,newData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldData = getDataOld();
        newData = getDataNew();

        btn_exchange = findViewById(R.id.btn_exchange);
        recyclerView = findViewById(R.id.recyclerview);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DiffAdapter();

        recyclerView.setAdapter(adapter);

        adapter.setDate(oldData);



        btn_exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diffResult = DiffUtil.calculateDiff(new CustomDiffCallBack(oldData,newData),true);
                diffResult.dispatchUpdatesTo(adapter);
                adapter.setDate(newData);
            }
        });
    }


    private List<DataBean> getDataOld(){
        List<DataBean> dataBeans = new ArrayList<>();
        for (int i=0;i<2;i++){
            DataBean dataBean = new DataBean();
            dataBean.setTitle("这是第"+i+"个item");
            dataBeans.add(dataBean);
        }
        return dataBeans;
    }

    private List<DataBean> getDataNew(){
        List<DataBean> dataBeans = new ArrayList<>();
        for (int i=0;i<3;i++){
            DataBean dataBean = new DataBean();
            dataBean.setTitle("这是第"+i+"个item");
            dataBeans.add(dataBean);
        }

        for (int i=3;i<6;i++){
            DataBean dataBean = new DataBean();
            dataBean.setTitle("这是第"+i+"A个item");
            dataBeans.add(dataBean);
        }

        for (int i=6;i<10;i++){
            DataBean dataBean = new DataBean();
            dataBean.setTitle("这是第"+i+"A个item");
            dataBeans.add(dataBean);
        }
        return dataBeans;
    }
}
