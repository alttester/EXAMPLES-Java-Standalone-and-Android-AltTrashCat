import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alttester.AltDriver;

import configreader.PropFileReader;
import pages.MainMenuPage;
import pages.StartPage;

public class MainMenuTest {

    private static AltDriver driver;
    private static StartPage startPage;
    private static MainMenuPage mainMenuPage;

    @BeforeClass
    public static void setUp() throws IOException {
        PropFileReader properties = new PropFileReader();
        driver = new AltDriver();
    }

    @Before
    public void loadLevel(){
        mainMenuPage = new MainMenuPage(driver);
        mainMenuPage.loadScene();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.stop();
        Thread.sleep(1000);
    }


    @Test
    public void TestMainPageLoadedCorrectly(){

        mainMenuPage.setCharacterName();
        mainMenuPage.setLeaderBoardButton();
        mainMenuPage.setMissionButton();
        mainMenuPage.setRunButton();
        mainMenuPage.setSettingsButton();
        mainMenuPage.setStoreButton();
        mainMenuPage.setThemeName();
        assertTrue(mainMenuPage.isDisplayed());
    }

}
