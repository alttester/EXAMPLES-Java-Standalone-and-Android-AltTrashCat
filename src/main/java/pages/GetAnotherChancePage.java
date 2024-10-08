package pages;

import com.alttester.AltDriver;
import com.alttester.AltObject;

public class GetAnotherChancePage extends BasePage {

    public GetAnotherChancePage(AltDriver driver) {
        super(driver);
    }

    public AltObject getGameOver() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.gameOver, 2));
    }

    public AltObject getPremiumButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.premiumButton, 2));
    }

    public AltObject getAvailableCurrency() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.premiumCount, 2));
    }

    @Override
    public boolean isDisplayed() {
        if (getGameOver() != null && getPremiumButton() != null && getAvailableCurrency() != null)
            return true;
        return false;
    }
}
