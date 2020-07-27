package me.myshop.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.myshop.R;
import me.myshop.common.constant.Mall;
import me.myshop.entity.Goods;
import me.myshop.fragment.ShoppingCarFragment;


public class CarAdapter extends BaseAdapter {
    //界面上下文
    private Context mContext;
    //listview外部控件对象持有者
    private ShoppingCarFragment.ExternalViewHolder mExternalViewHolder;

    //存储listview外部的全选的check状态
    private boolean mSelectAllFlag = false;
    //存储listview中商品总价
    private double mCarTotalCost;
    //存储listview中选中item的总数量
    private int mCountSelectedItem = 0;

    //创建一个存储各item的checked状态的map集合
    private Map<Integer, Boolean> mCheckedMap = new HashMap<>();
    //创建一个商品Goods类的对象集合，也就是listview数据源
    private List<Goods> mGoodsList;

    //布局填充器
    private LayoutInflater mInflater;

    public CarAdapter(List<Goods> mGoodsList, ShoppingCarFragment.ExternalViewHolder externalViewHolder, Context context) {
        this.mContext = context;
        this.mGoodsList = mGoodsList;
        this.mExternalViewHolder = externalViewHolder;
        this.mInflater = LayoutInflater.from(context);
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
        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.position = position;
        convertView = mInflater.inflate(R.layout.item_car_goods, null);

        //初始化item对象绑定
        initItemBind(viewHolder, convertView);
        //初始化item显示
        initItemUI(viewHolder);
        //初始化item数据
        initItemDate(viewHolder);

        //设置事件监听器
        viewHolder.tv_number.setOnClickListener(new MyNumberClickListener(viewHolder));

        //item减少商品数量监听事件（匿名内部类实现）
        viewHolder.btn_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得item数据源
                Goods goods = (Goods) getItem(viewHolder.position);
                //更新增减按钮状态
                int current_number = goods.getNumber();
                int after_number = current_number - 1;
                if (after_number <= 1) {
                    viewHolder.btn_decrease.setEnabled(false);
                    viewHolder.btn_increase.setEnabled(true);
                }
                else if (after_number >= Mall.MAX_GOODS_NUMBER) {
                    viewHolder.btn_decrease.setEnabled(true);
                    viewHolder.btn_increase.setEnabled(false);
                } else {
                    viewHolder.btn_decrease.setEnabled(true);
                    viewHolder.btn_increase.setEnabled(true);
                }
                //更新数据源
                goods.setNumber(after_number);
                //更新数量显示
                viewHolder.tv_number.setText(String.valueOf(after_number));

                //更新总金额数据，当前item状态在选择状态下，才会更新总金额
                boolean current_item_checked = mCheckedMap.get(viewHolder.position);
                if (current_item_checked) {
                    mCarTotalCost -= goods.getPrice();
                }

                //更新总金额显示
                mExternalViewHolder.tv_total_cost.setText(String.valueOf(mCarTotalCost));
            }

        });

        //item增加商品数量监听事件（匿名内部类实现）
        viewHolder.btn_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得item数据源
                Goods goods = (Goods) getItem(viewHolder.position);
                //更新增减按钮状态
                int current_number = goods.getNumber();
                int after_number = current_number + 1;
                //设定商品数量最少为1
                if (after_number <= 1) {
                    viewHolder.btn_decrease.setEnabled(false);
                    viewHolder.btn_increase.setEnabled(true);
                    //设定商品数量最大为200
                } else if (after_number >= Mall.MAX_GOODS_NUMBER) {
                    viewHolder.btn_decrease.setEnabled(true);
                    viewHolder.btn_increase.setEnabled(false);
                } else {
                    viewHolder.btn_decrease.setEnabled(true);
                    viewHolder.btn_increase.setEnabled(true);
                }
                //更新数据源
                goods.setNumber(after_number);
                //更新数量显示
                viewHolder.tv_number.setText(String.valueOf(after_number));

                //更新总金额数据，当前item状态在选择状态下，才会更新总金额
                boolean current_item_checked = mCheckedMap.get(viewHolder.position);
                if (current_item_checked) {
                    mCarTotalCost += goods.getPrice();
                }

                //更新总金额显示
                mExternalViewHolder.tv_total_cost.setText(String.valueOf(mCarTotalCost));
            }
        });

        //item复选框监听事件（匿名内部类实现）
        viewHolder.cb_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCheckedMap.put(viewHolder.position, isChecked);
                Goods goods = (Goods) getItem(viewHolder.position);
                double total_cost = goods.getTotalCost();
                if (isChecked) {
                    mCarTotalCost += total_cost;
                    mCountSelectedItem += 1;
                } else {
                    mCarTotalCost -= total_cost;
                    mCountSelectedItem -= 1;
                }

                //更新全选复选框状态
                if (isSelectAll()) {
                    mExternalViewHolder.cb_all_checked.setChecked(true);
                } else {
                    mExternalViewHolder.cb_all_checked.setChecked(false);
                }

                //更新总金额显示
                mExternalViewHolder.tv_total_cost.setText(String.valueOf(mCarTotalCost));

                mExternalViewHolder.btn_buy.setText("结算("+mCountSelectedItem+")");
            }

            //检查所有item复选框
            private boolean isSelectAll() {
                //默认选择所有，一旦有一个item为false，全选复选框都为false
                boolean select_all_flag = true;
                for(Map.Entry<Integer, Boolean> entry: mCheckedMap.entrySet()) {
                    if (!entry.getValue()) {
                        select_all_flag = false;
                    }
                }
                return select_all_flag;
            }

        });

        //getView方法返回含有数据的view
        return convertView;
    }

    private void initItemBind(ViewHolder viewHolder, View convertView) {
        viewHolder.cb_checked = convertView.findViewById(R.id.cb_checked);
        viewHolder.tv_title = convertView.findViewById(R.id.tv_title);
        viewHolder.tv_price = convertView.findViewById(R.id.tv_price);
        viewHolder.iv_image_icon = convertView.findViewById(R.id.iv_image_icon);
        viewHolder.btn_decrease = convertView.findViewById(R.id.btn_decrease);
        viewHolder.btn_increase = convertView.findViewById(R.id.btn_increase);
        viewHolder.tv_number = convertView.findViewById(R.id.tv_number);
    }

    private void initItemUI(ViewHolder viewHolder) {
        //初始化购物车状态显示
        if (getGoodsList().isEmpty()) {
            mExternalViewHolder.ll_shopping_car_null.setVisibility(View.VISIBLE);
        } else {
            mExternalViewHolder.ll_shopping_car_null.setVisibility(View.GONE);
        }

        //初始化数量显示，加下划线
        viewHolder.tv_number.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线

        //初始化总金额显示
        mExternalViewHolder.tv_total_cost.setText(String.valueOf(mCarTotalCost));

        //初始化结算按钮显示
        if (mCountSelectedItem == 0) {
            mExternalViewHolder.btn_buy.setText("结算(" + 0 + ")");
        }

        //初始化复选框状态显示
        if (mSelectAllFlag) {
            viewHolder.cb_checked.setChecked(true);
        }

        //取得各个item的数据源
        final Goods goods = (Goods) getItem(viewHolder.position);

        //初始化各个item的数据
        viewHolder.tv_title.setText(goods.getTitle());
        viewHolder.tv_price.setText(String.valueOf(goods.getPrice()));
//        viewHolder.iv_image_icon.setImageResource(goods.getImageIcon());

        Glide.with(mContext).load(Mall.BASE_URL + "/images/" + goods.getImageIcon()).fitCenter().centerCrop().placeholder(R.drawable.image_loading).into(viewHolder.iv_image_icon);


        viewHolder.tv_number.setText(String.valueOf(goods.getNumber()));

        //初始化增减数量状态显示
        if (goods.getNumber() <= 1) {
            viewHolder.btn_decrease.setEnabled(false);
        } else if (goods.getNumber() >= Mall.MAX_GOODS_NUMBER) {
            viewHolder.btn_increase.setEnabled(false);
        }

        //保存CheckBox状态，刚加载的时候，item数量不为全部，避免空引用
        if (mCheckedMap.get(viewHolder.position) != null) {
            viewHolder.cb_checked.setChecked(mCheckedMap.get(viewHolder.position));
        }
    }

    private void initItemDate(ViewHolder viewHolder) {
        //初始化所有CheckBox的Checked状态数据
        mCheckedMap.put(viewHolder.position, viewHolder.cb_checked.isChecked());
    }

    public void addGoods(Goods goods) {
        mGoodsList.add(goods);
    }

    public boolean isChecked(int position) {
        //遍历已选择的列表，如果存在直接返回true；如果不存在，返回false，说明传入的position的item没有选择
        for (Integer selected_position : getSelectedPositionList()) {
            if (selected_position == position) {
                return true;
            }
        }
        return false;
    }

    public ShoppingCarFragment.ExternalViewHolder getExternalViewHolder() {
        return mExternalViewHolder;
    }

    public boolean getSelectAll() {
        return mSelectAllFlag;
    }

    public void setSelectAllTag(boolean mSelectAllFlag) {
        this.mSelectAllFlag = mSelectAllFlag;
    }


    public double getCarTotalCost() {
        return mCarTotalCost;
    }

    public void setCarTotalCost(double mCarTotalCost) {
        this.mCarTotalCost = mCarTotalCost;
    }

    public int getCountSelectedItem() {
        return mCountSelectedItem;
    }

    public void setCountSelectedItem(int mCountSelectedItem) {
        this.mCountSelectedItem = mCountSelectedItem;
    }

    public Map<Integer, Boolean> getCheckedMap() {
        return mCheckedMap;
    }

    public void updateCheckedMapItem() {
        mCheckedMap.clear();
        int list_size = getCount();
        for (int i=0; i<=list_size-1; i++) {
            mCheckedMap.put(i, false);
        }
    }


    public List<Integer> getSelectedPositionList() {
        List<Integer> selected_good_list = new ArrayList<>();
        for(Map.Entry<Integer, Boolean> entry: getCheckedMap().entrySet()) {
            if (entry.getValue()) {
                //记录需要删除的商品的position
                selected_good_list.add(entry.getKey());
            }
        }
        //给选中的商品列表按从小到大排序
        Collections.sort(selected_good_list);
        return selected_good_list;
    }


    public List<Goods> getSelectedGoodsList() {
        List<Goods> selected_good_list = new ArrayList<>();
        for (Integer position : getSelectedPositionList()) {
            Goods goods = (Goods) getItem(position);
            selected_good_list.add(goods);
        }
        return selected_good_list;
    }

    public List<Goods> getGoodsList() {
        return mGoodsList;
    }

    public void removeGoodsListItem(int position) {
        mGoodsList.remove(position);
    }

    //计算数据源总金额
    public double countDateTotalCost() {
        double car_total_cost = 0;
        int list_size = getCount();
        for (int i = 0; i < list_size; i++) {
            Goods goods = (Goods) getItem(i);
            car_total_cost += goods.getTotalCost();
        }
        return car_total_cost;
    }

    //设置CheckedMap所有全选或全不选状态
    public void setCheckedMapAllItem(boolean choice) {
        if (choice) {
            for (int i=0; i<getCount(); i++) {
                mCheckedMap.put(i, true);
            }
        } else {
            for (int i=0; i<getCount(); i++) {
                mCheckedMap.put(i, false);
            }
        }
    }

    /**
     * 单个item的对象持有者
     */
    private class ViewHolder {
        private int position;

        private TextView tv_title;
        private TextView tv_price;
        private TextView tv_number;

        private ImageView iv_image_icon;

        private CheckBox cb_checked;

        private Button btn_decrease;
        private Button btn_increase;
    }

    private class MyTextChangeListener implements TextWatcher {
        private EditText mNumber;

        private MyTextChangeListener(EditText mNumber) {
            this.mNumber = mNumber;
        }

        @Override
        public void afterTextChanged(Editable s) {
            /**
             * 用于对话框中的文本输入框的数量限制，如果不加限制，那么int型的数量一旦超出整形范围值，程序直接奔溃
             */
            String number_value = mNumber.getText().toString();

            int max_goods_number = Mall.MAX_GOODS_NUMBER;

            if (number_value.isEmpty() || number_value.equals("0")) {
                mNumber.setText("1");
                mNumber.selectAll();
                Toast.makeText(mContext, "最少购买1件哦！", Toast.LENGTH_SHORT).show();
            } else {
                if (Integer.valueOf(number_value) > max_goods_number) {
                    mNumber.setText(String.valueOf(max_goods_number));
                    mNumber.selectAll();
                    Toast.makeText(mContext, "最多只能购买" + max_goods_number + "件哦！", Toast.LENGTH_SHORT).show();
                }
            }
        }

        //重写覆盖抽象方法
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        //重写覆盖抽象方法
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }

    private class MyNumberClickListener implements View.OnClickListener {
        private ViewHolder viewHolder;

        private MyNumberClickListener(ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View view) {
            final EditText et_number = new EditText(mContext);
            /**
             * 初始化et_number控件
             */
            //限制只能输入整数
            et_number.setInputType(InputType.TYPE_CLASS_NUMBER);
            //初始化数量
            et_number.setText(viewHolder.tv_number.getText());
            //选择数量
            et_number.selectAll();

            //添加文本改变事件监听器
            et_number.addTextChangedListener(new MyTextChangeListener(et_number));
            AlertDialog dialog_input_number = new AlertDialog.Builder(mContext)
                    .setTitle("请输入商品数量")
                    .setView(et_number)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            String number = et_number.getText().toString();
                            //改变前的数量
                            int unchanged_number = Integer.valueOf(viewHolder.tv_number.getText().toString());
                            //改变后的数量
                            int changed_number = Integer.valueOf(number);

                            //更新数量显示
                            viewHolder.tv_number.setText(number);

                            //获取item数据源
                            Goods goods = (Goods) getItem(viewHolder.position);
                            //更新数据源
                            goods.setNumber(changed_number);

                            //更新增减按钮状态
                            if (changed_number <= 1) {
                                viewHolder.btn_decrease.setEnabled(false);
                                viewHolder.btn_increase.setEnabled(true);
                            }
                            else if (changed_number >= Mall.MAX_GOODS_NUMBER) {
                                viewHolder.btn_decrease.setEnabled(true);
                                viewHolder.btn_increase.setEnabled(false);
                            } else {
                                viewHolder.btn_decrease.setEnabled(true);
                                viewHolder.btn_increase.setEnabled(true);
                            }

                            //重新计算总金额，当前编辑item的CheckBox选中时做处理
                            boolean current_item_checked = mCheckedMap.get(viewHolder.position);
                            if (current_item_checked) {
                                //当数量没变化时，不会触发本事件
                                if (unchanged_number < changed_number) {
                                    //数量变大了，做加法运算
                                    mCarTotalCost += goods.getPrice() * (changed_number - unchanged_number);
                                } else if (unchanged_number > changed_number) {
                                    //数量变小了，做减法运算
                                    mCarTotalCost -= goods.getPrice() * (unchanged_number - changed_number);
                                }
                                //更新总金额显示
                                mExternalViewHolder.tv_total_cost.setText(String.valueOf(mCarTotalCost));
                            }
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("取消",null)
                    .show();
            //修改确定按钮颜色
            dialog_input_number.getButton(dialog_input_number.BUTTON_POSITIVE).setTextColor(Color.BLUE);
            //禁止点击 dialog 外部取消弹窗
            dialog_input_number.setCanceledOnTouchOutside(false);
        }
    }

}