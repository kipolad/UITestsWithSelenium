/**
 * Created by Yulya Telysheva
 */
package ru.kipolad.pages.afisharu;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BaseView {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[.='Кино']//ancestor::div[@data-test='PAGE-SECTION-HEADER']/following-sibling::div[@data-test='SLIDER LIST CAROUSEL']//a[@data-test='LINK ITEM-URL' and contains(@href, 'movie')]")
    List<WebElement> movieList;

    @Step("Из списка фильмов выбрать первый")
    public MoviePage clickToFirstMovie() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                movieList.get(0));
        movieList.get(0).click();
        return new MoviePage(driver);
    }
}
