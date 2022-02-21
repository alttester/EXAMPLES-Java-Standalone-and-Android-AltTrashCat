package pages;

import ro.altom.altunitytester.AltUnityDriver;

public class BasePage {
    private AltUnityDriver driver;

    public BasePage(AltUnityDriver driver) {
        this.driver = driver;
    }

    public AltUnityDriver getDriver() {
        return driver;
    }

    public void setDriver(AltUnityDriver driver) {
        this.driver = driver;
    }
}
