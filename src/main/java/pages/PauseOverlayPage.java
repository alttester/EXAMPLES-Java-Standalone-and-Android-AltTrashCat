package pages;

import com.alttester.AltDriver;
import com.alttester.AltObject;

public class PauseOverlayPage extends BasePage{

    public PauseOverlayPage(AltDriver driver) {
        super(driver);
    }

    public AltObject getResumeButton(){
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPath(paths.resumeButton));
    }

    public AltObject getMainMenuButton(){
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPath(paths.exitButton));
    }

    public AltObject getTitle(){
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPath(paths.textPauseMenu));
    }

    @Override
    public boolean isDisplayed(){
        if(getResumeButton() != null && getMainMenuButton() != null && getTitle() != null){
            return true;
        }
        return false;
    }

    public void pressResume() {
        getResumeButton().tap();
    }

    public void pressMainMenu() {
        getMainMenuButton().tap();
    }
}
