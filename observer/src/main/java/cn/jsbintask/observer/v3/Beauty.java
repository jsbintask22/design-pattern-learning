package cn.jsbintask.observer.v3;

/**
 * @author jsbintask@gmail.com
 * @date 2019/4/15 16:41
 */
public interface Beauty {
    /**
     * 添加新备胎
     */
    void addLittleBoy(LittleBoy newLittleBoy);

    /**
     * 删除备胎
     */
    void removeLittleBoy(LittleBoy littleBoy);

    /**
     * 发布朋友圈
     */
    void publishWechat(String msg);
}
