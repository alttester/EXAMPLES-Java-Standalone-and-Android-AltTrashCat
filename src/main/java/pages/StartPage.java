package pages;

import ro.altom.altunitytester.AltUnityDriver;
import ro.altom.altunitytester.AltUnityObject;
import ro.altom.altunitytester.Commands.FindObject.AltFindObjectsParams;

import ro.altom.altunitytester.Commands.FindObject.AltWaitForObjectsParams;
import ro.altom.altunitytester.Commands.UnityCommand.AltLoadSceneParams;

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
        getDriver().loadScene(new AltLoadSceneParams.Builder("Start").build());
    }

    public void pressStart(){
        startButton.tap();
    }

    public String getStartingButtonText(){
        return startButton.getText();
    }

    public void getStartButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.NAME, "StartButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.startButton = getDriver().waitForObject(params);
    }

    public void getStartText(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.NAME, "StartText").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.startText = getDriver().waitForObject(params);
    }

    public void getLogoImage(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.NAME, "LogoImage").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.logoImage = getDriver().waitForObject(params);
    }

    public void getUnityURLButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.NAME, "UnityURLButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.unityUrlButton = getDriver().waitForObject(params);
    }
}
