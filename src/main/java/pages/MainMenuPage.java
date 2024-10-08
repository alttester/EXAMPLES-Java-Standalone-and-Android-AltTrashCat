package pages;

import com.alttester.AltDriver;
import com.alttester.AltObject;
import com.alttester.Commands.UnityCommand.AltLoadSceneParams;


public class MainMenuPage extends BasePage{

    public MainMenuPage(AltDriver driver) {
        super(driver);
    }

    public AltObject getStoreButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPath(paths.storeButton));
    }

    public AltObject getLeaderBoardButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPath(paths.openLeaderboard));
    }

    public AltObject getSettingsButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPath(paths.settingsButton));
    }

    public AltObject getMissionButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPath(paths.missionButton));
    }

    public AltObject getRunButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPath(paths.startButton));
    }

    public AltObject getCharacterName() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPath(paths.charName));
    }

    public AltObject getThemeName() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPath(paths.themeZone));
    }

    public void loadScene() {
        getDriver().loadScene(new AltLoadSceneParams.Builder("Main").build());
    }

    public void loadScene(boolean loadSingle) {
        getDriver().loadScene(new AltLoadSceneParams.Builder("Main").
                loadSingle(loadSingle).build());
    }

    @Override
    public boolean isDisplayed(){
        return getStoreButton()!= null && getLeaderBoardButton() != null &&
                getSettingsButton() != null && getMissionButton() != null &&
                getRunButton() != null && getCharacterName() != null && getThemeName() != null;
    }

    public void pressRun(){
        getRunButton().tap();
    }

    public void pressStore(){
        getStoreButton().tap();
    }
}
