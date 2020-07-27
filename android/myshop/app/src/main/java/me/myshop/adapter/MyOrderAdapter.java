package me.myshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


import me.myshop.R;
import me.myshop.entity.Order;


public class MyOrderAdapter extends BaseAdapter {

    private List<Order> mOrderList;

    private LayoutInflater mInflater;

    public MyOrderAdapter(List<Order> mOrderList, Context context) {
        this.mOrderList = mOrderList;
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mOrderList == null?0:mOrderList.size();  //判断有多少个Item
    }

    @Override
    public Object getItem(int position) {
        return mOrderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view = mInflater.inflate(R.layout.item_order_list,null);

        //获取数据源
        Order order = (Order) getItem(position);

        //在view 视图中查找 组件
        TextView tv_no = view.findViewById(R.id.tv_no);
        TextView tv_id = view.findViewById(R.id.tv_id);
        TextView tv_total_cost = view.findViewById(R.id.tv_total_cost);
        TextView tv_logistics_status = view.findViewById(R.id.tv_logistics_status);

        //为Item 里面的组件设置相应的数据
        tv_no.setText(String.valueOf(position + 1));
        tv_id.setText(String.valueOf(order.getId()));
        tv_total_cost.setText(String.valueOf(order.getTotalCost()));
        if (order.getLogisticsStatus() == null) {
            tv_logistics_status.setText("未发货");
        } else {
            tv_logistics_status.setText(order.getLogisticsStatus());
        }

        //返回含有数据的view
        return view;
    }

}
