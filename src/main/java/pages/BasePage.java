package pages;

import com.alttester.AltDriver;

public abstract class BasePage implements Displayable {
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

    @Override
    public abstract boolean isDisplayed();

}
