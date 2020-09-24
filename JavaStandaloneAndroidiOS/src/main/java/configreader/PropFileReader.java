//package altunity.alttrashcatj.configreader;
package configreader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropFileReader {

    InputStream inputStream;

    public String getDeviceIP() throws IOException {
        String deviceIp = new String("");
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found");
            }
            deviceIp += prop.getProperty("deviceIp");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return deviceIp;
    }
}
