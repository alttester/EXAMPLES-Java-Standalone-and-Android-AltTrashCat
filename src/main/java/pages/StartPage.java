package pages;

import com.alttester.AltDriver;
import com.alttester.AltObject;
import com.alttester.Commands.FindObject.AltFindObjectsParams;
import com.alttester.Commands.FindObject.AltWaitForObjectsParams;
import com.alttester.Commands.UnityCommand.AltLoadSceneParams;

public class StartPage extends BasePage{

    public AltObject startButton;
    public AltObject startText;
    public AltObject logoImage;
    public AltObject unityUrlButton;

    public StartPage(AltDriver driver) {
        super(driver);
    }

    public boolean isDisplayed(){
        if(startButton != null && startText != null && logoImage != null && unityUrlButton != null){
            return true;
        }
        return false;
    }

    public void load(){
        getDriver().loadScene(new AltLoadSceneParams.Builder("Start").build());
    }

    public void pressStart(){
        startButton.tap();
    }

    public String getStartingButtonText(){
        return startButton.getText();
    }

    public void getStartButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.NAME, "StartButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.startButton = getDriver().waitForObject(params);
    }

    public void getStartText(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.NAME, "StartText").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.startText = getDriver().waitForObject(params);
    }

    public void getLogoImage(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.NAME, "LogoImage").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.logoImage = getDriver().waitForObject(params);
    }

    public void getUnityURLButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.NAME, "UnityURLButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.unityUrlButton = getDriver().waitForObject(params);
    }
}
