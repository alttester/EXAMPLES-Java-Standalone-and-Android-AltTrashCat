import com.alttester.AltDriver;
import org.junit.*;

import pages.GamePlayPage;
import pages.GetAnotherChancePage;
import pages.MainMenuPage;
import pages.PauseOverlayPage;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class GamePlayTest {

    private static AltDriver driver;
    private static MainMenuPage mainMenuPage;
    private static PauseOverlayPage pauseOverlayPage;
    private static GetAnotherChancePage getAnotherChancePage;
    private static GamePlayPage gamePlayPage;

    @BeforeClass
    public static void setUp() {
        driver = new AltDriver();
        mainMenuPage = new MainMenuPage(driver);
        gamePlayPage = new GamePlayPage(driver);
        pauseOverlayPage = new PauseOverlayPage(driver);
        getAnotherChancePage = new GetAnotherChancePage(driver);
    }

    @Before
    public void loadLevel() {
        mainMenuPage.loadScene();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.stop();
        sleep(1000);
    }

    @Test
    public void testGamePlayDisplayedCorrectly() throws Exception {
        mainMenuPage.pressRun();
        assertTrue(gamePlayPage.isDisplayed());
    }

    @Test
    public void testGameCanBePausedAndResumed() throws Exception {
        mainMenuPage.pressRun();
        gamePlayPage.pressPause();
        assertTrue(pauseOverlayPage.isDisplayed());

        pauseOverlayPage.pressResume();
        assertTrue(gamePlayPage.isDisplayed());
    }

    @Test
    public void testGameCanBePausedAndStopped() throws Exception {
        mainMenuPage.pressRun();
        gamePlayPage.pressPause();
        pauseOverlayPage.pressMainMenu();
        assertTrue(mainMenuPage.isDisplayed());
    }

    @Test
    public void testAvoidANumberOfObstacles() throws Exception {
        mainMenuPage.pressRun();
        gamePlayPage.avoidObstacles(10);
        int lifeAfterAvoid = gamePlayPage.getCurrentLife();
        System.out.println("Current life after avoiding obstacles: " + lifeAfterAvoid);
        assertTrue("The cat did not survive for the expected number of obstacles", lifeAfterAvoid > 0);
    }

    @Test
    public void testSurviveTime() throws InterruptedException {
        mainMenuPage.pressRun();
        gamePlayPage.surviveTimeByAvoidingObstacles(15, getAnotherChancePage);
        int lifeAfterAvoid = gamePlayPage.getCurrentLife();
        System.out.println("Current life after the preset number of seconds: " + lifeAfterAvoid);
        assertTrue("The cat did not survive for the expected number of seconds", lifeAfterAvoid > 0);
    }

    @Test
    public void testPlayerDiesWhenObstacleNotAvoided() throws Exception {
        mainMenuPage.pressRun();
        float timeout = 20;
        while (timeout > 0) {
            try {
                getAnotherChancePage.isDisplayed();
                break;
            } catch (Exception e) {
                timeout -= 1;
            }
        }
    }

    @Test
    public void testDistanceRun() throws Exception {
        mainMenuPage.pressRun();
        int distanceCovered = gamePlayPage.getDistanceCovered(getAnotherChancePage);
        int distanceRun = gamePlayPage.getDistanceRun();
        assertEquals("There is a difference between the distance displayed and the calculated distance", distanceRun, distanceCovered);
    }

    @Test
    public void testCollectFishBonesOnMiddleLane() throws Exception {
        mainMenuPage.pressRun();
        int computedCollectedCoins = gamePlayPage.computeCollectedCoins(getAnotherChancePage);
        System.out.println("Total number of collected fishbones: " + computedCollectedCoins);
        int collectedCoins = gamePlayPage.getCollectedCoinsNumber();
        assertEquals("There is a difference between the number of collected fishbones and the number of coins", computedCollectedCoins, collectedCoins);
    }
}
