package pages;

import ro.altom.altunitytester.AltUnityDriver;
import ro.altom.altunitytester.AltUnityObject;
import ro.altom.altunitytester.Commands.FindObject.AltFindObjectsParameters;
import ro.altom.altunitytester.Commands.FindObject.AltWaitForObjectsParameters;

public class PauseOverlayPage extends BasePage{

    public AltUnityObject resumeButton;
    public AltUnityObject mainMenuButton;
    public AltUnityObject title;

    public PauseOverlayPage(AltUnityDriver driver) {
        super(driver);

    }

    public void getResumeButton(){
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Game/PauseMenu/Resume").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        this.resumeButton = getDriver().waitForObject(params);
    }

    public void getMainMenuButton(){
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Game/PauseMenu/Exit").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        this.mainMenuButton = getDriver().waitForObject(params);
    }

    public void getTitle(){
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Game/PauseMenu/Text").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
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
