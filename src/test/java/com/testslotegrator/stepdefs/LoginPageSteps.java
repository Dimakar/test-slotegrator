package com.testslotegrator.stepdefs;

import com.testslotegrator.page.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class LoginPageSteps {

    @Given("Open login page")
    public void openLoginPage() {
        open("/admin/login");
    }

    @When("Input {string} in Login Field")
    public void inputInLoginInput(String login) {
        page(LoginPage.class).inputLogin(login);
    }

    @And("Input {string} in Password Field")
    public void inputInPasswordInput(String pass) {
        page(LoginPage.class).inputPassword(pass);
    }

    @And("Click Submit Button")
    public void clickSubmitButton() {
        page(LoginPage.class).clickSubmitButton();
    }

    @Given("{string} {string} is authorized")
    public void auth(String login, String password) {
        openLoginPage();
        page(LoginPage.class).login(login, password);
    }
}