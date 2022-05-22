package cn.jsbintask.instrumentation;

import java.lang.instrument.Instrumentation;

/**
 * @author jianbin.
 * @date 2022/5/22 11:05
 */
public class MyAgent {
    /**
     * jvm 首先会调用 premain(String agentArgs, Instrumentation inst)方法
     */
    public static void premain(String args, Instrumentation inst) {
        System.out.println("Hello, I am premain method!");
        System.out.println("args: " + args);
        System.out.println("inst: " + inst);

        inst.addTransformer(new MyClassTransformer());
    }

    /**
     * 以 attach 的方式运行；
     */
    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("Hello, I am agentmain method!");
        System.out.println("args: " + args);
        System.out.println("inst: " + inst);

        inst.addTransformer(new MyClassTransformer());
    }
}
