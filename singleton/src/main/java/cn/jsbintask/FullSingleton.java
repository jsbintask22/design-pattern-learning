package cn.jsbintask;

/**
 * @author jsbintask@foxmail.com
 * @date 2019/1/29 15:56
 */
public class FullSingleton {
    private static FullSingleton ourInstance = new FullSingleton();

    public static FullSingleton getInstance() {
        return ourInstance;
    }

    private FullSingleton() {
        System.out.println("FullSingleton.FullSingleton");
    }

    public static void main(String[] args) {
        System.out.println(FullSingleton.getInstance() == FullSingleton.getInstance());
    }
}
