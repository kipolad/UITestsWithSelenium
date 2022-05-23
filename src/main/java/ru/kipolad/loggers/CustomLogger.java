/**
 * Created by Yulya Telysheva
 */
package ru.kipolad.loggers;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

public class CustomLogger implements WebDriverListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomLogger.class);

    public void beforeQuit(WebDriver driver) {
        Allure.addAttachment("Скриншот страницы",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}
