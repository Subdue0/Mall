package me.myshop;

import org.junit.Test;

import me.myshop.common.constant.GoodsTypeEnum;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        System.out.println(GoodsTypeEnum.compare("1"));


        String str = "true";
        System.out.println(str instanceof String);

        System.out.println(Boolean.valueOf(str) instanceof Boolean);

    }
}