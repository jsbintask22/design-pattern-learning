package cn.jsbintask.instrumentation;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @author jianbin.
 * @date 2022/5/22 11:05
 */
public class MyAgent {
    /**
     * jvm 首先会调用 premain(String agentArgs, Instrumentation inst)方法
     */
    public static void premain(String args, Instrumentation inst) {
        System.out.println("Hello, I am permain method!" + " args: " + args + ", inst: " + inst);


        inst.addTransformer(new MyClassTransformer(), true);
        extracted(inst);
    }

    /**
     * 以 attach 的方式运行；
     */
    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("Hello, I am agentmain method!" + " args: " + args + ", inst: " + inst);

        inst.addTransformer(new MyClassTransformer(), true);
        extracted(inst);
    }

    private static void extracted(Instrumentation inst) {
        try {
            inst.retransformClasses(String.class, Integer.class);
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }
    }
}
