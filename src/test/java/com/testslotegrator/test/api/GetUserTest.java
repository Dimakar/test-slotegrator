package com.testslotegrator.test.api;

import com.github.javafaker.Faker;
import com.testslotegrator.dto.CreatePlayerRequest;
import com.testslotegrator.dto.PlayerDto;
import com.testslotegrator.endpoints.PlayersEndpoint;
import com.testslotegrator.endpoints.PlayersIdEndpoint;
import com.testslotegrator.extensions.ApiTest;
import com.testslotegrator.testdata.Player;
import com.testslotegrator.testdata.BasicAuthUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.testslotegrator.testdata.TestDataHelper.getGuest;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ApiTest
public class GetUserTest {
    static Faker faker = new Faker();
    static BasicAuthUser guest = getGuest();
    static Player player1;
    static PlayerDto player1Dto;
    static Integer player2Id;

    static PlayersIdEndpoint playersIdEndpoint = new PlayersIdEndpoint();

    @BeforeEach
    void setUp() {
        player1 = new Player(faker.name().username() + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMddHHmmss")), faker.internet().password(), guest);

        player1Dto = new PlayersEndpoint().createPlayer(guest, CreatePlayerRequest.builder()
                .email(faker.internet().emailAddress())
                .passwordChange(player1.getEncodedPassword())
                .passwordRepeat(player1.getEncodedPassword())
                .username(player1.getUsername())
                .build());

        Player player2 = new Player(faker.name().username() + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMddHHmmss")), faker.internet().password(), guest);

        player2Id = new PlayersEndpoint().createPlayer(guest, CreatePlayerRequest.builder()
                .email(faker.internet().emailAddress())
                .passwordChange(player2.getEncodedPassword())
                .passwordRepeat(player2.getEncodedPassword())
                .username(player2.getUsername())
                .build()).getId();
    }

    @Test
    @DisplayName("Запросить данные профиля игрока")
    void getUserSuccessfulTest() {
        PlayerDto actualPlayerDto = new PlayersIdEndpoint().getPlayer(player1, player1Dto.getId());
        assertThat(actualPlayerDto)
                .usingRecursiveComparison()
                .isEqualTo(player1Dto);
    }

    @Test
    @DisplayName("Запросить данные другого игрока")
    void getAnotherUserFailedTest() {
        given()
                .header(player1.getBearerAuthHeader())
                .get(playersIdEndpoint.getEndpoint(), player2Id)
                .then()
                .statusCode(404);
    }
}
