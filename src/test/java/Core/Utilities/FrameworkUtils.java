package Core.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FrameworkUtils {
    private static final Logger log = LoggerFactory.getLogger(FrameworkUtils.class);

    protected static void displayWebDriverManagerBrowsersVersions(Boolean showBrowserVersions) {
        if (showBrowserVersions) {
            log.info(String.format("ChromeDriver available versions: %s", WebDriverManager.chromedriver().getDriverVersions()));
            log.info(String.format("GeckoDriver available versions: %s", WebDriverManager.firefoxdriver().getDriverVersions()));
            log.info(String.format("OperaDriver available versions: %s ", WebDriverManager.operadriver().getDriverVersions()));
            log.info(String.format("EdgeDriver available versions: %s", WebDriverManager.edgedriver().getDriverVersions()));
            log.info(String.format("IEDriver available versions: %s", WebDriverManager.iedriver().getDriverVersions()));
        }
    }

    public static void deleteOldLogs(boolean flag) {
        if (!flag)
            return;

        try {
            FileUtils.deleteDirectory(new File("logs"));
            FileUtils.deleteDirectory(new File("test-output"));
        } catch (IOException e) {
            log.error("Failed to delete logs directory!");
        }
    }

    public static String getCurrentPath() {
        return Paths.get(".").toAbsolutePath().normalize().toString();
    }

    public static void main(String[] args) {
        // displayWebDriverManagerBrowsersVersions(true);
        // deleteOldLogs(true);
    }
}
