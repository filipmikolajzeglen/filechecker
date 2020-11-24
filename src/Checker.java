import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Checker {

    private static Properties settings = null;

    private static String env(String variable) {
        if (settings == null) {
            settings = new Properties();
            try {
                settings.load(new FileInputStream("C:/Users/fzegle23/Desktop/Logs/settings.config"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return settings.getProperty(variable);
    }

    private Map<String, Integer> mapErrors() {
        Map<String, Integer> error = new HashMap<String, Integer>();
        error.put("\"111\"", 0);
        error.put("\"101\"", 0);
        error.put("\"102\"", 0);
        error.put("\"150\"", 0);
        error.put("\"140\"", 0);
        error.put("\"120\"", 0);
        return error;
    }

    void analyse() {
        File logs = new File(env("APP_LOG") + "app.log");
        Map<String, Integer> error = mapErrors();

            error.forEach((k, v) -> {
                Scanner scannedFile = null;
                try {
                    scannedFile = new Scanner(logs);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (scannedFile.hasNext()) {
                    String search = scannedFile.next();
                    if (search.contains(k)) {
                            ++v;
                    }
                }
                System.out.println("Error " + k + " Sum: " + v);
            });
    }
}
