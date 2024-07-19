package pages;

import com.alttester.AltDriver;
import com.alttester.AltObject;
import com.alttester.Commands.FindObject.AltFindObjectsParams;
import com.alttester.Commands.FindObject.AltWaitForObjectsParams;
import com.alttester.Commands.UnityCommand.AltLoadSceneParams;


public class MainMenuPage extends BasePage{

    public AltObject storeButton;
    //AltObject is a type from the AltTester framework used to interact with these elements.
    public AltObject leaderBoardButton;
    public AltObject settingsButton;
    public AltObject missionButton;
    public AltObject runButton;
    public AltObject characterName;
    public AltObject themeName;

    public MainMenuPage(AltDriver driver) {
        //constructor
        super(driver);
    }

    public AltObject setStoreButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/StoreButton").build();
        //Defines how to find the object
        //AltFindObjectsParams: A class used to define how to locate an object in the application's UI.

        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        //Combines the find parameters with a timeout to create the wait parameters

        storeButton = getDriver().waitForObject(params);
        return storeButton;
        //Uses the combined parameters to wait for the object to appear in the UI and assigns it to the field.
    }

    public AltObject setLeaderBoardButton() {
        //AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.NAME, "OpenLeaderboard").build();
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/OpenLeaderboard").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        leaderBoardButton = getDriver().waitForObject(params);
        return leaderBoardButton;
    }

    public AltObject setSettingsButton() {
        AltFindObjectsParams par= new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/SettingButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        settingsButton = getDriver().waitForObject(params);
        return settingsButton;
    }

    public AltObject setMissionButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/MissionButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        missionButton = getDriver().waitForObject(params);
        return missionButton;
    }

    public AltObject setRunButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/StartButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        runButton = getDriver().waitForObject(params);
        return runButton;
    }

    public AltObject setCharacterName() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/CharZone/CharName").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        characterName = getDriver().waitForObject(params);
        return characterName;
    }

    public AltObject setThemeName() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//UICamera/Loadout/ThemeZone").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        themeName = getDriver().waitForObject(params);
        return themeName;
    }

    public void loadScene(){

        getDriver().loadScene(new AltLoadSceneParams.Builder("Main").build());
    }

    public void initializeElements() {
        setStoreButton();
        setLeaderBoardButton();
        setSettingsButton();
        setMissionButton();
        setRunButton();
        setCharacterName();
        setThemeName();
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
