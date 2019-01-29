package cn.jsbintask;

import java.lang.reflect.Constructor;

/**
 * @author jsbintask@foxmail.com
 * @date 2019/1/29 14:48
 */
public enum BadSingletonEnum {
    /**
     *
     */
    INSTANCE();


    public static void main(String[] args) throws Exception{
        System.out.println(BadSingletonEnum.INSTANCE == BadSingletonEnum.INSTANCE);

        Constructor<BadSingletonEnum> badSingletonEnumConstructor = BadSingletonEnum.class.getDeclaredConstructor(String.class, int.class);
        badSingletonEnumConstructor.setAccessible(true);
        BadSingletonEnum badSingletonEnum = badSingletonEnumConstructor.newInstance("test", 0);

        System.out.println(BadSingletonEnum.INSTANCE == badSingletonEnum);
    }
}
