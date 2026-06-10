package game;
import java.io.*;
import java.util.Properties;

public class Settings {
    private static final String PATH = "settings.txt";
    private static Theme currentTheme = Theme.DARK;
    private static BlockLayout currentLayout = BlockLayout.CLASSIC;

    static {
        load();
    }

    public static Theme getTheme() { return currentTheme; }
    public static BlockLayout getLayout() { return currentLayout; }

    public static void setTheme(Theme t) {
        currentTheme = t;
        save();
    }

    public static void setLayout(BlockLayout l) {
        currentLayout = l;
        save();
    }

    private static void load() {
        Properties p = new Properties();
        try (FileReader fr = new FileReader(PATH)) {
            p.load(fr);
            try { currentTheme  = Theme.valueOf(p.getProperty("theme", "DARK")); }
            catch (Exception e) {}
            try { currentLayout = BlockLayout.valueOf(p.getProperty("layout", "CLASSIC")); }
            catch (Exception e) {}
        } catch (IOException e) {}
    }

    private static void save() {
        Properties p = new Properties();
        p.setProperty("theme",  currentTheme.name());
        p.setProperty("layout", currentLayout.name());
        try (FileWriter fw = new FileWriter(PATH)) {
            p.store(fw, null);
        } catch (IOException e) {}
    }
}
