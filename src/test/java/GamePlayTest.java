import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alttester.AltObject;
import com.alttester.AltDriver;
import org.junit.*;
import java.util.Map;
import java.util.HashMap;

import pages.GamePlayPage;
import pages.GetAnotherChancePage;
import pages.MainMenuPage;
import pages.PauseOverlayPage;

public class GamePlayTest {

    private static AltDriver driver;
    private static MainMenuPage mainMenuPage;
    private static PauseOverlayPage pauseOverlayPage;
    private static GetAnotherChancePage getAnotherChancePage;
    private static GamePlayPage gamePlayPage;

    @BeforeClass
    public static void setUp() throws IOException {
        driver = new AltDriver();
        mainMenuPage = new MainMenuPage(driver);
        gamePlayPage = new GamePlayPage(driver);
        pauseOverlayPage = new PauseOverlayPage(driver);
        getAnotherChancePage = new GetAnotherChancePage(driver);
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
    public void testFishCollectMultipleTimes() throws Exception {

        for (int i = 0; i < 5; i++) {
            System.out.println("i = "+ i);
            mainMenuPage.loadScene();
            testCollectFishesOnMiddleLane();
        }
    }
    @Test
    public void testCollectFishesOnMiddleLane() throws Exception {
        mainMenuPage.pressRun();

        int collectedFishCount = 0;  // Counter for the number of fish bones collected
        long startTime = System.currentTimeMillis();
        long duration = 30000; // 30 seconds
        long interval = 20; // 20 milliseconds
        long nextTimestamp = startTime + interval;

        int lastCatZ = 0;
        int catZOffset = 0;
        int catResetCounter = 0;

        List<Integer> collectedFishIds = new ArrayList<>();
        Map<Integer, AltObject> fishMap = new HashMap<>();
        Map<Integer, AltObject> allMiddleLaneFishMap = new HashMap<>();
        List<AltObject> middleLaneFishbones = new ArrayList<>();

        AltObject character = null;  // Declared outside the loop to access after the loop ends

        while (System.currentTimeMillis() - startTime < duration) {
            try {
                getAnotherChancePage.isDisplayed();
                break;
            } catch (Exception e) {
                if (System.currentTimeMillis() >= nextTimestamp) {
                    character = gamePlayPage.getCharacter();

                    int currentCatZ = (int) character.worldZ;
                    int adjustedCatZ = currentCatZ + catZOffset;
                    System.out.println("@test Timestamp= " + (System.currentTimeMillis() - startTime) + "ms" + "; Pisica se află la X: " + character.worldX + ", Y: " + character.worldY + ", Z: " + adjustedCatZ);

                    // Detect reset for the cat by checking if current Z is smaller than the last Z
                    if (currentCatZ <= lastCatZ) {
                        catResetCounter++;
                        catZOffset = catResetCounter * 100;
                        System.out.println("@test Origin reset detected! New cat Z Offset: " + catZOffset);
                    }
                    lastCatZ = currentCatZ;

                    // Retrieve all fishbones on the middle lane
                    allMiddleLaneFishMap = gamePlayPage.findAllFishMap();
                    for (Map.Entry<Integer, AltObject> entry : allMiddleLaneFishMap.entrySet()) {
                           AltObject fish = entry.getValue();
                           fishMap.put(entry.getKey(), fish);
                           System.out.println("   "+ entry.getKey() + "Pește " + fish.getId() + " pasibil sa fie colectat la Z: " + fish.worldZ);
                        }
                    nextTimestamp += interval;
                }
            }
        }

        for (Map.Entry<Integer, AltObject> entry : fishMap.entrySet()) {
            AltObject fish = entry.getValue();
            System.out.println("    la final: map id = "+ entry.getKey() + " .Pește " + fish.getId() + " la Z: " + fish.worldZ);
        }

        Set<String> uniqueFishSet = new HashSet<>(); // Set pentru a ține evidența combinațiilor unice de id și Z

        if (character != null) {
            int finalAdjustedCatZ = (int) character.worldZ + catZOffset;
            System.out.println("Pisica a murit. Coordonatele finale sunt: X: " + character.worldX + ", Y: " + character.worldY + ", Z: " + finalAdjustedCatZ);

            for (Map.Entry<Integer, AltObject> entry : fishMap.entrySet()) {
                AltObject fish = entry.getValue();
                int fishZ = (int) fish.worldZ; // Trunchiem partea zecimală
                String uniqueKey = fish.getId() + "_" + fishZ; // Creăm o cheie unică bazată pe id și Z

                if (fishZ <= finalAdjustedCatZ && !uniqueFishSet.contains(uniqueKey)) {
                    collectedFishCount++;
                    uniqueFishSet.add(uniqueKey); // Adăugăm combinația unică la set pentru a preveni duplicatele
                    System.out.println(entry.getKey() + " Pește " + fish.getId() + " colectat la Z: " + fishZ);
                }
            }



            System.out.println("@test Numărul total de pești colectați: " + collectedFishCount);
            int collectedCoins = gamePlayPage.getCollectedCoinsNumber();
            // assertEquals(collectedCoins, collectedFishCount);

        }
    }


    @Test
    public void testDistanceRun() throws Exception {
         mainMenuPage.pressRun();

         AltObject characterStart = gamePlayPage.getCharacter();
         System.out.println("START: Pisica a pornit de la X: " + characterStart.worldX + ", Y: " + characterStart.worldY + ", Z: " + characterStart.worldZ);

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
                     int distanceRun = gamePlayPage.getDistanceRun();
                     System.out.println("   Timestamp= " + (System.currentTimeMillis() - startTime) + "ms: Distanța afișată în joc:" + distanceRun + "; Pisica se află la X: " + character.worldX + ", Y: " + character.worldY + ", Z: " + character.worldZ);
                     zValues.add(character.worldZ);
                     nextTimestamp += interval;
                 }
             }
         }

         AltObject characterFinal = gamePlayPage.getCharacter();
         System.out.println("STOP: Pisica s-a oprit la X: " + characterFinal.worldX + ", Y: " + characterFinal.worldY + ", Z: " + characterFinal.worldZ);

         int distanceRun = gamePlayPage.getDistanceRun();
         System.out.println("Distanța afișată în joc: " + distanceRun);

         // Presupunem că valoarea de reset este de 100 și 200 (sau o valoare pe care o stabilești tu)
         float resetValue1 = 100.0f;
         float resetValue2 = 200.0f;

         // Determinăm dacă au avut loc reseturi
         int resetCount = 0;
         for (int i = 1; i < zValues.size(); i++) {
             if (zValues.get(i) < zValues.get(i - 1)) {
                 resetCount++;
                 System.out.println("Origin reset happened ");
             }
         }

         // Calculăm diferența între Z final și Z inițial, ținând cont de reseturi
         float zDistance = 0;
         if (resetCount == 1) {
             // Un singur reset
             zDistance = resetValue1 + characterFinal.worldZ - 2;
         } else if (resetCount == 2) {
             // Două reseturi
             zDistance = resetValue2 + characterFinal.worldZ - 2;
         } else {
             // Fără reset
             zDistance = characterFinal.worldZ - characterStart.worldZ;
         }

         System.out.println("Distanța calculată prin Z: " + zDistance);

         // Analizăm discrepanțele, dacă există
         if (Math.abs(zDistance - distanceRun) > 1) { // Toleranță mică pentru eroare
             System.out.println("Există o discrepanță între distanța calculată și cea afișată în joc.");
             System.out.println("Z Values during run: " + zValues);
         } else {
             System.out.println("Distanțele corespund.");
         }

         // Assert pentru a verifica distanța
         assertEquals("!!!Discrepanță între distanța calculată și cea afișată în joc.", distanceRun, Math.round(zDistance), 1);
 }

    @Test @Ignore
    public void testDistanceRunMultipleTimes() throws Exception {

        for (int i = 0; i < 15; i++) {
            mainMenuPage.loadScene();
            testDistanceRun();
        }
    }

}
