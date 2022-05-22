package cn.jsbintask.javassist.learning;

import javassist.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jianbin.
 * @date 2022/5/22 9:39
 */
public class Main {

    public static void main(String[] args) throws Exception {
        genNewClass();

        System.out.println("===========================");

        // 修改现有的类；
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("cn.jsbintask.javassist.learning.Main$Super");

        CtMethod sayHello = ctClass.getDeclaredMethod("sayHello");
        sayHello.setBody("{System.out.println(\"new ---------- hello\");}");

        //新增一个方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "joinFriend", new CtClass[]{}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(\"i want to be your friend\");}");
        ctClass.addMethod(ctMethod);

        // 添加一个属性；
        CtField name = new CtField(classPool.get("java.lang.String"), "name", ctClass);
        name.setModifiers(Modifier.PUBLIC);
        ctClass.addField(name);

        Object obj = ctClass.toClass().newInstance();
        System.out.println(obj);

        obj.getClass().getMethod("sayHello").invoke(obj);

        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        Field[] fields = obj.getClass().getFields();
        for (Field field : fields) {
            System.out.println(field);
        }


        new Super().sayHello();
        System.out.println(Super.class.getClassLoader());
        System.out.println("===========================");
    }

    private static void genNewClass() throws CannotCompileException, NotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // 通过javassist 创建一个类
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("cn.jianbin.javassist.learning.Person");

        // 添加一个属性；
        CtField name = new CtField(classPool.get("java.lang.String"), "name", ctClass);
        name.setModifiers(Modifier.PUBLIC);
        ctClass.addField(name);

        // 创建一个构造方法
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        ctConstructor.setBody("{name= \"张三\";}");
        ctConstructor.insertAfter("{System.out.println(\"构造方法执行了\");}");
        ctConstructor.insertBefore("{System.out.println(\"构造方法执行了\");}");

        ctClass.addConstructor(ctConstructor);


        // 添加一个方法； print name;
        CtMethod printName = new CtMethod(CtClass.voidType, "printName", new CtClass[]{}, ctClass);
        printName.setBody("{System.out.println(\"name = \" + name);}");
        ctClass.addMethod(printName);

        ctClass.debugWriteFile();

        Object person =  ctClass.toClass().getConstructor().newInstance();

        person.getClass().getMethod("printName").invoke(person);
    }



    public static class Super {
        public void sayHello() {
            System.out.println("hello");
        }
    }
}
