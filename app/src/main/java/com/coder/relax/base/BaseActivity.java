package com.coder.relax.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.coder.relax.basemvp.MvpBasePresenter;
import com.coder.relax.basemvp.MvpBaseView;
import com.coder.relax.permission.PermissionReq;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<V extends MvpBaseView, P extends MvpBasePresenter> extends AppCompatActivity {

    private Unbinder unbinder;
    protected P mPresenter;
    private V mView;
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);

        if (this.mPresenter==null){
            this.mPresenter=createPresenter();
        }
        if (this.mView==null){
            this.mView=createView();
        }
        if (mPresenter!=null && mView!=null){
            mPresenter.attachView(mView);
        }
        ActivityCollector.addActivity(this);
        initEventAndData();
        super.onCreate(savedInstanceState);
    }
    protected void setToolBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    protected abstract int getLayout();
    protected abstract P createPresenter();
    protected abstract V createView();
    protected abstract void initEventAndData();
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionReq.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
        unbinder.unbind();
        ActivityCollector.removeActivity(this);
    }
}
