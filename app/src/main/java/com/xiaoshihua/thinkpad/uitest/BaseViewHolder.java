package com.xiaoshihua.thinkpad.uitest;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by ThinkPad on 2016/9/17.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private SparseArray<View> views;
    private BaseRecycleView.OnClickListener onClickListener;
    public BaseViewHolder(View itemView, BaseRecycleView.OnClickListener onClickListener) {
        super(itemView);
        views = new SparseArray<>();
        this.onClickListener = onClickListener;
        itemView.setOnClickListener(this);
    }

    private View getView(int id){
        return findView(id);
    }
    //获取绑定的视图
    private <T extends View> T findView(int id){

        View view = views.get(id);
        if (view == null) {
            view  = itemView.findViewById(id);
            views.put(id,view);
        }
        return (T) view;
    }

    public TextView getTextView(int id){
        return findView(id);
    }

    public ImageView getImageView(int id){
        return findView(id);
    }

    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onItemClick(v,getLayoutPosition());
        }
    }
}
