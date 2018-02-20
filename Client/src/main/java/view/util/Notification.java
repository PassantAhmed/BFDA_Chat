package view.util;

import java.awt.*;

public class Notification {

    /**
    * displaying the notification to the active user  
    * @param title
    * @param notificationMsg
    * @throws AWTException
    **/
    public static void displayTray(String title, String notificationMsg) throws AWTException {

        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage(title, notificationMsg, TrayIcon.MessageType.INFO);
    }

}
