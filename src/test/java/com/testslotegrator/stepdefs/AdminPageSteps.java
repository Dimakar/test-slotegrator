package com.testslotegrator.stepdefs;

import com.testslotegrator.page.AdminPage;
import io.cucumber.java.en.Then;

import static com.codeborne.selenide.Selenide.page;

public class AdminPageSteps {

    @Then("Admin panel is opened")
    public void adminPanelIsOpened() {
        page(AdminPage.class).checkAdminLeftSidebarIsOpened();
    }

    @Then("{string} authorized successfully")
    public void authorizedSuccessfully(String username) {
        page(AdminPage.class).checkUserName(username);
    }
}
