//import altunity.alttrashcatj.configreader.PropFileReader;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import configreader.PropFileReader;
import pages.MainMenuPage;
import pages.StartPage;
import ro.altom.altunitytester.AltUnityDriver;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class MainMenuTest {

    private static AltUnityDriver driver;
    private static StartPage startPage;
    private static MainMenuPage mainMenuPage;

    @BeforeClass
    public static void setUp() throws IOException {
        PropFileReader properties = new PropFileReader();
        driver = new AltUnityDriver(properties.getDeviceIP(), 13000,";","&",true);
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
