package cn.jsbintask.proxy.v3;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jianbin.
 * @date 2022/5/21 17:10
 *
 * 针对 v3 版本留下的问题； 我们通过 动态代理解决；
 *
 */
public class Main {

    public static void main(String[] args) {
        // jdk 动态代理
        Sellable sellable = new HouseOwner(80);

        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 时间代理；
        InvocationHandler handler = new TimeProxy(sellable);

        Sellable timeProxy = (Sellable) Proxy.newProxyInstance(sellable.getClass().getClassLoader(), new Class[]{Sellable.class}, handler);
        timeProxy.sell();

        // 市场代理；
        InvocationHandler handler2 = new MarketProxy(sellable);
        Sellable marketProxy = (Sellable) Proxy.newProxyInstance(sellable.getClass().getClassLoader(), new Class[]{Sellable.class}, handler2);
        marketProxy.sell();

        System.out.println(marketProxy.toString());

        // 怎么看到具体的 动态生成的 代理类？
        Integer i = -889275714;
        System.out.println(Integer.toHexString(i));
    }

    /**
     * 将买房能力抽象为一个接口
     */
    interface Sellable {
        void sell();
    }

    static class HouseOwner implements Sellable {
        int price;

        public HouseOwner(int expectPrice) {
            this.price = expectPrice;
        }

        @Override
        public void sell() {
            int p = this.price;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            System.out.println("卖房; 成交，卖出价格: " + p +"w");
        }
    }

    static class TimeProxy implements InvocationHandler {
        private Sellable sellable;

        public TimeProxy(Sellable sellable) {
            this.sellable = sellable;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("sell")) {
                System.out.println("操作开始时间: " + DateUtil.now());
                sellable.sell();
                System.out.println("操作结束时间: " + DateUtil.now());
                return null;
            }

            return method.invoke(sellable, args);
        }
    }

    /**
     * 行情代理，检查当前市场行情； 再销售；
     */
    static class MarketProxy implements InvocationHandler {
        private Sellable sellable;

        public MarketProxy(Sellable sellable) {
            this.sellable = sellable;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("sell")) {
                int marketPrice = RandomUtil.randomInt(70, 100);
                System.out.println("检查行情，当前价格: " + marketPrice);
                if (marketPrice > 80) {
                    System.out.println("当前市场行情好，肯定还能涨，不卖。");
                } else {
                    sellable.sell();
                }

                return null;
            }

            return method.invoke(sellable, args);
        }

    }

}
