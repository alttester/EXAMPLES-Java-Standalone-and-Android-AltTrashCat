package pages;

import ro.altom.altunitytester.AltUnityDriver;
import ro.altom.altunitytester.AltUnityObject;
import ro.altom.altunitytester.Commands.FindObject.AltFindObjectsParams;
import ro.altom.altunitytester.Commands.FindObject.AltWaitForObjectsParams;

public class GetAnotherChancePage extends BasePage{

    public AltUnityObject gameOverButton;
    public AltUnityObject premiumButton;
    public AltUnityObject availableCurrency;

    public GetAnotherChancePage(AltUnityDriver driver) {
        super(driver);
    }

    public void getGameOver(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Game/DeathPopup/GameOver").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.gameOverButton = getDriver().waitForObject(params);
    }

    public void getPremiumButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Game/DeathPopup/ButtonLayout/Premium Button").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.premiumButton = getDriver().waitForObject(params);
    }

    public void getAvailableCurrency(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Game/DeathPopup/PremiumDisplay/PremiumOwnCount").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.availableCurrency = getDriver().waitForObject(params);
    }

    public boolean isDisplayed(){
        if(gameOverButton != null && premiumButton != null && availableCurrency != null)
            return true;
        return false;
    }

    public void pressGameOver(){
        gameOverButton.tap();
    }

    public void pressPremiumBotton(){
        premiumButton.tap();
    }
}
