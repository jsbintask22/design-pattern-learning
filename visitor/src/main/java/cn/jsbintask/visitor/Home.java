package cn.jsbintask.visitor;

/**
 * @author jianbin.
 * @date 2022/5/21 14:33
 *
 * 这是你家的 抽象 类；
 *
 * 房子 包含 电视、沙发、家具 等一众家具；
 */
public class Home {
    public static void main(String[] args) {
        // 这是你的家；
        Home home = new Home();

        // 朋友来你家访问
        home.accept(home.new Friend());

        // 老丈人访问；
        home.accept(home.new FatherInLaw());
    }




    /**
     * 房子装修是固定的； 包含 电视、沙发、地板 等一众家具；
     */
    private Furniture tv = new TV();

    private Furniture sofa = new Sofa();

    private Furniture floor = new Floor();

    public void accept(HomeVisitor visitor) {
        tv.accept(visitor);
        sofa.accept(visitor);
        floor.accept(visitor);
    }

    /**
     * 家具抽象。
     */
    interface Furniture {

        /**
         * 有访问者要来访问这件家具，具体怎么做，由访问者来决定。
         * @param visitor
         */
        void accept(HomeVisitor visitor);
    }

    /**
     * 家具的实现， TV、Sofa、Floor
     */
    class TV implements Furniture {

        @Override
        public void accept(HomeVisitor visitor) {
            visitor.visit(this);
        }
    }

    class Sofa implements Furniture {

        @Override
        public void accept(HomeVisitor visitor) {
            visitor.visit(this);
        }
    }

    class Floor implements Furniture {

        @Override
        public void accept(HomeVisitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * 访问者， 专门访问你的家，做个抽象
     */
    interface HomeVisitor {
        void visit(TV tv);

        void visit(Sofa sofa);

        void visit(Floor floor);
    }

    /**
     * 朋友
     */
    class Friend implements HomeVisitor {

        @Override
        public void visit(TV tv) {
            System.out.println("朋友说电视真大，他也想买一个一样的。");
        }

        @Override
        public void visit(Sofa sofa) {
            System.out.println("朋友坐在沙发，坐姿随意。");
        }

        @Override
        public void visit(Floor floor) {
            System.out.println("朋友说这地板真亮。");
        }
    }

    /**
     * 老丈人
     */
    class FatherInLaw implements HomeVisitor {

        @Override
        public void visit(TV tv) {
            System.out.println("老丈人说我们家电视不行，掏出了他的收音机。");
        }

        @Override
        public void visit(Sofa sofa) {
            System.out.println("老丈人说沙发坐着不舒服，掏出了他的小板凳");
        }

        @Override
        public void visit(Floor floor) {
            System.out.println("老丈人说这地板不错，送他几块带回家。");
        }
    }
}
