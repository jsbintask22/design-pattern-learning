package cn.jsbintask.decorator.v2;

/**
 * @author jsbintask@gmail.com
 * @date 2019/5/23 10:15
 */
public abstract class Coffee {
    // 是否加了牛奶
    protected boolean addedMilk;
    // 是否加了糖
    protected boolean addedSugar;
    // 是否加了薄荷
    protected boolean addedMint;

    /**
     * 获取咖啡得名字
     */
    public abstract String getName();

    /**
     * 获取咖啡的价格
     */
    public abstract double getPrice();
}
