package cn.jsbintask;

/**
 * @author jsbintask@foxmail.com
 * @date 2019/1/29 16:03
 */
public class HungrySafeSingleton {
    private static volatile HungrySafeSingleton instance;

    public static HungrySafeSingleton getInstance() {
        /* 使用一个本地变量可以提高性能 */
        HungrySafeSingleton result = instance;

        if (result == null) {

            synchronized (HungrySafeSingleton.class) {

                result = instance;
                if (result == null) {
                    instance = result = new HungrySafeSingleton();
                }
            }
        }

        return result;
    }

    private HungrySafeSingleton() {
        System.out.println("HungryUnsafeSingleton.HungryUnsafeSingleton");
    }

    public static void main(String[] args) {
        System.out.println(HungrySafeSingleton.getInstance() == HungrySafeSingleton.getInstance());
    }
}
