package com.testslotegrator.endpoints;

import com.testslotegrator.dto.AuthRequest;
import com.testslotegrator.dto.AuthResponse;
import com.testslotegrator.testdata.User;
import io.qameta.allure.Step;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;

@Endpoint("/v2/oauth2/token")
public class AuthTokenEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: Авторизация")
    public AuthResponse auth(User user, AuthRequest authRequest) {
        return given()
                .header(user.getBasicAuthHeader())
                .body(authRequest)
                .post(endpoint)
                .then()
                .statusCode(200)
                .body("token", Matchers.not(Matchers.emptyString()))
                .extract()
                .as(AuthResponse.class);
    }
}
