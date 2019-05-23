package cn.jsbintask.decorator.v3;


/**
 * @author jsbintask@gmail.com
 * @date 2019/5/23 11:17
 */
public abstract class CoffeeDecorator implements Coffee {
    private Coffee delegate;

    public CoffeeDecorator(Coffee coffee) {
        this.delegate = coffee;
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public double getPrice() {
        return delegate.getPrice();
    }
}
