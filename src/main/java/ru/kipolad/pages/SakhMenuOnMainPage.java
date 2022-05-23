/**
 * Created by Yulya Telysheva
 */
package ru.kipolad.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SakhMenuOnMainPage extends BaseView {
    public SakhMenuOnMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//ul[@id='sakhmenu']//a[.='Авто']")
    private WebElement auto;

    @Step("В меню на главной странице выбрать 'Авто'")
    public void clickAuto() {
        actions.moveToElement(auto)
                .click()
                .build()
                .perform();
        new SakhComAutoFilters(driver);
    }
}
