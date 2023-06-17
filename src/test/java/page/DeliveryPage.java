package page;

import com.codeborne.selenide.SelenideElement;
import com.google.common.base.CharMatcher;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DeliveryPage {

    private final SelenideElement tarifsButton = $("#tarifs");

    // списки населенных пунктов
    private final List<SelenideElement> districts = $$(".delivery-page-point");
    private final List<SelenideElement> districtNames = $$(".delivery_point__name");
    private final List<SelenideElement> deliveryPrice = $$(".delivery_price");

    public void clickTarifs() {
        tarifsButton.click();
    }

    public void clickDistrict(int index) {
        districtNames.get(index).click();
    }

    public int districtCount() {
        return districts.size();
    }

    public String getDistrictName(int index) {
        return districtNames.get(index).getText().trim();
    }

    public String getDayPrice(int index) {
        String[] text = deliveryPrice.get(index+5).getText().split(" ");
        return CharMatcher.anyOf("0123456789").retainFrom(text[0]);
    }

    public String getNightPrice(int index) {
        String[] text = deliveryPrice.get(index+5).getText().split(" ");
        return CharMatcher.anyOf("0123456789").retainFrom(text[4]);
    }

}
