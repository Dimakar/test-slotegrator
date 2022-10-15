package com.testslotegrator.stepdefs;

import com.testslotegrator.page.LeftSideBar;
import com.testslotegrator.page.PlayerPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.open;

public class PlayerPageSteps {


    @When("Click {string} -> {string} on the sidebar")
    public void clickOnTheSidebar(String arg0, String arg1) {
        new LeftSideBar().clickMenuItem(arg0, arg1);
    }

    @Then("Player list is visible")
    public void playerListIsVisibleAndCorrectFor() {
        new PlayerPage().tableIsLoaded();
    }

    @And("Players Page is opened")
    public void playersPageIsOpened() {
        open("/user/player/admin");
    }

    @When("Select sorting by {string}")
    public void selectSortingBy(String column) {
        new PlayerPage().selectSorting(column);
    }

    @Then("Sorting by registration date is {string}")
    public void sortingByRegistrationDateIsAsc(String sortType) {
        new PlayerPage().checkRegistrationSort(sortType);
    }
}
