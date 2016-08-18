package com.linford.mydaily.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.linford.mydaily.R;
import com.linford.mydaily.adapter.PullAdapter;

import java.util.ArrayList;
import java.util.List;

public class StaggeredActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private PullAdapter mPullAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView= (RecyclerView) findViewById(R.id.dailyRecycler);

        initData();

        mPullAdapter=new PullAdapter(mDatas,getApplicationContext());

        mRecyclerView.setAdapter(mPullAdapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mPullAdapter.setOnItemClickListener(new PullAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                mDatas.set(position,(Integer.parseInt(mDatas.get(position))+1)+"");
                mPullAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnItemLongClick(View view, int position) {
                Toast.makeText(StaggeredActivity.this, mDatas.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        mDatas=new ArrayList<String>();
        for (int i = 'a'; i <='z' ; i++) {
            mDatas.add(""+i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_staggered,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_action_add:
                mPullAdapter.addData(1);
                break;
            case R.id.id_action_delete:
                mPullAdapter.removeData(1);
                break;
        }
        return  true;
    }
}
