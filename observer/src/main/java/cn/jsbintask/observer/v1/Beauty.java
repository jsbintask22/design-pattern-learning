package cn.jsbintask.observer.v1;

import java.util.Date;

/**
 * @author jsbintask@gmail.com
 * @date 2019/4/15 15:47
 */
public class Beauty {
    public volatile boolean sentMsg = false;

    public void publishWechat() {
        System.out.println("小丽：今天天气不错！" + new Date());
        sentMsg = true;
    }
}
