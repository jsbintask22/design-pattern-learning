package cn.jsbintask.decorator.v3;

/**
 * @author jsbintask@gmail.com
 * @date 2019/5/23 11:32
 */
public class BlueCoffee implements Coffee{
    @Override
    public String getName() {
        return "蓝山咖啡";
    }

    @Override
    public double getPrice() {
        return 10.3;
    }
}
