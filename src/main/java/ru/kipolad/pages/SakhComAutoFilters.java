/**
 * Created by Yulya Telysheva
 */
package ru.kipolad.pages;

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

    @FindBy(xpath = "//label[.='Модель']/following-sibling::select")
    private WebElement carModelSelect;

    @FindBy(id = "sale-filter-submit")
    private WebElement submit;

    @FindBy(xpath = "//div[@class='sale-title desktop']//a[@class='sale-link']")
    private List<WebElement> autoTitles;

    public void clickSubmit() {
        submit.click();
    }

    public SakhComAutoFilters chooseModelBrand(String model) {
        chooseWebElementFromFilter(carModelsList, model);
        return new SakhComAutoFilters(driver);
    }

    public SakhComAutoFilters clickToCarModelFilter() {
        actions.moveToElement(carModelFilter)
                .click()
                .build()
                .perform();
        return new SakhComAutoFilters(driver);
    }

    public SakhComAutoFilters chooseCarBrand(String carBrand) {
        Select autoBrandSelect = new Select(carBrandSelect);
        autoBrandSelect.selectByVisibleText(carBrand);
        return new SakhComAutoFilters(driver);
    }

    public SakhComAutoFilters clickToCarBrandFilter() {
        actions.moveToElement(carBrandFilter)
                .click()
                .build()
                .perform();
        return new SakhComAutoFilters(driver);
    }

    public boolean isRightFilter(String brand, String model) {
        boolean result = true;
        for (WebElement autoElement : autoTitles) {
            if (!autoElement.getText().contains(brand + " " + model)) result = false;
        }
        return result;
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
