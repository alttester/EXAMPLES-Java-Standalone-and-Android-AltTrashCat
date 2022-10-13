package pages;

import ro.altom.alttester.AltDriver;

public class BasePage {
    private AltUDriver driver;

    public BasePage(AltDriver driver) {
        this.driver = driver;
    }

    public AltDriver getDriver() {
        return driver;
    }

    public void setDriver(AltDriver driver) {
        this.driver = driver;
    }
}
