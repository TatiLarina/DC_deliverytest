package data;
import lombok.Value;

import static com.codeborne.selenide.Selenide.open;

public class DataHelper {

    private static final String prod = "https://www.dostavka-tsvetov.com/";

    // Открыть страницу доставки
    public static void OpenDeliveryPage() {
        open(prod + "dostavka_cvetov");
    }

    @Value
    public static class DistrictInfo {
        String name;
        String dayPrice;
        String nightPrice;
    }

    //Создание массива
    public static int[] districtCount(int size) {
        int[] count = new int[size];
        for (int i=0; i<size; i++) {
            count[i] = i;
        }
        return count;
    }

}
