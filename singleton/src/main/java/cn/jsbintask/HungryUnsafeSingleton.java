package cn.jsbintask;

/**
 * @author jsbintask@foxmail.com
 * @date 2019/1/29 16:03
 */
public class HungryUnsafeSingleton {
    private static HungryUnsafeSingleton instance;

    public static HungryUnsafeSingleton getInstance() {
        if (instance == null) {
            instance = new HungryUnsafeSingleton();
        }

        return instance;
    }

    private HungryUnsafeSingleton() {
        System.out.println("HungryUnsafeSingleton.HungryUnsafeSingleton");
    }

    public static void main(String[] args) {
        System.out.println(HungryUnsafeSingleton.getInstance() == HungryUnsafeSingleton.getInstance());
    }
}
