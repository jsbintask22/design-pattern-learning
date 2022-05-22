package cn.jsbintask.instrumentation;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author jianbin.
 * @date 2022/5/22 11:49
 */
public class MyClassTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.startsWith("cn/jsbintask")) {
            System.out.println("MyClassTransformer.transform()  " + className);

            // 给每个方法调用前都 打印 一个日志
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new MyClassVisitor(cw);
            cr.accept(cv, 0);

            return cw.toByteArray();
        }

        return classfileBuffer;
    }


    public class MyMethodVisitor extends MethodVisitor {
        public MyMethodVisitor(MethodVisitor mv) {
            super(ASM5, mv);
        }


        @Override
        public void visitCode() {
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("tostring before execute from jianbin.:");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }


    }

    public class MyClassVisitor extends ClassVisitor {
        public MyClassVisitor(ClassVisitor cv) {
            super(ASM5, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
            if(name.equals("toString")){
                return new MyMethodVisitor(methodVisitor);
            }else {
                return methodVisitor;
            }
        }
    }
}
