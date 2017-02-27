package com.atguigu.shoppingmall.user;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall.base.BaseFragment;

/**
 * Created by 何健 on 2017/2/24.
 */

public class UserFragment extends BaseFragment {

    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("我是个人中心页面");
    }
}
