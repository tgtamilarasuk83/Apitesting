package LMSServer.Ulility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Propertyfilereader {

    private static final String FILE_PATH =
            "src/test/java/LMSServer/Resources/Token.properties";

    Properties prop = new Properties();

    public Propertyfilereader() throws IOException {
        FileInputStream fis = new FileInputStream(FILE_PATH);
        prop.load(fis);
        fis.close();
    }

    // Read token
    public String getToken() {
        return prop.getProperty("token");
    }

    // Update token
    public void setToken(String token) throws IOException {

        prop.setProperty("token", token);

        FileOutputStream fos = new FileOutputStream(FILE_PATH);
        prop.store(fos, "Updated Token");
        fos.close();
    }
}