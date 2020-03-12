package practice_tests.tests.chrome;

import com.codeborne.selenide.Configuration;
import config.ResourcesReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import selenoid_support.SelenoidChromeDriverProvider;
import utils.Constants;

import java.util.Properties;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    protected static String baseUrl;
    protected static Constants constants = new Constants();
    protected static ResourcesReader resourcesReader = new ResourcesReader();
    protected static Properties props = resourcesReader.loadPropertiesFile(constants.getFILE_PATH());

    @BeforeAll
    public static void setUp() {
        Configuration.startMaximized = true;

        //setup local or remote (via selenoid) browser
//        if (props.getProperty("browser.remote").equals("true")) {
//            Configuration.browser = SelenoidChromeDriverProvider.class.getName();
//        } else {
//            Configuration.browser = props.getProperty("browser.type1");
//        }
        Configuration.browser = SelenoidChromeDriverProvider.class.getName();
    }

    @AfterAll
    public static void close() {
        closeWebDriver();
    }
}