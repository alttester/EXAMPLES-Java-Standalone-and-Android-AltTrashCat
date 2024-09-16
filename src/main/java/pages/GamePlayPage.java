package pages;

import java.util.*;

import com.alttester.AltDriver;
import com.alttester.AltObject;
import com.alttester.Commands.FindObject.AltFindObjectsParams;
import com.alttester.Commands.FindObject.AltWaitForObjectsParams;
import com.alttester.Commands.ObjectCommand.AltCallComponentMethodParams;

import static com.alttester.Commands.FindObject.AltFindObjectsParams.*;

import static com.alttester.Commands.FindObject.AltFindObjectsParams.*;

public class GamePlayPage extends BasePage {


    public GamePlayPage(AltDriver driver) {
        super(driver);
    }

    public AltObject getPauseButton() {
        AltFindObjectsParams par = new Builder(AltDriver.By.PATH, "//Game/WholeUI/pauseButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        return getDriver().waitForObject(params);
    }

        Set<String> uniqueFishPairs = new HashSet<>(); //ensures that each fish added to the final list is unique, based on a combination of the fish's ID and its adjusted worldZ value

        List<AltObject> middleLaneFishbones = new ArrayList<>(); //stores the filtered list of fish that are in the middle lane.
        int lastFishZ = 0;
        int fishZOffset = 0;
        int fishResetCounter = 0;

        for (AltObject fish : allFishbones) {
            if (fish.worldX == 0.0f) { // add only the middle lane fishes
                int currentFishZ = (int) fish.worldZ;

                // Detect origin reset for the fish by checking if current Z is smaller than the last Z
                if (currentFishZ < lastFishZ) {
                    fishResetCounter++;
                    fishZOffset = fishResetCounter * 100; // Adjust offset by 100 for each reset
                }
                lastFishZ = currentFishZ;

                int adjustedFishZ = currentFishZ + fishZOffset;
                fish.worldZ = adjustedFishZ; // Update fish Z to the adjusted value

                String fishPair = fish.getId() + "-" + adjustedFishZ;
                if (uniqueFishPairs.add(fishPair)) { // Add only unique fishbones based on (ID, Z) pair
                    middleLaneFishbones.add(fish);
                }
            }
        }

        return middleLaneFishbones;
    }

    public AltObject getCharacter() {
        AltFindObjectsParams par = new Builder(AltDriver.By.NAME, "PlayerPivot").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        return getDriver().waitForObject(params);
    }

    @Override
    public boolean isDisplayed() {
        return (getPauseButton() != null && getCharacter() != null);
    }

    public void pressPause() {
        getPauseButton().tap();
    }

    public int getCurrentLife() {
        AltObject character = getCharacter();
        if (character == null) {
            throw new NullPointerException("Character not found");
        }

        return character.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController",
                "get_currentLife", "Assembly-CSharp", new Object[]{}).build(), Integer.class);
    }

    public void avoidObstacles(int nrOfObstacles) throws Exception {
        AltObject character1 = getCharacter();
        if (character1 == null) {
            throw new NullPointerException("Character not found at the start.");
        }

        boolean movedLeft = false;
        boolean movedRight = false;

        for (int i = 0; i < nrOfObstacles; i++) {

            AltFindObjectsParams params = new AltFindObjectsParams.Builder(AltDriver.By.NAME, "Obstacle").build();
            List<AltObject> allObstacles = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContain(params)));



            allObstacles.sort((x, y) -> {
                if (x.worldZ == y.worldZ) return 0;
                return x.worldZ > y.worldZ ? 1 : -1;
            });

            //remove obstacles behind the character
            List<AltObject> toBeRemoved = new ArrayList<>();
            for (AltObject obs : allObstacles) {
                if (obs.worldZ < character1.worldZ)
                    toBeRemoved.add(obs);
            }
            allObstacles.removeAll(toBeRemoved);

            AltObject obstacle = allObstacles.get(0);

            long startTime = System.currentTimeMillis();
            while (obstacle.worldZ - character1.worldZ > 5 && (System.currentTimeMillis() - startTime) < 15000) {
                params = new AltFindObjectsParams.Builder(AltDriver.By.ID, "" + obstacle.id).build();
                obstacle = getDriver().findObject(params);
                if (obstacle == null) {
                    System.out.println("Obstacle not found during update.");
                    break;
                }
                params = new AltFindObjectsParams.Builder(AltDriver.By.NAME, "PlayerPivot").build();
                character1 = getDriver().findObject(params);
                if (character1 == null) {
                    throw new NullPointerException("PlayerPivot not found");
                }
            }

            // avoiding obstacles
            // avoiding obstacles
            if (obstacle.name.contains("ObstacleHighBarrier")) {
                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "Slide", "Assembly-CSharp", new Object[]{}).build(), Void.class);

            } else if (obstacle.name.contains("ObstacleLowBarrier") || obstacle.name.contains("Rat")) {
                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "Jump", "Assembly-CSharp", new Object[]{}).build(), Void.class);

            } else {
                if (allObstacles.size() > 1 && obstacle.worldZ == allObstacles.get(1).worldZ) {
                     if (obstacle.worldX == character1.worldX) {
                        if (allObstacles.get(1).worldX == -1.5f) {
                            character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{1}).build(), Void.class);
                            movedRight = true;
                         } else {
                            character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{-1}).build(), Void.class);
                            movedLeft = true;
                         }
                    } else {
                        if (allObstacles.get(1).worldX == character1.worldX) {
                             if (obstacle.worldX == -1.5f) {
                                 character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{1}).build(), Void.class);
                                movedRight = true;
                             } else {
                                 character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{-1}).build(), Void.class);
                                movedLeft = true;
                             }
                        }
                    }
                } else {
                     if (obstacle.worldX == character1.worldX) {
                        character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{1}).build(), Void.class);
                        movedRight = true;
                     }
                }
            }

             startTime = System.currentTimeMillis();
            while (character1.worldZ - 3 < obstacle.worldZ && character1.worldX < 99 && (System.currentTimeMillis() - startTime) < 15000) {
                params = new AltFindObjectsParams.Builder(AltDriver.By.ID, "" + obstacle.id).build();
                obstacle = getDriver().findObject(params);
                if (obstacle == null) {
                    System.out.println("Obstacle not found during second update.");
                    break;
                }
                params = new AltFindObjectsParams.Builder(AltDriver.By.NAME, "PlayerPivot").build();
                character1 = getDriver().findObject(params);
                if (character1 == null) {
                    throw new NullPointerException("PlayerPivot not found");
                }
             }

             if (movedRight) {
                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{-1}).build(), Void.class);
                movedRight = false;
             }
            if (movedLeft) {
                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{1}).build(), Void.class);
                movedLeft = false;
             }
        }
    }

            // Bucla suplimentară pentru a verifica trecerea obstacolului
        // Sortare folosind expresii lambda
        allFishbones.sort((x, y) -> {
            if (x.worldZ == y.worldZ) return 0;
            return x.worldZ > y.worldZ ? 1 : -1;
        });

    public List<AltObject> findAllFish() throws Exception {
        AltFindObjectsParams params = new AltFindObjectsParams.Builder(AltDriver.By.NAME, "Fishbone").build();
        List<AltObject> allFishbones = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContain(params)));

        allFishbones.sort((x, y) -> { // sort the list based on the elements' worldZ positions in ascending order
            int xZ = (int) x.worldZ;
            int yZ = (int) y.worldZ;
            return Integer.compare(xZ, yZ);
        });

        Set<String> uniqueFishPairs = new HashSet<>(); //ensures that each fish added to the final list is unique, based on a combination of the fish's ID and its adjusted worldZ value

        List<AltObject> middleLaneFishbones = new ArrayList<>(); //stores the filtered list of fish that are in the middle lane.
        int lastFishZ = 0;
        int fishZOffset = 0;
        int fishResetCounter = 0;

        for (AltObject fish : allFishbones) {
            if (fish.worldX == 0.0f) { // add only the middle lane fishes
                int currentFishZ = (int) fish.worldZ;

                // Detect origin reset for the fish by checking if current Z is smaller than the last Z
                if (currentFishZ < lastFishZ) {
                    fishResetCounter++;
                    fishZOffset = fishResetCounter * 100; // Adjust offset by 100 for each reset
                }
                lastFishZ = currentFishZ;

                int adjustedFishZ = currentFishZ + fishZOffset;
                fish.worldZ = adjustedFishZ; // Update fish Z to the adjusted value

                String fishPair = fish.getId() + "-" + adjustedFishZ;
                if (uniqueFishPairs.add(fishPair)) { // Add only unique fishbones based on (ID, Z) pair
                    middleLaneFishbones.add(fish);
                }
            }
        }

        return middleLaneFishbones;
    }


    public List<AltObject> findAllFish() throws Exception {
        AltFindObjectsParams params = new AltFindObjectsParams.Builder(AltDriver.By.NAME, "Fishbone").build();
        List<AltObject> allFishbones = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContain(params)));

    public int getCollectedCoinsNumber() throws Exception {
        AltObject character = getCharacter();
        if (character == null) {
            throw new NullPointerException("Character not found");
        }

        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "/UICamera/Game/WholeUI/CoinZone/CoinText").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        AltObject coinsUI = getDriver().waitForObject(params);

        if (coinsUI == null) {
            throw new NullPointerException("CoinText object not found");
        }

        return Integer.parseInt(coinsUI.getText());
    }
    public int getDistanceRun() throws Exception {
        AltObject character = getCharacter();
        if (character == null) {
            throw new NullPointerException("Character not found");
        }

        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "/UICamera/Game/WholeUI/DistanceZone/DistanceText").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        AltObject distance = getDriver().waitForObject(params);

        String numericText = distance.getText().replaceAll("[^\\d]", "");

        try {
            return Integer.parseInt(numericText);
        } catch (NumberFormatException e) {
            throw new Exception("Failed to parse distance text into an integer: " + distance.getText(), e);
        }
    }

    public int getCollectedCoinsNumber() throws Exception {
        AltObject character = getCharacter();
        if (character == null) {
            throw new NullPointerException("Character not found");
        }

        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "/UICamera/Game/WholeUI/CoinZone/CoinText").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        AltObject coinsUI = getDriver().waitForObject(params);

        if (coinsUI == null) {
            throw new NullPointerException("CoinText object not found");
        }

        return Integer.parseInt(coinsUI.getText());
    }
    public int getDistanceRun() throws Exception {
        AltObject character = getCharacter();
        if (character == null) {
            throw new NullPointerException("Character not found");
        }

        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "/UICamera/Game/WholeUI/DistanceZone/DistanceText").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        AltObject distance = getDriver().waitForObject(params);

        String numericText = distance.getText().replaceAll("[^\\d]", "");

        try {
            return Integer.parseInt(numericText);
        } catch (NumberFormatException e) {
            throw new Exception("Failed to parse distance text into an integer: " + distance.getText(), e);
        }
    }

}

