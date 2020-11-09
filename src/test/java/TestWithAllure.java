import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.OutputStreamAppender;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.slf4j.Logger.ROOT_LOGGER_NAME;

/**
 * Demo Test - for AllureReports
 *  Report Directory in a gradle project : build/allure-results
 *  Generate allure reports after running test : allure serve <path to of allure-results>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestWithAllure {
    AppiumDriver<MobileElement> driver;
    DemoPOM demoPOM;
    SoftAssertions softAssertions;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static OutputStreamAppender<ILoggingEvent> appender;
    private static ByteArrayOutputStream stream;


    @BeforeAll
    public void setUp() throws MalformedURLException {
        logger.info("BeforeAll setup");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("app","/Users/admin/builds/Royal-Dev-1.23.Silver.apk");
        capabilities.setCapability("noReset","true");
        capabilities.setCapability("fullReset","false");
        capabilities.setCapability("appPackage", "com.rccl.royalcaribbean.debug");
        capabilities.setCapability("appActivity","com.rcl.excalibur.activity.AppSplashActivity");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        softAssertions=new SoftAssertions();
        configureInMemoryLogging();
    }


    @BeforeEach
    public void setupAll(){
        logger.info("BeforeEach setup");
    }

    @Issue("JIRA-8909")
    @DisplayName("Test 1")
    @Description("Test1 execution")
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

    @Issue("JIRA-8909")
    @DisplayName("Test 2")
    @Description("Test2 execution")
    @Test
    public void testDemo2() {
        logger.info("Test2");

    }


    @AfterAll
    public void teardown(){
        logger.info("AfterAll setup");
        driver.quit();
    }

    @AfterEach
    public void teardownEach(){
        logger.info("AfterEach teardown");
        //for adding logs to allure reports
        if (stream != null) {
            Allure.addAttachment("ExecutionLog", stream.toString());
        }
        if (appender != null) {
            appender.stop();
        }
    }

    private void configureInMemoryLogging() {
        stream = new ByteArrayOutputStream();
        Logger rootLogger = LoggerFactory.getLogger(ROOT_LOGGER_NAME);
            ch.qos.logback.classic.Logger logbackRootLogger = (ch.qos.logback.classic.Logger) rootLogger;
//            logbackRootLogger.setLevel(Level.INFO);

            appender = new OutputStreamAppender<>();
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
            patternLayoutEncoder.setPattern("[%date] [%level] [%logger{10}] %msg%n");
            patternLayoutEncoder.setContext(loggerContext);
            patternLayoutEncoder.start();
            appender.setContext(loggerContext);
            appender.setOutputStream(stream);
            appender.setName("buffered");
            appender.setEncoder(patternLayoutEncoder);
            appender.start();

            logbackRootLogger.addAppender(appender);
            logbackRootLogger.setAdditive(true);
    }

}
