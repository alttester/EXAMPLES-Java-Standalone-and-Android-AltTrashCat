
import configreader.PropFileReader;

import pages.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ro.altom.alttester.AltDriver;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class GamePlayTest {

    private static AltDriver driver;
    private static MainMenuPage mainMenuPage;
    private static PauseOverlayPage pauseOverlayPage;
    private static GetAnotherChancePage getAntoherChancePage;
    private static GamePlayPage gamePlayPage;

    @BeforeClass
    public static void setUp() throws IOException {
        driver = new AltDriver();
    }

    @Before
    public void loadLevel() throws Exception {

        mainMenuPage = new MainMenuPage(driver);
        mainMenuPage.loadScene();
        mainMenuPage.setCharacterName();
        mainMenuPage.setLeaderBoardButton();
        mainMenuPage.setMissionButton();
        mainMenuPage.setRunButton();
        mainMenuPage.setSettingsButton();
        mainMenuPage.setStoreButton();
        mainMenuPage.setThemeName();

        gamePlayPage = new GamePlayPage(driver);
        pauseOverlayPage = new PauseOverlayPage(driver);
        getAntoherChancePage = new GetAnotherChancePage(driver);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.stop();
        Thread.sleep(1000);
    }

    @Test
    public void testGamePlayDisplayedCorrectly(){
        mainMenuPage.pressRun();
        gamePlayPage.getPauseButton();
        gamePlayPage.getCharacter();
        assertTrue(gamePlayPage.isDisplayed());
    }

    @Test
    public void testGameCanBePausedAndResumed(){
        mainMenuPage.pressRun();
        gamePlayPage.getCharacter();
        gamePlayPage.getPauseButton();
        gamePlayPage.pressPause();
        pauseOverlayPage.getTitle();
        pauseOverlayPage.getMainMenuButton();
        pauseOverlayPage.getResumeButton();
        assertTrue(pauseOverlayPage.isDisplayed());
        pauseOverlayPage.pressResume();
        assertTrue(gamePlayPage.isDisplayed());
    }

    @Test
    public void testGameCanBePausedAndStopped(){
        mainMenuPage.pressRun();
        gamePlayPage.getCharacter();
        gamePlayPage.getPauseButton();
        gamePlayPage.pressPause();
        pauseOverlayPage.getTitle();
        pauseOverlayPage.getMainMenuButton();
        pauseOverlayPage.getResumeButton();
        pauseOverlayPage.pressMainMenu();
        assertTrue(mainMenuPage.isDisplayed());
    }

    @Test
    public void testAvoidingObstacles() throws Exception {
        mainMenuPage.pressRun();
        gamePlayPage.getCharacter();
        gamePlayPage.getPauseButton();
        gamePlayPage.avoidObstacles(0);
        assertTrue(gamePlayPage.getCurrentLife()>=0);
    }

    @Test
    public void testPlayerDiesWhenObstacleNotAvoided() throws Exception {

        mainMenuPage.pressRun();
        gamePlayPage.getCharacter();
        gamePlayPage.getPauseButton();

        float timeout = 20;
        while(timeout>0){
            try {
                getAntoherChancePage.getGameOver();
                getAntoherChancePage.getAvailableCurrency();
                getAntoherChancePage.getPremiumButton();
                getAntoherChancePage.isDisplayed();
                break;
            }catch(Exception e){
                timeout -= 1;
            }
        }

        assertTrue(getAntoherChancePage.isDisplayed());

    }
}
