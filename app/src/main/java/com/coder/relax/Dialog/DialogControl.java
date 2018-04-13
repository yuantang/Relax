package com.coder.relax.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.coder.relax.Adapter.BaseAdapter;
import com.coder.relax.Adapter.ViewHolder;
import com.coder.relax.R;
import com.coder.relax.activity.MainActivity;
import com.coder.relax.business.TimeStringConVert;
import com.coder.relax.business.Timer.TickTimer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




/**
 * Created by GreendaMi on 2016/11/26.
 */

public class DialogControl {

    AlertDialog mDialog;
    Context mContext;
    List<String> mDatas ;
    List<String> mShowTimes ;
    TextView mIconFontTextView;
    TickTimer timer;
    Handler mHandler;

    public void initDialog(Context context, TextView iconFontTextView) {
        mContext = context;
        mIconFontTextView = iconFontTextView;
        mDialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mShareMenu = inflater.inflate(R.layout.timedialog,null);
        mHandler = new Handler();
        initdatas();
        ListView mListView = mShareMenu.findViewById(R.id.timelistview);
        mListView.setAdapter(new BaseAdapter<String>(context, mDatas, R.layout.timelistview_item) {
            @Override
            public void convert(ViewHolder helper, final String item, final int position) {
                helper.setText(R.id.timetext,item);
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ((MainActivity)(mContext)).controlTimeView(mShowTimes.get(position));

                        Dismiss();
                    }
                });
            }
        });

        Window window = mDialog.getWindow();
        window.setGravity(Gravity.NO_GRAVITY);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.ButtomDialog);  //添加动画
        mDialog.show();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mDialog.addContentView(mShareMenu,params);
        mDialog.dismiss();
    }


    public void Show(){
        mDialog.show();
        if(timer != null && timer.isRunning()){
            timer.stop();
        }
    }

    public void Dismiss(){
        mDialog.dismiss();
        if(timer != null && !timer.isRunning()){
            timer.start();
        }
    }

    private void initdatas() {
        mDatas = Arrays.asList(mContext.getResources().getStringArray(R.array.timer_time));
        mShowTimes = Arrays.asList(mContext.getResources().getStringArray(R.array.timer_name));
        List<String> temp = new ArrayList<>();
        for (String s:mDatas) {
            temp.add(s);
            Log.d("DialogControl", TimeStringConVert.getTimeString(mContext, s));
        }
        mDatas = temp;
    }
}
