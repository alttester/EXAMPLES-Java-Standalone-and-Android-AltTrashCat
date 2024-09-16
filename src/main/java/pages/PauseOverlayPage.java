package pages;

import com.alttester.AltDriver;
import com.alttester.AltObject;
import com.alttester.Commands.FindObject.AltFindObjectsParams;
import com.alttester.Commands.FindObject.AltWaitForObjectsParams;

public class PauseOverlayPage extends BasePage{

    public AltObject resumeButton;
    public AltObject mainMenuButton;
    public AltObject title;

    public PauseOverlayPage(AltDriver driver) {
        super(driver);

    }

    public AltObject getResumeButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Game/PauseMenu/Resume").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getMainMenuButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Game/PauseMenu/Exit").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getTitle(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Game/PauseMenu/Text").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }
    @Override
    public boolean isDisplayed(){
        if(getResumeButton() != null && getMainMenuButton() != null && getTitle() != null){
            return true;
        }
        return false;
    }

    public void pressResume() {
        AltObject resumeButton = getResumeButton();
        if (resumeButton != null) {
            resumeButton.tap();
        } else {
            System.out.println("Resume Button not found, cannot tap.");
        }
    }

    public void pressMainMenu() {
        AltObject mainMenuButton = getMainMenuButton();
        if (mainMenuButton != null) {
            mainMenuButton.tap();
        } else {
            System.out.println("Main Menu Button not found, cannot tap.");
        }
    }
}
