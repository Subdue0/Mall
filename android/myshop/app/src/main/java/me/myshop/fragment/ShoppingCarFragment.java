package me.myshop.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import me.myshop.R;
import me.myshop.activity.OrderDetailActivity;
import me.myshop.adapter.CarAdapter;
import me.myshop.common.constant.Mall;
import me.myshop.entity.Goods;
import me.myshop.entity.ShoppingCar;

public class ShoppingCarFragment extends Fragment {
    private View mRootView;

    //数据源
    private static List<Goods> mGoodsList = new ArrayList<>();

    private ListView mListView;

    private static CarAdapter mCarAdapter;
    private MyCarAdapterListener mMyCarAdapterListener;

    public static ShoppingCarFragment newInstance() {
        Bundle args = new Bundle();
        ShoppingCarFragment fragment = new ShoppingCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface MyCarAdapterListener{
        void sendContent(CarAdapter mCarAdapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMyCarAdapterListener = (MyCarAdapterListener) getActivity();
    }
    

    public static void mergeAddedGoodsWithoutObject(Context context, Goods goods) {
//        AnonymousUser.LoadingData(mGoodsList);

        int max_goods_number = Mall.MAX_GOODS_NUMBER;

        int id = goods.getId();
        boolean has_same_flag = false;

        for (int position = 0; position < mGoodsList.size(); position++) {
            Goods goods_item = mGoodsList.get(position);
            if (goods_item.getId() == id) {
                has_same_flag = true;
                /**
                 * 有相同商品就直接在原来的基础上叠加数量，上限200
                 */
                int added_number = goods_item.getNumber() + goods.getNumber();
                if (added_number >= max_goods_number) {
                    //exile_number表示数量距离上限200的增量，就是添加进购物车的实际数量
                    int exile_number = max_goods_number - goods_item.getNumber();
                    //设置下实际添加进购物车的数量为增量
                    goods.setNumber(exile_number);
                    //设置下添加商品后的数量为200
                    goods_item.setNumber(max_goods_number);
                    //设置下实际添加进购物车的数量为增量
                    goods.setNumber(exile_number);

                    //原有商品已满两百件
                    if (exile_number == 0) {
                        Toast.makeText(context, "添加失败，原有商品已满" + max_goods_number + "件", Toast.LENGTH_LONG).show();
                    } else if (exile_number > 0) {
                        if (added_number > max_goods_number) {
                            //添加商品数量后大于200时，弹出提示
                            Toast.makeText(context, "添加了" + exile_number + "件商品，商品已满" + max_goods_number + "件", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    //添加商品数量后小于200时直接设为添加后的商品数量
                    goods_item.setNumber(added_number);
                }
                break;
            }
        }
        //没有相同商品就新添一种
        if (!has_same_flag) {
            mGoodsList.add(goods);
        }
    }

    public static void mergeAddedGoodsWithObject(Context context, Goods goods) {
        int id = goods.getId();
        boolean has_same_flag = false;

        int max_goods_number = Mall.MAX_GOODS_NUMBER;

        for (int position = 0; position < mGoodsList.size(); position++) {
            Goods goods_item = mGoodsList.get(position);
            if (goods_item.getId() == id) {
                has_same_flag = true;
                /**
                 * 有相同商品就直接在原来的基础上叠加数量，上限200
                 */
                int added_number = goods_item.getNumber() + goods.getNumber();
                if (added_number >= max_goods_number) {
                    //exile_number表示数量距离上限200的增量，就是添加进购物车的实际数量
                    int exile_number = max_goods_number - goods_item.getNumber();
                    //设置下实际添加进购物车的数量为增量
                    goods.setNumber(exile_number);
                    //设置下添加商品后的数量为200
                    goods_item.setNumber(max_goods_number);
                    //设置下实际添加进购物车的数量为增量
                    goods.setNumber(exile_number);

                    //原有商品已满200件
                    if (exile_number == 0) {
                        Toast.makeText(context, "添加失败，原有商品已满" + max_goods_number + "件", Toast.LENGTH_LONG).show();
                    } else if (exile_number > 0)  {
                        if (added_number > max_goods_number) {
                            //添加商品数量后大于200时，弹出提示
                            Toast.makeText(context, "添加了" + exile_number + "件商品，商品已满" + max_goods_number + "件", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    //添加商品数量后小于200时直接设为添加后的商品数量
                    goods_item.setNumber(added_number);
                }

                //添加的商品被选择
                if (mCarAdapter.isChecked(position)) {
                    double total_cost = mCarAdapter.getCarTotalCost();
                    //更新总金额数据
                    mCarAdapter.setCarTotalCost(total_cost + goods.getTotalCost());
                }
                break;
            }
        }
        //没有相同商品就新添一种
        if (!has_same_flag) {
            mCarAdapter.addGoods(goods);
            //添加后，全选复选框标志位false，
            mCarAdapter.setSelectAllTag(false);
            //添加后，全选checkbox显示为不全选
            mCarAdapter.getExternalViewHolder().cb_all_checked.setChecked(false);
        }
        //刷新购物车列表
        mCarAdapter.notifyDataSetChanged();
    }


    /**
     * activity重建后保存数据状态
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = inflater.inflate(R.layout.fragment_shopping_car, null);
        }
        return mRootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //绑定对象持有者，用于listview内部控制外部
        final ExternalViewHolder externalViewHolder = new ExternalViewHolder();
        externalViewHolder.cb_all_checked = getActivity().findViewById(R.id.cb_all);
        externalViewHolder.tv_total_cost = getActivity().findViewById(R.id.tv_total_cost);
        externalViewHolder.btn_buy = getActivity().findViewById(R.id.btn_buy);

        externalViewHolder.ll_shopping_car_null = getActivity().findViewById(R.id.ll_shopping_car_null);

        //绑定对象
        ImageView iv_delete = getActivity().findViewById(R.id.iv_delete);
        ImageView iv_save = getActivity().findViewById(R.id.iv_save);

        //如果列表为空，表示没有初始值，则加载默认数据
//        if (mGoodsList.isEmpty()) {
//            AnonymousUser.LoadingData(mGoodsList);
//        }

        if (mGoodsList.isEmpty()) {
            externalViewHolder.ll_shopping_car_null.setVisibility(View.VISIBLE);
        }

        //初始化ListView控件
        mListView = getActivity().findViewById(R.id.lv_car);

        mCarAdapter = new CarAdapter(mGoodsList, externalViewHolder, getActivity());
        //将内容进行回传
        mMyCarAdapterListener.sendContent(mCarAdapter);

        //设置Adapter
        mListView.setAdapter(mCarAdapter);

        //删除按钮
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCarAdapter.getGoodsList().isEmpty()) {
                    Toast.makeText(getActivity(), "购物车为空，请加入商品", Toast.LENGTH_SHORT).show();
                } else {
                    final List<Integer> list_position = mCarAdapter.getSelectedPositionList();
                    if (list_position.isEmpty()) {
                        Toast.makeText(getActivity(), "请选择需要删除的商品", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                                .setTitle("提示")
                                .setMessage("是否删除这" + mCarAdapter.getCountSelectedItem() + "种商品？")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //总金额数据归零
                                        mCarAdapter.setCarTotalCost(0);
                                        //总金额显示归零
                                        externalViewHolder.tv_total_cost.setText("0.0");

                                        //恢复复选框状态显示
                                        externalViewHolder.cb_all_checked.setChecked(false);

                                        //所选item数量归零
                                        mCarAdapter.setCountSelectedItem(0);
                                        //结算按钮显示归零
                                        externalViewHolder.btn_buy.setText("结算(" + 0 + ")");

                                        /**
                                         * 此处数据源的删除，涉及列表的多项删除，必须采用倒序删除，否则会影响到位置索引，大大增加删除难度
                                         */
                                        int list_size = list_position.size();
                                        for (int i = 0; i <= list_size - 1; i++) {
                                            //此处的max_value必须用int包装类Integer，因为列表的remove方法对int和Integer（Object）做了重载，
                                            // 如果用int，那么在列表中删除的就是下标为int值的对象
                                            Integer max_value = Collections.max(list_position);
                                            list_position.remove(max_value);
                                            mCarAdapter.removeGoodsListItem(max_value);
                                        }
                                        //更新所选按钮状态显示
                                        mCarAdapter.updateCheckedMapItem();
                                        //刷新listview列表显示
                                        mCarAdapter.notifyDataSetChanged();

                                        //更新购物车状态显示
                                        if (mCarAdapter.getGoodsList().isEmpty()) {
                                            externalViewHolder.ll_shopping_car_null.setVisibility(View.VISIBLE);
                                        }
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
                }
            }
        });

        //保存按钮
        iv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "购物车存储待开发", Toast.LENGTH_SHORT).show();

                double total_cost = mCarAdapter.getCarTotalCost();
                Map<Integer, Boolean> checked_map = mCarAdapter.getCheckedMap();
                List<Goods> goods_list = mCarAdapter.getGoodsList();
                ShoppingCar shopping_car = new ShoppingCar(total_cost, checked_map, goods_list);
            }
        });

        //全选复选框，监听点击事件
        externalViewHolder.cb_all_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (externalViewHolder.cb_all_checked.isChecked()) {
                    mCarAdapter.setSelectAllTag(true);
                    mCarAdapter.setCarTotalCost(mCarAdapter.countDateTotalCost());
                    mCarAdapter.setCheckedMapAllItem(true);

                    mCarAdapter.setCountSelectedItem(mCarAdapter.getCount());
                    externalViewHolder.btn_buy.setText("结算(" + mCarAdapter.getCount() + ")");

                    mCarAdapter.notifyDataSetChanged();
                } else {
                    mCarAdapter.setSelectAllTag(false);
                    mCarAdapter.setCarTotalCost(0);
                    mCarAdapter.setCheckedMapAllItem(false);

                    mCarAdapter.setCountSelectedItem(0);
                    externalViewHolder.btn_buy.setText("结算(" + 0 + ")");

                    mCarAdapter.notifyDataSetChanged();
                }
            }
        });

        //结算按钮，监听点击事件
        externalViewHolder.btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Goods> selected_good_list = (ArrayList<Goods>) mCarAdapter.getSelectedGoodsList();
                if (mCarAdapter.getGoodsList().isEmpty()) {
                    Toast.makeText(getActivity(), "亲，赶快去选购喜欢的商品吧！", Toast.LENGTH_SHORT).show();
                } else {
                    if (!selected_good_list.isEmpty()) {
                        Intent intent_order_detail = new Intent(getActivity(), OrderDetailActivity.class);
                        intent_order_detail.putExtra("from", "shopping_car");
                        intent_order_detail.putExtra("goods_list", selected_good_list);
                        intent_order_detail.putExtra("all_goods_total_cost", mCarAdapter.getCarTotalCost());
                        startActivity(intent_order_detail);
                    }
                    else {
                        Toast.makeText(getActivity(), "亲，至少选择一件商品才能结算哦！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public class ExternalViewHolder {
        public CheckBox cb_all_checked;
        public TextView tv_total_cost;
        public Button btn_buy;

        public LinearLayout ll_shopping_car_null;
    }

}