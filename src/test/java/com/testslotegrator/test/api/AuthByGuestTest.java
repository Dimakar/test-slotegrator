package com.testslotegrator.test.api;

import com.testslotegrator.dto.AuthRequest;
import com.testslotegrator.endpoints.AuthTokenEndpoint;
import com.testslotegrator.extensions.ApiTest;
import com.testslotegrator.testdata.BasicAuthUser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.testslotegrator.testdata.TestDataHelper.getGuest;
import static io.restassured.RestAssured.given;

@ApiTest
public class AuthByGuestTest {
    static BasicAuthUser guest = getGuest();
    static AuthTokenEndpoint authTokenEndpoint = new AuthTokenEndpoint();

    @Test
    @DisplayName("Получить токен гостя")
    void apiTokenTest() {
        given()
                .header(guest.getBasicAuthHeader())
                .body(AuthRequest.builder()
                        .grantType("client_credentials")
                        .scope("guest:default")
                        .build())
                .post(authTokenEndpoint.getEndpoint())
                .then()
                .statusCode(200)
                .body("token", Matchers.not(Matchers.emptyString()));
    }
}
