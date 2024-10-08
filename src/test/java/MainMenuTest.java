import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alttester.AltDriver;

import pages.MainMenuPage;

public class MainMenuTest {

    private static AltDriver driver;
    private static MainMenuPage mainMenuPage;

    @BeforeClass
    public static void setUp() {
        driver = new AltDriver();
        mainMenuPage = new MainMenuPage(driver);
    }

    @Before
    public void loadLevel() {
        mainMenuPage.loadScene();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.stop();
        Thread.sleep(1000);
    }

    @Test
    public void TestMainPageLoadedCorrectly() {
        assertTrue(mainMenuPage.isDisplayed());
    }
}
