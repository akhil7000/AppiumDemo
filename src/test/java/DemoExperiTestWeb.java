import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

/**
 ** Demo Test - execution on Experitest Cloud Browser.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoExperiTestWeb {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String accessKey = "eyJ4cC51Ijo0NDQ4MjksInhwLnAiOjEsInhwLm0iOiJNVFU0TmpnMU5qVXhPRGd6T1EiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE5MDIyMTY1MTksImlzcyI6ImNvbS5leHBlcml0ZXN0In0.OcJLpyVAh-nA-8Qy90SqSSX-qw5zIpogZEeTAlz8Sbg";
    private RemoteWebDriver driver;
    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeEach
    public void setUp() throws MalformedURLException {
        dc.setCapability("testName", "Timed out rendering Issue");
        dc.setCapability("accessKey", accessKey);
        dc.setCapability("browserName", "chrome");
        dc.setCapability("browserVersion", "83");
        dc.setCapability("platformName", "MAC");
        dc.setCapability("os.name", "MAC");
        dc.setCapability("newCommandTimeout", 600);
        dc.setCapability("acceptInsecureCerts", true);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized","--incognito","--disable-gpu");
        dc.setCapability(ChromeOptions.CAPABILITY,options);

        driver = new RemoteWebDriver(new URL("https://rccl.experitest.com/wd/hub"), dc);
        WebDriverRunner.setWebDriver(this.driver);
        Configuration.timeout=20000;
    }

    @Test
    public void quickStartWebDemo() throws InterruptedException {
        open("https://www.stage1.celebritycruises.com/account/?ga-app-version=yellow");
        Thread.sleep(10000);
        $x("//input[@id='mat-input-0']").shouldBe(Condition.visible).sendKeys("test_account@test.com");
        $x("//input[@id='mat-input-1']").shouldBe(Condition.visible).sendKeys("Password1");
        $x("//button[@class='hybris-button hybris-button--regular--celebrity']").shouldBe(Condition.visible).click();
        Thread.sleep(30000);
        open("https://www.stage1.celebritycruises.com/account/?ga-app-version=yellowsignin/?wuc=MEX&wul=es");
    }

    @AfterEach
    public void tearDown() {
        logger.info("Report URL: "+ driver.getCapabilities().getCapability("reportUrl"));
        driver.quit();
    }

}
