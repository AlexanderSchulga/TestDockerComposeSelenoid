import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.core.cli.Main;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class SelenoidTest {
    private static final ThreadLocal<String> browserHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> versionHolder = new ThreadLocal<>();

    private void runScript(String command) throws IOException, InterruptedException {
        String scriptPath = System.getProperty("user.dir") + "\\src\\test\\resources\\selenoid_manager.bat";

        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", scriptPath, command);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
        Configuration.timeout = 3000;
        //sleep(3000);

    }

    private void runScriptClose(String command) throws IOException, InterruptedException {
        String scriptPath = System.getProperty("user.dir") + "\\src\\test\\resources\\selenoid_manager_close.bat";

        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", scriptPath, command);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
        Configuration.timeout = 3000;
        // sleep(3000);

    }

    @Parameters({"browser", "version"})
    @BeforeMethod
    public void setUp(String browser, String version) throws IOException, InterruptedException {
        runScript("start");

        browserHolder.set(browser);
        versionHolder.set(version);

        // Настройки Selenide + Selenoid
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = browserHolder.get();
        Configuration.browserVersion = versionHolder.get();
        Configuration.browserSize = "1920x1080";
         //Configuration.remoteConnectionTimeout = 120000;
        Configuration.timeout = 5000;

        // Общие капабилити для Selenoid
        var capabilities = Map.of(
                "selenoid:options", Map.of(
                        "enableVNC", true,
                        "enableVideo", false,
                        "enableLog", true,
                        "screenResolution", "1920x1080x24"
                )
        );

        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions();
            options.setCapability("selenoid:options", capabilities.get("selenoid:options"));
            Configuration.browserCapabilities = options;
        } else if ("firefox".equalsIgnoreCase(browser)) {
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("selenoid:options", capabilities.get("selenoid:options"));
            Configuration.browserCapabilities = options;
        }
    }

    @Test
    public void runCucumberTests() throws Exception {
        byte exitStatus = Main.run(
                "--glue", "steps",
                "classpath:features/Rfpl.feature",
                "--plugin", "pretty",
                "--plugin", "html:target/cucumber-report.html"
        );

        if (exitStatus != 0) {
            throw new RuntimeException("тесты завершились с ошибкой: " + exitStatus);
        }
    }

    @AfterMethod
    public void tearDown() throws IOException, InterruptedException {
                runScript("stop");
        runScriptClose("start");
        Thread.sleep(3000);
        runScriptClose("stop");
         //Закрываем браузер и сессию в Selenoid
        WebDriverRunner.closeWebDriver();
    }
}





