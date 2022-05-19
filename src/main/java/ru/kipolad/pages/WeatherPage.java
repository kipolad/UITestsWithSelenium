/**
 * Created by Yulya Telysheva
 */
package ru.kipolad.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WeatherPage extends BaseView {
    public WeatherPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(., 'Южно-Сахалинск')]")
    private WebElement weatherMenu;

    @FindBy(xpath = "//li[@class='city-dropdown city-select']//li//a")
    private List<WebElement> citiesList;

    public void chooseACity(String city) {
        webDriverWait.until
                (ExpectedConditions.elementToBeClickable(citiesList.stream()
                        .filter(c -> c.getText().contains(city)).findFirst().get()));

        actions.moveToElement(citiesList.stream()
                        .filter(c -> c.getText().contains(city)).findFirst().get())
                .click()
                .build()
                .perform();
    }

    public void clickToWeatherMenu() {
        actions.moveToElement(weatherMenu)
                .click()
                .build()
                .perform();
        new WeatherPage(driver);
    }
}
