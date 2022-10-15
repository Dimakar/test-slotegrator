package com.testslotegrator.page;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    public LoginPage inputLogin(String login) {
        $(By.id("UserLogin_username")).sendKeys(login);
        return this;
    }

    public LoginPage inputPassword(String password) {
        $(By.id("UserLogin_password")).sendKeys(password);
        return this;
    }

    public AdminPage clickSubmitButton() {
        $x("//input[@type='submit']").click();
        return new AdminPage();
    }

    public AdminPage login(String login, String password) {
        return inputLogin(login)
                .inputPassword(password)
                .clickSubmitButton();
    }
}