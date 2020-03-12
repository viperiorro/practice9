package practice_tests.tests.firefox;

import com.codeborne.selenide.Configuration;
import config.ResourcesReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.voting.VotingPage;
import pages.voting.VotingResultPage;
import selenoid_support.SelenoidGeckoDriverProvider;
import utils.Constants;

import java.util.Properties;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static data.Data.numberOfMembers;

public class CrossBrowserTest {

    protected static String baseUrl;
    protected static ResourcesReader resourcesReader = new ResourcesReader();
    protected static Constants constants = new Constants();
    protected static Properties props = resourcesReader.loadPropertiesFile(constants.getFILE_PATH());

    private static VotingPage votingPage;
    private static VotingResultPage votingResultPage;

    @BeforeAll
    public static void setUp() {
        Configuration.startMaximized = true;

//        //setup local or remote (via selenoid) browser
//        if (props.getProperty("browser.remote").equals("true")) {
//            Configuration.browser = SelenoidGeckoDriverProvider.class.getName();
//        } else {
//            Configuration.browser = props.getProperty("browser.type2");
//        }
        Configuration.browser = SelenoidGeckoDriverProvider.class.getName();

        baseUrl = props.getProperty("voting.url");

        votingPage = new VotingPage();
        votingResultPage = new VotingResultPage();
    }

    @Test
    public void checkThatResultPageInFirefoxContainsSameDataAsChrome() {
        open(baseUrl);
        votingPage.addMember("name1");
        numberOfMembers++;

        votingResultPage.getNumberOfMembersInFirstSport().shouldHave(text(numberOfMembers.toString()));
    }

    @AfterAll
    public static void close() {
        closeWebDriver();
    }
}
