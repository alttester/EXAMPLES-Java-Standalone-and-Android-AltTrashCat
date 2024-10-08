import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alttester.AltObject;
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

        List<Float> zValues = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        long duration = 25000;
        long interval = 1000;
        long nextTimestamp = startTime + interval;

        while (System.currentTimeMillis() - startTime < duration) {
            try {
                getAnotherChancePage.isDisplayed();
                break;
            } catch (Exception e) {
                if (System.currentTimeMillis() >= nextTimestamp) {
                    AltObject character = gamePlayPage.getCharacter();
                    zValues.add(character.worldZ);
                    nextTimestamp += interval;
                }
            }
        }

        AltObject characterFinal = gamePlayPage.getCharacter();
        int distanceRun = gamePlayPage.getDistanceRun();
        int resetCount = 0;

        for (int i = 1; i < zValues.size(); i++) {
            if (zValues.get(i) < zValues.get(i - 1)) {
                resetCount++;
            }
        }

        float zDistance = resetCount * 100 + characterFinal.worldZ - 2;
        assertEquals("There is a difference between the distance displayed and the calculated distance", distanceRun, Math.round(zDistance), 1);
    }

    @Test
    public void testCollectFishBonesOnMiddleLane() throws Exception {
        mainMenuPage.pressRun();

        int collectedFishCount = 0;  // counter for the number of fishbones collected
        long startTime = System.currentTimeMillis();
        long duration = 25000;
        long interval = 20;
        long nextTimestamp = startTime + interval;

        int lastCatZ = 0;
        int catZOffset = 0;
        int catResetCounter = 0;

        int lastFishZ = 0;
        int fishZOffset = 0;
        int fishResetCounter = 0;

        double tolerance = 0.3;  // tolerance for checking if the cat has passed the fishbone
        List<Integer> collectedFishIds = new ArrayList<>();
        Set<String> uniqueFishPairs = new HashSet<>();
        List<AltObject> middleLaneFishbones = new ArrayList<>();

        AltObject character = null;

        while (System.currentTimeMillis() - startTime < duration) {
            try {
                getAnotherChancePage.isDisplayed();
                break;
            } catch (Exception e) {
                if (System.currentTimeMillis() >= nextTimestamp) {
                    character = gamePlayPage.getCharacter();

                    int currentCatZ = (int) character.worldZ;

                    //detect origin reset for the cat by checking if current Z is smaller than the last Z
                    if (currentCatZ < lastCatZ) {
                        catResetCounter++;
                        catZOffset = catResetCounter * 100;
                    }
                    lastCatZ = currentCatZ;

                    //update middleLaneFishbones by adding new fishbones spawned during the game
                    List<AltObject> currentFishbones = gamePlayPage.findAllFish();
                    for (AltObject fish : currentFishbones) {
                        int fishZ = (int) fish.worldZ;

                    //detect reset for the fish by checking if current Z is smaller than the last Z
                        if (fishZ < lastFishZ) {
                            fishResetCounter++;
                            fishZOffset = fishResetCounter * 100; //adjust offset by 100 for each reset

                        }
                        lastFishZ = fishZ;

                        int adjustedFishZ = fishZ + fishZOffset;  //apply fish-specific Z correction
                        String fishPair = fish.getId() + "-" + adjustedFishZ;
                        if (uniqueFishPairs.add(fishPair)) { //add only unique fishbones based on (ID, Z) pair
                            fish.worldZ = adjustedFishZ; //update Z with corrected value
                            middleLaneFishbones.add(fish);
                        }
                    }

                    nextTimestamp += interval;
                }
            }
        }

        //after the cat has died check how many fishbones were passed
        for (AltObject fish : middleLaneFishbones) {
            int fishZ = (int) fish.worldZ;  //this is already adjusted with Z offset
            int adjustedCatZ = lastCatZ + catZOffset;
            //use corrected Z for comparison
            if (fishZ <= adjustedCatZ + tolerance && !collectedFishIds.contains(fish.id)) {
                collectedFishCount++;
                collectedFishIds.add(fish.id);
            }
        }

        System.out.println("Total number of collected fishbones: " + collectedFishCount);
        int collectedCoins = gamePlayPage.getCollectedCoinsNumber();
        assertEquals("There is a difference between the number of collected fishbones and the number of coins", collectedFishCount, collectedCoins);
    }
}
