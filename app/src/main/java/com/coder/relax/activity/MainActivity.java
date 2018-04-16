package com.coder.relax.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.coder.relax.Constant;
import com.coder.relax.Listener.onSeekChangeLisner;
import com.coder.relax.Module.ACache;
import com.coder.relax.R;
import com.coder.relax.UI.MySeekBar;
import com.coder.relax.bean.FavoriteBean;
import com.coder.relax.business.DecSecond;
import com.coder.relax.business.Timer.TickTimer;
import com.coder.relax.business.Timer.TimeSpan;
import com.coder.relax.business.VoiceController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GreendaMi on 2017/1/17.
 */

public class MainActivity extends Activity {
    private static final String TAG="MainActivity";
    @BindView(R.id.seek1)
    MySeekBar mSeekOne;
    @BindView(R.id.seek2)
    MySeekBar mSeekTwo;
    @BindView(R.id.seek3)
    MySeekBar mSeekThree;
    @BindView(R.id.seek4)
    MySeekBar mSeekFour;
    @BindView(R.id.seek5)
    MySeekBar mSeekFive;
    @BindView(R.id.seek6)
    MySeekBar mSeekSix;
    @BindView(R.id.seek7)
    MySeekBar mSeekSeven;
    @BindView(R.id.seek8)
    MySeekBar mSeekEight;
    @BindView(R.id.seek9)
    MySeekBar mSeekNine;
    @BindView(R.id.seek10)
    MySeekBar mSeekTen;
    @BindView(R.id.seek11)
    MySeekBar mSeekEleven;
    @BindView(R.id.seek12)
    MySeekBar mSeekTwelve;
    @BindView(R.id.seek13)
    MySeekBar mSeekThirteen;
    @BindView(R.id.seek14)
    MySeekBar mSeekFourteen;
    @BindView(R.id.seek15)
    View mSeekFavorites;
    @BindView(R.id.play)
    TextView mPlay;
    @BindView(R.id.timer)
    TextView mTimer;

    VoiceController mVoiceController;
    Handler mHandler;
    TickTimer timer;

    HashMap mMap = new HashMap();
    @BindView(R.id.add)
    TextView add;
    ArrayList<FavoriteBean> mFavorite = new ArrayList<>();
    String times[]={"00:00:00","00:01:00","00:03:00","00:05:00","00:10:00","00:15:00",
            "00:20:00","00:30:00","00:40:00","00:45:00","00:50:00","01:00:00","02:00:00",
            "04:00:00","06:00:00","08:00:00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mVoiceController = new VoiceController();
        mVoiceController.init(this);

        mHandler = new Handler();
        initEvent();

    }

    /**
     * 注册回调
     */
    private void initEvent() {
        mSeekOne.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(1, per);
            }
        });
        mSeekTwo.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(2, per);
            }
        });
        mSeekThree.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(3, per);
            }
        });
        mSeekFour.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(4, per);
            }
        });
        mSeekFive.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(5, per);
            }
        });
        mSeekSix.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(6, per);
            }
        });
        mSeekSeven.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(7, per);
            }
        });
        mSeekEight.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(8, per);
            }
        });
        mSeekNine.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(9, per);
            }
        });
        mSeekTen.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(10, per);
            }
        });
        mSeekEleven.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(11, per);
            }
        });
        mSeekTwelve.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(12, per);
            }
        });
        mSeekThirteen.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(13, per);
            }
        });
        mSeekFourteen.setOnSeekChangeLisner(new onSeekChangeLisner() {
            @Override
            public void onChange(int per) {
                control(14, per);
            }
        });
        mSeekFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, FavoriteActivity.class);
                        startActivityForResult(intent,1);
                    }
                },200);

            }
        });

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVoiceController.playOrPause();
                mSeekOne.setCurrent(0);
                mSeekTwo.setCurrent(0);
                mSeekThree.setCurrent(0);
                mSeekFour.setCurrent(0);
                mSeekFive.setCurrent(0);
                mSeekSix.setCurrent(0);
                mSeekSeven.setCurrent(0);
                mSeekEight.setCurrent(0);
                mSeekNine.setCurrent(0);
                mSeekTen.setCurrent(0);
                mSeekEleven.setCurrent(0);
                mSeekTwelve.setCurrent(0);
                mSeekThirteen.setCurrent(0);
                mSeekFourteen.setCurrent(0);
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                mMap.clear();
            }
        });
        mPlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mPlay.setScaleX(0.9f);
                    mPlay.setScaleY(0.9f);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    mPlay.setScaleX(1f);
                    mPlay.setScaleY(1f);
                }
                return false;
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMap.size()>0){
                    Intent intent=new Intent(MainActivity.this,FavoriteActivity.class);
                    intent.putExtra(Constant.INTENT_KEY_FAV,mMap);
                    startActivity(intent);
                }
            }
        });

        add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    add.setScaleX(0.9f);
                    add.setScaleY(0.9f);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    add.setScaleX(1f);
                    add.setScaleY(1f);
                }
                return false;
            }
        });

        mTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.set_timer_txt)
                        .setItems(times,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        controlTimeView(times[which]);
                                    }
                                }).show();

            }
        });

        mTimer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mTimer.setScaleX(0.9f);
                    mTimer.setScaleY(0.9f);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    mTimer.setScaleX(1f);
                    mTimer.setScaleY(1f);
                }
                return false;
            }
        });

    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVoiceController.playOrPause();
    }


    private void SHowNotification() {
        NotificationManager manger = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //为了版本兼容  选择V7包下的NotificationCompat进行构造
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //Ticker是状态栏显示的提示
        builder.setTicker(getResources().getString(R.string.app_name));
        //第一行内容  通常作为通知栏标题
        builder.setContentTitle(getString(R.string.is_running));
        //第二行内容 通常是通知正文
        builder.setContentText(getString(R.string.be_relax));
        //第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
        //ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
        //builder.setContentInfo("2");
        //number设计用来显示同种通知的数量和ContentInfo的位置一样，如果设置了ContentInfo则number会被隐藏
//        builder.setNumber(1);
        //可以点击通知栏的删除按钮删除
        builder.setAutoCancel(false);
        //系统状态栏显示的小图标
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //下拉显示的大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 1, intent, 0);
        //点击跳转的intent
        builder.setContentIntent(pIntent);
        //通知默认的声音 震动 呼吸灯
        builder.setDefaults(NotificationCompat.FLAG_FOREGROUND_SERVICE);
        Notification notification = builder.build();
        manger.notify(177, notification);
    }

    public void control(int index, int per) {
        mVoiceController.voiceControl(index, per);
        SHowNotification();
        mMap.remove(index);
        if (per != 0) {
            mMap.put(index, per);
        }
        if (mMap.isEmpty()) {
            NotificationManager manger = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
            manger.cancel(177);
        }

    }

    /**
     * 控制计时器倒计时
     *
     * @param startTime
     */
    public void controlTimeView(final String startTime) {
        if(timer != null && timer.isRunning()){
            timer.stop();
        }
        timer = new TickTimer(TimeSpan.fromSeconds(1f), new Runnable() {
            String mTime = startTime;
            @Override
            public void run() {
                if (!mTime.equals("00:00:00") && !mTime.equals("计时")) {
                    mTime = DecSecond.DecOneSecond(mTime);
                } else {
                    mTime = "计时";
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTimer.setText(mTime);

                        if (mTime.equals("00:00:00")) {
                            mPlay.callOnClick();
                        }
                        if (!timer.isRunning()) {
                            mTimer.setText("计时");
                        }
                    }
                });
            }
        });
        timer.start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int result = -1;
        if(data != null){
            result = data.getExtras().getInt(Constant.INTENT_KEY_FAV_PLAY_ITEM, -1);
        }
        ACache mCache = ACache.get(MainActivity.this);
        if(mCache.getAsObject(Constant.CACHE_KEY_FAV) != null){
            mFavorite = (ArrayList<FavoriteBean>) mCache.getAsObject(Constant.CACHE_KEY_FAV);
        }
        if(mFavorite.size() > result && result != -1){
            mMap = mFavorite.get(result).getMap();
            //重置播放状态
            mVoiceController.playOrPause();
            mSeekOne.setCurrent(0);
            mSeekTwo.setCurrent(0);
            mSeekThree.setCurrent(0);
            mSeekFour.setCurrent(0);
            mSeekFive.setCurrent(0);
            mSeekSix.setCurrent(0);
            mSeekSeven.setCurrent(0);
            mSeekEight.setCurrent(0);
            mSeekNine.setCurrent(0);
            mSeekTen.setCurrent(0);
            mSeekEleven.setCurrent(0);
            mSeekTwelve.setCurrent(0);
            mSeekThirteen.setCurrent(0);
            mSeekFourteen.setCurrent(0);

            SHowNotification();
            Iterator<Map.Entry<Integer, Integer>> it = mMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> entry = it.next();
                int in = entry.getKey();
                int value = entry.getValue();
                mVoiceController.voiceControl(in,value);

                switch (in){
                    case 1:
                        mSeekOne.setCurrent(value);
                        break;
                    case 2:
                        mSeekTwo.setCurrent(value);
                        break;
                    case 3:
                        mSeekThree.setCurrent(value);
                        break;
                    case 4:
                        mSeekFour.setCurrent(value);
                        break;
                    case 5:
                        mSeekFive.setCurrent(value);
                        break;
                    case 6:
                        mSeekSix.setCurrent(value);
                        break;
                    case 7:
                        mSeekSeven.setCurrent(value);
                        break;
                    case 8:
                        mSeekEight.setCurrent(value);
                        break;
                    case 9:
                        mSeekNine.setCurrent(value);
                        break;
                    case 10:
                        mSeekTen.setCurrent(value);
                        break;
                    case 11:
                        mSeekEleven.setCurrent(value);
                        break;
                    case 12:
                        mSeekTwelve.setCurrent(value);
                        break;
                    case 13:
                        mSeekThirteen.setCurrent(value);
                        break;
                    case 14:
                        mSeekFourteen.setCurrent(value);
                        break;
                }
            }
        }
    }
}