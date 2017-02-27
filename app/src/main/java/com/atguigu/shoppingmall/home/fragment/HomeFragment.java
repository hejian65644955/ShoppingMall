package com.atguigu.shoppingmall.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.bean.HomeBean;
import com.atguigu.shoppingmall.home.adapter.HomeAdapter;
import com.atguigu.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by 何健 on 2017/2/24.
 */

public class HomeFragment extends BaseFragment {

    @InjectView(R.id.tv_search_home)
    TextView tvSearchHome;
    @InjectView(R.id.tv_message_home)
    TextView tvMessageHome;
    @InjectView(R.id.rv_home)
    RecyclerView rvHome;
    @InjectView(R.id.ib_top)
    ImageButton ibTop;
    private HomeAdapter homeAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                //地址
                .url(Constants.HOME_URL)
                .id(100)//http
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "请求失败==" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "请求成功==");
                        //解析数据
                        processData(response);

                    }
                });

    }

    private void processData(String response) {
        HomeBean homeBean = JSON.parseObject(response, HomeBean.class);
        Log.e("TAG", "解析成功==" + homeBean.getResult().getAct_info().get(0).getName());
        //设置RecyclerView适配器
        homeAdapter = new HomeAdapter(mContext, homeBean.getResult());
        rvHome.setAdapter(homeAdapter);
        //设置布局管理器
        rvHome.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_search_home, R.id.tv_message_home, R.id.ib_top})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search_home:
                break;
            case R.id.tv_message_home:
                break;
            case R.id.ib_top:
                break;
        }
    }
}
