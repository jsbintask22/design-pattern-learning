package cn.jsbintask.decorator.v1;

/**
 * @author jsbintask@gmail.com
 * @date 2019/5/23 10:35
 * 蓝山牛奶咖啡
 */
public class BlueMilkCoffee extends Coffee {
    @Override
    public String getName() {
        return "蓝山奶牛咖啡";
    }

    @Override
    public double getPrice() {
        return 10.34;
    }
}
