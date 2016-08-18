package com.linford.mydaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linford.mydaily.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
public class PullAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    List<String> mDatas;
    Context context;
    LayoutInflater inflater;
    List<Integer> heights;

    private OnItemClickListener mOnItemClickListener;

    public PullAdapter(List<String> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
        inflater=LayoutInflater.from(context);
        heights=new ArrayList<Integer>();
        for (int i = 0; i <mDatas.size(); i++) {
            heights.add((int) (100 + Math.random() * 300));
        }
    }
    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
        void OnItemLongClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_home, parent, false);
        HomeAdapter.MyViewHolder holder = new HomeAdapter.MyViewHolder(view);
        holder.mTextView= (TextView) view.findViewById(R.id.mTextView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final HomeAdapter.MyViewHolder holder, int position) {
        ViewGroup.LayoutParams lp = holder.mTextView.getLayoutParams();
        lp.height = heights.get(position);

        holder.mTextView.setLayoutParams(lp);
        holder.mTextView.setText(mDatas.get(position));

        if (mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickListener.OnItemClick(holder.itemView,pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickListener.OnItemLongClick(holder.itemView,pos);
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
        mDatas.add(position, "1");
        heights.add(position,(int) (100 + Math.random() * 300));
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        heights.remove(position);
        notifyItemRemoved(position);
    }

}
