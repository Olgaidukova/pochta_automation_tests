package pages;

import static com.codeborne.selenide.Selenide.open;

public class PochtaPage {

    public PochtaPage openPage(){
        open("/");
        return this;
    }

    public PochtaPage openDeliveryPage(){
        open("/parcels");
        return this;
    }
}
