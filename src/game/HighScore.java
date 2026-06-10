package game;
import java.io.*;

public class HighScore {

    private static String filename(String key) {
        return "highscore_" + key + ".txt";
    }

    public static int load(String key) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename(key)))) {
            String line = br.readLine();
            if (line != null) return Integer.parseInt(line.trim());
        } catch (IOException | NumberFormatException e) {}
        return 0;
    }

    public static void save(String key, int score) {
        try (FileWriter fw = new FileWriter(filename(key), false)) {
            fw.write(Integer.toString(score));
        } catch (IOException e) {}
    }

    public static int load()           { return load("classic_medium"); }
    public static void save(int score) { save("classic_medium", score); }
}
