package com.testslotegrator.endpoints;

import com.testslotegrator.dto.CreatePlayerRequest;
import com.testslotegrator.dto.PlayerDto;
import com.testslotegrator.testdata.BasicAuthUser;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

@Endpoint("/v2/players")
public class PlayersEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: Создание игрока")
    public PlayerDto createPlayer(BasicAuthUser user, CreatePlayerRequest createPlayerRequest) {
        return given()
                .body(createPlayerRequest)
                .header(user.getBearerAuthHeader())
                .post(endpoint)
                .prettyPeek()
                .then()
                .statusCode(201)
                .extract()
                .as(PlayerDto.class);
    }
}
