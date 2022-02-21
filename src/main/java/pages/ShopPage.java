package pages;

import ro.altom.altunitytester.AltUnityDriver;
import ro.altom.altunitytester.AltUnityObject;
import ro.altom.altunitytester.Commands.FindObject.AltFindObjectsParams;
import ro.altom.altunitytester.Commands.FindObject.AltWaitForObjectsParams;
import ro.altom.altunitytester.Commands.UnityCommand.AltLoadSceneParams;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopPage extends BasePage {

    public AltUnityObject storeTitle;
    public AltUnityObject itemsButton;
    public AltUnityObject charactersButton;
    public AltUnityObject accessoriesButton;
    public AltUnityObject themesButton;
    public AltUnityObject premiumButton;
    public AltUnityObject coinSection;
    public AltUnityObject closeButton;

    public AltUnityObject premiumPopup;
    public AltUnityObject closePremiumPopup;

    public ShopPage(AltUnityDriver driver) {
        super(driver);
    }

    public void loadScene(){
        getDriver().loadScene(new AltLoadSceneParams.Builder("Shop").build());
    }

    public void getStoreTitle() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Background/StoreTitle").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        storeTitle = getDriver().waitForObject(params);
    }

    public void getItemsButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Background/TabsSwitch/Item").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        itemsButton = getDriver().waitForObject(params);
    }

    public void getCharactersButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Background/TabsSwitch/Character").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        charactersButton = getDriver().waitForObject(params);
    }

    public void getAccessoriesButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Background/TabsSwitch/Accesories").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        accessoriesButton = getDriver().waitForObject(params);
    }

    public void getThemesButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Background/TabsSwitch/Themes").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        themesButton = getDriver().waitForObject(params);
    }

    public void getPremiumButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Background/Premium/Button").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        premiumButton = getDriver().waitForObject(params);
    }

    public void getCoinSection() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Background/Premium").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        coinSection = getDriver().waitForObject(params);
    }

    public void getCloseButton() {
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Background/Button").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        closeButton = getDriver().waitForObject(params);
    }

    public boolean isDisplayedCorrectly(){
        if(storeTitle != null && itemsButton != null && charactersButton != null && accessoriesButton != null && themesButton != null && premiumButton != null && coinSection != null && closeButton != null){
            return true;
        }
        return false;
    }

    public Integer getShopItemCount(int itemIndex){

        AltFindObjectsParams params = new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Icon/Count").build();
        List<AltUnityObject> shopItemsCounts = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContain(params)));

        return Integer.parseInt(shopItemsCounts.get(itemIndex).getText());
    }

    public void clickBuyButton(int index){
        AltFindObjectsParams params = new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//NamePriceButtonZone/PriceButtonZone/BuyButton").build();
        List<AltUnityObject> itemsBuyButtons = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContain(params)));
        itemsBuyButtons.get(index).tap();
    }

    public void getPopup(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Background/IAPPopup").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        premiumPopup = getDriver().waitForObject(params);
    }

    public void getClosePopupButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Background/IAPPopup/Image/Close").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
        closePremiumPopup = getDriver().waitForObject(params);
    }

    public void pressPremiumPopUp(){
        premiumButton.tap();
    }

    public void pressClosePremiumPopup(){
        closePremiumPopup.tap();
    }

    public boolean checkPopupOpen(){
        try{
            AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Background/IAPPopup").build();
            AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(2).build();
            premiumPopup = getDriver().waitForObject(params);
            return true;

        }catch(Exception e){
            return false;
        }
    }

    public void pressClose(){
        closeButton.tap();
    }
}
