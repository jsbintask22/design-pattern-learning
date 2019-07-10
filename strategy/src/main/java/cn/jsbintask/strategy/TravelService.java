package cn.jsbintask.strategy;

/**
 * @author jsbintask@gmail.com
 * @date 2019/7/10 14:57
 */
public class TravelService {
    private TravelStrategy travelStrategy;

    public void chooseTravelStrategy(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    public void doTravel() {
        this.travelStrategy.travel();
    }
}
