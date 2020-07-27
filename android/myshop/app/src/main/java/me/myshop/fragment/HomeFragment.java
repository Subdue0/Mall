package me.myshop.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mzlion.easyokhttp.HttpClient;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import me.myshop.R;
import me.myshop.activity.GoodsDetailActivity;
import me.myshop.adapter.GoodsAdapter;
import me.myshop.common.constant.GoodsTypeEnum;
import me.myshop.common.constant.Mall;
import me.myshop.common.utils.MyData;
import me.myshop.entity.Goods;

public class HomeFragment extends Fragment {
    private View mRootView;

    private static List<Goods> mGoodsList = new ArrayList<>();

    private List<Goods> mSingleTypeGoodsList = new ArrayList<>();

    private GoodsAdapter mGoodsAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private String mCurrentType;

    private ListView mListView;

    private int[] id = new int[]{2, 3, 4, 5};
    private String[] titles = new String[]{"i3", "i5", "i7", "i9"};
    private double[] prices = new double[]{500, 1000, 1500, 2000};
    private int[] images_icon = new int[]{R.drawable.i3, R.drawable.i5, R.drawable.i7, R.drawable.i9};

    private Handler mHandler = new Handler();

    public static HomeFragment newInstance() {
        //获取所有商品数据源
        mGoodsList = MyData.getInstance().getGoodsList();

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = inflater.inflate(R.layout.fragment_home, null);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Integer> banner_images= new ArrayList<>();
        banner_images.add(R.drawable.adv_1);
        banner_images.add(R.drawable.adv_2);
        banner_images.add(R.drawable.adv_3);
        banner_images.add(R.drawable.adv_4);
        banner_images.add(R.drawable.adv_5);

        Banner banner = getActivity().findViewById(R.id.banner_home);
        banner.setImages(banner_images).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageResource((int)path);
            }
        }).start();

        ImageView iv_cpu = getActivity().findViewById(R.id.iv_cpu);
        ImageView iv_gpu = getActivity().findViewById(R.id.iv_gpu);
        ImageView iv_disk = getActivity().findViewById(R.id.iv_disk);
        ImageView iv_ram = getActivity().findViewById(R.id.iv_ram);


        mSwipeRefreshLayout = getActivity().findViewById(R.id.srf_swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_blue_bright, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

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

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //刷新完成
                                mSwipeRefreshLayout.setRefreshing(false);

                                //更新所有商品数据源
                                mGoodsList = MyData.getInstance().getGoodsList();

                                //获得当前显示的列表类型
                                GoodsTypeEnum goodsType = GoodsTypeEnum.compare(mCurrentType);
                                //取得最新的数据源
                                switchGoodsList(goodsType);
                                //更新列表
                                mGoodsAdapter.notifyDataSetChanged();
                            }
                        }, 4000);

                    }
                }).start();


            }
        });

        View.OnClickListener listener = new MyImageViewClickListener();
        iv_cpu.setOnClickListener(listener);
        iv_gpu.setOnClickListener(listener);
        iv_disk.setOnClickListener(listener);
        iv_ram.setOnClickListener(listener);

//        //匿名用户数据
//        for (int i = 0; i<titles.length; i++) {
//            Goods goods = new Goods();
//            goods.setId(id[i]);
//            goods.setTitle(titles[i]);
//            goods.setPrice(prices[i]);
//            goods.setImageIcon(images_icon[i]);
//            mGoodsList.add(goods);
//        }


        //给cpu列表装载数据
        switchGoodsList(GoodsTypeEnum.CPU);


        //初始化ListView控件
        mListView = getActivity().findViewById(R.id.lv_home);
        //创建一个Adapter的实例
        mGoodsAdapter = new GoodsAdapter(mSingleTypeGoodsList, getActivity());
        //设置Adapter
        mListView.setAdapter(mGoodsAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Goods goods = (Goods) parent.getAdapter().getItem(position);
                Intent intent_goods_detail = new Intent(getActivity(), GoodsDetailActivity.class);
                intent_goods_detail.putExtra("goods", goods);
                startActivityForResult(intent_goods_detail, 1);
            }
        });


    }

    private void switchGoodsList(GoodsTypeEnum goodsType) {
        mSingleTypeGoodsList.clear();
        for (Goods goods : mGoodsList) {
            if (goods.getType().equals(Integer.valueOf(String.valueOf(goodsType)))) {
                mSingleTypeGoodsList.add(goods);
            }
        }
        mCurrentType = String.valueOf(goodsType);
    }


    private class MyImageViewClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_cpu:
//                    mGoodsList.clear();
//                    id = new int[]{2, 3, 4, 5};
//                    titles = new String[]{"i3", "i5", "i7", "i9"};
//                    prices = new double[]{500, 1000, 1500, 2000};
//                    images_icon = new int[]{R.drawable.i3, R.drawable.i5, R.drawable.i7, R.drawable.i9};
//                    for (int i = 0; i<titles.length; i++) {
//                        Goods goods = new Goods();
//                        goods.setId(id[i]);
//                        goods.setTitle(titles[i]);
//                        goods.setPrice(prices[i]);
////                        goods.setImageIcon(images_icon[i]);
//                        mGoodsList.add(goods);
//                    }
                    //给cpu列表装载数据
                    switchGoodsList(GoodsTypeEnum.CPU);
                    mGoodsAdapter.notifyDataSetChanged();
                    break;
                case R.id.iv_gpu:
//                    mGoodsList.clear();
//                    id = new int[]{10, 11, 12, 13};
//                    titles = new String[]{"技嘉(GIGABYTE)GeForce GTX 1660", "蓝宝石（Sapphire）RX580 2048SP 8G D5 白金版 OC 1306MHz/8000MHz", "铭瑄 (MAXSUN) MS-GeForce RTX2060 Super iCraft 8G 电竞之心", "七彩虹（Colorful）iGame GeForce RTX 2060 Ultra 1680-1710MHz"};
//                    prices = new double[]{1899, 1069, 2649, 2499};
//                    images_icon = new int[]{R.drawable.xk1, R.drawable.xk2, R.drawable.xk3, R.drawable.xk4};
//                    for (int i = 0; i<titles.length; i++) {
//                        Goods goods = new Goods();
//                        goods.setId(id[i]);
//                        goods.setTitle(titles[i]);
//                        goods.setPrice(prices[i]);
////                        goods.setImageIcon(images_icon[i]);
//                        mGoodsList.add(goods);
//                    }
                    //给gpu列表装载数据
                    switchGoodsList(GoodsTypeEnum.GPU);
                    mGoodsAdapter.notifyDataSetChanged();
                    break;
                case R.id.iv_disk:
//                    mGoodsList.clear();
//                    id = new int[]{6, 7, 8, 9};
//                    titles = new String[]{"希捷(Seagate)2TB 256MB 7200RPM", "西部数据(WD)蓝盘 1TB SATA6Gb/s", "希捷(Seagate)4TB 256MB", "希捷酷鱼1TB机械硬盘+希捷酷鱼500B固态硬盘"};
//                    prices = new double[]{389, 289, 579, 888};
//                    images_icon = new int[]{R.drawable.yp1, R.drawable.yp2, R.drawable.yp3, R.drawable.yp4};
//                    for (int i = 0; i<titles.length; i++) {
//                        Goods goods = new Goods();
//                        goods.setId(id[i]);
//                        goods.setTitle(titles[i]);
//                        goods.setPrice(prices[i]);
////                        goods.setImageIcon(images_icon[i]);
//                        mGoodsList.add(goods);
//                    }
                    //给disk列表装载数据
                    switchGoodsList(GoodsTypeEnum.DISK);
                    mGoodsAdapter.notifyDataSetChanged();
                    break;
                case R.id.iv_ram:
//                    mGoodsList.clear();
//                    id = new int[]{14, 15, 16, 17};
//                    titles = new String[]{"金士顿(Kingston) DDR4 2666 8GB", "金士顿(Kingston) DDR4 2666 8GB", "惠普(HP) DDR4 2666 8GB 台式机内存 V6系列", "金士顿(Kingston) DDR4 3200 16GB(8G×2)套装"};
//                    prices = new double[]{235, 479, 209, 499};
//                    images_icon = new int[]{R.drawable.nc1, R.drawable.nc2, R.drawable.nc3, R.drawable.nc4};
//                    for (int i = 0; i<titles.length; i++) {
//                        Goods goods = new Goods();
//                        goods.setId(id[i]);
//                        goods.setTitle(titles[i]);
//                        goods.setPrice(prices[i]);
////                        goods.setImageIcon(images_icon[i]);
//                        mGoodsList.add(goods);
//                    }
                    //给ram列表装载数据
                    switchGoodsList(GoodsTypeEnum.RAM);
                    mGoodsAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }

}

