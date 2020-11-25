import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Checker {
    private static String applog = "app.log";
    private static Properties settings = null;

    private static String env(String variable) {
        if (settings == null) {
            settings = new Properties();
            try {
                settings.load(new FileInputStream("settings.config"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return settings.getProperty(variable);
    }

    private Map<String, String> mapOfErrors() {
        Map<String, String> error = new HashMap<String, String>();
        error.put("Exception:", "\t");
        return error;
    }

    void analyse() {
        BufferedReader reader = null;
        Map<String, String> error = mapOfErrors();

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\fzegle23\\Desktop\\Logs\\plc027226\\" + applog));
        } catch (FileNotFoundException e) {
            System.out.println("Plik \"" + applog + "\" nie istnieje!\t");
        }
            try {
                String line = reader.readLine();
                while (line != null) {
                    for (Map.Entry<String, String> entry : error.entrySet()) {
                        String k = entry.getKey();
                        String v = entry.getValue();
                        if (!line.startsWith(v) && line.contains(k)) {
                            System.out.println(k + "\t" + line);
                        }
                    }
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}