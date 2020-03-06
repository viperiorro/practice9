package practice9.tests.chrome;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.calculator.CalculationResultPage;
import pages.calculator.CalculatorPage;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static practice9.data.Data.*;

public class CalculatorTest extends BaseTest {

    private CalculatorPage calculatorPage;
    private CalculationResultPage resultPage;

    @BeforeClass
    public void init() {
        baseUrl = props.getProperty("calculator.url");

        calculatorPage = new CalculatorPage();
        resultPage = new CalculationResultPage();
    }

    @BeforeMethod
    public void openHomePage() {
        open(baseUrl);
    }

    @DataProvider
    public static Object[][] positiveParameters() {
        return new Object[][]{
                {"2", "3", ADDITION, "5"},
                {"-5", "-7", ADDITION, "-12"},
                {"7", "3", SUBTRACTION, "4"},
                {"-12", "3", SUBTRACTION, "-15"}
        };
    }

    @Test(dataProvider = "positiveParameters")
    public void checkCalculationWithPositiveParameters(String x, String y, String operation, String expectedResult) {
        calculatorPage.calculate(x, y, operation);

        resultPage.getCalculationResult().shouldHave(text(expectedResult));
    }

/*
    @DataProvider
    public static Object[][] negativeParameters() {
        return new Object[][]{
                {"", "", ADDITION},
                {"-5", "", ADDITION},
                {"", "3", ADDITION},
                {"qwe", "qwe", ADDITION},
                {"-12", "qwe", ADDITION},
                {"qwe", "3", ADDITION},
                {"", "", SUBTRACTION},
                {"-5", "", SUBTRACTION},
                {"", "3", SUBTRACTION},
                {"qwe", "qwe", SUBTRACTION},
                {"-12", "qwe", SUBTRACTION},
                {"qwe", "3", SUBTRACTION},
        };
    }


    @Test(dataProvider = "negativeParameters")
    public void checkCalculationWithNegativeParameters(String x, String y, String operation) {
        calculatorPage.calculate(x, y, operation);

        resultPage.getCalculationResult().shouldHave(text(INVALID_OPERAND_RESULT));
    }
*/
}