package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alttester.AltDriver;
import com.alttester.AltObject;
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
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.storeTitle, 2));
    }

    public AltObject getItemsButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.itemButton, 2));
    }

    public AltObject getCharactersButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.character, 2));
    }

    public AltObject getAccessoriesButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.accessories, 2));
    }

    public AltObject getThemesButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.themes, 2));
    }

    public AltObject getPremiumButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.premiumShopButton, 2));
    }

    public AltObject getCoinSection() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.backgroundPremium, 2));
    }

    public AltObject getCloseButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.backgroundClose, 5));
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
        List<AltObject> shopItemsCounts = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContain(elementsHelper.getFindElementByPath(paths.itemCount))));
        return Integer.parseInt(shopItemsCounts.get(itemIndex).getText());
    }

    public void clickBuyButton(int index) {
        List<AltObject> itemsBuyButtons = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContain(elementsHelper.getFindElementByPath(paths.buyButton))));
        itemsBuyButtons.get(index).tap();
    }

    public AltObject getPopup() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.iapPopup, 2));
    }

    public AltObject getClosePopupButton() {
        return getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.closePopup, 2));
    }

    public void pressPremiumPopUp() {
        getPremiumButton().tap();
    }

    public void pressClosePremiumPopup() {
        getClosePopupButton().tap();
    }

    public boolean checkPopupOpen() {
        try {
            getDriver().waitForObject(elementsHelper.getWaitForElementByPathWithTimeout(paths.iapPopup, 2));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void closeShopPage() {
        getCloseButton().tap();
    }
}

