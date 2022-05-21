package cn.jsbintask.asm.learning;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author jianbin.
 * @date 2022/5/21 9:56
 */
public class HelloWorldCls {
    public byte[] genHelloWorldCls() {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC |Opcodes.ACC_SUPER, "cn/jianbin/asm/HelloWorldAsm",
                null, "java/lang/Object", null);


        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        methodVisitor.visitCode();

        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(1, 1);  // can ignored.
        methodVisitor.visitEnd();

        // print() System.out.println();
        MethodVisitor methodVisitor2 = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "print", "()V", null, null);
        methodVisitor2.visitCode();
        methodVisitor2.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor2.visitLdcInsn("Hello World!");
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        methodVisitor2.visitInsn(Opcodes.RETURN);
        methodVisitor2.visitMaxs(2, 1);  // can ignored.
        methodVisitor2.visitEnd();

        return classWriter.toByteArray();
    }


    // custom class loader;
    static class HelloWorldClsLoader extends ClassLoader {
        public Class<?> defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }

    public static void main(String[] args) throws Exception {
        HelloWorldCls helloWorldCls = new HelloWorldCls();
        byte[] bytes = helloWorldCls.genHelloWorldCls();

        Class<?> helloWorldAsmCls = new HelloWorldClsLoader().defineClass("cn.jianbin.asm.HelloWorldAsm", bytes);

        // new instance
        Object helloWorldAsm = helloWorldAsmCls.getConstructor().newInstance();

        // invoke print();
        helloWorldAsmCls.getMethod("print").invoke(helloWorldAsm);
    }
}
