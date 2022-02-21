
import pages.*;
import configreader.PropFileReader;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ro.altom.altunitytester.AltUnityDriver;
import ro.altom.altunitytester.Commands.UnityCommand.AltLoadSceneParams;


import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class StartPageTest {

    private static AltUnityDriver driver;
    private static StartPage startPage;
    private static MainMenuPage mainMenuPage;

    @BeforeClass
    public static void setUp() throws IOException {
        PropFileReader properties = new PropFileReader();
        driver = new AltUnityDriver();
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
