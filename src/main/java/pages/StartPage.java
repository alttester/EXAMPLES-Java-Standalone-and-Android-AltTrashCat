package pages;

import com.alttester.AltDriver;
import com.alttester.AltObject;
import com.alttester.Commands.UnityCommand.AltLoadSceneParams;

public class StartPage extends BasePage {

    public StartPage(AltDriver driver) {
        super(driver);
    }

    public void load() {
        getDriver().loadScene(new AltLoadSceneParams.Builder("Start").build());
    }
    @Override
    public boolean isDisplayed() {
        return (getStartButton() != null && getStartText() != null && getLogoImage() != null && getUnityUrlButton() != null);
    }

    public void pressStart() {
        getStartButton().tap();
    }

    public AltObject getStartButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByNameWithTimeout(paths.startBtn, 2));
    }

    public AltObject getStartText() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByNameWithTimeout(paths.startTxt, 2));
    }

    public AltObject getLogoImage() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByNameWithTimeout(paths.logoImage, 2));
    }

    public AltObject getUnityUrlButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByNameWithTimeout(paths.unityUrlBtn, 2));
    }
}
