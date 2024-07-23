import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alttester.AltDriver;

import pages.GamePlayPage;
import pages.GetAnotherChancePage;
import pages.MainMenuPage;
import pages.PauseOverlayPage;

public class GamePlayTest {

    private static AltDriver driver;
    private static MainMenuPage mainMenuPage;
    private static PauseOverlayPage pauseOverlayPage;
    private static GetAnotherChancePage getAntoherChancePage;
    private static GamePlayPage gamePlayPage;

    @BeforeClass
    public static void setUp() throws IOException {
        driver = new AltDriver();
        mainMenuPage = new MainMenuPage(driver);
        gamePlayPage = new GamePlayPage(driver);
        pauseOverlayPage = new PauseOverlayPage(driver);
        getAntoherChancePage = new GetAnotherChancePage(driver);
    }

    @Before
    public void loadLevel() throws Exception {
        mainMenuPage.loadScene();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.stop();
        Thread.sleep(1000);
    }

    @Test
    public void testGamePlayDisplayedCorrectly(){
        mainMenuPage.pressRun();
        assertTrue(gamePlayPage.isDisplayed());
    }

    @Test
    public void testGameCanBePausedAndResumed() throws Exception{
        mainMenuPage.pressRun();
        Thread.sleep(10000);
        gamePlayPage.pressPause();
        Thread.sleep(10000);
        //assertTrue(pauseOverlayPage.isDisplayed());

       // pauseOverlayPage.pressResume();
       // assertTrue(gamePlayPage.isDisplayed());
    }

    @Test
    public void testGameCanBePausedAndStopped(){
        gamePlayPage.pressPause();
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
