
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alttester.AltDriver;
import com.alttester.Commands.UnityCommand.AltLoadSceneParams;

import configreader.PropFileReader;
import pages.MainMenuPage;
import pages.StartPage;

public class StartPageTest {

    private static AltDriver driver;
    private static StartPage startPage;
    private static MainMenuPage mainMenuPage;

    @BeforeClass
    public static void setUp() throws IOException {
        PropFileReader properties = new PropFileReader();
        driver = new AltDriver();
    }

    @Before
    public void loadLevel() throws Exception {
        driver.loadScene(new AltLoadSceneParams.Builder("Start").build());
//        startPage.load();
        startPage = new StartPage(driver);
        mainMenuPage = new MainMenuPage(driver);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.stop();
        Thread.sleep(1000);
    }

    @Test
    public void testStartPageLoadedCorrectly(){
        startPage.getUnityURLButton();
        startPage.getLogoImage();
        startPage.getStartText();
        startPage.getStartButton();
        assertTrue(startPage.isDisplayed());
    }

    @Test
    public void testStartButtonLoadMainMenu(){

        startPage.getUnityURLButton();
        startPage.getLogoImage();
        startPage.getStartText();
        startPage.getStartButton();
        startPage.pressStart();

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
