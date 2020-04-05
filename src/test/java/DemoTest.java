import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.internal.bytebuddy.build.Plugin;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoTest {
    AppiumDriver<MobileElement> driver;
    DemoPOM demoPOM;
    SoftAssertions softAssertions;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @BeforeAll
    public void setUp() throws MalformedURLException {
        logger.info("BeforeAll setup");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("app","/Users/admin/builds/Royal-Dev-1.23.Silver.apk");
        capabilities.setCapability("noReset","false");
        capabilities.setCapability("fullReset","false");
        capabilities.setCapability("appPackage", "com.rccl.royalcaribbean.debug");
        capabilities.setCapability("appActivity","com.rcl.excalibur.activity.AppSplashActivity");
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        softAssertions=new SoftAssertions();
    }


    @BeforeEach
    public void setupAll(){
        logger.info("BeforeEach setup");
    }


    @Order(2)
    @Test
    public void testDemo1() {
        logger.info("Test1");

        demoPOM=new DemoPOM(driver);
        demoPOM.clickOnSkip();
        Assertions.assertTrue(demoPOM.clickOnAllow(),"Allow Button Not Found - Hard Assert");
        softAssertions.assertThat(demoPOM.clickOnAllow())
                .as("Allow Button Not Found - Soft Assert")
                .isTrue();
    }

    @Order(3)
    @Test
    public void testDemo2() {
        logger.info("Test2");
        System.setProperty("selenide.browser", "Chrome");
        open("http://google.com");
        $(By.name("q")).setValue("Selenide").pressEnter();


    }

    @Order(1)
    @ParameterizedTest
    @ValueSource(strings = {"Parameter1","Parameter2"})
    public void testDemo3(String test)  {

        logger.info("Test3: "+test);
    }

    @Order(4)
    @Tag("Royal")
    @Test
    public void testDemoTag() {
        logger.info("Only for a given tag");

    }

    @AfterAll
    public void teardown(){
        logger.info("AfterAll setup");
        driver.quit();
    }

    @AfterEach
    public void teardownEach(){
        logger.info("AfterEach teardown");
    }

}
