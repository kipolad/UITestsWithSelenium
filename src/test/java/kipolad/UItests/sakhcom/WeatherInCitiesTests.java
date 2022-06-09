/**
 * Created by Yulya Telysheva
 */
package kipolad.UItests.sakhcom;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kipolad.loggers.CustomLogger;
import ru.kipolad.pages.sakhcom.MainPage;
import ru.kipolad.pages.sakhcom.WeatherPage;

import java.time.Duration;

@Epic("Pogoda.sakh.com")
public class WeatherInCitiesTests {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new EventFiringDecorator(new CustomLogger()).decorate(new ChromeDriver());
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get("https://sakh.com/");
        new MainPage(driver)
                .clickWeatherLinkFromRightSection()
                .clickToWeatherMenu();
    }

    @ParameterizedTest
    @CsvSource({"Москва, msk",
            "Южно-Сахалинск, ys",
            "Александровск-С, aleksandrovsk",
            "Анива, aniva",
            "Долинск, dolinsk",
            "Корсаков, korsakov",
            "Курильск, kurilsk",
            "Макаров, makarov",
            "Невельск, nevelsk",
            "Ноглики, nogliki",
            "Оха, okha",
            "Поронайск, poronaysk",
            "Северо-Курильск, skurilsk",
            "Смирных, smirnyh",
            "Томари, tomari",
            "Тымовское, tymovskoe",
            "Углегорск, uglegorsk",
            "Холмск, kholmsk",
            "Южно-Курильск, ykurilsk",
            "Хабаровск, khv"})
    @Feature("Просмотр прогноза погоды по городам переходом с блока погоды на главной странице")
    @Story("Просмотр страницы прогноза погоды через меню выбора городов")
    void checkWeatherInMoscowFromMainPage(String city, String urlContainsCity) {
        new WeatherPage(driver).chooseACity(city);

        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains(urlContainsCity),
                        "Url does not contain '" + urlContainsCity + "'"),
                () -> Assertions.assertTrue(driver.findElement(By.xpath("//span[contains(., '" + city + "')]")).isDisplayed(),
                        "The selected city is not displayed in the Weathers menu")
        );
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
