package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.bean.HomeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;

import java.util.ArrayList;
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
                return new ChannelViewHolder(inflater.inflate(R.layout.channel_item, null), mContext);

            case ACT:
            case SECKILL:
            case RECOMMEND:
            case HOT:
        }
        return null;
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.gv_channel)
        GridView gvChannel;
        ChannerAdapter channerAdapter;

        private final Context mContext;

        public ChannelViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext =mContext;
            ButterKnife.inject(this,itemView);
        }

        public void setData(List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
            channerAdapter = new ChannerAdapter(mContext, channel_info);
            gvChannel.setAdapter(channerAdapter);

        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private Banner banner;

        public BannerViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.inject(BannerViewHolder.this, itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            List<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }
            //使用
            banner.setImages(images)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);
                        }
                    }).start();
            //设置样式
            banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
            //设置banner的点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //根据类型的位置绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER:
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.setData(datas.getBanner_info());
                break;
            case CHANNEL:
                ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
                channelViewHolder.setData(datas.getChannel_info());
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
        return 2;
    }
}
