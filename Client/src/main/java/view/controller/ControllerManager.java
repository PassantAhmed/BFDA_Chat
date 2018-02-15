package view.controller;

public class ControllerManager {

    private WelcomeController welcomeController;
    private RegisterController registerController;
    private MainController mainController;
    private LoginController loginController;

    private static ControllerManager instance;

    public static ControllerManager getInstance() {
        if(instance == null)
            instance = new ControllerManager();
        return instance;
    }

    private ControllerManager(){}

    public WelcomeController getWelcomeController() {
        return welcomeController;
    }

    public void setWelcomeController(WelcomeController welcomeController) {
        this.welcomeController = welcomeController;
    }

    public RegisterController getRegisterController() {
        return registerController;
    }

    public void setRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
