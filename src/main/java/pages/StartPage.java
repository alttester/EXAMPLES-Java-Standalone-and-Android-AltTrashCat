package pages;

import com.alttester.AltDriver;
import com.alttester.AltObject;
import com.alttester.Commands.FindObject.AltFindObjectsParams;
import com.alttester.Commands.FindObject.AltWaitForObjectsParams;
import com.alttester.Commands.UnityCommand.AltLoadSceneParams;

public class StartPage extends BasePage {

    public StartPage(AltDriver driver) {
        super(driver);
    }

    public void load() {
        getDriver().loadScene(new AltLoadSceneParams.Builder("Start").build());
    }

    public boolean isDisplayed() {
        return (getStartButton() != null && getStartText() != null && getLogoImage() != null && getUnityUrlButton() != null);
    }

    public void pressStart() {
        getStartButton().tap();
    }

    public String getStartButtonText() {
        return getStartButton().getText();
    }

    public AltObject getStartButton() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.NAME, "StartButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getStartText() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.NAME, "StartText").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getLogoImage() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.NAME, "LogoImage").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getUnityUrlButton() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.NAME, "UnityURLButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }
}
