package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.MethodSource;
import org.apache.commons.lang3.StringUtils;
import page.DeliveryPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.params.ParameterizedTest;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DeliveryPageTest {

    static DeliveryPage deliveryPage = new DeliveryPage();

    @BeforeAll
    static void before() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true).savePageSource(true));
        DataHelper.OpenDeliveryPage();
        deliveryPage.clickTarifs();
    }

    @AfterEach
    void after() {
        DataHelper.OpenDeliveryPage();
        deliveryPage.clickTarifs();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Сверка городов и страниц")
    void clickDistrict(int argument) {
        String name = deliveryPage.getDistrictName(argument);
        if (name.contains(" ")) {
            String[] words = name.split(" ");
            name = words[1];
        }
        String dayPrice = deliveryPage.getDayPrice(argument);
        String nightPrice = deliveryPage.getNightPrice(argument);
        deliveryPage.clickDistrict(argument);
        $(".h2").shouldHave(text(StringUtils.substring(name, 0, name.length() - 1)));
        $(".padding-row").shouldHave(text(dayPrice));
        $(".padding-row").shouldHave(text(nightPrice));
    }

    static IntStream clickDistrict() {
        int[] count = DataHelper.districtCount(deliveryPage.districtCount());
        return Arrays.stream(count);
    }

}
