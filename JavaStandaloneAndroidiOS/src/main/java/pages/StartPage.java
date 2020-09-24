package pages;

import ro.altom.altunitytester.AltUnityDriver;
import ro.altom.altunitytester.AltUnityObject;
import ro.altom.altunitytester.Commands.FindObject.AltFindObjectsParameters;
import ro.altom.altunitytester.Commands.FindObject.AltWaitForObjectsParameters;

public class StartPage extends BasePage{

    public AltUnityObject startButton;
    public AltUnityObject startText;
    public AltUnityObject logoImage;
    public AltUnityObject unityUrlButton;

    public StartPage(AltUnityDriver driver) {
        super(driver);
    }

    public boolean isDisplayed(){
        if(startButton != null && startText != null && logoImage != null && unityUrlButton != null){
            return true;
        }
        return false;
    }

    public void load(){
        getDriver().loadScene("Start");
    }

    public void pressStart(){
        startButton.tap();
    }

    public String getStartingButtonText(){
        return startButton.getText();
    }

    public void getStartButton(){
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.NAME, "StartButton").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        this.startButton = getDriver().waitForObject(params);
    }

    public void getStartText(){
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.NAME, "StartText").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        this.startText = getDriver().waitForObject(params);
    }

    public void getLogoImage(){
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.NAME, "LogoImage").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        this.logoImage = getDriver().waitForObject(params);
    }

    public void getUnityURLButton(){
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.NAME, "UnityURLButton").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        this.unityUrlButton = getDriver().waitForObject(params);
    }
}
