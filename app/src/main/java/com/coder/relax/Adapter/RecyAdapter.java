package com.coder.relax.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coder.relax.Constant;
import com.coder.relax.Module.ACache;
import com.coder.relax.R;
import com.coder.relax.UI.IconFontTextView;
import com.coder.relax.UI.Rainbow;
import com.coder.relax.activity.FavoriteActivity;
import com.coder.relax.bean.FavoriteBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * Created by GreendaMi on 17/1/2.
 */

public class RecyAdapter extends RecyclerView.Adapter {
    Context mContext;
    ArrayList<FavoriteBean> mList;
    LayoutInflater inflater;

    public RecyAdapter(Context context, ArrayList<FavoriteBean> list) {
        mContext = context;
        mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_choosesave, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder)holder).rainbow.setmList(mList.get(position).getMap());
        ((MyViewHolder) holder).name.setText(mList.get(position).getName());

        ((MyViewHolder)holder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mList.size() > position){
                    mList.remove(position);
                    notifyItemRemoved(position);

                    if(position != mList.size()){
                        notifyItemRangeChanged(position, mList.size() - position);
                    }
                    ACache mCache = ACache.get(mContext);
                    mCache.put(Constant.CACHE_KEY_FAV,mList);
                }
            }
        });
        ((MyViewHolder)holder).mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra(Constant.INTENT_KEY_FAV_PLAY_ITEM, position);
                //设置返回数据
                ((FavoriteActivity)mContext).setResult(RESULT_OK, intent);
                //关闭Activity
                ((FavoriteActivity)mContext).finish();

            }
        });
        ((MyViewHolder)holder).mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ((MyViewHolder)holder).rainbow.setScaleX(0.95f);
                    ((MyViewHolder)holder).rainbow.setScaleY(0.95f);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    ((MyViewHolder)holder).rainbow.setScaleX(1f);
                    ((MyViewHolder)holder).rainbow.setScaleY(1f);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    ((MyViewHolder)holder).rainbow.setScaleX(1f);
                    ((MyViewHolder)holder).rainbow.setScaleY(1f);
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.rainbow)
        Rainbow rainbow;
        @BindView(R.id.delete)
        IconFontTextView delete;
        @BindView(R.id.text_edit)
        TextView name;

        View mView;


        public MyViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

    }

}
