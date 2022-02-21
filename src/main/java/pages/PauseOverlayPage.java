package pages;

import ro.altom.altunitytester.AltUnityDriver;
import ro.altom.altunitytester.AltUnityObject;
import ro.altom.altunitytester.Commands.FindObject.AltFindObjectsParams;
import ro.altom.altunitytester.Commands.FindObject.AltWaitForObjectsParams;

public class PauseOverlayPage extends BasePage{

    public AltUnityObject resumeButton;
    public AltUnityObject mainMenuButton;
    public AltUnityObject title;

    public PauseOverlayPage(AltUnityDriver driver) {
        super(driver);

    }

    public void getResumeButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Game/PauseMenu/Resume").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.resumeButton = getDriver().waitForObject(params);
    }

    public void getMainMenuButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Game/PauseMenu/Exit").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.mainMenuButton = getDriver().waitForObject(params);
    }

    public void getTitle(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Game/PauseMenu/Text").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.title = getDriver().waitForObject(params);
    }

    public boolean isDisplayed(){
        if(resumeButton != null && mainMenuButton != null && title != null){
            return true;
        }
        return false;
    }

    public void pressResume(){
        resumeButton.tap();
    }

    public void pressMainMenu(){
        mainMenuButton.tap();
    }
}
