package pages;

import com.alttester.AltDriver;
import com.alttester.AltObject;
import com.alttester.Commands.FindObject.AltFindObjectsParams;
import com.alttester.Commands.FindObject.AltWaitForObjectsParams;
import com.alttester.Commands.UnityCommand.AltLoadSceneParams;


public class MainMenuPage extends BasePage{

    public AltObject storeButton;

    public MainMenuPage(AltDriver driver) {
        super(driver);
    }

    public AltObject getStoreButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/StoreButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getLeaderBoardButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/OpenLeaderboard").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getSettingsButton() {
        AltFindObjectsParams par= new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/SettingButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getMissionButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/MissionButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getRunButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/StartButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getCharacterName() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/CharZone/CharName").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getThemeName() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/ThemeZone").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        return getDriver().waitForObject(params);

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
        if(getStoreButton()!= null && getLeaderBoardButton() != null && getSettingsButton() != null && getMissionButton() != null && getRunButton() != null && getCharacterName() != null && getThemeName() != null){
            return true;
        }
        return false;
    }

    public void pressRun(){
        getRunButton().tap();
    }

    public void pressStore(){ storeButton.tap();}


}
