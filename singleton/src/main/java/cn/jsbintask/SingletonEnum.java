package cn.jsbintask;

/**
 * @author jsbintask@foxmail.com
 * @date 2019/1/29 14:48
 */
public enum SingletonEnum {
    /**
     *
     */
    INSTANCE;

    public static void main(String[] args) {
        System.out.println(SingletonEnum.INSTANCE == SingletonEnum.INSTANCE);
    }
}
