package cn.jsbintask.decorator.v3;

/**
 * @author jsbintask@gmail.com
 * @date 2019/5/23 11:33
 */
public class MintCoffeeDecorator extends CoffeeDecorator {
    public MintCoffeeDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getName() {
        return "薄荷, " + super.getName();
    }

    @Override
    public double getPrice() {
        return 0.3 + super.getPrice();
    }
}
