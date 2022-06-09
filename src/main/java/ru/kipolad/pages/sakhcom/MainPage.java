/**
 * Created by Yulya Telysheva
 */
package ru.kipolad.pages.sakhcom;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BaseView {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[.='Погода' and @class='black']")
    private WebElement weatherLinkRightSection;

    @Step("Клик 'Погода' в правом блоке главного экрана")
    public WeatherPage clickWeatherLinkFromRightSection() {
        weatherLinkRightSection.click();
        return new WeatherPage(driver);
    }
}
