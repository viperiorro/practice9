package practice_tests.tests.chrome;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.calculator.CalculationResultPage;
import pages.calculator.CalculatorPage;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static practice_tests.data.Data.*;

public class CalculatorTest extends BaseTest {

    private static CalculatorPage calculatorPage = new CalculatorPage();;
    private static CalculationResultPage resultPage = new CalculationResultPage();

    @BeforeAll
    public static void init() {
        baseUrl = props.getProperty("calculator.url");
    }

    @BeforeEach
    public void openHomePage() {
        calculatorPage = open(baseUrl, CalculatorPage.class);
    }

    static Stream<Arguments> positiveTestProvider() {
        return Stream.of(
                arguments("2", "3", ADDITION, "5"),
                arguments("-5", "-7", ADDITION, "-12"),
                arguments("7", "3", SUBTRACTION, "4"),
                arguments("-12", "3", SUBTRACTION, "-15")
        );
    }

    @ParameterizedTest(name = "{0} {2} {1} = {3}")
    @MethodSource("positiveTestProvider")
    public void checkCalculationWithPositiveParameters(String x, String y, String operation, String expectedResult) {
        calculatorPage.calculate(x, y, operation);

        resultPage.getCalculationResult().shouldHave(text(expectedResult));
    }

    static Stream<Arguments> negativeTestProvider() {
        return Stream.of(
                arguments("", "", ADDITION),
                arguments("", "3", ADDITION),
                arguments("-5", "", ADDITION),
                arguments("", "", SUBTRACTION),
                arguments("", "3", SUBTRACTION),
                arguments("-5", "", SUBTRACTION)
        );
    }

    @ParameterizedTest(name = "{0} {2} {1} = error")
    @MethodSource("negativeTestProvider")
    public void checkCalculationWithPositiveParameters(String x, String y, String operation) {
        calculatorPage.calculate(x, y, operation);

        resultPage.getCalculationResult().shouldHave(text(INVALID_OPERAND_RESULT));
    }
}