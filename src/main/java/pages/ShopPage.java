package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alttester.AltDriver;
import com.alttester.AltObject;
import com.alttester.Commands.FindObject.AltFindObjectsParams;
import com.alttester.Commands.FindObject.AltWaitForObjectsParams;
import com.alttester.Commands.UnityCommand.AltLoadSceneParams;

public class ShopPage extends BasePage {

    public ShopPage(AltDriver driver) {
        super(driver);
    }


    public void loadScene() {
        getDriver().loadScene(new AltLoadSceneParams.Builder("Shop").build());
    }

    public void loadScene(boolean loadSingle) {
        getDriver().loadScene(new AltLoadSceneParams.Builder("Shop").
                loadSingle(loadSingle).build());
    }

    public AltObject getStoreTitle() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Background/StoreTitle").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getItemsButton() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Background/TabsSwitch/Item").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getCharactersButton() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Background/TabsSwitch/Character").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getAccessoriesButton() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Background/TabsSwitch/Accesories").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getThemesButton() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Background/TabsSwitch/Themes").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getPremiumButton() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Background/Premium/Button").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getCoinSection() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Background/Premium").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getCloseButton() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Background/Button").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(5).build();
        AltObject closeButton = getDriver().waitForObject(params);
        return closeButton;
    }
    @Override
    public boolean isDisplayed() {
        if (getStoreTitle() != null && getItemsButton() != null && getCharactersButton() != null &&
                getAccessoriesButton() != null && getThemesButton() != null && getPremiumButton() != null && getCoinSection() != null && getCloseButton() != null) {
            return true;
        }
        return false;
    }

    public Integer getShopItemCount(int itemIndex) {

        AltFindObjectsParams params = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Icon/Count").build();
        List<AltObject> shopItemsCounts = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContain(params)));

        return Integer.parseInt(shopItemsCounts.get(itemIndex).getText());
    }

    public void clickBuyButton(int index) {
        AltFindObjectsParams params = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//NamePriceButtonZone/PriceButtonZone/BuyButton").build();
        List<AltObject> itemsBuyButtons = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContain(params)));
        itemsBuyButtons.get(index).tap();
    }

    public AltObject getPopup() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Background/IAPPopup").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getClosePopupButton() {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Background/IAPPopup/Image/Close").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        return getDriver().waitForObject(params);
    }

    public void pressPremiumPopUp() {
        getPremiumButton().tap();
    }

    public void pressClosePremiumPopup() {
        getClosePopupButton().tap();
    }

    public boolean checkPopupOpen(){
        try{

            AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Background/IAPPopup").build();
            AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
            AltObject  getPremiumPopup = getDriver().waitForObject(params);
            return true;

        }catch(Exception e){
            return false;
        }
    }

    public void closeShopPage() {
        AltObject closeButton = getCloseButton();
        closeButton.tap();
    }
}

