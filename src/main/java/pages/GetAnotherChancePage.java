package pages;

import ro.altom.alttester.AltDriver;
import ro.altom.alttester.AltObject;
import ro.altom.alttester.Commands.FindObject.AltFindObjectsParams;
import ro.altom.alttester.Commands.FindObject.AltWaitForObjectsParams;

public class GetAnotherChancePage extends BasePage{

    public AltObject gameOverButton;
    public AltObject premiumButton;
    public AltObject availableCurrency;

    public GetAnotherChancePage(AltDriver driver) {
        super(driver);
    }

    public void getGameOver(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Game/DeathPopup/GameOver").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.gameOverButton = getDriver().waitForObject(params);
    }

    public void getPremiumButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Game/DeathPopup/ButtonLayout/Premium Button").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        this.premiumButton = getDriver().waitForObject(params);
    }

    public void getAvailableCurrency(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Game/DeathPopup/PremiumDisplay/PremiumOwnCount").build();
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
