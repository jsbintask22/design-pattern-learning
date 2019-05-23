package cn.jsbintask.decorator.v3;

/**
 * @author jsbintask@gmail.com
 * @date 2019/5/23 11:33
 */
public class SugarCoffeeDecorator extends CoffeeDecorator {
    public SugarCoffeeDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getName() {
        return "加糖, " + super.getName();
    }

    @Override
    public double getPrice() {
        return 3.7 + super.getPrice();
    }
}
