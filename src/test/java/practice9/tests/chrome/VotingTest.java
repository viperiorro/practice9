package practice9.tests.chrome;

import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.voting.VotingPage;
import pages.voting.VotingResultPage;

import static com.codeborne.selenide.Selenide.open;
import static data.Data.numberOfMembers;

public class VotingTest extends BaseTest {

    private VotingPage votingPage;
    private VotingResultPage resultPage;

    @BeforeClass
    public void init() {
        baseUrl = props.getProperty("voting.url");

        votingPage = new VotingPage();
        resultPage = new VotingResultPage();
    }

    @BeforeMethod
    public void openHomePage() {
        open(baseUrl);
    }

    @Test()
    public void checkAdditionMember() {
        votingPage.addMember("Bob");
        numberOfMembers++;

        Assert.assertEquals(resultPage.getNumberOfMembersInFirstSport().text(), numberOfMembers.toString());
    }

    @Test
    public void checkNumberOfMembers() {
        votingPage.addMember("Jon");
        numberOfMembers++;

        Assert.assertTrue(resultPage.getNumberOfMembersInFirstSport().text().contains(numberOfMembers.toString()));
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

        Assert.assertTrue(resultPage.getNamesInFirstSport().text().contains("check1") &&
                        resultPage.getNamesInFirstSport().getText().contains("check2"));
    }
}