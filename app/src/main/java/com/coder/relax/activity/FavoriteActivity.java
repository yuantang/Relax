package com.coder.relax.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.coder.relax.Adapter.RecyAdapter;
import com.coder.relax.Constant;
import com.coder.relax.Module.ACache;
import com.coder.relax.R;
import com.coder.relax.UI.IconFontTextView;
import com.coder.relax.UI.Rainbow;
import com.coder.relax.bean.FavoriteBean;
import com.coder.relax.utils.InputUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GreendaMi on 17/1/1.
 */

public class FavoriteActivity extends Activity {
    String TAG="FavoriteActivity";
    @BindView(R.id.recycle)
    RecyclerView recycle;
    RecyclerView.Adapter mAdapter;
    @BindView(R.id.add_fav_name_edit)
    EditText addFavNameEdit;
    @BindView(R.id.rainbow)
    Rainbow rainbow;
    @BindView(R.id.add_fav_img)
    IconFontTextView addFavImg;
    @BindView(R.id.add_fav_rly)
    RelativeLayout addFavRly;
    private ACache mCache;
    private Map likeItem;
    private ArrayList<FavoriteBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosesave);
        ButterKnife.bind(this);
        mCache = ACache.get(this);
        mList = (ArrayList<FavoriteBean>) mCache.getAsObject(Constant.CACHE_KEY_FAV);
        if (mList==null){
            mList=new ArrayList<>();
        }
        if (getIntent().hasExtra(Constant.INTENT_KEY_FAV)) {
            addFavRly.setVisibility(View.VISIBLE);
            likeItem = (Map) getIntent().getSerializableExtra(Constant.INTENT_KEY_FAV);
            rainbow.setmList(likeItem);
        }else {
            addFavRly.setVisibility(View.GONE);
            InputUtils.hidekeybord(recycle);
        }
        initView();
    }

    private void initView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(mLayoutManager);
        recycle.setHasFixedSize(true);
        mAdapter = new RecyAdapter(this, mList);
        recycle.setAdapter(mAdapter);
    }

    @OnClick(R.id.add_fav_img)
    public void onClick() {
        String name=addFavNameEdit.getText().toString();
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this,"请给收藏取一个名字吧！",Toast.LENGTH_SHORT).show();
            return;
        }
        FavoriteBean favoriteBean = new FavoriteBean();
        favoriteBean.setName(name);
        favoriteBean.setMap((HashMap) likeItem);
        mList.add(0,favoriteBean);
        mCache.put(Constant.CACHE_KEY_FAV,mList);
        mAdapter.notifyDataSetChanged();
        addFavRly.setVisibility(View.GONE);
        InputUtils.hidekeybord(addFavNameEdit);
    }
}
