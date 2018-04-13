package com.coder.relax;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.coder.relax.Adapter.NoiseAdapter;
import com.coder.relax.UI.CHTextView;
import com.coder.relax.base.BaseActivity;
import com.coder.relax.basemvp.MvpBasePresenter;
import com.coder.relax.basemvp.MvpBaseView;
import com.coder.relax.bean.NoiseBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.add)
    CHTextView add;
    @BindView(R.id.play)
    CHTextView play;
    @BindView(R.id.timer)
    CHTextView timer;
    @BindView(R.id.line)
    LinearLayout line;


    private NoiseAdapter noiseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected MvpBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected MvpBaseView createView() {
        return null;
    }

    @Override
    protected void initEventAndData() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recycle.setLayoutManager(gridLayoutManager);

        noiseAdapter=new NoiseAdapter(this,R.layout.layout_noise_recycle_item);
        recycle.setAdapter(noiseAdapter);

        List<NoiseBean> list=new ArrayList<>();
        String[] noiseName= getResources().getStringArray(R.array.noise_name);
        String[] noiseIcon= getResources().getStringArray(R.array.noise_icon);
        String[] noiseColor= getResources().getStringArray(R.array.noise_color);
        for (int i=0;i<noiseName.length;i++ ){
            NoiseBean noiseBean=new NoiseBean();
            noiseBean.setName(noiseName[i]);
            noiseBean.setIcon(noiseIcon[i]);
            noiseBean.setColor(noiseColor[i]);
            list.add(noiseBean);
        }

        noiseAdapter.upData(list);
    }

    @OnClick({R.id.add, R.id.play, R.id.timer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                break;
            case R.id.play:
                break;
            case R.id.timer:
                break;
        }
    }
}
