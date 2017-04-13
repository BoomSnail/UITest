package com.xiaoshihua.thinkpad.uitest;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ThinkPad on 2016/9/17.
 */
public abstract class BaseRecycleView<T, H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mDadas;
    protected Context context;
    protected  OnClickListener onClickListener;
    protected int layoutId;
    protected View view;
    private LayoutInflater inflater;

    public BaseRecycleView(Context context, List<T> mDadas,int layoutId) {
        this.mDadas = mDadas;
        this.context = context;
        this.layoutId = layoutId;
        inflater = LayoutInflater.from(context);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(layoutId,parent,false);
        return new BaseViewHolder(view,onClickListener);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T t = getItem(position);
        bindData(holder,t);
    }

    private T getItem(int position) {
        return mDadas.get(position);
    }

    @Override
    public int getItemCount() {
        return mDadas.size();
    }

    public void clearData(){
        mDadas.clear();
        notifyItemRangeChanged(0,mDadas.size());
    }

    public void addData(List<T> datas){
        addData(0,datas);
    }
    public void addData(int position,List<T> datas){
        if (datas != null && datas.size()>0) {
            mDadas.addAll(datas);
            notifyItemRangeChanged(position,mDadas.size());
        }
    }

    public abstract void bindData(BaseViewHolder viewHolder, T t);

    public interface OnClickListener{
        void onItemClick(View view,int position);
    }
}
