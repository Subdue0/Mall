package me.myshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import me.myshop.R;
import me.myshop.adapter.MyOrderAdapter;
import me.myshop.common.utils.MyData;
import me.myshop.entity.Order;

import java.util.List;

public class MyOrderActivity extends AppCompatActivity {
    private ListView mListView;
    private List<Order> mOrderList;

    private MyOrderAdapter mMyOrderAdapter;

    private LinearLayout mLLListTitle;
    private LinearLayout mLLOrderNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        ImageView iv_back = findViewById(R.id.iv_back);

        mLLListTitle = findViewById(R.id.ll_list_title);
        mLLOrderNull = findViewById(R.id.ll_order_null);

        //加载数据源
        loadingMyOrder();

        //初始化ListView控件
        mListView = findViewById(R.id.lv_order);
        //创建一个Adapter的实例
        mMyOrderAdapter = new MyOrderAdapter(mOrderList, this);
        //设置Adapter
        mListView.setAdapter(mMyOrderAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order order = (Order) parent.getAdapter().getItem(position);
                Intent intent_order_detail = new Intent(MyOrderActivity.this, OrderDetailActivity.class);
                intent_order_detail.putExtra("from", "my_order");
                intent_order_detail.putExtra("order", order);
                startActivityForResult(intent_order_detail, 1);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //加载数据源
    private void loadingMyOrder() {
        if (MyData.getInstance().getMyOrderList().isEmpty()) {
            mLLListTitle.setVisibility(View.GONE);
            mLLOrderNull.setVisibility(View.VISIBLE);
        } else {
            //如果订单列表不为空，把列表传给成员变量mOrderList
            mOrderList = MyData.getInstance().getMyOrderList();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            if (intent.getStringExtra("which").equals("order_detail")) {
                mMyOrderAdapter.notifyDataSetChanged();
                if (mOrderList.isEmpty()) {
                    mLLListTitle.setVisibility(View.GONE);
                    mLLOrderNull.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}