package me.myshop.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.Arrays;
import java.util.List;

import me.myshop.R;
import me.myshop.common.constant.Mall;
import me.myshop.entity.Goods;

public class GoodsDetailActivity extends AppCompatActivity {
    //商品数据源
    private Goods mGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_detail);


        //获取商品数据源
        mGoods = (Goods) getIntent().getSerializableExtra("goods");


        TextView tv_price_pre = findViewById(R.id.tv_price_pre);
        //添加删除线
        tv_price_pre.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        List<String> banner_images = Arrays.asList(mGoods.getImageDetail().split(","));

//        //轮播图测试
//        List<Integer> banner_images= new ArrayList<>();
//        banner_images.add(R.drawable.adv_1);
//        banner_images.add(R.drawable.adv_2);
//        banner_images.add(R.drawable.adv_3);

        Banner banner = findViewById(R.id.banner_goods_detail);
        TextView tv_price = findViewById(R.id.tv_price);
        TextView tv_title = findViewById(R.id.tv_title);
        final EditText number = findViewById(R.id.et_number);
        //添加文本改变事件监听器
        number.addTextChangedListener(new MyTextChangeListener(number));

        Button btn_buy = findViewById(R.id.btn_buy);
        Button btn_add_shopping_car = findViewById(R.id.btn_add_shopping_car);

        tv_price.setText(String.valueOf(mGoods.getPrice()));
        tv_title.setText(mGoods.getTitle());

        banner.setImages(banner_images).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
//                //本地图片加载
//                imageView.setImageResource((int)path);
                Glide.with(context).load(Mall.BASE_URL + "/images/" + path).centerCrop().placeholder(R.drawable.image_loading).into(imageView);

            }
        }).start();


        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_order_detail = new Intent(GoodsDetailActivity.this, OrderDetailActivity.class);
                intent_order_detail.putExtra("from", "goods_detail");
                mGoods.setNumber(Integer.valueOf(number.getText().toString()));
                intent_order_detail.putExtra("goods", mGoods);
                startActivity(intent_order_detail);
                finish();
            }
        });

        btn_add_shopping_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_shopping_car = getIntent();
                //把用户输入的数量回传到父Activity（MainActivity）
                mGoods.setNumber(Integer.valueOf(number.getText().toString()));
                intent_shopping_car.putExtra("which", "goods_detail");
                intent_shopping_car.putExtra("goods", mGoods);
                setResult(RESULT_OK, intent_shopping_car);

                finish();
                //回退到首页，使用特殊关闭activity的动画覆盖默认的动画
                overridePendingTransition(R.anim.unchanged, R.anim.slide_in_top);
            }
        });
    }


    /**
     *    点击空白处,隐藏键盘并且清除焦点
     */
    //事件分发控制
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
                view.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    /**
     * 判定是否需要隐藏
     */
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    /**
     * 隐藏软键盘
     */
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
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
            int max_goods_number = Mall.MAX_GOODS_NUMBER;

            String number_value = mNumber.getText().toString();
            if (number_value.isEmpty() || number_value.equals("0")) {
                mNumber.setText("1");
                mNumber.selectAll();
                Toast.makeText(GoodsDetailActivity.this, "最少购买1件哦！", Toast.LENGTH_SHORT).show();
            } else {
                if (Integer.valueOf(number_value) > max_goods_number) {
                    mNumber.setText(String.valueOf(max_goods_number));
                    mNumber.selectAll();
                    Toast.makeText(GoodsDetailActivity.this, "最多只能购买" + max_goods_number + "件哦！", Toast.LENGTH_SHORT).show();
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

}