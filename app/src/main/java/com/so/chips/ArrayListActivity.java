package com.so.chips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.so.chips.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> mList;
    private EditText mEtAdd;
    private EditText mEtAddIndex;
    private EditText mEtDel;
    private EditText mEtDelIndex;
    private EditText mEtSet;
    private EditText mEtSetIndex;
    private EditText mEtCheck;
    private TextView mTvContent;
    private String mCheck = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arraylist);

        ArrayList<String> list = new ArrayList<>();
        list.add("java");
        list.add("c");
        list.add("c++");
        String[] strings = list.toArray(new String[0]);
        Log.i("tag", Arrays.toString(strings));

        init();
        initUI();
    }

    private void init() {
        mList = new ArrayList<>();
    }

    private void initUI() {
        mEtAdd = (EditText) findViewById(R.id.et_add);
        mEtAddIndex = (EditText) findViewById(R.id.et_add_index);
        mEtDel = (EditText) findViewById(R.id.et_del);
        mEtDelIndex = (EditText) findViewById(R.id.et_del_index);
        mEtSet = (EditText) findViewById(R.id.et_set);
        mEtSetIndex = (EditText) findViewById(R.id.et_set_index);
        mEtCheck = (EditText) findViewById(R.id.et_check);
        mTvContent = (TextView) findViewById(R.id.tv_content);

        CardView cvAdd = (CardView) findViewById(R.id.cv_add);
        CardView cvDel = (CardView) findViewById(R.id.cv_del);
        CardView cvSet = (CardView) findViewById(R.id.cv_set);
        CardView cvCheck = (CardView) findViewById(R.id.cv_check);
        CardView cvClear = (CardView) findViewById(R.id.cv_clear);
        cvAdd.setOnClickListener(this);
        cvDel.setOnClickListener(this);
        cvSet.setOnClickListener(this);
        cvCheck.setOnClickListener(this);
        cvClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_add:
                String add = mEtAdd.getText().toString();
                String addIndex = mEtAddIndex.getText().toString();

                if (!TextUtils.isEmpty(add)) {
                    if (!TextUtils.isEmpty(addIndex)) {
                        int addI = Integer.valueOf(addIndex);
                        if (addI <= mList.size())
                            mList.add(addI, add);
                    } else {
                        mList.add(add);
                    }
                }
                check();
                log();
                break;
            case R.id.cv_del:
                String del = mEtDel.getText().toString();
                String delIndex = mEtDelIndex.getText().toString();
                if (!TextUtils.isEmpty(del)) {
                    mList.remove(del);
                }
                if (!TextUtils.isEmpty(delIndex)) {
                    int delI = Integer.valueOf(delIndex);
                    if (delI < mList.size())
                        mList.remove(delI);
                }
                check();
                log();
                break;
            case R.id.cv_set:
                String set = mEtSet.getText().toString();
                String setIndex = mEtSetIndex.getText().toString();
                if (!TextUtils.isEmpty(set) && !TextUtils.isEmpty(setIndex)) {
                    int setI = Integer.valueOf(setIndex);
                    if (setI < mList.size())
                        mList.set(Integer.valueOf(setIndex), set);
                }
                check();
                log();
                break;
            case R.id.cv_check:
                String check = mEtCheck.getText().toString();
                if (!TextUtils.isEmpty(check)) {
                    if (mList.contains(check)) {
                        mCheck = check + "存在. 首次出现: "
                                + mList.indexOf(check) + " 末次出现: "
                                + mList.lastIndexOf(check);
                    } else {
                        mCheck = check + "不存在.";
                    }
                } else {
                    mCheck = "";
                }
                log();
                break;
            case R.id.cv_clear:
                mList.clear();
                check();
                log();
                break;
        }
    }

    public void log() {
        String[] strings = mList.toArray(new String[0]);
        String result;
        if (TextUtils.isEmpty(mCheck)) {
            result = Arrays.toString(strings) + " 总个数: " + mList.size();
        } else {
            result = Arrays.toString(strings) + mCheck + " 总个数: " + mList.size();
        }
        mTvContent.setText(result);
    }

    public void check() {
        String check = mEtCheck.getText().toString();
        if (!TextUtils.isEmpty(check)) {
            if (mList.contains(check)) {
                mCheck = check + "存在. 首次出现: "
                        + mList.indexOf(check) + " 末次出现: "
                        + mList.lastIndexOf(check);
            } else {
                mCheck = check + "不存在.";
            }
        } else {
            mCheck = "";
        }
    }
}
