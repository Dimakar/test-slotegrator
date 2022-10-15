package com.testslotegrator.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class AdminPage {

    private final SelenideElement header = $(By.id("header"));
    private final LeftSideBar leftSideBar = new LeftSideBar();

    public void checkAdminLeftSidebarIsOpened() {
        leftSideBar.checkMenuItems(List.of(
                "Dashboard",
                "Agent Info",
                "Settings",
                "Games",
                "Money",
                "Content",
                "SEO",
                "Users",
                "Bonuses",
                "Jackpots",
                "Messaging",
                "FAQ",
                "Shop",
                "Logs",
                "Reports"
        ));
    }

    public void checkUserName(String username) {
        header.$x(".//li[contains(@class, 'dropdown text-normal nav-profile')]//span")
                .shouldHave(Condition.text(username));
    }
}
