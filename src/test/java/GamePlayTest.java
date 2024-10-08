import com.alttester.AltDriver;
import org.junit.*;

import pages.GamePlayPage;
import pages.GetAnotherChancePage;
import pages.MainMenuPage;
import pages.PauseOverlayPage;

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
        Thread.sleep(1000);
    }

    @Test
    public void testGamePlayDisplayedCorrectly() {
        mainMenuPage.pressRun();
        assertTrue(gamePlayPage.isDisplayed());
    }

    @Test
    public void testGameCanBePausedAndResumed() {
        mainMenuPage.pressRun();
        gamePlayPage.pressPause();
        assertTrue(pauseOverlayPage.isDisplayed());

        pauseOverlayPage.pressResume();
        assertTrue(gamePlayPage.isDisplayed());
    }

    @Test
    public void testGameCanBePausedAndStopped() {
        mainMenuPage.pressRun();
        gamePlayPage.pressPause();
        pauseOverlayPage.pressMainMenu();
        assertTrue(mainMenuPage.isDisplayed());
    }

    @Test
    public void testAvoidingObstacles() throws Exception {
        mainMenuPage.pressRun();
        gamePlayPage.avoidObstacles(5);
        System.out.println("Current life after avoiding obstacles: " + gamePlayPage.getCurrentLife());
        assertTrue(gamePlayPage.getCurrentLife() > 0);
    }

    @Test
    public void testSurviveTime() {
        mainMenuPage.pressRun();
        gamePlayPage.surviveTimeByAvoidingObstacles(20);
        System.out.println("Current life after avoiding obstacles: " + gamePlayPage.getCurrentLife());
        assertTrue(gamePlayPage.getCurrentLife() > 0);
    }

    @Test
    public void testPlayerDiesWhenObstacleNotAvoided() {
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
        assertEquals("There is a difference between the distance displayed and the calculated distance", 0, distanceCovered);
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
