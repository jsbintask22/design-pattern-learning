package cn.jsbintask.state;

/**
 * @author jsbintask@gmail.com
 * @date 2019/2/2 11:13
 */
public class UserController {
    private HttpService httpService;

    public UserController() {
        httpService = new NotLoggedHttpServiceImpl();
    }

    public String getUserInfo() {
        return httpService.getUserInfo();
    }

    public synchronized void login() {
        httpService = new LoggedHttpServiceImpl();
    }

    public synchronized void logout() {
        httpService = new NotLoggedHttpServiceImpl();
    }
}
