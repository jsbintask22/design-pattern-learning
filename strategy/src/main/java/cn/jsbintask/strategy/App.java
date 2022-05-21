package cn.jsbintask.strategy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jsbintask@gmail.com
 * @date 2019/7/10 14:58
 */

@Data
@Slf4j
public class App {
    public static void main(String[] args) {
        System.out.println("天气不错，小明准备出门旅行，它带上了它的旅行助手'TravelService'来帮它制定出行策略.");
        TravelService travelService = new TravelService();

        System.out.println("'TravelService': 天气不错，您可以选择徒步出行");
        travelService.chooseTravelStrategy(new FootTravelStrategy());
        travelService.doTravel();

        System.out.println("'TravelService': 2小时后可能转小雨，建议您选择自行车加快形成，锻炼身体！");
        travelService.chooseTravelStrategy(new BikeTravelStrategy());
        travelService.doTravel();

        System.out.println("'TravelService': 半小时后会有暴雨，建议您选择自驾汽车去目的地.");
        travelService.chooseTravelStrategy(new CarTravelStrategy());
        travelService.doTravel();

        System.out.println("'TravelService': 天气已经好转，您可以随意选择出行方式。");
        travelService.chooseTravelStrategy(() -> {
            log.info("骑行摩托车开始，半小时形成30公里。刺激！");
        });
        travelService.doTravel();

        System.out.println("'TravelService': 您已到达目的地.");

        // 创建一个字符串；
        // 打印字符串
    }
}
