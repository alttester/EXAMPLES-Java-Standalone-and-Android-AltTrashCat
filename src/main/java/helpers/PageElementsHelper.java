package helpers;

import com.alttester.AltDriver;
import com.alttester.Commands.FindObject.AltFindObjectsParams;
import com.alttester.Commands.FindObject.AltWaitForObjectsParams;

public class PageElementsHelper {
    public AltWaitForObjectsParams getWaitForElementByPath(String path) {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, path).build();
        return new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
    }

    public AltWaitForObjectsParams getWaitForElementByPathWithTimeout(String path, int timeout) {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.PATH, path).build();
        return new AltWaitForObjectsParams.Builder(par).withTimeout(timeout).build();
    }

    public AltWaitForObjectsParams getWaitForElementByName(String name) {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.NAME, name).build();
        return new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
    }

    public AltWaitForObjectsParams getWaitForElementByNameWithTimeout(String name, int timeout) {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.NAME, name).build();
        return new AltWaitForObjectsParams.Builder(par).withTimeout(timeout).build();
    }

    public AltWaitForObjectsParams getWaitForElementById(String id) {
        AltFindObjectsParams par = new AltFindObjectsParams.Builder(AltDriver.By.ID, id).build();
        return new AltWaitForObjectsParams.Builder(par).withTimeout(10).build();
    }

    public AltFindObjectsParams getFindElementByName(String name){
        return new AltFindObjectsParams.Builder(AltDriver.By.NAME, name).build();
    }

    public AltFindObjectsParams getFindElementByPath(String path){
        return new AltFindObjectsParams.Builder(AltDriver.By.PATH, path).build();
    }
}
