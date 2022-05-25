package cn.jsbintask.instrumentation.learning;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.io.IOException;
import java.util.List;

/**
 * @author jianbin.
 * @date 2022/5/22 11:04
 *
 * -Djava.agent.path=src/main/java/cn/jsbintask/instrumentation/learning/MyAgent.jar
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // 获取当前运行的 jvm
        byAttach();

        // vm load agent
        Integer int1 = 1;
        System.out.println(int1);

        String ss = "ss";
        System.out.println(ss.toString());

        HelloWorld helloWorld = new HelloWorld();
        helloWorld.printHello();

        String x = helloWorld.toString();
        System.out.println(x);
    }

    public static void byAttach() throws Exception {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor virtualMachineDescriptor : list) {
            // System.out.println("加载的vm：" + virtualMachineDescriptor);

            String pid = virtualMachineDescriptor.id();
            VirtualMachine vm = VirtualMachine.attach(pid);
            try {
                vm.loadAgent("/Users/aaron.zou/IdeaProjects/design-pattern-learning/proxy/proxy-agent/target/proxy-agent-1.0-SNAPSHOT.jar");
            } catch (Exception e){
            }

            vm.detach();
        }

    }


}
