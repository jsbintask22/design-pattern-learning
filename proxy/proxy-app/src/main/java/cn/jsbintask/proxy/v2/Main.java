package cn.jsbintask.proxy.v2;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * @author jianbin.
 * @date 2022/5/21 17:10
 *
 * 针对 v1 版本留下的问题；
 *
 * 通过继承解决；
 */
public class Main {

    public static void main(String[] args) {
        new TimeHouseOwner().sell();

        new RecordHouseOwner().sell();

        // 新问题： 我如果在卖房前或者卖房后，我有更多的需求。 比如 记录事务、比较市场行情等等
        // 又或者； 我现在既想  记录 时间、又想比较市场行情； 那我是不是应该再写更多的继承类；
    }


    static class HouseOwner {
        int price = 100_0000;

        public void sell() {
            int p = this.price;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            System.out.println("卖房; 成交，卖出价格: " + p);
        }
    }


    static class TimeHouseOwner extends HouseOwner {
        @Override
        public void sell() {
            System.out.println("我准备卖房了，时间是：" + DateUtil.now());
            super.sell();
            System.out.println("我卖房卖完了，时间是：" + DateUtil.now());
        }
    }

    static class RecordHouseOwner extends HouseOwner {
        @Override
        public void sell() {
            System.out.println("我准备卖房了，合同编号是："+ RandomUtil.randomNumbers(8) +
                    "对方姓名是: "+ RandomUtil.randomString(2) + "xxxxxxx");

            super.sell();

        }
    }
}
