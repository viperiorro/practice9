package practice_tests.tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import practice_tests.tests.chrome.CalculatorTest;
import practice_tests.tests.chrome.VotingTest;
import practice_tests.tests.firefox.CrossBrowserTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({CalculatorTest.class, VotingTest.class, CrossBrowserTest.class})
public class TestSuite {
}
