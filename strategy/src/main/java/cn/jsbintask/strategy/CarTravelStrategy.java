package cn.jsbintask.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jsbintask@gmail.com
 * @date 2019/7/10 14:53
 * 汽车旅行策略
 */
@Slf4j
public class CarTravelStrategy implements TravelStrategy {
    @Override
    public void travel() {
      log.info("自驾旅行半小时，行程40公里，感觉时间走的真慢。");
    }
}
