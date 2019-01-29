## 什么是单例？为什么要用单例？
一个类被设计出来，就代表它表示具有某种行为（方法），属性（成员变量），而一般情况下，当我们想使用这个类时，会使用**new**关键字，这时候jvm会帮我们构造一个该类的实例。而我们知道，对于**new**这个关键字以及该实例，相对而言是比较耗费资源的。所以如果我们能够想办法在jvm启动时就**new**好，或者在某一次实例**new**好以后，以后不再需要这样的动作，就能够节省很多资源了。

## 哪些类可以使用单例？
一般而言，我们总是希望**无状态**的类能够设计成单例，那这个**无状态**代表什么呢？ 简单而言，对于同一个实例，如果多个线程同时使用，并且不使用额外的线程同步手段，不会出现线程同步的问题，我们就可以认为是**无状态**的，再简单点：一个类没有成员变量，或者它的成员变量也是**无状态**的，我们就可以考虑设计成单例。

## 实现方法
好了，我们已经知道什么是单例，为什么要使用单例了，那我们接下来继续讨论下怎么实现单例。
一般来说，我们可以把单例分为**行为上的单例**和**管理上的单例**。**行为上的单例**代表不管如何操作（此处不谈cloneable，反射），至始至终jvm中都只有一个类的实例，而**管理上的单例**则可以理解为：不管谁去使用这个类，都要守一定的**规矩**，比方说，我们使用某个类，只能从指定的地方’去拿‘，这样拿到就是同一个类了。
而对于**管理上的单例**，相信大家最为熟悉的就是spring了，spring将所有的类放到一个**容器**中，以后使用该类都从该**容器**去取，这样就保证了单例。
所以这里我们剩下的就是接着来谈谈如何实现**行为上的单例**了。一般来说，这种单例实现有两种思路，**私有构造器，枚举**。

### 枚举实现单例
枚举实现单例是最为推荐的一种方法，因为就算通过序列化，反射等也没办法破坏单例性，例子：
```java
public enum SingletonEnum {
    INSTANCE;

    public static void main(String[] args) {
        System.out.println(SingletonEnum.INSTANCE == SingletonEnum.INSTANCE);
    }
}
```
结果自然是**true**，而如果我们尝试使用反射破坏单例性：
```java
public enum BadSingletonEnum {
    /**
     *
     */
    INSTANCE;

    public static void main(String[] args) throws Exception{
        System.out.println(BadSingletonEnum.INSTANCE == BadSingletonEnum.INSTANCE);

        Constructor<BadSingletonEnum> badSingletonEnumConstructor = BadSingletonEnum.class.getDeclaredConstructor();
        badSingletonEnumConstructor.setAccessible(true);
        BadSingletonEnum badSingletonEnum = badSingletonEnumConstructor.newInstance();

        System.out.println(BadSingletonEnum.INSTANCE == badSingletonEnum);
    }
}
```
结果如下：
```java
Exception in thread "main" java.lang.NoSuchMethodException: cn.jsbintask.BadSingletonEnum.<init>()
	at java.lang.Class.getConstructor0(Class.java:3082)
	at java.lang.Class.getDeclaredConstructor(Class.java:2178)
	at cn.jsbintask.BadSingletonEnum.main(BadSingletonEnum.java:18)
```
异常居然是没有**init**方法，这是为什么呢？ 那我们反编译查看下这个枚举类的字节码：
```java
// class version 52.0 (52)
// access flags 0x4031
// signature Ljava/lang/Enum<Lcn/jsbintask/BadSingletonEnum;>;
// declaration: cn/jsbintask/BadSingletonEnum extends java.lang.Enum<cn.jsbintask.BadSingletonEnum>
public final enum cn/jsbintask/BadSingletonEnum extends java/lang/Enum {

  // compiled from: BadSingletonEnum.java

  // access flags 0x4019
  public final static enum Lcn/jsbintask/BadSingletonEnum; INSTANCE

  // access flags 0x101A
  private final static synthetic [Lcn/jsbintask/BadSingletonEnum; $VALUES
}
```
结果发现这个枚举类继承了抽象类**java.lang.Enum**，我们接着看下**Enum**，发现构造器：
```java
/**
    * Sole constructor.  Programmers cannot invoke this constructor.
    * It is for use by code emitted by the compiler in response to
    * enum type declarations.
    *
    * @param name - The name of this enum constant, which is the identifier
    *               used to declare it.
    * @param ordinal - The ordinal of this enumeration constant (its position
    *         in the enum declaration, where the initial constant is assigned
    *         an ordinal of zero).
*/
protected Enum(String name, int ordinal) {
    this.name = name;
    this.ordinal = ordinal;
}
```
那我们接着改变代码，反射调用这个构造器：
```java
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
```
结果如下：
```java
Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects
	at java.lang.reflect.Constructor.newInstance(Constructor.java:417)
	at cn.jsbintask.BadSingletonEnum.main(BadSingletonEnum.java:21)
```
这次虽然方法找到了，但是直接给我们了一句**Cannot reflectively create enum objects**，不能够反射创造枚举对象，接着我们继续看下**newInstance（...）**这个方法：
```java
public T newInstance(Object ... initargs)
        throws InstantiationException, IllegalAccessException,
               IllegalArgumentException, InvocationTargetException
    {
        if (!override) {
            if (!Reflection.quickCheckMemberAccess(clazz, modifiers)) {
                Class<?> caller = Reflection.getCallerClass();
                checkAccess(caller, clazz, null, modifiers);
            }
        }
        if ((clazz.getModifiers() & Modifier.ENUM) != 0)
            throw new IllegalArgumentException("Cannot reflectively create enum objects");
        ConstructorAccessor ca = constructorAccessor;   // read volatile
        if (ca == null) {
            ca = acquireConstructorAccessor();
        }
        @SuppressWarnings("unchecked")
        T inst = (T) ca.newInstance(initargs);
        return inst;
    }
```
关键代码就是：**if ((clazz.getModifiers() & Modifier.ENUM) != 0) throw new IllegalArgumentException("Cannot reflectively create enum objects");**，所以就是jdk从根本上拒绝了使用反射去创建（知道为啥java推荐使用enum实现单例了吧），另外，我们再观察下**Enum**类的clone和序列化方法，如下：
```java
protected final Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
}

private void readObject(ObjectInputStream in) throws IOException,
    ClassNotFoundException {
    throw new InvalidObjectException("can't deserialize enum");
}

private void readObjectNoData() throws ObjectStreamException {
    throw new InvalidObjectException("can't deserialize enum");
}
```
一眼看出，直接丢出异常，**不允许这么做！（真亲儿子系列）**。
所以，结论就是：枚举是最靠谱的实现单例的方式！

### 私有构造器
另外一个实现单例最普通的方法则是**私有构造器，开放获取实例公共方法**，虽然这种方法还是可以用clone，序列化，反射破坏单例性（除非特殊情况，我们不会这么做），但是却是最容易理解使用的。而这种方式又分了**饱汉式**，**饿汉式**。

#### 饿汉式
看名字就知道，饥渴！（咳咳，开个玩笑），它指的是当一个类被jvm加载的时候就会被实例化，这样可以从根本上解决多个线程的同步问题，例子如下：
```java
public class FullSingleton {
    private static FullSingleton ourInstance = new FullSingleton();

    public static FullSingleton getInstance() {
        return ourInstance;
    }

    private FullSingleton() {
    }

    public static void main(String[] args) {
        System.out.println(FullSingleton.getInstance() == FullSingleton.getInstance());
    }
}
```
结果自然是**true**，虽然这种做法很方便的帮我们解决了多线程实例化的问题，但是缺点也很明显，因为这句代码**private static FullSingleton ourInstance = new FullSingleton();**的关系，所以该类一旦被jvm加载就会马上实例化，那如果我们不想用这个类怎么办呢？ 是不是就浪费了呢？既然这样，我们来看下替代方案！ 饱汉式。

#### 饱汉式
既然是**饱**，就代表它不着急，那我们可以这么写：
```java
public class HungryUnsafeSingleton {
    private static HungryUnsafeSingleton instance;
    
    public static HungryUnsafeSingleton getInstance() {
        if (instance == null) {
            instance = new HungryUnsafeSingleton();
        }
        
        return instance;
    }
    
    private HungryUnsafeSingleton() {}
}
```
用意很容易理解，就是用到**getInstance（）**方法才去检查instance，如果为null，就new一个，这样就不怕浪费了，但是这个时候问题就来了：现在有这么一种情况，在有两个线程同时 运行到了  **instane == null**这个语句，并且都通过了，那他们就会都实例化一个对象，这样就又不是单例了。既然这样，哪有什么解决办法呢？ **锁方法**
1. 直接同步方法
这种方法比较干脆利落，那就是直接在getInstance（）方法上加锁，这样就解决了线程问题：
```java
public class HungrySafeSingleton {
    private static HungrySafeSingleton instance;

    public static synchronized HungrySafeSingleton getInstance() {
        if (instance == null) {
            instance = new HungrySafeSingleton();
        }

        return instance;
    }

    private HungrySafeSingleton() {
        System.out.println("HungryUnsafeSingleton.HungryUnsafeSingleton");
    }

    public static void main(String[] args) {
        System.out.println(HungrySafeSingleton.getInstance() == HungrySafeSingleton.getInstance());
    }
}
```
很简单，很容易理解，加锁，只有一个线程能实例该对象。但是，此时问题又来了，我们知道对于静态方法而言，synchronized关键字会锁住整个 Class，这时候又会有性能问题了（尼玛墨迹），那有没有优化的办法呢？ **双重检查锁**：
```java
public class HungrySafeSingleton {
    private static volatile HungrySafeSingleton instance;

    public static HungrySafeSingleton getInstance() {
        /* 使用一个本地变量可以提高性能 */
        HungrySafeSingleton result = instance;

        if (result == null) {

            synchronized (HungrySafeSingleton.class) {

                result = instance;
                if (result == null) {
                    instance = result = new HungrySafeSingleton();
                }
            }
        }

        return result;
    }

    private HungrySafeSingleton() {
        System.out.println("HungryUnsafeSingleton.HungryUnsafeSingleton");
    }

    public static void main(String[] args) {
        System.out.println(HungrySafeSingleton.getInstance() == HungrySafeSingleton.getInstance());
    }
}
```
用意也很明显，synchronized关键字只加在了关键的地方，并且通过本地变量提高了性能（effective java），这样线程安全并且不浪费资源的单例就完成了。

## 总结
本章，我们一步一步从什么是单例，到为什么要使用单例，再到怎么使用单例，并且从源码角度分析了为什么枚举是最适合的实现方式，然后接着讲解了饱汉式，饿汉式的写法以及好处，缺点。