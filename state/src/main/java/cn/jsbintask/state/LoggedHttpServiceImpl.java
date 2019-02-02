package cn.jsbintask.state;

/**
 * @author jsbintask@gmail.com
 * @date 2019/2/2 11:10
 */
public class LoggedHttpServiceImpl implements HttpService {
    @Override
    public String getUserInfo() {
        return "jsbintask";
    }
}
