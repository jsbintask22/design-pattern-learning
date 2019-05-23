package cn.jsbintask.decorator;

import cn.jsbintask.decorator.v1.BlueMilkCoffee;
import cn.jsbintask.decorator.v1.Coffee;

/**
 * @author jsbintask@gmail.com
 * @date 2019/5/22 16:10
 * 装饰器模式
 */
public class App {
    public static void main(String[] args) {
        Coffee blueMilkCoffee = new BlueMilkCoffee();
        System.out.println(blueMilkCoffee.getName());
        System.out.println(blueMilkCoffee.getPrice());
    }
}
