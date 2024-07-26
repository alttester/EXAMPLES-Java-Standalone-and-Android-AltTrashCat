package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.alttester.AltDriver;
import com.alttester.AltObject;
import com.alttester.Commands.FindObject.AltFindObjectsParams;
import com.alttester.Commands.FindObject.AltWaitForObjectsParams;
import com.alttester.Commands.ObjectCommand.AltCallComponentMethodParams;

public class GamePlayPage extends BasePage {


    public GamePlayPage(AltDriver driver) {
        super(driver);
    }

    public AltObject getPauseButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltDriver.By.PATH, "//Game/WholeUI/pauseButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        return getDriver().waitForObject(params);
    }


    public AltObject getCharacter(){
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.NAME, "PlayerPivot").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        return getDriver().waitForObject(params);
    }

    public boolean isDisplayed(){
        if(getPauseButton() != null && getCharacter() != null){
            return true;
        }
        return false;
    }

    public void pressPause(){
        getPauseButton().tap();
    }

    public int getCurrentLife() throws Exception {
        AltObject character = getCharacter();
        if (character ==null)
        {
            throw new NullPointerException("Character not found");
        }

        return character.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController",
                                "get_currentLife", "Assembly-CSharp", new Object[] {}).build(), Integer.class);
    }


    public void avoidObstacles(int nrOfObstacles) throws Exception {
        AltObject character1 = getCharacter();
        if (character1 == null) {
            throw new NullPointerException("Character not found at the start.");
        }

        boolean movedLeft = false;
        boolean movedRight = false;

        for (int i = 0; i < nrOfObstacles; i++) {
            System.out.println("i= " + i);

            AltFindObjectsParams params = new AltFindObjectsParams.Builder(AltDriver.By.NAME, "Obstacle").build();
            List<AltObject> allObstacles = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContain(params)));
            System.out.println("   Toate obstacolele sunt in nr de " + allObstacles.size());
            for (AltObject obs : allObstacles) {
                System.out.println("   Obstacole inainte de filtrare: " + obs.name + ", z: " + obs.worldZ + ", x: " + obs.worldX);
            }

            // Sortare folosind expresii lambda
            allObstacles.sort((x, y) -> {
                if (x.worldZ == y.worldZ) return 0;
                return x.worldZ > y.worldZ ? 1 : -1;
            });

            // Eliminarea obstacolelor care se află în spatele personajului
            List<AltObject> toBeRemoved = new ArrayList<>();
            for (AltObject obs : allObstacles) {
                if (obs.worldZ < character1.worldZ)
                    toBeRemoved.add(obs);
            }
            allObstacles.removeAll(toBeRemoved);

            // Afișarea obstacolelor după filtrare
            System.out.println("   Obstacole rămase după filtrare: " + allObstacles.size());
            for (AltObject obs : allObstacles) {
                System.out.println("    Obstacolele rămase după filtrare: " + obs.name + ", z: " + obs.worldZ + ", x: " + obs.worldX);
            }

            // Selectarea obstacolului cel mai apropiat
            AltObject obstacle = allObstacles.get(0);
            System.out.println("   Closest obstacle: " + obstacle.name + ", z:" + obstacle.worldZ + ", x:" + obstacle.worldX);

            // Bucla de așteptare cu limitare de timp
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
                    throw new NullPointerException("PlayerPivot nu a fost găsit.");
                }
                System.out.println("   Actualizare: Character z:" + character1.worldZ + ", Obstacle " + obstacle.name + " z:" + obstacle.worldZ);
            }
            if (character1 == null || obstacle == null) {
                System.out.println("Character or obstacle is null, skipping this iteration.");
                continue;
            }
            // Acțiuni pentru evitare obstacole
            if (obstacle.name.contains("ObstacleHighBarrier")) {
                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "Slide", "Assembly-CSharp", new Object[]{}).build(), Void.class);
                System.out.println("    Slide pentru obstacol înalt");
            } else if (obstacle.name.contains("ObstacleLowBarrier") || obstacle.name.contains("Rat")) {
                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "Jump", "Assembly-CSharp", new Object[]{}).build(), Void.class);
                System.out.println("    Saritura pentru obstacol jos sau șobolan");
            } else {
                if (allObstacles.size() > 1 && obstacle.worldZ == allObstacles.get(1).worldZ) {
                    System.out.println("   Două obstacole la aceeași distanță Z: " + obstacle.name + " și " + allObstacles.get(1).name);
                    if (obstacle.worldX == character1.worldX) {
                        // Primul obstacol este pe aceeași bandă ca și caracterul
                        if (allObstacles.get(1).worldX == -1.5f) {
                            // Al doilea obstacol este pe banda stângă
                            character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{1}).build(), Void.class);
                            movedRight = true;
                            System.out.println("    Schimbare pe banda dreaptă");
                        } else {
                            // Al doilea obstacol este pe banda dreaptă sau centrală
                            character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{-1}).build(), Void.class);
                            movedLeft = true;
                            System.out.println("    Schimbare pe banda stângă");
                        }
                    } else {
                        if (allObstacles.get(1).worldX == character1.worldX) {
                            // Al doilea obstacol este pe aceeași bandă ca și caracterul
                            if (obstacle.worldX == -1.5f) {
                                // Primul obstacol este pe banda stângă
                                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{1}).build(), Void.class);
                                movedRight = true;
                                System.out.println("   Schimbare pe banda dreaptă");
                            } else {
                                // Primul obstacol este pe banda dreaptă sau centrală
                                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{-1}).build(), Void.class);
                                movedLeft = true;
                                System.out.println("    Schimbare pe banda stângă");
                            }
                        }
                    }
                } else {
                    // Dacă obstacolul curent este pe aceeași bandă ca și caracterul
                    if (obstacle.worldX == character1.worldX) {
                        character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{1}).build(), Void.class);
                        movedRight = true;
                        System.out.println("   Schimbare pe banda dreaptă");
                    }
                }
            }

            // Bucla suplimentară pentru a verifica trecerea obstacolului
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
                    throw new NullPointerException("PlayerPivot nu a fost găsit.");
                }
                System.out.println("   Verificare trecere obstacol: Character z:" + character1.worldZ + ", Obstacle z:" + obstacle.worldZ);
            }

            // Verificăm dacă pisica s-a mutat la dreapta și trebuie să revină pe banda stângă
            if (movedRight) {
                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{-1}).build(), Void.class);
                movedRight = false;
                System.out.println("    Revenire pe banda stângă după mutare la dreapta");
            }
            if (movedLeft) {
                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController", "ChangeLane", "Assembly-CSharp", new Object[]{1}).build(), Void.class);
                movedLeft = false;
                System.out.println("    Revenire pe banda dreaptă după mutare la stânga");
            }
        }
    }



}
