package cn.jsbintask.state;

/**
 * @author jsbintask@gmail.com
 * @date 2019/2/2 11:11
 */
public class NotLoggedHttpServiceImpl implements HttpService {
    @Override
    public String getUserInfo() {
        return "please sign in.";
    }
}
