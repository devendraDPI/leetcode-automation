package xleetcode;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

public class TestCases {
    WebDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");
        System.out.println("Start Tests: TestCases");

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Set path for log file
        File theDir = new File("logs");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "logs" + File.separator + "chromedriver.log");

        driver = new ChromeDriver(options);

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void endTest() {
        System.out.println("End Tests: TestCases");
        driver.quit();
    }

    /** <STRONG>Test Case 01</STRONG>: Verify the Leetcode Homepage URL <p>
     *  <STRONG>Description</STRONG>: Verify that the Leetcode homepage URL contains "leetcode" <p>
     *  <STRONG>Expected Result</STRONG>: The URL of the Leetcode homepage contains "leetcode" <p>
     *
     *  1. Launch chrome browser <p>
     *  2. Navigate to the Leetcode website https://leetcode.com <p>
     *  3. Verify that the URL contains "leetcode" <p>
     */
    public void testCase01() {
        System.out.println("\nTestCase01: START");

        // Launch chrome browser
        // Navigate to the Leetcode website https://leetcode.com
        driver.get("https://leetcode.com");

        // Verify that the URL contains "leetcode"
        String actualUrl = driver.getCurrentUrl();
        String expectedUrlContent = "leetcode";

        if (actualUrl.contains(expectedUrlContent)) {
            System.out.println("TestCase01: The URL contains 'leetcode': PASS");
        } else {
            System.out.println("TestCase01: The URL does not contains 'leetcode': FAIL");
        }

        System.out.println("TestCase01: END\n");
    }

    /** <STRONG>Test Case 02</STRONG>: Verify Problem Set URL and Display First 5 Questions <p>
     *  <STRONG>Description</STRONG>: Retrieve details of the first 5 questions on the problems page <p>
     *  <STRONG>Expected Result</STRONG>: The correct details of the problems are obtained and verified <p>
     *
     *  1. Launch chrome browser <p>
     *  2. Navigate to the Leetcode website https://leetcode.com <p>
     *  3. Scroll to view question link <p>
     *  4. Click on the "View Questions" link on the Leetcode homepage <p>
     *  5. Verify that you are on the problem set page, by checking the URL contains "problemset" <p>
     *  6. Retrieve the details of the first 5 questions and print them <p>
     *  7. Make sure to check that the title of each question is correct and that you are selecting the questions from the first problem, i.e., "Two Sum" <p>
     */
    public void testCase02() {
        System.out.println("\nTestCase02: START");

        // Launch chrome browser
        // Navigate to the Leetcode website https://leetcode.com
        driver.get("https://leetcode.com");

        // Scroll to view question link
        WebElement viewQuestionLink = driver.findElement(By.xpath("//p[contains(text(), 'View Questions')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", viewQuestionLink);

        // Click on the "View Questions" link on the Leetcode homepage
        viewQuestionLink.click();

        // Verify that you are on the problem set page, by checking the URL contains "problemset"
        String currentUrl = driver.getCurrentUrl();
        String expectedUrlContains = "problemset";
        if (currentUrl.contains(expectedUrlContains)) {
            System.out.println("TestCase02: The URL contains 'problemset': PASS");
        } else {
            System.out.println("TestCase02: The URL does not contains 'problemset': FAIL");
        }

        // Retrieve the details of the first 5 questions and print them
        WebElement questionList = driver.findElement(By.xpath("(//div[contains(@role, 'rowgroup')])[3]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionList);

        List<WebElement> questionsTitle = driver.findElements(By.xpath("(//div[contains(@class, 'truncate')]/a)[position()>1 and position() <= 6]"));
        System.out.println("First 5 questions are:");
        for (WebElement question : questionsTitle) {
            System.out.println("\t\t" + question.getText());
        }

        // Make sure to check that the title of each question is correct and that you are selecting the questions from the first problem, i.e., "Two Sum"
        List<String> expectedQuestions = Arrays.asList("Two Sum", "Add Two Numbers", "Longest Substring Without Repeating Characters", "Median of Two Sorted Arrays", "Longest Palindromic Substring");
        System.out.println("Check that the title of each question is correct:");
        for (int i=0; i<5; i++) {
            boolean isCorrectTitle = questionsTitle.get(i).getText().contains(expectedQuestions.get(i));
            System.out.println("\t\t" + questionsTitle.get(i).getText() + ": " + (isCorrectTitle ? "Correct title" : "Incorrect title"));
        }

        System.out.println("TestCase02: END\n");
    }

    /** <STRONG>Test Case 03</STRONG>: Verify the Two Sum problem <p>
     *  <STRONG>Description</STRONG>: Open the Two Sum problem and verify the URL <p>
     *  <STRONG>Expected Result</STRONG>: The URL of the problem contains "two-sum" <p>
     *
     *  1. Launch chrome browser <p>
     *  2. Navigate to the Leetcode website https://leetcode.com <p>
     *  3. Scroll to view question link <p>
     *  4. Click on the "View Questions" link on the Leetcode homepage <p>
     *  5. Open the first problem i.e, Two Sum <p>
     *  6. Verify that the URL contains "two-sum" <p>
     */
    public void testCase03() {
        System.out.println("\nTestCase03: START");
        // Launch chrome browser
        // Navigate to the Leetcode website https://leetcode.com
        driver.get("https://leetcode.com");

        // Scroll to view question link
        WebElement viewQuestionLink = driver.findElement(By.xpath("//p[contains(text(), 'View Questions')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", viewQuestionLink);

        // Click on the "View Questions" link on the Leetcode homepage
        viewQuestionLink.click();

        // Open the first problem i.e, Two Sum
        WebElement twoSum = driver.findElement(By.xpath("//a[contains(normalize-space(),'Two Sum')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", twoSum);
        twoSum.click();

        // Verify that the URL contains "two-sum"
        String actualUrl = driver.getCurrentUrl();
        String expectedUrlContent = "two-sum";

        if (actualUrl.contains(expectedUrlContent)) {
            System.out.println("TestCase03: The URL contains 'two-sum': PASS");
        } else {
            System.out.println("TestCase03: The URL does not contains 'two-sum': FAIL");
        }

        System.out.println("TestCase03: END\n");
    }

    /** <STRONG>Test Case 04</STRONG>: Verify the submissions tab displays "Register or Sign In" <p>
     *  <STRONG>Description</STRONG>: Check the submissions tab and verify if it displays "Register or Sign In" <p>
     *  <STRONG>Expected Result</STRONG>: The message "Register or Sign In" is displayed when you click on the submissions tab. <p>
     *
     *  1. Launch chrome browser <p>
     *  2. Navigate to the Leetcode website https://leetcode.com <p>
     *  3. Scroll to view question link <p>
     *  4. Click on the submission tab <p>
     *  5. Verify that it displays "Register or Sign In" <p>
     */
    public void testCase04() {
        System.out.println("\nTestCase04: START");
        // Launch chrome browser
        // Navigate to the Leetcode website https://leetcode.com
        driver.get("https://leetcode.com");

        // Scroll to view question link
        WebElement viewQuestionLink = driver.findElement(By.xpath("//p[contains(text(), 'View Questions')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", viewQuestionLink);

        // Click on the "View Questions" link on the Leetcode homepage
        viewQuestionLink.click();

        // Open the first problem i.e, Two Sum
        WebElement twoSum = driver.findElement(By.xpath("//a[contains(normalize-space(), 'Two Sum')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", twoSum);
        twoSum.click();

        // Click on the submission tab
        WebElement submissionTab = driver.findElement(By.xpath("(//div[contains(text(), 'Submission')])[2]"));
        submissionTab.click();

        // Verify that it displays "Register or Sign In"
        String status = "FAIL";
        try {
            WebElement registerOrSignIn = driver.findElement(By.xpath("//a[contains(text(), 'Register or Sign In')]"));
            if (registerOrSignIn.getText().equals("Register or Sign In")) {
                status = "PASS";
            }
        } catch (Exception e) {}
        System.out.println("TestCase04: Submission tab displays 'Register or Sign In': " + status);

        System.out.println("TestCase04: END\n");
    }
}
