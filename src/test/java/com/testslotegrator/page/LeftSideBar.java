package com.testslotegrator.page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class LeftSideBar {
    private final SelenideElement self = $(By.id("nav"));

    public void checkMenuItems(List<String> sidebarMenuList) {
        self.$$x("./li/a").shouldHave(CollectionCondition
                .exactTexts(sidebarMenuList));
    }

    public void clickMenuItem(String item1, String item2) {
        SelenideElement itemEl1 = self.$x("./li/a[normalize-space(.)='" + item1 + "']");
        itemEl1.click();
        itemEl1.parent().$x("./ul/li/a[.='" + item2 + "']").click();
    }
}