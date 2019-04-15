package cn.jsbintask.observer.v3;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jsbintask@gmail.com
 * @date 2019/4/15 16:44
 */
public class BeautyImpl implements Beauty {
    private Set<LittleBoy> littleBoys = new HashSet<>();

    @Override
    public void addLittleBoy(LittleBoy newLittleBoy) {
        littleBoys.add(newLittleBoy);
    }

    @Override
    public void removeLittleBoy(LittleBoy littleBoy) {
        littleBoys.remove(littleBoy);
    }

    @Override
    public void publishWechat(String msg) {
        System.out.println("女神：" + msg + new Date());

        // 通知备胎们！
        littleBoys.forEach(littleBoy -> {
            littleBoy.receivedMsg(msg);
        });
    }
}
