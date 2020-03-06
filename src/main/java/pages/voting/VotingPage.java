package pages.voting;

import com.codeborne.selenide.SelenideElement;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@NoArgsConstructor
public class VotingPage {

    private SelenideElement firstSport = $(By.xpath("//input[@type='radio'][1]"));
    private SelenideElement nameField = $(By.cssSelector("input[type='text']"));
    private SelenideElement submitButton = $(By.cssSelector("input[type='submit']"));

    public void addMember(String name) {
        firstSport.click();
        nameField.val(name);
        submitButton.click();
    }
}
