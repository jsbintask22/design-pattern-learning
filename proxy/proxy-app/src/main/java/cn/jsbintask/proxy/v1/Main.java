package cn.jsbintask.proxy.v1;

/**
 * @author jianbin.
 * @date 2022/5/21 17:10
 */
public class Main {

    public static void main(String[] args) {
        new HouseOwner().sell();

        // 我现在想在卖房钱打印下 现在的市场行情。 我想把买房过程记录下来。 我想把卖房时间记录下来。 我想记录下当时卖房的市场行情？
        // 我应该直接直接修改 HouseOwner 吗？

    }


    static class HouseOwner {
        int price = 100_0000;

        public void sell() {
            int p = this.price;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            System.out.println("卖房; 卖出价格: " + p);
        }
    }
}
