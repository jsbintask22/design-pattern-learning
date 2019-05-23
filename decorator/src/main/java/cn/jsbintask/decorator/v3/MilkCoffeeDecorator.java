package cn.jsbintask.decorator.v3;

/**
 * @author jsbintask@gmail.com
 * @date 2019/5/23 11:29
 */
public class MilkCoffeeDecorator extends CoffeeDecorator {
    public MilkCoffeeDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getName() {
        return "牛奶, " + super.getName();
    }

    @Override
    public double getPrice() {
        return 1.1 + super.getPrice();
    }
}
