package cn.jsbintask.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jsbintask@gmail.com
 * @date 2019/7/10 14:55
 * 自行车骑行策略
 */
@Slf4j
public class BikeTravelStrategy implements TravelStrategy {
    @Override
    public void travel() {
      log.info("自行车骑行半小时，行程18公里，感觉卡路里在燃烧！");
    }
}
