package cn.jsbintask.proxy.v3;

import cn.hutool.core.date.DateUtil;

/**
 * @author aaron.zou
 * @date 2022/5/24 7:51 PM
 */
public class Main {

    public static void main(String[] args) {
        HouseOwner houseOwner = new HouseOwner(80);

        // 获得记录时间能力。
        TimeProxy timeProxy = new TimeProxy(houseOwner);
        timeProxy.sell();

        // 获得事务能力。
        TransactionProxy transactionProxy = new TransactionProxy(houseOwner);
        transactionProxy.sell();

        // 我有很多的 场景 都要记录时间、事务、市场行情 等等。 我应该为每种场景一一去实现子类 Proxy 代理吗？

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

    static class TimeProxy implements Sellable {
        HouseOwner sellable;
        public TimeProxy(HouseOwner sellable) {
            this.sellable = sellable;
        }

        @Override
        public void sell() {
            System.out.println("我开始操作了，时间是：" + DateUtil.now());
            sellable.sell();
            System.out.println("我操作完成了了，时间是：" + DateUtil.now());
        }
    }

    static class Translates {
        void start() {
            System.out.println("事务开始。");
        }

        void commit() {
            System.out.println("事务完成。 提交.");
        }

        void rollback(Exception e) {
            System.out.println("事务异常。回滚.");
        }

        void close() {
            System.out.println("事务结束。关闭.");
        }
    }

    static class TransactionProxy implements Sellable {
        HouseOwner sellable;

        private Translates translates = new Translates();

        public TransactionProxy(HouseOwner sellable) {
            this.sellable = sellable;
        }


        @Override
        public void sell() {
            translates.start();

            try {
                sellable.sell();
                translates.commit();
            } catch (Exception e) {
                translates.rollback(e);
            } finally {
                translates.close();
            }
        }
    }
}
