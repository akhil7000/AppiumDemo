import com.experitest.appium.SeeTestClient;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoExperiTest {
    SoftAssertions softAssertions;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String accessKey = "eyJ4cC51Ijo0NDQ4MjksInhwLnAiOjEsInhwLm0iOiJNVFU0TmpnMU5qVXhPRGd6T1EiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE5MDIyMTY1MTksImlzcyI6ImNvbS5leHBlcml0ZXN0In0.OcJLpyVAh-nA-8Qy90SqSSX-qw5zIpogZEeTAlz8Sbg";
    protected IOSDriver<IOSElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeEach
    public void setUp() throws MalformedURLException {
        dc.setCapability("testName", "Quick Start iOS Native Demo");
        dc.setCapability("accessKey", accessKey);
        dc.setCapability("deviceQuery", "@os='ios' and @category='PHONE' and @serialnumber='39b8e418c73372a1f68cb6c1e6ea0823f5221b12'");
        dc.setCapability("app", "cloud:com.experitest.ExperiBank");
        dc.setCapability("bundleId", "com.experitest.ExperiBank");
        dc.setCapability("appiumVersion", "1.15.1-p1");
        driver = new IOSDriver<>(new URL("https://rccl.experitest.com/wd/hub"), dc);
        SeeTestClient seetest = new SeeTestClient(driver);
        seetest.setNetworkConnection("wifi",false);

    }

    @Test
    public void quickStartiOSNativeDemo() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.hideKeyboard();
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Report URL: "+ driver.getCapabilities().getCapability("reportUrl"));
        driver.quit();
    }

}
