package pages;

import ro.altom.altunitytester.AltUnityDriver;
import ro.altom.altunitytester.AltUnityObject;
import ro.altom.altunitytester.Commands.FindObject.AltFindObjectsParameters;
import ro.altom.altunitytester.Commands.FindObject.AltWaitForObjectsParameters;

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
        getDriver().loadScene("Shop");
    }

    public void getStoreTitle() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Background/StoreTitle").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        storeTitle = getDriver().waitForObject(params);
    }

    public void getItemsButton() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Background/TabsSwitch/Item").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        itemsButton = getDriver().waitForObject(params);
    }

    public void getCharactersButton() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Background/TabsSwitch/Character").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        charactersButton = getDriver().waitForObject(params);
    }

    public void getAccessoriesButton() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Background/TabsSwitch/Accesories").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        accessoriesButton = getDriver().waitForObject(params);
    }

    public void getThemesButton() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Background/TabsSwitch/Themes").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        themesButton = getDriver().waitForObject(params);
    }

    public void getPremiumButton() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Background/Premium/Button").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        premiumButton = getDriver().waitForObject(params);
    }

    public void getCoinSection() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Background/Premium").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        coinSection = getDriver().waitForObject(params);
    }

    public void getCloseButton() {
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Background/Button").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        closeButton = getDriver().waitForObject(params);
    }

    public boolean isDisplayedCorrectly(){
        if(storeTitle != null && itemsButton != null && charactersButton != null && accessoriesButton != null && themesButton != null && premiumButton != null && coinSection != null && closeButton != null){
            return true;
        }
        return false;
    }

    public Integer getShopItemCount(int itemIndex){

        AltFindObjectsParameters params = new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Icon/Count").build();
        List<AltUnityObject> shopItemsCounts = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContains(params)));

        return Integer.parseInt(shopItemsCounts.get(itemIndex).getText());
    }

    public void clickBuyButton(int index){
        AltFindObjectsParameters params = new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//NamePriceButtonZone/PriceButtonZone/BuyButton").build();
        List<AltUnityObject> itemsBuyButtons = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContains(params)));
        itemsBuyButtons.get(index).tap();
    }

    public void getPopup(){
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Background/IAPPopup").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
        premiumPopup = getDriver().waitForObject(params);
    }

    public void getClosePopupButton(){
        AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Background/IAPPopup/Image/Close").build();
        AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
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
            AltFindObjectsParameters par=new AltFindObjectsParameters.Builder(AltUnityDriver.By.PATH, "//Background/IAPPopup").build();
            AltWaitForObjectsParameters params = new AltWaitForObjectsParameters.Builder(par).withTimeout(2).build();
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
