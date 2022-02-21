package pages;

import ro.altom.altunitytester.AltUnityDriver;
import ro.altom.altunitytester.AltUnityObject;
import ro.altom.altunitytester.Commands.FindObject.AltFindObjectsParams;
import ro.altom.altunitytester.Commands.FindObject.AltWaitForObjectsParams;
import ro.altom.altunitytester.Commands.ObjectCommand.AltCallComponentMethodParams;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GamePlayPage extends BasePage {

    public AltUnityObject pauseButton;
    public AltUnityObject character;

    public GamePlayPage(AltUnityDriver driver) {
        super(driver);
    }

    public void getPauseButton(){
        AltFindObjectsParams par=new AltFindObjectsParams.Builder(AltUnityDriver.By.PATH, "//Game/WholeUI/pauseButton").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        pauseButton = getDriver().waitForObject(params);
    }


    public void getCharacter(){
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltUnityDriver.By.NAME, "PlayerPivot").build();
        AltWaitForObjectsParams params = new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
        this.character = getDriver().waitForObject(params);
    }

    public boolean isDisplayed(){
        if(pauseButton != null && character != null){
            return true;
        }
        return false;
    }

    public void pressPause(){
        pauseButton.tap();
    }

    public int getCurrentLife() throws Exception {
        AltCallComponentMethodParams altCallComponentMethodParams = new AltCallComponentMethodParams.Builder("CharacterInputController","get_currentLife",new Object[] {}).build();

        return character.callComponentMethod(altCallComponentMethodParams,Integer.class);
    }

    public void avoidObstacles(int nrOfObstacles) throws Exception {
        AltUnityObject character1 = character;
        boolean movedLeft = false;
        boolean movedRight = false;
        for(int i=0; i< nrOfObstacles; i++){

            AltFindObjectsParams params = new AltFindObjectsParams.Builder(AltUnityDriver.By.NAME, "Obstacle").build();
            List<AltUnityObject> allObstacles = new ArrayList<>(Arrays.asList(getDriver().findObjectsWhichContain(params)));
            allObstacles.sort((x,y) -> {
                if(x.worldZ==y.worldY)
                    return 0;
                if (x.worldZ>y.worldZ){
                    return 1;
                }
                return -1;
            });

            List<AltUnityObject> toBeRemoved = new ArrayList<>();
            for(AltUnityObject obs: allObstacles){
                if(obs.worldZ < character1.worldZ)
                    toBeRemoved.add(obs);
            }
            allObstacles.removeAll(toBeRemoved);

            AltUnityObject obstacle = allObstacles.get(5);
            System.out.println(("Obstacle: "+ obstacle.name+", z:"+obstacle.worldZ+", x:"+obstacle.worldX));

            while(obstacle.worldZ - character1.worldZ > 5) {
                params = new AltFindObjectsParams.Builder(AltUnityDriver.By.ID, ""+ obstacle.id).build();
                obstacle = getDriver().findObject(params);
                params = new AltFindObjectsParams.Builder(AltUnityDriver.By.NAME, "PlayerPivot").build();
                character1 = getDriver().findObject(params);
            }

            if(obstacle.name.contains("ObstacleHighBarrier")){
                character.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController","Slide",new Object[] {}).build(),Void.class);
            }
            else if (obstacle.name.contains("ObstacleLowBarrier") || obstacle.name.contains("Rat")){
                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController","Jump",new Object[] {}).build(),Void.class);

            }
            else {
                if (obstacle.worldZ == allObstacles.get(1).worldZ) {
                    if (obstacle.worldX == character1.worldX) {
                        if (allObstacles.get(1).worldX == -1.5f) {
                            character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController","ChangeLane",new Object[] {1}).build(),Void.class);
                            movedRight = true;
                        } else {
                            character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController","ChangeLane",new Object[] {-1}).build(),Void.class);
                            movedLeft = true;
                        }
                    } else {
                        if (allObstacles.get(1).worldX == character1.worldX) {
                            if (obstacle.worldX == -1.5f) {
                                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController","ChangeLane",new Object[] {1}).build(),Void.class);
                                movedRight = true;
                            } else {
                                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController","ChangeLane",new Object[] {-1}).build(),Void.class);
                                movedLeft = true;
                            }
                        }
                    }
                }
                else{
                    if(obstacle.worldX == character1.worldX){
                        character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController","ChangeLane",new Object[] {1}).build(),Void.class);
                        movedRight = true;
                    }
                }
            }

            while(character1.worldZ - 3 < obstacle.worldZ && character1.worldX < 99){
                params = new AltFindObjectsParams.Builder(AltUnityDriver.By.ID, ""+obstacle.id).build();
                obstacle = getDriver().findObject(params);
                params = new AltFindObjectsParams.Builder(AltUnityDriver.By.NAME, "PlayerPivot").build();
                character1 = getDriver().findObject(params);
            }

            if(movedRight){
                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController","ChangeLane",new Object[] {-1}).build(),Void.class);
                movedRight = false;
            }

            if(movedLeft){
                character1.callComponentMethod(new AltCallComponentMethodParams.Builder("CharacterInputController","ChangeLane",new Object[] {1}).build(),Void.class);
                movedLeft = false;
            }
        }

    }
}
