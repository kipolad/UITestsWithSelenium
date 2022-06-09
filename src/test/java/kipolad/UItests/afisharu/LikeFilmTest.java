/**
 * Created by Yulya Telysheva
 */
package kipolad.UItests.afisharu;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kipolad.loggers.CustomLogger;
import ru.kipolad.pages.afisharu.MainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("afisha.ru")
public class LikeFilmTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    public static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new EventFiringDecorator(new CustomLogger()).decorate(new ChromeDriver());
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get("https://www.afisha.ru");
    }

    @Feature("Кнопка like")
    @Story("При попытке поставить like выбранному фильму открывается форма авторизации пользователя.")
    @Test
    void likeFilmTest() {
        new MainPage(driver)
                .clickToFirstMovie()
                .clickLikeButton()
                .userAuthorizationIframe();

        assertTrue(driver.findElement(By.id("login")).isDisplayed());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
