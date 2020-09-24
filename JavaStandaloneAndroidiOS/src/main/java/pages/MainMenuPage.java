package pages;

import ro.altom.altunitytester.AltUnityDriver;
import ro.altom.altunitytester.AltUnityObject;
import ro.altom.altunitytester.Commands.FindObject.AltFindObjectsParameters;
import ro.altom.altunitytester.Commands.FindObject.AltWaitForObjectsParameters;

public class MainMenuPage extends BasePage{

    public AltUnityObject storeButton;
    public AltUnityObject leaderBoardButton;
    public AltUnityObject settingsButton;
    public AltUnityObject missionButton;
    public AltUnityObject runButton;
    public AltUnityObject characterName;
    public AltUnityObject themeName;

    public MainMenuPage(AltUnityDriver driver) {
        super(driver);
    }

    public void setStoreButton() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//UICamera/Loadout/StoreButton").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(10).build();
        this.storeButton = getDriver().waitForObject(params);
    }

    public void setLeaderBoardButton() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.NAME, "OpenLeaderboard").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(10).build();
        this.leaderBoardButton = getDriver().waitForObject(params);
    }

    public void setSettingsButton() {
        AltFindObjectsParameters par= new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//UICamera/Loadout/SettingButton").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(10).build();
        this.settingsButton = getDriver().waitForObject(params);
    }

    public void setMissionButton() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//UICamera/Loadout/MissionButton").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(10).build();
        this.missionButton = getDriver().waitForObject(params);
    }

    public void setRunButton() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//UICamera/Loadout/StartButton").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(10).build();
        this.runButton = getDriver().waitForObject(params);
    }

    public void setCharacterName() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//UICamera/Loadout/CharZone/CharName").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(10).build();
        this.characterName = getDriver().waitForObject(params);
    }

    public void setThemeName() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//UICamera/Loadout/ThemeZone").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(10).build();
        this.themeName = getDriver().waitForObject(params);
    }

    public void loadScene(){
        getDriver().loadScene("Main");
    }

    public boolean isDisplayed(){
        if(storeButton != null && leaderBoardButton != null && settingsButton != null && missionButton != null && runButton != null && characterName != null && themeName != null){
            return true;
        }
        return false;
    }

    public void pressRun(){
        runButton.tap();
    }

    public void pressStore(){ storeButton.tap();}
}
