package practice_tests.tests.chrome;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.voting.VotingPage;
import pages.voting.VotingResultPage;

import static com.codeborne.selenide.Selenide.open;
import static data.Data.numberOfMembers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VotingTest extends BaseTest {

    private static VotingPage votingPage;
    private static VotingResultPage resultPage;

    @BeforeAll
    public static void init() {
        baseUrl = props.getProperty("voting.url");

        votingPage = new VotingPage();
        resultPage = new VotingResultPage();
    }

    @BeforeEach
    public void openHomePage() {
        open(baseUrl);
    }

    @Test
    public void checkAdditionMember() {
        votingPage.addMember("Bob");
        numberOfMembers++;

        assertEquals(resultPage.getNumberOfMembersInFirstSport().text(), numberOfMembers.toString());
    }

    @Test
    public void checkNumberOfMembers() {
        votingPage.addMember("Jon");
        numberOfMembers++;

        assertTrue(resultPage.getNumberOfMembersInFirstSport().text().contains(numberOfMembers.toString()));
    }

    @Test
    public void checkThatNameWitchAlreadyExistIsNotAdded() {
        votingPage.addMember("name");
        numberOfMembers++;
        resultPage.goBack();
        votingPage.addMember("name");

        resultPage.getErrorMessage().shouldBe(Condition.exist);
    }

    @Test
    public void checkNames() {
        votingPage.addMember("check1");
        numberOfMembers++;
        resultPage.goBack();
        votingPage.addMember("check2");
        numberOfMembers++;

        assertTrue(resultPage.getNamesInFirstSport().text().contains("check1") &&
                        resultPage.getNamesInFirstSport().getText().contains("check2"));
    }
}