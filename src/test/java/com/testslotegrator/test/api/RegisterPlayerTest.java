package com.testslotegrator.test.api;

import com.github.javafaker.Faker;
import com.testslotegrator.dto.CreatePlayerRequest;
import com.testslotegrator.dto.PlayerDto;
import com.testslotegrator.endpoints.PlayersEndpoint;
import com.testslotegrator.extensions.ApiTest;
import com.testslotegrator.testdata.BasicAuthUser;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.stream.Stream;

import static com.testslotegrator.testdata.TestDataHelper.getGuest;
import static io.qameta.allure.Allure.step;

@ApiTest
@DisplayName("/v2/players")
public class RegisterPlayerTest {

    static BasicAuthUser guest = getGuest();
    static PlayersEndpoint playersEndpoint = new PlayersEndpoint();

    public static Stream<CreatePlayerRequest> players() {
        Faker faker = new Faker();
        String password = Base64.getEncoder()
                .encodeToString(faker.internet().password()
                        .getBytes(StandardCharsets.UTF_8));

        return Stream.of(CreatePlayerRequest.builder()
                        .email(faker.internet().emailAddress())
                        .name(faker.name().firstName())
                        .surname(faker.name().lastName())
                        .passwordChange(password)
                        .passwordRepeat(password)
                        .username(faker.name().username() + LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("MMddHHmmss")))
                        .currencyCode("USD")
                        .build(),
                CreatePlayerRequest.builder()
                        .email(faker.internet().emailAddress())
                        .name(faker.name().firstName())
                        .surname(faker.name().lastName())
                        .passwordChange(password)
                        .passwordRepeat(password)
                        .username(faker.name().username() + LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("MMddHHmmss")))
                        .gender("male")
                        .phoneNumber(faker.number().numberBetween(10000000, 99999999))
                        .countryId(1)
                        .birthdate(LocalDate.of(1990, 1, 1))
                        .timezoneId(1)
                        .build(),
                CreatePlayerRequest.builder()
                        .email(faker.internet().emailAddress())
                        .name(faker.name().firstName())
                        .surname(faker.name().lastName())
                        .passwordChange(password)
                        .passwordRepeat(password)
                        .username(faker.name().username() + LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("MMddHHmmss")))
                        .gender("female")
                        .phoneNumber(faker.number().numberBetween(10000, 99999))
                        .countryId(2)
                        .birthdate(LocalDate.of(1900, 1, 1))
                        .timezoneId(2)
                        .build(),
                CreatePlayerRequest.builder()
                        .email(faker.internet().emailAddress())
                        .passwordChange(password)
                        .passwordRepeat(password)
                        .username(faker.name().username() + LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("MMddHHmmss")))
                        .build()
        );
    }

    @ParameterizedTest
    @MethodSource("players")
    @DisplayName("/v2/players POST 201: Register new player")
    void createPlayerTest(CreatePlayerRequest createPlayerRequest) {
        PlayerDto playerDto = playersEndpoint.createPlayer(guest, createPlayerRequest);

        step("Check created player is correct", () -> {
            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(playerDto)
                    .usingRecursiveComparison()
                    .ignoringFields("id", "isVerified", "bonusesAllowed")
                    .isEqualTo(createPlayerRequest);
            softAssertions.assertThat(playerDto.getId()).isNotNull();
            softAssertions.assertThat(playerDto.getIsVerified()).isFalse();
            softAssertions.assertThat(playerDto.getBonusesAllowed()).isTrue();
            softAssertions.assertAll();
        });
    }
}
