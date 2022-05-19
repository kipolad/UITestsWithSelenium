/**
 * Created by Yulya Telysheva
 */
package kipolad.UItests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kipolad.pages.MainPage;
import ru.kipolad.pages.WeatherPage;

import java.time.Duration;

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
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get("https://sakh.com/");
        new MainPage(driver)
                .clickWeatherLinkFromRightSection()
                .clickToWeatherMenu();
    }

    @Test
    void checkWeatherInMoscowFromMainPage() {
        new WeatherPage(driver).chooseACity("Москва");

        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains("msk"), "Url does not contain 'msk'"),
                () -> Assertions.assertTrue(driver.findElement(By.xpath("//span[contains(., 'Москва')]")).isDisplayed(),
                        "Moscow city is not displayed in the Weathers menu")
        );
    }

    @Test
    void checkWeatherInYuzhnoSakhalinskFromMainPage() {
        new WeatherPage(driver).chooseACity("Южно-Сахалинск");

        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains("ys"), "Url does not contain 'ys'"),
                () -> Assertions.assertTrue(driver.findElement(By.xpath("//span[contains(., 'Южно-Сахалинск')]")).isDisplayed(),
                        "Yuzhno-Sakhalinsk city is not displayed in the Weathers menu")
        );
    }

    @Test
    void checkWeatherInAnivaFromMainPage() {
        new WeatherPage(driver).chooseACity("Анива");

        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains("aniva"), "Url does not contain 'aniva'"),
                () -> Assertions.assertTrue(driver.findElement(By.xpath("//span[contains(., 'Анива')]")).isDisplayed(),
                        "Aniva city is not displayed in the Weathers menu")
        );
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
