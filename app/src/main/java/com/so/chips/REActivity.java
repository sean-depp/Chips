package com.so.chips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.so.chips.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re);

        initUI();
    }

    /**
     * 初始化ui
     */
    private void initUI() {
        // 获取控件
        mTvResult = (TextView) findViewById(R.id.tv_result);

        CardView cvMatchNum = (CardView) findViewById(R.id.cv_match_num);
        CardView cvMatchLowerCase = (CardView) findViewById(R.id.cv_match_lower_case);
        CardView cvMatchUpperCase = (CardView) findViewById(R.id.cv_match_upper_case);
        CardView cvMatchAllCase = (CardView) findViewById(R.id.cv_match_all_case);
        CardView cvMatchCustom = (CardView) findViewById(R.id.cv_match_custom);
        CardView cvAllMatch = (CardView) findViewById(R.id.cv_all_match);

        // 设置监听
        cvMatchNum.setOnClickListener(this);
        cvMatchLowerCase.setOnClickListener(this);
        cvMatchUpperCase.setOnClickListener(this);
        cvMatchAllCase.setOnClickListener(this);
        cvMatchCustom.setOnClickListener(this);
        cvAllMatch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Pattern p;
        Matcher m;
        switch (v.getId()) {
            case R.id.cv_match_num:
                p = Pattern.compile("\\d+");
                m = p.matcher(getMatchStr());
                logResult(m);
                break;

            case R.id.cv_match_lower_case:
                p = Pattern.compile("[a-z]+");
                m = p.matcher(getMatchStr());
                logResult(m);
                break;

            case R.id.cv_match_upper_case:
                p = Pattern.compile("[A-Z]+");
                m = p.matcher(getMatchStr());
                logResult(m);
                break;

            case R.id.cv_match_all_case:
                p = Pattern.compile("[a-zA-Z0-9]+");
                m = p.matcher(getMatchStr());
                logResult(m);
                break;

            case R.id.cv_match_custom:
                p = Pattern.compile(getRule());
                m = p.matcher(getMatchStr());
                logResult(m);
                break;

            case R.id.cv_all_match:
                p = Pattern.compile(getRule());
                m = p.matcher(getMatchStr());
                String r = "result: " + m.matches();
                mTvResult.setText(r);
                break;
        }
    }

    /**
     * 打印匹配结果
     *
     * @param m 匹配结果
     */
    private void logResult(Matcher m) {
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            sb.append(m.group());
            sb.append(" start: ");
            sb.append(m.start());
            sb.append(" end: ");
            sb.append(m.end());
            sb.append(" ");
        }
        mTvResult.setText(sb.toString());
    }

    /**
     * 获取输入
     *
     * @return 获取到的输入
     */
    private String getMatchStr() {
        EditText etMatchStr = (EditText) findViewById(R.id.et_match_str);
        return etMatchStr.getText().toString();
    }

    /**
     * 获取规则
     *
     * @return 获取到的规则
     */
    private String getRule() {
        EditText etRule = (EditText) findViewById(R.id.et_rule);
        return etRule.getText().toString();
    }
}
