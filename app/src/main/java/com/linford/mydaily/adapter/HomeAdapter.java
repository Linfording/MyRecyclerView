package com.linford.mydaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linford.mydaily.R;

import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    List<String> mDatas;
    Context context;
    LayoutInflater inflater;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    private  OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public HomeAdapter(List<String> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_home, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        holder.mTextView= (TextView) view.findViewById(R.id.mTextView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mTextView.setText(mDatas.get(position));

        if (mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public void addData(int position){
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
//    这里更新数据集不是用adapter.notifyDataSetChanged()而是
//    notifyItemInserted(position)与notifyItemRemoved(position)
//    否则没有动画效果。
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

}
