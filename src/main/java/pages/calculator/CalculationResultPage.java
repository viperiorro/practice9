package pages.calculator;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Getter
@NoArgsConstructor
public class CalculationResultPage {

    @Getter
    private SelenideElement calculationResult = $(By.name("result"));
    private SelenideElement backButton = $("[href='calculator.html']");

    public void goBack() {
        backButton.click();
    }
}