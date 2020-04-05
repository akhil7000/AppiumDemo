import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class DemoPOM {
    AppiumDriver<MobileElement> driver;

    /**
     * The skip intro button.
     */
    @iOSXCUITFindBy(accessibility = "Skip")
    @AndroidFindBy(id = "button_configurator_intro_skip")
    private MobileElement skipIntroButton;

    /**
     * The allow button.
     */
    @HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE, androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @iOSXCUITFindBy(accessibility = "Allow")
    @iOSXCUITFindBy(accessibility = "Permitir")
    @iOSXCUITFindBy(accessibility = "允许")
    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    private MobileElement allowButton;


    protected void initPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public DemoPOM(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        initPage();
    }


    @Step("Click on Skip Button")
    public void clickOnSkip(){
        elementExists(skipIntroButton,120);
        skipIntroButton.click();
    }

    @Step("Click on Allow Button")
    public boolean clickOnAllow(){
        try {
            elementExists(allowButton,120);
            allowButton.click();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    protected FluentWait waitOn(AppiumDriver<MobileElement> driver, int timeOutSeconds) {
        return new FluentWait<>(driver).ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withTimeout(Duration.ofSeconds(timeOutSeconds));
    }

    public FluentWait<MobileDriver<MobileElement>> getWait() {
        return waitOn(driver, 120);
    }

    protected boolean elementExists(MobileElement element, int timeout) {
        try {
            waitOn(driver, timeout).until(visibilityOf(element));
        } catch (Exception toe) {
            return false;
        }
        return true;
    }
}
