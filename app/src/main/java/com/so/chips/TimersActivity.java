package com.so.chips;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.so.chips.R;


public class TimersActivity extends AppCompatActivity implements View.OnClickListener {

    private Chronometer mTimer;
    private TextView mTvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timers);

        initUI();
    }

    /**
     * 初始化自定义ui
     */
    private void initUI() {
        mTimer = (Chronometer) findViewById(R.id.timer);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        CardView cvAddTenHour = (CardView) findViewById(R.id.cv_add_ten_hour);
        CardView cvAddHour = (CardView) findViewById(R.id.cv_add_hour);
        CardView cvAddMin = (CardView) findViewById(R.id.cv_add_min);
        CardView cvRemainHour = (CardView) findViewById(R.id.cv_remain_hour);
        CardView cvRemainMin = (CardView) findViewById(R.id.cv_remain_min);
        CardView cvStart = (CardView) findViewById(R.id.cv_start1);
        CardView cvStop = (CardView) findViewById(R.id.cv_stop1);
        CardView cvStart2 = (CardView) findViewById(R.id.cv_start2);
        CardView cvStop2 = (CardView) findViewById(R.id.cv_stop2);

        cvAddTenHour.setOnClickListener(this);
        cvAddHour.setOnClickListener(this);
        cvAddMin.setOnClickListener(this);
        cvRemainHour.setOnClickListener(this);
        cvRemainMin.setOnClickListener(this);
        cvStart.setOnClickListener(this);
        cvStop.setOnClickListener(this);
        cvStart2.setOnClickListener(this);
        cvStop2.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_add_ten_hour:
                mTimer.setBase(-35999000 + SystemClock.elapsedRealtime());
                mTimer.setCountDown(false);
                mTimer.start();
                break;
            case R.id.cv_add_hour:
                mTimer.setBase(-3600000 + SystemClock.elapsedRealtime());
                mTimer.setCountDown(false);
                mTimer.start();
                break;
            case R.id.cv_add_min:
                mTimer.setBase(-60000 + SystemClock.elapsedRealtime());
                mTimer.setCountDown(false);
                mTimer.start();
                break;
            case R.id.cv_remain_hour: {
                mTimer.setBase(SystemClock.elapsedRealtime() + 3600000);
                mTimer.setCountDown(true);

                final long base = mTimer.getBase();
                mTimer.start();
                mTimer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer mTimer) {
                        long time = SystemClock.elapsedRealtime();

                        if (time >= base) {
                            if (mTimer.isCountDown())
                                mTimer.stop();
                        }
                    }
                });
            }
            break;
            case R.id.cv_remain_min: {
                mTimer.setBase(SystemClock.elapsedRealtime() + 60000);
                mTimer.setCountDown(true);

                final long base = mTimer.getBase();
                mTimer.start();
                mTimer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    @Override
                    public void onChronometerTick(Chronometer mTimer) {
                        long time = SystemClock.elapsedRealtime();

                        if (time >= base) {
                            if (mTimer.isCountDown())
                                mTimer.stop();
                        }
                    }
                });
            }
            break;
            case R.id.cv_start1:
                mTimer.setBase(SystemClock.elapsedRealtime());
                mTimer.setCountDown(false);
                mTimer.start();
                break;
            case R.id.cv_stop1:
                mTimer.stop();
                break;
            case R.id.cv_start2:
                mCountDownTimer.start();
                break;
            case R.id.cv_stop2:
                mCountDownTimer.cancel();
                break;
        }
    }

    private CountDownTimer mCountDownTimer = new CountDownTimer(10000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            String str = "剩余" + (millisUntilFinished / 1000) + "秒";
            mTvTime.setText(str);
        }

        @Override
        public void onFinish() {
            mTvTime.setEnabled(true);
            mTvTime.setText("倒计时结束");
        }
    };
}