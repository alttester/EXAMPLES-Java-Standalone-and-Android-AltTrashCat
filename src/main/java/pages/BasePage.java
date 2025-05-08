package pages;

import com.alttester.AltDriver;
import helpers.PageElementsHelper;
import helpers.PageElementsIdentifiers;

public abstract class BasePage {
    private AltDriver driver;
    PageElementsIdentifiers paths = new PageElementsIdentifiers();
    PageElementsHelper elementsHelper = new PageElementsHelper();

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
