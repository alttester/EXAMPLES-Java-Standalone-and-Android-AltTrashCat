package pages;

import com.alttester.AltDriver;

public abstract class BasePage {
    private AltDriver driver;

    public BasePage(AltDriver driver) {
        this.driver = driver;
    }

    public AltDriver getDriver() {
        return driver;
    }

    public void setDriver(AltDriver driver) {
        this.driver = driver;
    }

    public abstract boolean isDisplayed();

}
