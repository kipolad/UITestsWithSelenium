/**
 * Created by Yulya Telysheva
 */
package ru.kipolad.pages.afisharu;

import io.qameta.allure.Step;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MoviePage extends BaseView{
    public MoviePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@data-test='LINK LINK-BUTTON TICKET-BUTTON']/following-sibling::button[@data-test='BUTTON FAVORITE']")
    WebElement likeButton;

    @Step("Клик like выбранному фильму")
    public MoviePage clickLikeButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(likeButton));
        likeButton.click();
        return new MoviePage(driver);
    }

    @Step("Открыта форма авторизации пользователя")
    public void userAuthorizationIframe() {
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'login')]")));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("login")));
    }

}
