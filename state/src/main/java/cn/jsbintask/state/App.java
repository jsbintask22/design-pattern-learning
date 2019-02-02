package cn.jsbintask.state;

/**
 * @author jsbintask@gmail.com
 * @date 2019/2/2 11:07
 */
public class App {
    public static void main(String[] args) {
        UserController userController = new UserController();
        System.out.println(userController.getUserInfo());

        userController.login();

        System.out.println(userController.getUserInfo());
    }
}
