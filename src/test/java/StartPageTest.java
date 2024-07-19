import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alttester.AltDriver;
import pages.MainMenuPage;
import pages.StartPage;

public class StartPageTest {

    private static AltDriver driver;
    private static StartPage startPage;
    private static MainMenuPage mainMenuPage;

    @BeforeClass
    public static void setUp()  {
        driver = new AltDriver();
        startPage = new StartPage(driver);
        mainMenuPage = new MainMenuPage(driver);
    }

    @Before
    public void loadLevel() {
        startPage.load();
    }

    @Test
    public void testStartPageLoadedCorrectly() {
        assertTrue(startPage.isDisplayed());
    }

    @Test
    public void testStartButtonLoadMainMenu() {
        startPage.pressStart();
        mainMenuPage.initializeElements();
        assertTrue(mainMenuPage.isDisplayed());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.stop();
        Thread.sleep(1000);
    }
}
