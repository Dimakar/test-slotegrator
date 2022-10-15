package com.testslotegrator.testdata;

import com.testslotegrator.dto.AuthRequest;
import com.testslotegrator.endpoints.AuthTokenEndpoint;
import io.restassured.http.Header;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthUser {
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String password;
    private Header basicAuthHeader;
    private Header bearerAuthHeader;

    public BasicAuthUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Header getBasicAuthHeader() {
        if (basicAuthHeader == null) {
            basicAuthHeader = new Header("Authorization", "Basic " + Base64.getEncoder()
                    .encodeToString((this.getLogin() + ":" + this.getPassword())
                            .getBytes(StandardCharsets.UTF_8)));
        }
        return basicAuthHeader;
    }

    public Header getBearerAuthHeader() {
        if (bearerAuthHeader == null) {
            String accessToken = new AuthTokenEndpoint().auth(this, AuthRequest.builder()
                    .grantType("client_credentials")
                    .scope("guest:default")
                    .build()).getAccessToken();
            bearerAuthHeader = new Header("Authorization", "Bearer " + accessToken);
        }
        return bearerAuthHeader;
    }
}
