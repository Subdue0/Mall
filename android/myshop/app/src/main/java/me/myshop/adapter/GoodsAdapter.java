package me.myshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



import me.myshop.R;
import me.myshop.common.constant.Mall;
import me.myshop.entity.Goods;

import java.util.List;


public class GoodsAdapter extends BaseAdapter {

    private Context mContext;

    private List<Goods> mGoodsList;   //创建一个Goods 类的对象 集合

    private LayoutInflater mInflater;

    public GoodsAdapter (List<Goods> mGoodsList, Context mContext) {
        this.mContext = mContext;
        this.mGoodsList = mGoodsList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mGoodsList == null?0:mGoodsList.size();  //判断有多少个Item
    }

    @Override
    public Object getItem(int position) {
        return mGoodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view = mInflater.inflate(R.layout.item_home_goods,null);
        Goods goods = (Goods) getItem(position);

        //在view 视图中查找 组件
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_price = view.findViewById(R.id.tv_price);
        ImageView iv_image_icon = view.findViewById(R.id.iv_icon);

        //为Item 里面的组件设置相应的数据
        tv_title.setText(goods.getTitle());
        tv_price.setText(String.valueOf(goods.getPrice()));

        //匿名用户数据
//        iv_image_icon.setImageResource(goods.getImageIcon());

        Glide.with(mContext).load(Mall.BASE_URL + "/images/" + goods.getImageIcon()).placeholder(R.drawable.image_loading).centerCrop().into(iv_image_icon);

        //返回含有数据的view
        return view;
    }

}
