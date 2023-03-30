package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    private static final String
        saved_menu = "//*[@resource-id='org.wikipedia:id/nav_tab_reading_lists']";
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickSaved() {
        this.waitForElementAndClick(
                By.xpath(saved_menu),
                "Cannot find button of bottom menu 'Saved'",
                5
        );
    }
}
