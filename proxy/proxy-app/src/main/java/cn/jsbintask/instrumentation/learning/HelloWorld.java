package cn.jsbintask.instrumentation.learning;

/**
 * @author jianbin.
 * @date 2022/5/22 11:44
 */
public class HelloWorld {
    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public void printHello() {
        System.out.println("Hello World!");
    }

    @Override
    public String toString() {
        return "这是正常重写的 tostring: hello world toString";
    }
}
