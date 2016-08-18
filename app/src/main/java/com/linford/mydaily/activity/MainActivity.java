package com.linford.mydaily.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.linford.mydaily.R;
import com.linford.mydaily.adapter.HomeAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView dailyRecycler;
    private List<String> mDatas;
    private HomeAdapter mHomeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initData();
        initView();
        setListener();
        Logger.init("MyDaily");
    }

    private void findView() {
        dailyRecycler= (RecyclerView) findViewById(R.id.dailyRecycler);
    }

    private void initData() {
        mDatas=new ArrayList<String>();
        for (int i='a';i<='z';i++){
            mDatas.add((char)i+"");
        }
    }

    private void initView() {
        initLinearRecycler();
//        initGridRecycler();
//        initStaggeredRecycler(StaggeredGridLayoutManager.HORIZONTAL);
//        StaggeredGridLayoutManager.HORIZONTAL时recycler为横向，spanCount代表有多少行，如果传入的是StaggeredGridLayoutManager.VERTICAL，recycler为纵向spanCount代表有多少列
//        设置横向的时候要注意设置宽度，因为横向的宽度没有约束了，因为控件可以滑动了
//        initStaggeredPullRecycler(StaggeredGridLayoutManager.VERTICAL);
//        实现瀑布流
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_action_add:
                mHomeAdapter.addData(1);
                break;
            case  R.id.id_action_delete:
                mHomeAdapter.removeData(1);
                break;
            case R.id.id_action_gridview:
                dailyRecycler.setLayoutManager(new GridLayoutManager(this,4));
                break;
            case R.id.id_action_listview:
                dailyRecycler.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_horizontalGridView:
                dailyRecycler.setLayoutManager(new StaggeredGridLayoutManager(4,
                    StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.id_action_staggeredgridview:
                startActivity(new Intent(MainActivity.this , StaggeredActivity.class));
                break;
        }
        return true;
    }

//    private void initStaggeredPullRecycler(int orientation) {
//        dailyRecycler.setLayoutManager(new StaggeredGridLayoutManager(3,orientation));
//        mPullAdapter=new PullAdapter(mDatas,getApplicationContext());
//        dailyRecycler.setAdapter(mPullAdapter);
//        dailyRecycler.setItemAnimator(new DefaultItemAnimator());
//    }

    private void setListener() {
        mHomeAdapter.setmOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,position+"click",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,position+"long click",Toast.LENGTH_SHORT).show();
                mHomeAdapter.removeData(position);
            }
        });
    }

    private void initStaggeredRecycler(int orientation) {
        dailyRecycler.setLayoutManager(new StaggeredGridLayoutManager(4,orientation));
        mHomeAdapter=new HomeAdapter(mDatas,getApplication());
        dailyRecycler.setAdapter(mHomeAdapter);
//        dailyRecycler.addItemDecoration(new DividerGridItemDecoration(getApplication()));
    }

    private void initGridRecycler() {
        dailyRecycler.setLayoutManager(new GridLayoutManager(this,4));
        mHomeAdapter=new HomeAdapter(mDatas,getApplication());
        dailyRecycler.setAdapter(mHomeAdapter);
//        dailyRecycler.addItemDecoration(new DividerGridItemDecoration(getApplication()));
    }

    private void initLinearRecycler() {
        dailyRecycler.setLayoutManager(new LinearLayoutManager(this));
        mHomeAdapter=new HomeAdapter(mDatas,getApplicationContext());
        dailyRecycler.setAdapter(mHomeAdapter);
//        dailyRecycler.addItemDecoration(new DividerLinerItemDecoration(getApplicationContext(), DividerLinerItemDecoration.VERTICAL_LIST
//        ));
    }
}
