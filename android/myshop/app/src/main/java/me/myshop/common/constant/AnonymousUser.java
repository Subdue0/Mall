package me.myshop.common.constant;



import me.myshop.R;
import me.myshop.entity.Goods;

import java.util.List;

public class AnonymousUser {
    private static int[] id = {2, 3, 4, 5, 6, 7, 8, 9};
    private static int[] number = {1, 2, 3, 4, 5, 6, 7, 8};
    private static String[] titles = {"i3", "i5", "i7", "i9", "希捷(Seagate)2TB 256MB 7200RPM", "西部数据(WD)蓝盘 1TB SATA6Gb/s", "希捷(Seagate)4TB 256MB", "希捷酷鱼1TB机械硬盘+希捷酷鱼500B固态硬盘"};
    private static double[] prices = {500, 1000, 1500, 2000, 389, 289, 579, 888};
    private static int[] images_icon = {R.drawable.i3, R.drawable.i5, R.drawable.i7, R.drawable.i9, R.drawable.yp1, R.drawable.yp2, R.drawable.yp3, R.drawable.yp4};

    public static void LoadingData(List<Goods> mGoodsList) {
        for (int i = 0; i < titles.length; i++) {
            Goods goods = new Goods();
            goods.setId(id[i]);
            goods.setTitle(titles[i]);
            goods.setPrice(prices[i]);
//            goods.setImageIcon(images_icon[i]);
            goods.setNumber(number[i]);
            mGoodsList.add(goods);
        }
    }
}
