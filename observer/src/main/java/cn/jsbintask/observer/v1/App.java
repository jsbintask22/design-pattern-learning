package cn.jsbintask.observer.v1;

/**
 * @author jsbintask@gmail.com
 * @date 2019/4/15 15:52
 */
public class App {
    public static void main(String[] args) throws Exception{
        Beauty beauty = new Beauty();
        // 成功添加了女神微信
        LittleBoy littleBoy = new LittleBoy(beauty);

        // 开始查看女神朋友圈
        littleBoy.start();

        // 5s后，女神发布了朋友圈。
        Thread.sleep(5000L);
        beauty.publishWechat();

        System.in.read();
    }
}
