package pages;

import com.alttester.AltDriver;
import com.alttester.AltObject;
import com.alttester.Commands.FindObject.AltFindObjectsParams;
import com.alttester.Commands.FindObject.AltWaitForObjectsParams;

public class GetAnotherChancePage extends BasePage{

    public AltObject gameOverButton;
    public AltObject premiumButton;
    public AltObject availableCurrency;

    public GetAnotherChancePage(AltDriver driver) {
        super(driver);
    }

    public AltObject getGameOver(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Game/DeathPopup/GameOver").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getPremiumButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Game/DeathPopup/ButtonLayout/Premium Button").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getAvailableCurrency(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Game/DeathPopup/PremiumDisplay/PremiumOwnCount").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public boolean isDisplayed(){
        if(getGameOver() != null && getPremiumButton () != null && getAvailableCurrency () != null)
            return true;
        return false;
    }

    public void pressGameOver(){
        gameOverButton = getGameOver();
        gameOverButton.tap();
    }

    public void pressPremiumButton(){
        premiumButton= getPremiumButton ();
        premiumButton.tap();
    }
}
