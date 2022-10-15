package com.testslotegrator.endpoints;

import com.testslotegrator.dto.PlayerDto;
import com.testslotegrator.testdata.Player;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

@Endpoint("/v2/players/{id}")
public class PlayersIdEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: получить игрока по id")
    public PlayerDto getPlayer(Player player, Integer id) {
        return given()
                .header(player.getBearerAuthHeader())
                .get(endpoint, id)
                .then()
                .statusCode(200)
                .extract()
                .as(PlayerDto.class);
    }
}
