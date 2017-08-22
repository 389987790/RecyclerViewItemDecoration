package com.example.kongjian.recyclerviewitemdecoration.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.kongjian.recyclerviewitemdecoration.decoration.DividerGridViewItemDecoration;
import com.example.kongjian.recyclerviewitemdecoration.decoration.DividerItemDecoration;
import com.example.kongjian.recyclerviewitemdecoration.R;
import com.example.kongjian.recyclerviewitemdecoration.adapter.MyRecyclerAdapter;
import com.example.kongjian.recyclerviewitemdecoration.adapter.MyStaggedRecyclerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> infoBeans;
    private RecyclerView recyclerView;
    DividerItemDecoration addItemDecoration;
    DividerGridViewItemDecoration dividerGridViewItemDecoration;
    boolean isVertical;
    boolean isGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.rv_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new MyRecyclerAdapter(infoBeans));
        //初始为竖直布局
        addItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(addItemDecoration);
        isVertical = true;
        isGrid = false;

    }
    /**
     * 初始化数据
     *
     */
    public void initData() {
        infoBeans = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String str = "这是第" + i + "个item";
            infoBeans.add(str);
        }
    }
    /**
     * 竖直布局和水平布局切换
     *
     */
    public View switchVerOrHor(View v) {
        isGrid = false;
        //移除分割线
        if (dividerGridViewItemDecoration != null) {
            recyclerView.removeItemDecoration(dividerGridViewItemDecoration);
        }
        if (addItemDecoration != null) {
            recyclerView.removeItemDecoration(addItemDecoration);
        }
        recyclerView.setAdapter(new MyRecyclerAdapter(infoBeans));
        if (isVertical) {//水平布局
            isVertical = false;
            addItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.addItemDecoration(addItemDecoration);
        } else {//竖直布局
            isVertical = true;
            addItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recyclerView.addItemDecoration(addItemDecoration);
        }
        return v;
    }
    /**
     * 网格布局和流式布局切换
     *
     */
    public View switchLayoutManager(View v) {
        isVertical = false;
        //移除分割线
        if (dividerGridViewItemDecoration != null) {
            recyclerView.removeItemDecoration(dividerGridViewItemDecoration);
        }
        if (addItemDecoration != null) {
            recyclerView.removeItemDecoration(addItemDecoration);
        }
        if (isGrid) {//流式布局
            isGrid = false;
            recyclerView.setAdapter(new MyStaggedRecyclerAdapter(infoBeans));
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL));


        } else {//网格布局
            isGrid = true;
            dividerGridViewItemDecoration = new DividerGridViewItemDecoration(MainActivity.this);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
            recyclerView.setAdapter(new MyRecyclerAdapter(infoBeans));
            recyclerView.addItemDecoration(dividerGridViewItemDecoration);
        }
        return v;
    }
}
