package pages.calculator;

import com.codeborne.selenide.SelenideElement;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@NoArgsConstructor
public class CalculatorPage {

    private SelenideElement firstNumber = $(By.name("x"));
    private SelenideElement secondNumber = $(By.name("y"));
    private SelenideElement operation = $(By.name("operation"));
    private SelenideElement executeCalculationButton = $("[type='submit']");

    public void calculate(String x, String y, String operation) {
        firstNumber.val(x);
        secondNumber.val(y);
        this.operation.selectOptionByValue(operation);
        executeCalculationButton.click();
    }
}