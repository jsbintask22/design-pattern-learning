package cn.jsbintask.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author jianbin.
 * @date 2022/5/21 20:41
 */
public class Main {
    public static void main(String[] args) {
        // 使用CGLIB动态代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HouseOwner.class);
        enhancer.setCallback(new TimeMethodInterceptor());

        HouseOwner houseOwner = (HouseOwner) enhancer.create();
        houseOwner.sell();

        System.out.println(houseOwner.hashCode());
    }

    static class TimeMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("start execute...");

            Object result = proxy.invokeSuper(obj, args);

            System.out.println("end execute...");

            return result;
        }
    }

    static class HouseOwner {
        public void sell() {
            System.out.println("卖房成交。" );
        }
    }
}
