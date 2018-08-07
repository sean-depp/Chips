package com.so.chips;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.so.chips.R;


public class SeekBarActivity extends AppCompatActivity {

    private Handler mHandler;
    private SeekBar mSbTest;
    private SeekBar mSbTest2;
    private TextView mTvProgress;
    private TextView mTvProgress2;
    private MyRunnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        mTvProgress = (TextView) findViewById(R.id.tv_progress);
        mTvProgress2 = (TextView) findViewById(R.id.tv_progress2);
        findViewById(R.id.cv_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRunnable == null) {
                    mRunnable = new MyRunnable();
                    mHandler.postDelayed(mRunnable, 0);
                }
            }
        });

        findViewById(R.id.cv_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeCallbacks(mRunnable);
                mRunnable = null;
            }
        });

        mHandler = new Handler();

        mSbTest = (SeekBar) findViewById(R.id.sb_test);
        mSbTest2 = (SeekBar) findViewById(R.id.sb_test2);
        mSbTest2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            int progress = mSbTest.getProgress();
            mTvProgress.setText(String.valueOf(progress));
            mSbTest.setProgress(++progress);
            mSbTest.setSecondaryProgress(progress + 10);

            int progress2 = mSbTest2.getProgress();
            mTvProgress2.setText(String.valueOf(progress2));
            mSbTest2.setProgress(++progress2);
            mSbTest2.setSecondaryProgress(progress2 + 20);

            mHandler.postDelayed(this, 50);
        }
    }
}
