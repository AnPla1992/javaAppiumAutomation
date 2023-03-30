package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SkipStartCondition extends MainPageObject{

    private static final String
        skip_button = "//*[contains(@resource-id,'org.wikipedia:id/fragment_onboarding_skip_button')]",
        gotIt_button = "//*[contains(@resource-id,'org.wikipedia:id/view_announcement_action_negative')]";
    public SkipStartCondition(AppiumDriver driver) {
        super(driver);
    }

    public void skipAllStartCondition() {

        this.waitForElementAndClick(
                By.xpath(skip_button),
                "Cann't find button 'SKIP'",
                5
        );
        this.waitForElementAndClick(
                By.xpath(gotIt_button),
                "Cann't find button 'Got It'",
                10
        );
    }
}
