package cn.jsbintask.visitor.v1;

/**
 * @author aaron.zou
 * @date 2022/5/24 7:23 PM
 */
public class Home {
    private Furniture tv = new TV();
    private Furniture sofa = new Sofa();

    public void accept(Visitor visitor) {
        tv.accept(visitor);
        sofa.accept(visitor);
    }

    public static void main(String[] args) {
        Home home = new Home();
        home.accept(new Friend());
        home.accept(new FatherInLaw());

        // 现在我有一个新的访问者，他是一个美女； 必须要修改 每个 家具类； 才能满足新的需求；
    }

    interface Visitor {
    }

    public static class Friend implements Visitor {
    }

    public static class FatherInLaw implements Visitor {
    }

    interface Furniture {
        void accept(Visitor visitor);
    }

    public static class TV implements Furniture {
        @Override
        public void accept(Visitor visitor) {
            if (visitor instanceof Friend) {
                System.out.println("friend visit tv");
            } else {
                System.out.println("father in law visit tv");
            }
        }
    }

    public static class Sofa implements Furniture {
        @Override
        public void accept(Visitor visitor) {
            if (visitor instanceof Friend) {
                System.out.println("friend visit sofa");
            } else {
                System.out.println("father in law visit sofa");
            }
        }
    }
}
