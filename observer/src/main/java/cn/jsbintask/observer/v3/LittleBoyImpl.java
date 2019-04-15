package cn.jsbintask.observer.v3;

/**
 * @author jsbintask@gmail.com
 * @date 2019/4/15 16:48
 */
public class LittleBoyImpl implements LittleBoy {
    String name;

    public LittleBoyImpl(String name) {
        this.name = name;
    }

    @Override
    public void receivedMsg(String newMsg) {
        System.out.println(" " + name + ": " + (newMsg.contains("彩虹") ? "--能不能把我的愿望还给我。" : " --回忆的画面，在荡着秋千。"));
    }
}
