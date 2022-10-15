package com.testslotegrator.test.api;

import com.github.javafaker.Faker;
import com.testslotegrator.dto.AuthRequest;
import com.testslotegrator.dto.AuthResponse;
import com.testslotegrator.dto.CreatePlayerRequest;
import com.testslotegrator.endpoints.AuthTokenEndpoint;
import com.testslotegrator.endpoints.PlayersEndpoint;
import com.testslotegrator.extensions.ApiTest;
import com.testslotegrator.testdata.Player;
import com.testslotegrator.testdata.BasicAuthUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.testslotegrator.testdata.TestDataHelper.getGuest;
import static org.assertj.core.api.Assertions.assertThat;

@ApiTest
@DisplayName("/v2/oauth2/token")
public class AuthByNewPlayerTest {
    static BasicAuthUser guest = getGuest();
    Player player;

    @BeforeEach
    void setUp() {
        Faker faker = new Faker();
        player = new Player(faker.name().username()
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmss")),
                faker.internet().password(), guest);
        new PlayersEndpoint().createPlayer(guest, CreatePlayerRequest.builder()
                .email(faker.internet().emailAddress())
                .passwordChange(player.getEncodedPassword())
                .passwordRepeat(player.getEncodedPassword())
                .username(player.getUsername())
                .build());
    }

    @Test
    @DisplayName("/v2/oauth2/token POST 200: Auth by new player")
    void authByNewPlayerTest() {
        AuthResponse authResponse = new AuthTokenEndpoint().auth(guest, AuthRequest.builder()
                .grantType("password")
                .password(player.getEncodedPassword())
                .username(player.getUsername())
                .build());
        assertThat(authResponse.getAccessToken()).isNotEmpty();
    }
}
