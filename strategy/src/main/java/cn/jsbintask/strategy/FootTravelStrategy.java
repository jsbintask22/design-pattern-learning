package cn.jsbintask.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jsbintask@gmail.com
 * @date 2019/7/10 14:51
 * 徒步旅行策略
 */
@Slf4j
public class FootTravelStrategy implements TravelStrategy {

    @Override
    public void travel() {
        log.info("徒步旅行半小时，行走5公里，可以好好欣赏周边景色");
    }
}
