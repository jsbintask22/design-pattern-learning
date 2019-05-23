package cn.jsbintask.decorator.v2;

/**
 * @author jsbintask@gmail.com
 * @date 2019/5/23 10:46
 */
public class BuleCoffee extends Coffee {
    @Override
    public String getName() {
        StringBuilder name = new StringBuilder();
        name.append("蓝山");
        if (addedMilk) {
            name.append("牛奶");
        }
        if (addedMilk) {
            name.append("薄荷");
        }
        if (addedSugar) {
            name.append("加糖");
        }
        return name.toString();
    }

    @Override
    public double getPrice() {
        double price = 10;
        if (addedMilk) {
            price += 1.1;
        }
        if (addedMilk) {
            price += 3.2;
        }
        if (addedSugar) {
            price += 2.7;
        }

        return price;
    }
}
