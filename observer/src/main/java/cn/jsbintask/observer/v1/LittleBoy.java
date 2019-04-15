package cn.jsbintask.observer.v1;

import java.util.Date;

/**
 * @author jsbintask@gmail.com
 * @date 2019/4/15 15:49
 */
public class LittleBoy extends Thread{
    Beauty beauty;

    public LittleBoy(Beauty beauty) {
        this.beauty = beauty;
    }

    @Override
    public void run() {
        while (true) {
            if (beauty.sentMsg) {
                System.out.println("备胎：哇，天气不错，这是在哪玩耍呢！");
                beauty.sentMsg = false;
            } else {
                System.out.println("备胎查看女神朋友圈，没有动态！ " + new Date());
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
