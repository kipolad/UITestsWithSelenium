/**
 * Created by Yulya Telysheva
 */
package kipolad.UItests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kipolad.loggers.CustomLogger;
import ru.kipolad.pages.SakhComAutoFilters;
import ru.kipolad.pages.SakhMenuOnMainPage;

import java.time.Duration;

@Epic("Auto.sakh.com")
public class AutoMarketFilterTest {
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
        new SakhMenuOnMainPage(driver).clickAuto();
    }

    @ParameterizedTest
    @CsvSource({"Subaru, XV",
            "Toyota, Corolla Fielder",
            "Lexus, RX 350",
            "Nissan, Juke",
            "Mitsubishi, Pajero"})
    @Feature("Фильтр авто")
    @Story("Подборка по марке и модели авто")
    public void selectionOfCarsByBrandAndModel(String brand, String model) {
        new SakhComAutoFilters(driver)
                .clickToCarBrandFilter()
                .chooseCarBrand(brand)
                .clickToCarModelFilter()
                .chooseCarModel(model)
                .clickSubmit();

        Allure.step("Проверка списка авто в соответствии с выбранными маркой и моделью");
        Assertions.assertTrue(new SakhComAutoFilters(driver)
                .isRightFilter(brand, model), "Filters return wrong list of cars");
    }

    @ParameterizedTest
    @CsvSource({"Subaru, XV, 2015, 2020",
            "Toyota, Corolla Fielder, 2016, 2018",
            "Honda, CR-V, 2010, 2019",
            "Honda, Fit Shuttle, 2010, 2017",
            "Mitsubishi, Pajero, 2000, 2009"})
    @Feature("Фильтр авто")
    @Story("Подборка по марке, модели и интервалу года выпуска авто")
    public void selectionOfCarsByBrandModelAndYear(String brand, String model, int yearFrom, int yearBefore) {
        new SakhComAutoFilters(driver)
                .clickToCarBrandFilter()
                .chooseCarBrand(brand)
                .clickToCarModelFilter()
                .chooseCarModel(model)
                .clickToCarYearFromFilter()
                .chooseCarYearFrom(Integer.toString(yearFrom))
                .clickToCarYearBeforeFilter()
                .chooseCarYearBefore(Integer.toString(yearBefore))
                .clickSubmit();

        Allure.step("Проверка списка авто в соответствии с выбранными маркой, моделью и интервалом года выпуска");
        Assertions.assertTrue(new SakhComAutoFilters(driver)
                        .isRightFilter(brand, model) && new SakhComAutoFilters(driver)
                        .isRightFilterForCarYears(yearFrom, yearBefore),
                "Filters return wrong list of cars");
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
