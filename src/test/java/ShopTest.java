
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import com.alttester.AltDriver;

import pages.MainMenuPage;
import pages.ShopPage;

public class ShopTest {

    private static AltDriver driver;
    private static MainMenuPage mainMenuPage;
    private static ShopPage shopPage;

    @BeforeClass
    public static void setUp() {
        driver = new AltDriver();
        mainMenuPage = new MainMenuPage(driver);
        shopPage = new ShopPage(driver);
    }

    @Before
    public void loadLevel(){
        mainMenuPage.loadScene(true);
        shopPage.loadScene(false);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.stop();
        Thread.sleep(1000);
    }

    @Test
    public void testShopPageLoadedCorrectly(){
        assertTrue(shopPage.isDisplayed());
    }

    @Test
    public void testShopPageCanBeClosed(){
        shopPage.closeShopPage();
        assertTrue(mainMenuPage.isDisplayed());
    }

    @Test
    public void testPremiumPopUpOpen(){
        shopPage.pressPremiumPopUp();
        assertTrue(shopPage.checkPopupOpen());
    }

    @Test
    public void testPremiumPopUpClosed(){
        shopPage.pressPremiumPopUp();
        shopPage.pressClosePremiumPopup();
        assertFalse(shopPage.checkPopupOpen());
        assertTrue(shopPage.isDisplayed());
    }
}
