package pages;

import java.util.*;

import com.alttester.AltDriver;
import com.alttester.AltObject;
import com.alttester.Commands.FindObject.AltFindObjectsParams;
import com.alttester.Commands.FindObject.AltWaitForObjectsParams;
import com.alttester.Commands.InputActions.AltSwipeParams;
import com.alttester.Commands.ObjectCommand.AltCallComponentMethodParams;
import com.alttester.position.Vector2;

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


    public AltObject getCharacter() {
        AltFindObjectsParams par = new Builder(AltDriver.By.NAME, "PlayerPivot").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        return getDriver().waitForObject(params);
    }

    public AltObject getObstacle(int obstacleId) {
        AltFindObjectsParams par = new Builder(AltDriver.By.ID, String.valueOf(obstacleId)).build();
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

        boolean movedLeft = false;
        boolean movedRight = false;

        for (int i = 0; i < nrOfObstacles; i++) {

            List<AltObject> allObstacles = getAllObstacles();

            sortObstaclesByLane(allObstacles);

            removeObstaclesBehindCharacter(allObstacles, character1);

            AltObject obstacle = allObstacles.get(0);

            long startTime = System.currentTimeMillis();
            while (obstacle.worldZ - character1.worldZ > 5 && (System.currentTimeMillis() - startTime) < 15000) {
                obstacle = getObstacle(obstacle.id);
                if (obstacle == null) {
                    System.out.println("Obstacle not found during update.");
                    break;
                }
                character1 = getCharacter();
            }

            // avoiding obstacles
            if (obstacle.name.contains("ObstacleHighBarrier")) {
                slide(character1);
            } else if (obstacle.name.contains("ObstacleLowBarrier") || obstacle.name.contains("Rat")) {
                jump(character1);
            } else {
                if (allObstacles.size() > 1 && obstacle.worldZ == allObstacles.get(1).worldZ) {
                    if (obstacle.worldX == character1.worldX) {
                        if (allObstacles.get(1).worldX == -1.5f) {
                            changeLane(character1, 1);
                            movedRight = true;
                        } else {
                            changeLane(character1, -1);
                            movedLeft = true;
                        }
                    } else {
                        if (allObstacles.get(1).worldX == character1.worldX) {
                            if (obstacle.worldX == -1.5f) {
                                changeLane(character1, 1);
                                movedRight = true;
                            } else {
                                changeLane(character1, -1);
                                movedLeft = true;
                            }
                        }
                    }
                } else {
                    if (obstacle.worldX == character1.worldX) {
                        changeLane(character1, -1);
                        movedRight = true;
                    }
                }
            }

            startTime = System.currentTimeMillis();
            while (character1.worldZ - 3 < obstacle.worldZ && character1.worldX < 99 && (System.currentTimeMillis() - startTime) < 15000) {
                obstacle = getObstacle(obstacle.id);
                if (obstacle == null) {
                    System.out.println("Obstacle not found during second update.");
                    break;
                }
                character1 = getCharacter();
            }

            if (movedRight) {
                changeLane(character1, -1);
                movedRight = false;
            }
            if (movedLeft) {
                changeLane(character1, 1);
                movedLeft = false;
            }
        }
    }


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
            if (fish.worldX != 0.0f) {
                continue;
            }

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

        return middleLaneFishbones;
    }

    public int getCollectedCoinsNumber() throws Exception {

        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, "/UICamera/Game/WholeUI/CoinZone/CoinText").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        AltObject coinsUI = getDriver().waitForObject(params);

        if (coinsUI == null) {
            throw new NullPointerException("CoinText object not found");
        }

        return Integer.parseInt(coinsUI.getText());
    }

    public int getDistanceRun() throws Exception {

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

    public void surviveTimeByAvoidingObstacles(long seconds) {
        boolean movedRight = false;
        boolean movedLeft = false;
        boolean inTheMiddle = true;
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < seconds * 1000) {

            List<AltObject> allObstacles = getAllObstacles();
            AltObject character1 = getCharacter();
            removeObstaclesBehindCharacter(allObstacles, character1);

            for (AltObject obstacle : allObstacles) {
                if (obstacle.getWorldZ() - 6.0f < character1.getWorldZ()) {
                    if (obstacle.getWorldX() < 0) {
                        // obstacle on the left
                        if (isHighBarrier(obstacle)) {
                            slide(character1);
                        } else if (isLowBarrier(obstacle)) {
                            jump(character1);
                        } else if (movedLeft && !inTheMiddle) {
                            changeLane(character1, 1);
                            inTheMiddle = true;
                            movedRight = true;
                            movedLeft = false;
                        }

                    } else if (obstacle.getWorldX() > 0) {
                        // obstacle on the right
                        if (isHighBarrier(obstacle)) {
                            slide(character1);
                        } else if (isLowBarrier(obstacle)) {
                            jump(character1);
                        } else if (movedRight && !inTheMiddle) {
                            changeLane(character1, -1);
                            inTheMiddle = true;
                            movedRight = false;
                            movedLeft = true;
                        }

                    } else {
                        // obstacle in the middle
                        if (isHighBarrier(obstacle)) {
                            slide(character1);
                        } else if (isLowBarrier(obstacle)) {
                            jump(character1);
                        } else if (inTheMiddle && movedLeft) {
                            changeLane(character1, -1);
                            // don't move back right
                            movedRight = true;
                        } else if (inTheMiddle && movedRight) {
                            changeLane(character1, 1);
                            // don't move back left
                            movedLeft = true;
                        } else {
                            changeLane(character1, 1);
                            movedLeft = true;
                            inTheMiddle = false;
                        }
                    }
                }
            }
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    private boolean isLowBarrier(AltObject obstacle) {
        return obstacle.getName().contains("LowBarrier");
    }

    private boolean isHighBarrier(AltObject obstacle) {
        return obstacle.getName().contains("HighBarrier");
    }

    private boolean isRat(AltObject obstacle) {
        return obstacle.getName().contains("Rat");
    }

    private boolean isRoadworksCone(AltObject obstacle) {
        return obstacle.getName().contains("RoadworksCone");
    }

    private boolean isRoadworksBarrier(AltObject obstacle) {
        return obstacle.getName().contains("RoadworksBarrier");
    }

    private boolean isObstacleDog(AltObject obstacle) {
        return obstacle.getName().contains("ObstacleDog");
    }

    private boolean isBin(AltObject obstacle) {
        return obstacle.getName().contains("Bin");
    }

    private void jump(AltObject character) {
        character.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "Jump", "Assembly-CSharp", new Object[]{}).build(), Void.class);
        System.out.println("Jumping");
    }

    private void slide(AltObject character) {
        character.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "Slide", "Assembly-CSharp", new Object[]{}).build(), Void.class);
        System.out.println("Sliding");
    }

    private void changeLane(AltObject character, int direction) {
        character.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{direction}).build(), Void.class);
        if (direction == 1)
            System.out.println("Moving right");
        else if (direction == -1)
            System.out.println("Moving left");
    }

    private void slideSwipe(AltObject character) {
        Vector2 endCharacterPosition = character.getScreenPosition();
        Vector2 startCharacterPosition = new Vector2(character.getScreenPosition().getX(), character.getScreenPosition().getY() + 20f);

        getDriver().swipe(new AltSwipeParams.Builder(startCharacterPosition, endCharacterPosition).withDuration(0.2167969f).build());

    }

    private void jumpSwipe(AltObject character) {
        Vector2 startCharacterPosition = character.getScreenPosition();
        Vector2 endCharacterPosition = new Vector2(character.getScreenPosition().getX(), character.getScreenPosition().getX() - 20f);

        getDriver().swipe(new AltSwipeParams.Builder(startCharacterPosition, endCharacterPosition).withDuration(0.2167969f).build());
    }

    private void swipeRightSwipe(AltObject character) {
        Vector2 startCharacterPosition = character.getScreenPosition();
        Vector2 endCharacterPosition = new Vector2(character.getScreenPosition().getX() + 20f, character.getScreenPosition().getY());

        getDriver().swipe(new AltSwipeParams.Builder(startCharacterPosition, endCharacterPosition).withDuration(0.2167969f).build());
    }

    private void swipeLeftSwipe(AltObject character) {
        Vector2 startCharacterPosition = character.getScreenPosition();
        Vector2 endCharacterPosition = new Vector2(character.getScreenPosition().getX() - 20f, character.getScreenPosition().getY());

        getDriver().swipe(new AltSwipeParams.Builder(startCharacterPosition, endCharacterPosition).withDuration(0.2167969f).build());
    }

    private List<AltObject> getAllObstacles() {
        AltFindObjectsParams params = new AltFindObjectsParams.Builder(AltDriver.By.NAME, "Obstacle").build();
        return new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContain(params)));
    }

    private void sortObstaclesByLane(List<AltObject> obstacles) {
        obstacles.sort((x, y) -> {
            if (x.worldZ == y.worldZ) return 0;
            return x.worldZ > y.worldZ ? 1 : -1;
        });
    }

    private void removeObstaclesBehindCharacter(List<AltObject> obstacles, AltObject character) {
        List<AltObject> toBeRemoved = new ArrayList<>();
        for (AltObject obstacle : obstacles) {
            if (obstacle.worldZ < character.worldZ)
                toBeRemoved.add(obstacle);
        }
        obstacles.removeAll(toBeRemoved);
    }

}

