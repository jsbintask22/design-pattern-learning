package cn.jsbintask.decorator.v3;

/**
 * @author jsbintask@gmail.com
 * @date 2019/5/23 11:32
 */
public class App {
    public static void main(String[] args) {
        // 得到一杯原始的蓝山咖啡
        Coffee blueCoffee = new BlueCoffee();
        System.out.println(blueCoffee.getName() + ": " + blueCoffee.getPrice());

        // 加入牛奶
        blueCoffee = new MilkCoffeeDecorator(blueCoffee);
        System.out.println(blueCoffee.getName() + ": " + blueCoffee.getPrice());

        // 再加入薄荷
        blueCoffee = new MintCoffeeDecorator(blueCoffee);
        System.out.println(blueCoffee.getName() + ": " + blueCoffee.getPrice());

        // 再加入糖
        blueCoffee = new SugarCoffeeDecorator(blueCoffee);
        System.out.println(blueCoffee.getName() + ": " + blueCoffee.getPrice());
    }
}
