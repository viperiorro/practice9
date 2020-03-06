package pages.voting;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


@NoArgsConstructor
public class VotingResultPage {

    private SelenideElement backButton = $(By.cssSelector("[href='voting.html']"));
    @Getter
    private SelenideElement numberOfMembersInFirstSport = $(By.xpath("//tr[1]/td[2]"));
    @Getter
    private SelenideElement namesInFirstSport = $(By.xpath("//tr[1]/td[3]"));
    @Getter
    private SelenideElement errorMessage = $(By.cssSelector("[name='error']"));

    public void goBack() {
        backButton.click();
    }
}