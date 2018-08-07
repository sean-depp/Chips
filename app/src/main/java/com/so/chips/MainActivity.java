package com.so.chips;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView cvArrayList = (CardView) findViewById(R.id.cv_array_list);
        CardView cvRe = (CardView) findViewById(R.id.cv_re);
        CardView cvTimer = (CardView) findViewById(R.id.cv_timer);
        CardView cvSeekBar = (CardView) findViewById(R.id.cv_seek_bar);

        cvArrayList.setOnClickListener(this);
        cvRe.setOnClickListener(this);
        cvTimer.setOnClickListener(this);
        cvSeekBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_array_list: {
                Intent intent = new Intent(MainActivity.this, ArrayListActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.cv_re: {
                Intent intent = new Intent(MainActivity.this, REActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.cv_timer: {
                Intent intent = new Intent(MainActivity.this, TimersActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.cv_seek_bar: {
                Intent intent = new Intent(MainActivity.this, SeekBarActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
