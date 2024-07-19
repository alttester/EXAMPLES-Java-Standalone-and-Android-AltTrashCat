
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alttester.AltDriver;

import configreader.PropFileReader;
import pages.MainMenuPage;
import pages.ShopPage;

public class ShopTest {

    private static AltDriver driver;
    private static MainMenuPage mainMenuPage;
    private static ShopPage shopPage;

    private static void getAllObjectsShopPage(){
        shopPage.getStoreTitle();
        shopPage.getAccessoriesButton();
        shopPage.getCharactersButton();
        shopPage.getItemsButton();
        shopPage.getCloseButton();
        shopPage.getThemesButton();
        shopPage.getPremiumButton();
        shopPage.getCoinSection();
    }

    private static void getAllObjectsMainMenuPage(){
        mainMenuPage.getStoreButton();
        mainMenuPage.getThemeName();
        mainMenuPage.getSettingsButton();
        mainMenuPage.getRunButton();
        mainMenuPage.getMissionButton();
        mainMenuPage.getLeaderBoardButton();
        mainMenuPage.getCharacterName();
    }

    @BeforeClass
    public static void setUp() throws IOException {
        PropFileReader properties = new PropFileReader();
        driver = new AltDriver();
    }

    @Before
    public void loadLevel(){
        mainMenuPage = new MainMenuPage(driver);
        shopPage = new ShopPage(driver);
        shopPage.loadScene();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.stop();
        Thread.sleep(1000);
    }

    @Test
    public void ShopPageLoadedCorrectly(){
        getAllObjectsShopPage();
        assertTrue(shopPage.isDisplayedCorrectly());
        shopPage.pressClose();
    }

    @Test
    public void testShopPageCanBeClosed(){
        getAllObjectsShopPage();
        shopPage.pressClose();
        mainMenuPage.loadScene();
        getAllObjectsMainMenuPage();
        assertTrue(mainMenuPage.isDisplayed());
    }

    @Test
    public void testPremiumPopUpOpen(){
        shopPage.getPremiumButton();
        shopPage.pressPremiumPopUp();
        shopPage.getPopup();
        assertTrue(shopPage.checkPopupOpen());
    }

    @Test
    public void testPremiumPopUpClosed(){
        shopPage.getPremiumButton();
        shopPage.pressPremiumPopUp();
        shopPage.getPopup();
        shopPage.getClosePopupButton();
        shopPage.pressClosePremiumPopup();
        assertFalse(shopPage.checkPopupOpen());
    }
}
