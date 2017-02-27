package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.bean.HomeBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 何健 on 2017/2/24.
 */

public class HomeAdapter extends RecyclerView.Adapter {


    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;

    public static final int HOT = 5;

    private final Context mContext;
    private final HomeBean.ResultBean datas;

    private int currentType;
    private final LayoutInflater inflater;

    public HomeAdapter(Context context, HomeBean.ResultBean result) {
        this.mContext = context;
        this.datas = result;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    //根据类型创建viewHolder初始化布局
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                return new BannerViewHolder(inflater.inflate(R.layout.banner_viewpager, null), mContext);
            case CHANNEL:
            case ACT:
            case SECKILL:
            case RECOMMEND:
            case HOT:
        }
        return null;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @InjectView(R.id.tv_tilte)
        TextView tvTilte;

        public BannerViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.inject(BannerViewHolder.this,itemView);
        }

        public void setData(List<HomeBean.ResultBean.ActInfoBean> act_info) {
            tvTilte.setText("我是banner!!!");
        }
    }

    //根据类型的位置绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER:
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.setData(datas.getAct_info());
                break;
            case CHANNEL:
                break;
            case ACT:
                break;
            case SECKILL:
                break;
            case RECOMMEND:
                break;
            case HOT:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
