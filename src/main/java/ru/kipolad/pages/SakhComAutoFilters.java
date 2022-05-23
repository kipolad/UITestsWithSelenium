/**
 * Created by Yulya Telysheva
 */
package ru.kipolad.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SakhComAutoFilters extends BaseView {
    public SakhComAutoFilters(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@data-select2-id='1']")
    private WebElement carBrandFilter;

    @FindBy(xpath = "//label[.='Марка']/following-sibling::select")
    private WebElement carBrandSelect;

    @FindBy(xpath = "//ul[@id='select2-id_f-model_id-results']//li")
    private List<WebElement> carModelsList;

    @FindBy(xpath = "//span[@data-select2-id='3']")
    private WebElement carModelFilter;

    @FindBy(xpath = "//span[@id='select2-year_s-container']")
    private WebElement carYearFromFilter;

    @FindBy(xpath = "//ul[@id='select2-year_s-results']//li")
    private List<WebElement> carYearFromList;
    ////////////////////
    @FindBy(xpath = "//span[@id='select2-year_e-container']")
    private WebElement carYearBeforeFilter;

    @FindBy(xpath = "//ul[@id='select2-year_e-results']//li")
    private List<WebElement> carYearBeforeList;

    @FindBy(id = "sale-filter-submit")
    private WebElement submit;

    @FindBy(xpath = "//div[@class='sale-title desktop']//a[@class='sale-link']")
    private List<WebElement> autoTitles;

    @Step("Клик Найти")
    public void clickSubmit() {
        submit.click();
    }

    @Step("Клик на фильтр марки авто")
    public SakhComAutoFilters clickToCarBrandFilter() {
        actions.moveToElement(carBrandFilter)
                .click()
                .build()
                .perform();
        return new SakhComAutoFilters(driver);
    }

    @Step("Выбрать марку авто")
    public SakhComAutoFilters chooseCarBrand(String carBrand) {
        Select autoBrandSelect = new Select(carBrandSelect);
        autoBrandSelect.selectByVisibleText(carBrand);
        return new SakhComAutoFilters(driver);
    }

    @Step("Клик на фильтр модели авто")
    public SakhComAutoFilters clickToCarModelFilter() {
        actions.moveToElement(carModelFilter)
                .click()
                .build()
                .perform();
        return new SakhComAutoFilters(driver);
    }

    @Step("Выбрать модель авто")
    public SakhComAutoFilters chooseCarModel(String model) {
        chooseWebElementFromFilter(carModelsList, model);
        return new SakhComAutoFilters(driver);
    }

    @Step("Клик на фильтр год выпуска: от...")
    public SakhComAutoFilters clickToCarYearFromFilter() {
        actions.moveToElement(carYearFromFilter)
                .click()
                .build()
                .perform();
        return new SakhComAutoFilters(driver);
    }

    @Step("Выбрать год выпуска: от...")
    public SakhComAutoFilters chooseCarYearFrom(String carsYearFrom) {
        chooseWebElementFromFilter(carYearFromList, carsYearFrom);
        return new SakhComAutoFilters(driver);
    }

    @Step("Клик на фильтр год выпуска: до...")
    public SakhComAutoFilters clickToCarYearBeforeFilter() {
        actions.moveToElement(carYearBeforeFilter)
                .click()
                .build()
                .perform();
        return new SakhComAutoFilters(driver);
    }

    @Step("Выбрать год выпуска: до...")
    public SakhComAutoFilters chooseCarYearBefore(String carsYearBefore) {
        chooseWebElementFromFilter(carYearBeforeList, carsYearBefore);
        return new SakhComAutoFilters(driver);
    }

    public boolean isRightFilter(String firstFilter, String secondFilter) {
        boolean result = true;
        for (WebElement autoElement : autoTitles) {
            if (!autoElement.getText().contains(firstFilter) &&
                    !autoElement.getText().contains(secondFilter)) result = false;
        }
        return result;
    }

    public boolean isRightFilter(String firstFilter, String secondFilter, String thirdFilter) {
        boolean result = true;
        for (WebElement autoElement : autoTitles) {
            if (!autoElement.getText().contains(firstFilter) &&
                    !autoElement.getText().contains(secondFilter) &&
                    !autoElement.getText().contains(thirdFilter)) result = false;
        }
        return result;
    }

    public boolean isRightFilterForCarYears(int yearFrom, int yearBefore) {
        boolean isRightYear = false;
        for (WebElement autoTitle : autoTitles) {
            isRightYear = false;
            for (int i = yearFrom; i <= yearBefore; i++) {
                if (autoTitle.getText().contains(Integer.toString(i))) {
                    isRightYear = true;
                    break;
                }
            }
            if (!isRightYear) break;
        }
        return isRightYear;
    }

    private void chooseWebElementFromFilter(List<WebElement> webElementList, String choice) {
        webDriverWait.until
                (ExpectedConditions.elementToBeClickable(webElementList.stream()
                        .filter(c -> c.getText().contains(choice)).findFirst().get()));

        actions.moveToElement(webElementList.stream()
                        .filter(c -> c.getText().contains(choice)).findFirst().get())
                .click()
                .build()
                .perform();
    }
}
