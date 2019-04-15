package cn.jsbintask.observer.v2;

/**
 * @author jsbintask@gmail.com
 * @date 2019/4/15 15:52
 */
public class App {
    public static void main(String[] args) throws Exception{
        Beauty beauty = new Beauty();
        LittleBoy littleBoy = new LittleBoy();
        // 添加女神微信
        beauty.littleBoy = littleBoy;

        // 发布动态
        beauty.publishWechat();
    }
}
