/**
 * Created by Yulya Telysheva
 */
package ru.kipolad.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BaseView {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[.='Погода' and @class='black']")
    private WebElement weatherLinkRightSection;

    public WeatherPage clickWeatherLinkFromRightSection() {
        weatherLinkRightSection.click();
        return new WeatherPage(driver);
    }
}
