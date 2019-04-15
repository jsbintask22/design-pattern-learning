package cn.jsbintask.observer.v3;

/**
 * @author jsbintask@gmail.com
 * @date 2019/4/15 16:43
 */
public class App {

    public static void main(String[] args) {
        Beauty beauty = new BeautyImpl();
        LittleBoy boy1 = new LittleBoyImpl("小豪");
        LittleBoy boy2 = new LittleBoyImpl("小吴");

        // 添加两个备胎
        beauty.addLittleBoy(boy1);
        beauty.addLittleBoy(boy2);

        // 发布朋友圈
        beauty.publishWechat("最美的不是下雨天，是曾和你一起躲过雨的屋檐！");

        // 删除备胎1，并且新添加了备胎3
        beauty.removeLittleBoy(boy1);
        beauty.addLittleBoy(msg -> {
            System.out.println(" 小李：哎哟，不错哦！");
        });

        // 再次发布朋友圈
        beauty.publishWechat("哪里有彩虹告诉我。。。");
    }
}
