package cn.jsbintask.asm.learning;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.HexUtil;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

/**
 * @author jianbin.
 * @date 2022/5/21 10:56
 */
public class HelloWorld {
    private Integer id;

    public String  hello() {
        return "hello jianbin. " + id;
    }

    public static void main(String[] args) throws Exception {
        String filePath = "cn/jsbintask/asm/learning/HelloWorld.class";

        byte[] bytes = FileUtil.readBytes(filePath);

        // print bytes by hex
        System.out.println(HexUtil.encodeHexStr(bytes));


        // print class read trace
        // (1) 设置参数
        String className = "cn.jsbintask.asm.learning.HelloWorld$Hello";
        int parsingOptions = ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG;
        boolean asmCode = true;

        // (2) 打印结果
        Printer printer = asmCode ? new ASMifier() : new Textifier();
        PrintWriter printWriter = new PrintWriter(System.out, true);
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, printer, printWriter);
        new ClassReader(className).accept(traceClassVisitor, parsingOptions);
    }

    public static class Hello {
        private Integer id;

        public String hello() {
            return "hello jianbin. " + id;
        }
    }
}
