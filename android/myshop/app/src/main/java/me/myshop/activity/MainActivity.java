package me.myshop.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mzlion.easyokhttp.HttpClient;

import java.util.ArrayList;
import java.util.List;

import me.myshop.R;
import me.myshop.adapter.CarAdapter;
import me.myshop.broadcast.NetworkReceiver;
import me.myshop.common.constant.Mall;
import me.myshop.common.utils.JsonParse;
import me.myshop.common.utils.MyData;
import me.myshop.entity.Goods;
import me.myshop.entity.Order;
import me.myshop.entity.Result;
import me.myshop.fragment.HomeFragment;
import me.myshop.fragment.MyFragment;
import me.myshop.fragment.ShoppingCarFragment;


public class MainActivity extends AppCompatActivity implements ShoppingCarFragment.MyCarAdapterListener, MyFragment.MyLogoutListener {
    private BottomNavigationView mNavBottom;
    private List<Fragment> mFragments;

    private LinearLayout mLLNoLogin;

    private FrameLayout mFLContainer;

    //适配器
    private CarAdapter mCarAdapter;

    //上次fragment的位置
    private int mLastPosition;
    //要显示的Fragment
    private Fragment mCurrentFragment;
    //要隐藏的Fragment
    private Fragment mHideFragment;

    private Bundle mSavedInstanceState;

    NetworkReceiver mNetworkChangedReceiver = new NetworkReceiver();

    private Handler mHandler = new Handler();

    //页面显示切换
    public void switchPage(boolean isLogin) {
        if (isLogin) {
            mFLContainer.setVisibility(View.VISIBLE);
            mLLNoLogin.setVisibility(View.GONE);
            mNavBottom.setVisibility(View.VISIBLE);
        } else {
            mFLContainer.setVisibility(View.GONE);
            mLLNoLogin.setVisibility(View.VISIBLE);
            mNavBottom.setVisibility(View.GONE);
        }
    }

    @Override
    public void sendContent(CarAdapter mCarAdapter) {
        this.mCarAdapter = mCarAdapter;
    }

    @Override
    public void sendContent(Result result) {
        if (result.getStatus().equals(Mall.SUCCESS)) {
            Toast.makeText(MainActivity.this, "用户退出成功", Toast.LENGTH_SHORT).show();
            //清除数据
            MyData.getInstance().clearData();
            //切换页面
            switchPage(false);
        } else {
            Toast.makeText(MainActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("last_position",mLastPosition);//activity重建时保存页面的位置
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mLastPosition = savedInstanceState.getInt("last_position");//获取重建时的fragment的位置
        setSelectedFragment(mLastPosition);//恢复销毁前显示的fragment
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 注册“网络变化”广播
         */
        IntentFilter intent_filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkChangedReceiver, intent_filter);

        Button btn_login = findViewById(R.id.btn_login);
        Button btn_register = findViewById(R.id.btn_register);

        mLLNoLogin = findViewById(R.id.ll_no_login);

        mFLContainer = findViewById(R.id.fl_container);

        mSavedInstanceState = savedInstanceState;

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_login = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent_login, 1);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent_register);
            }
        });

    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(ShoppingCarFragment.newInstance());
        mFragments.add(MyFragment.newInstance());
    }

    private void initView(Bundle savedInstanceState) {
        mNavBottom = (BottomNavigationView) findViewById(R.id.nav_bottom);

        if (savedInstanceState == null){
            //根据传入的Bundle对象判断是正常启动还是重建 true表示正常启动，false表示重建
            setSelectedFragment(0);
        }

        mNavBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_home:
                        setSelectedFragment(0);
                        break;
                    case R.id.item_shopping_car:
                        setSelectedFragment(1);
                        break;
                    case R.id.item_my:
                        setSelectedFragment(2);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 根据位置选择Fragment
     * @param position 要选中的fragment的位置
     */
    private void  setSelectedFragment(int position){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mCurrentFragment = fragmentManager.findFragmentByTag("fragment"+position);//要显示的fragment(解决了activity重建时新建实例的问题)
        mHideFragment = fragmentManager.findFragmentByTag("fragment" + mLastPosition);//要隐藏的fragment(解决了activity重建时新建实例的问题)
        if (position != mLastPosition){//如果位置不同
            if (mHideFragment != null){//如果要隐藏的fragment存在，则隐藏
                transaction.hide(mHideFragment);
            }
            if (mCurrentFragment == null){//如果要显示的fragment不存在，则新加并提交事务
                mCurrentFragment = mFragments.get(position);
                transaction.add(R.id.fl_container,mCurrentFragment,"fragment"+position);
            }else {//如果要显示的存在则直接显示
                transaction.show(mCurrentFragment);
            }
        }

        if (position == mLastPosition){//如果位置相同
            if (mCurrentFragment == null){//如果fragment不存在(第一次启动应用的时候)
                mCurrentFragment = mFragments.get(position);
                transaction.add(R.id.fl_container,mCurrentFragment,"fragment"+position);
            }//如果位置相同，且fragment存在，则不作任何操作
        }
        transaction.commit();//提交事务
        mLastPosition = position;//更新要隐藏的fragment的位置
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            String which = intent.getStringExtra("which");
            if (which.equals("login_success")){
                //初始化当前登录用户的所有数据,开启新线程，从数据库中拿到用户的所有需要使用的数据
                initUserData();
            } else if (which.equals("goods_detail")) {
                Goods goods = (Goods) intent.getSerializableExtra("goods");
                if (mCarAdapter == null) {
                    ShoppingCarFragment.mergeAddedGoodsWithoutObject(this, goods);
                } else {
                    ShoppingCarFragment.mergeAddedGoodsWithObject(this, goods);
                }
                //设置选中购物车fragment
                setSelectedFragment(1);
                //设置导航栏选中购物车fragment
                mNavBottom.getMenu().getItem(1).setChecked(true);
            }

        }
    }


    private void initUserData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();

                //拿到数据库中的所有上架商品
                String allGoods = HttpClient
                        .get(Mall.BASE_URL + Mall.GOODS_REL_PATH + "/showallgoods")
                        .execute()
                        .asString();

                List<Goods> goods_list = gson.fromJson(allGoods, new TypeToken<List<Goods>>(){}.getType());
                if (!goods_list.isEmpty()) {
                    MyData.getInstance().setGoodsList(goods_list);
                }

                //拿到数据库中的所有属于该用户的订单
                String myOders = HttpClient
                        .textBody(Mall.BASE_URL + Mall.ORDER_REL_PATH + "/showmyorders")
                        .json(JsonParse.toJson(MyData.getInstance().getUser()))
                        .execute()
                        .asString();

                List<Order> order_list = gson.fromJson(myOders, new TypeToken<List<Order>>(){}.getType());
                if (!order_list.isEmpty()) {
                    MyData.getInstance().setMyOrderList(order_list);
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //创建fragment实例
                        initData();
                        initView(mSavedInstanceState);

                        switchPage(true);

                        //设置导航栏选中首页fragment
                        mNavBottom.getMenu().getItem(0).setChecked(true);
                    }
                });

            }
        }).start();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 注销广播
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetworkChangedReceiver);
    }

}