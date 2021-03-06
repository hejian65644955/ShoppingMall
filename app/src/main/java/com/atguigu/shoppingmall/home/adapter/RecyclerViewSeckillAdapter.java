package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.bean.HomeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 何健 on 2017/3/2.
 */
public class RecyclerViewSeckillAdapter extends RecyclerView.Adapter<RecyclerViewSeckillAdapter.ViewHolder> {
    private final Context mContext;
    private final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> datas;


    public RecyclerViewSeckillAdapter(Context mContext, HomeBean.ResultBean.SeckillInfoBean seckill_info) {
        this.mContext = mContext;
        this.datas = seckill_info.getList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_seckill, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(datas,position);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_figure)
        ImageView ivFigure;
        @InjectView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @InjectView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @InjectView(R.id.ll_root)
        LinearLayout llRoot;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemClick(itemView,getLayoutPosition());
                    }
                }
            });
        }


        public void setData(List<HomeBean.ResultBean.SeckillInfoBean.ListBean> datas, int position) {
            HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = datas.get(position);
            //2.绑定数据
            tvCoverPrice.setText("￥"+listBean.getCover_price());
            tvOriginPrice.setText("￥"+listBean.getOrigin_price());
            Glide.with(mContext)
                    .load(Constants.BASE_URL_IMAGE+listBean.getFigure())
                    .into(ivFigure);

        }
    }

    /**
     * 点击item的接口
     */
    public interface OnItemClickListener{
        public void onItemClick(View v,int position);
    }

    private OnItemClickListener listener;

    /**
     * 设置item的点击事件
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
