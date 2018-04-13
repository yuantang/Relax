package com.coder.relax.Adapter;

import android.content.Context;
import android.graphics.Color;

import com.coder.relax.Listener.onSeekChangeLisner;
import com.coder.relax.R;
import com.coder.relax.UI.CHTextView;
import com.coder.relax.UI.IconFontTextView;
import com.coder.relax.UI.MySeekBar;
import com.coder.relax.base.BaseRecyclerViewAdapter;
import com.coder.relax.base.BaseViewHolder;
import com.coder.relax.bean.NoiseBean;

/**
 * Created by TUS on 2018/3/2.
 */

public class NoiseAdapter extends BaseRecyclerViewAdapter<NoiseBean> {
    private Context context;
    public NoiseAdapter(Context context, int LayoutId) {
        super(context, LayoutId);
        this.context=context;
    }

    @Override
    protected void bindView(BaseViewHolder holder, NoiseBean noiseBean, int position) {

        MySeekBar mySeekBar=holder.getView(R.id.seek_bar);
        mySeekBar.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {

            }
        });
        mySeekBar.setBackgroundColor(Color.parseColor(noiseBean.getColor()));
        CHTextView chTextView= holder.getView(R.id.ch_txt);
        IconFontTextView iconFontTextView= holder.getView(R.id.icon_txt);
        chTextView.setText(noiseBean.getName());
        iconFontTextView.setText(noiseBean.getIcon());

    }


}
