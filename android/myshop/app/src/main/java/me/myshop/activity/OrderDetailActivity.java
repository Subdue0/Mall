package me.myshop.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mzlion.easyokhttp.HttpClient;

import java.util.ArrayList;
import java.util.List;

import me.myshop.R;
import me.myshop.common.constant.Mall;
import me.myshop.common.utils.JsonParse;
import me.myshop.common.utils.MyData;
import me.myshop.entity.Goods;
import me.myshop.entity.Order;
import me.myshop.entity.RecInfo;
import me.myshop.entity.Result;
import me.myshop.entity.User;
import me.myshop.entity.UserAndOrder;

public class OrderDetailActivity extends AppCompatActivity {

    private Order mOrder;
    private double mAllGoodsTotalCost;
    //数据源
    private List<Goods> mGoodsList = new ArrayList<>();

    private EditText mETReceiverName;
    private EditText mETReceiverPhone;
    private EditText mETReceiverAddress;

    private String mPayMode;
    private RecInfo mRecInfo;

    //布局填充器
    private LayoutInflater mInflater;

    private Handler mHandler = new Handler();

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        mInflater = LayoutInflater.from(this);

        ImageView iv_back = findViewById(R.id.iv_back);

        mETReceiverName = findViewById(R.id.et_receiver_name);
        mETReceiverPhone = findViewById(R.id.et_receiver_phone);
        mETReceiverAddress = findViewById(R.id.et_receiver_address);

        //订单创建时间
        final RelativeLayout rl_create_time = findViewById(R.id.rl_create_time);
        final TextView tv_create_time = findViewById(R.id.tv_create_time);

        //支付方式
        final TextView tv_pay_mode = findViewById(R.id.tv_pay_mode);
        ImageView iv_more = findViewById(R.id.iv_more);

        //物流进度
        LinearLayout ll_logistics_progress = findViewById(R.id.ll_logistics_progress);
        TextView tv_logistics_progress = findViewById(R.id.tv_logistics_progress);

        //操作按钮
        Button btn_cancel = findViewById(R.id.btn_cancel);
        Button btn_pay = findViewById(R.id.btn_pay);
        Button btn_modify = findViewById(R.id.btn_modify);
        Button btn_delete = findViewById(R.id.btn_delete);

        //商品清单和总价
        TableLayout tl_buy_list = findViewById(R.id.tl_buy_list);
        TextView tv_total_cost = findViewById(R.id.tv_total_cost);

        //操作按钮显示切换
        LinearLayout ll_pay_order = findViewById(R.id.ll_pay_order);
        LinearLayout ll_manager_order = findViewById(R.id.ll_manager_order);

        String from = getIntent().getStringExtra("from");
        switch(from){
            case "shopping_car" :
                readRecInfo();

                mGoodsList = (List<Goods>) getIntent().getSerializableExtra("goods_list");
                mAllGoodsTotalCost = getIntent().getDoubleExtra("all_goods_total_cost", 0);

                for (int i = 0; i < mGoodsList.size(); i++) {
                    Goods goods = mGoodsList.get(i);

                    View row = mInflater.inflate(R.layout.item_goods_list, null);
                    TextView tv_no = row.findViewById(R.id.tv_no);
                    TextView tv_title = row.findViewById(R.id.tv_title);
                    TextView tv_number = row.findViewById(R.id.tv_number);
                    TextView tv_price = row.findViewById(R.id.tv_price);
                    TextView tv_goods_total_cost = row.findViewById(R.id.tv_total_cost);

                    tv_no.setText(String.valueOf(i + 1));
                    tv_title.setText(String.valueOf(goods.getTitle()));
                    tv_number.setText(String.valueOf(goods.getNumber()));
                    tv_price.setText(String.valueOf(goods.getPrice()));
                    tv_goods_total_cost.setText(String.valueOf(goods.getTotalCost()));

                    tl_buy_list.addView(row);
                }
                tv_total_cost.setText(String.valueOf(mAllGoodsTotalCost));

                break;
            case "goods_detail" :
                readRecInfo();

                Goods goods = (Goods) getIntent().getSerializableExtra("goods");
                mGoodsList.add(goods);

                View row = mInflater.inflate(R.layout.item_goods_list, null);
                TextView tv_no = row.findViewById(R.id.tv_no);
                TextView tv_title = row.findViewById(R.id.tv_title);
                TextView tv_number = row.findViewById(R.id.tv_number);
                TextView tv_price = row.findViewById(R.id.tv_price);
                TextView tv_goods_total_cost = row.findViewById(R.id.tv_total_cost);

                tv_no.setText(String.valueOf(1));
                tv_title.setText(String.valueOf(goods.getTitle()));
                tv_number.setText(String.valueOf(goods.getNumber()));
                tv_price.setText(String.valueOf(goods.getPrice()));
                tv_goods_total_cost.setText(String.valueOf(goods.getTotalCost()));

                tl_buy_list.addView(row);

                mAllGoodsTotalCost = goods.getTotalCost();
                tv_total_cost.setText(String.valueOf(mAllGoodsTotalCost));

                break;
            case "my_order" :
                mOrder = (Order) getIntent().getSerializableExtra("order");

                ll_pay_order.setVisibility(View.GONE);
                ll_manager_order.setVisibility(View.VISIBLE);

                mETReceiverName.setText(mOrder.getRecInfo().getName());
                mETReceiverPhone.setText(mOrder.getRecInfo().getPhone());
                mETReceiverAddress.setText(mOrder.getRecInfo().getAddress());

                rl_create_time.setVisibility(View.VISIBLE);
                tv_create_time.setText(mOrder.getCreateTime());

                tv_pay_mode.setText(mOrder.getPayMode());

                ll_logistics_progress.setVisibility(View.VISIBLE);

                if (mOrder.getLogisticsStatus().equals("未发货")) {
                    tv_logistics_progress.setText("商品未发货!");
                } else if (mOrder.getLogisticsStatus().equals("已发货")) {
                    tv_logistics_progress.setText(mOrder.getLogisticsProgress());
                }

                mGoodsList = mOrder.getGoodsList();
                for (int i = 0; i < mGoodsList.size(); i++) {
                    goods = mGoodsList.get(i);

                    row = mInflater.inflate(R.layout.item_goods_list, null);
                    tv_no = row.findViewById(R.id.tv_no);
                    tv_title = row.findViewById(R.id.tv_title);
                    tv_number = row.findViewById(R.id.tv_number);
                    tv_price = row.findViewById(R.id.tv_price);
                    tv_goods_total_cost = row.findViewById(R.id.tv_total_cost);

                    tv_no.setText(String.valueOf(i + 1));
                    tv_title.setText(String.valueOf(goods.getTitle()));
                    tv_number.setText(String.valueOf(goods.getNumber()));
                    tv_price.setText(String.valueOf(goods.getPrice()));
                    tv_goods_total_cost.setText(String.valueOf(goods.getTotalCost()));

                    tl_buy_list.addView(row);
                }

                mAllGoodsTotalCost = mOrder.getTotalCost();
                tv_total_cost.setText(String.valueOf(mAllGoodsTotalCost));

                break;
            default :
                break;
        }

        iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items = {"微信", "支付宝"};

                //初始值为0表示选择微信，如果是支付宝则表示为1
                int pay_mode_choice = 0;
                if (tv_pay_mode.getText().toString().equals("支付宝")) {
                    pay_mode_choice = 1;
                }

                AlertDialog dialog_choice = new AlertDialog.Builder(OrderDetailActivity.this)
                        .setTitle("请选择一种支付方式")
                        .setSingleChoiceItems(items, pay_mode_choice, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    tv_pay_mode.setText("微信");
                                } else if (which == 1) {
                                    tv_pay_mode.setText("支付宝");
                                }
                                dialog.dismiss();
                            }
                        })
                        .show();
                //禁止点击 dialog 外部取消弹窗
                dialog_choice.setCanceledOnTouchOutside(false);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPayMode = tv_pay_mode.getText().toString();

                String name = mETReceiverName.getText().toString();
                String phone = mETReceiverPhone.getText().toString();
                String address = mETReceiverAddress.getText().toString();
                if (name.equals("") || phone.equals("") || address.equals("")) {
                    new AlertDialog.Builder(OrderDetailActivity.this)
                            .setTitle("提示")
                            .setMessage("请填写完整收件人信息")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                } else {
                    //本地存储收件人信息
                    writeRecInfo();

                    RecInfo rec_info = new RecInfo(name, phone, address);


                    User user = MyData.getInstance().getUser();

                    mOrder = new Order(user.getUid(), mPayMode, mAllGoodsTotalCost, "未发货", rec_info, mGoodsList);

                    final UserAndOrder userAndOrder = new UserAndOrder();
                    userAndOrder.setUser(user);
                    userAndOrder.setOrder(mOrder);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String responseData = HttpClient
                                    .textBody(Mall.BASE_URL + Mall.ORDER_REL_PATH + "/submit")
                                    .json(JsonParse.toJson(userAndOrder))
                                    .execute()
                                    .asString();

                            final Result result = JsonParse.fromJson(responseData, Result.class);

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (result.getStatus().equals(Mall.SUCCESS)) {
                                        Toast.makeText(OrderDetailActivity.this, "提交成功", Toast.LENGTH_SHORT).show();

                                        mOrder.setId(Integer.valueOf(result.getId()));
                                        mOrder.setCreateTime(result.getOrderCreateTime());

                                        MyData.getInstance().addMyOrderList(mOrder);

                                        //结束activity
                                        finish();
                                        //使用特殊关闭activity的动画覆盖默认的动画
                                        overridePendingTransition(R.anim.unchanged, R.anim.slide_in_top);
                                    } else {
                                        Toast.makeText(OrderDetailActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }).start();

                }
            }
        });


        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mETReceiverName.getText().toString();
                String phone = mETReceiverPhone.getText().toString();
                String address = mETReceiverAddress.getText().toString();

                mPayMode = tv_pay_mode.getText().toString();

                if (name.equals("") || phone.equals("") || address.equals("")) {
                    new AlertDialog.Builder(OrderDetailActivity.this)
                            .setTitle("提示")
                            .setMessage("请填写完整收件人信息")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                } else {

                    mRecInfo = new RecInfo(name, phone, address);
                    User user = MyData.getInstance().getUser();
                    Order order = new Order(mOrder.getId(), user.getUid(), mPayMode, mRecInfo);

                    final UserAndOrder userAndOrder = new UserAndOrder();
                    userAndOrder.setUser(user);
                    userAndOrder.setOrder(order);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String responseData = HttpClient
                                    .textBody(Mall.BASE_URL + Mall.ORDER_REL_PATH + "/modify")
                                    .json(JsonParse.toJson(userAndOrder))
                                    .execute()
                                    .asString();

                            final Result result = JsonParse.fromJson(responseData, Result.class);

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (result.getStatus().equals(Mall.SUCCESS)) {
                                        Toast.makeText(OrderDetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                        MyData.getInstance().modifyOrder(mOrder.getId(), mRecInfo, mPayMode);
                                        finish();
                                        //使用特殊关闭activity的动画覆盖默认的动画
                                        overridePendingTransition(R.anim.unchanged, R.anim.slide_in_top);
                                    } else {
                                        Toast.makeText(OrderDetailActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }).start();


                }

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(OrderDetailActivity.this)
                        .setTitle("提示")
                        .setMessage("是否删除当前订单？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final User user = MyData.getInstance().getUser();

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String responseData = HttpClient
                                                .textBody(Mall.BASE_URL + Mall.ORDER_REL_PATH + "/delete")
                                                .queryString("id", mOrder.getId())
                                                .json(JsonParse.toJson(user))
                                                .execute()
                                                .asString();

                                        final Result result = JsonParse.fromJson(responseData, Result.class);

                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (result.getStatus().equals(Mall.SUCCESS)) {
                                                    Toast.makeText(OrderDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                                    MyData.getInstance().deleteOrder(mOrder.getId());

                                                    //设置结果和来源，用于更新我的订单
                                                    Intent intent_home = getIntent();
                                                    intent_home.putExtra("which", "order_detail");
                                                    setResult(RESULT_OK, intent_home);

                                                    finish();
                                                    //使用特殊关闭activity的动画覆盖默认的动画
                                                    overridePendingTransition(R.anim.unchanged, R.anim.slide_in_top);
                                                } else {
                                                    Toast.makeText(OrderDetailActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                    }
                                }).start();

                                dialog.dismiss();

                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                //修改确定按钮颜色
                dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
                //禁止点击 dialog 外部取消弹窗
                dialog.setCanceledOnTouchOutside(false);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void readRecInfo() {
        SharedPreferences sp = getSharedPreferences("receiver_info",MODE_PRIVATE);

        String rec_name = sp.getString("receiver_name", null);
        String rec_phone = sp.getString("receiver_phone",null);
        String rec_address = sp.getString("receiver_address",null);

        //提取保存的收件人信息显示到界面
        if (rec_name != null) {
            mETReceiverName.setText(rec_name);
        }
        if (rec_phone != null) {
            mETReceiverPhone.setText(rec_phone);
        }
        if (rec_address != null) {
            mETReceiverAddress.setText(rec_address);
        }

    }

    private void writeRecInfo() {
        SharedPreferences sp = getSharedPreferences("receiver_info",MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences("receiver_info",MODE_PRIVATE).edit();

        String rec_name = sp.getString("receiver_name", null);
        String rec_phone = sp.getString("receiver_phone",null);
        String rec_address = sp.getString("receiver_address",null);

        String receiver_name = mETReceiverName.getText().toString();
        String receiver_phone = mETReceiverPhone.getText().toString();
        String receiver_address = mETReceiverAddress.getText().toString();

        //本地提取出来的收件人信息哪个是空的就保存哪个
        if (rec_name == null) {
            editor.putString("receiver_name", receiver_name);
        } else {
            if (!receiver_name.equals(rec_name)) {
                editor.putString("receiver_name", receiver_name);
            }
        }
        if (rec_phone == null) {
            editor.putString("receiver_phone", receiver_phone);
        } else {
            if (!receiver_phone.equals(rec_phone)) {
                editor.putString("receiver_phone", receiver_phone);
            }
        }
        if (rec_address == null) {
            editor.putString("receiver_address", receiver_address);
        } else {
            if (!receiver_address.equals(rec_address)) {
                editor.putString("receiver_address", receiver_address);
            }
        }
        editor.commit();
    }

}