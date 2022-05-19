/**
 * Created by Yulya Telysheva
 */
package kipolad.UItests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kipolad.pages.SakhComAutoFilters;
import ru.kipolad.pages.SakhMenuOnMainPage;

import java.time.Duration;

public class AutoMarketFilterTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    String brand;
    String model;

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
        new SakhMenuOnMainPage(driver).clickAuto();
    }


    @Test
    public void chooseSubaruFilter() {
        brand = "Subaru";
        model = "XV";
        new SakhComAutoFilters(driver)
                .clickToCarBrandFilter()
                .chooseCarBrand(brand)
                .clickToCarModelFilter()
                .chooseModelBrand(model)
                .clickSubmit();

        Assertions.assertTrue(new SakhComAutoFilters(driver)
                .isRightFilter(brand, model), "Filters return wrong list of cars");
    }

    @Test
    public void chooseToyotaFilter() {
        brand = "Toyota";
        model = "Corolla Fielder";
        new SakhComAutoFilters(driver)
                .clickToCarBrandFilter()
                .chooseCarBrand(brand)
                .clickToCarModelFilter()
                .chooseModelBrand(model)
                .clickSubmit();

        Assertions.assertTrue(new SakhComAutoFilters(driver)
                .isRightFilter(brand, model), "Filters return wrong list of cars");
    }

    @Test
    public void chooseLexusFilter() {
        brand = "Lexus";
        model = "RX 350";
        new SakhComAutoFilters(driver)
                .clickToCarBrandFilter()
                .chooseCarBrand(brand)
                .clickToCarModelFilter()
                .chooseModelBrand(model)
                .clickSubmit();

        Assertions.assertTrue(new SakhComAutoFilters(driver)
                .isRightFilter(brand, model), "Filters return wrong list of cars");
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
