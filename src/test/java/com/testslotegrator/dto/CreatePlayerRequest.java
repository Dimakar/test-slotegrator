package com.testslotegrator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePlayerRequest {

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("name")
    private String name;

    @JsonProperty("password_change")
    private String passwordChange;

    @JsonProperty("password_repeat")
    private String passwordRepeat;

    @JsonProperty("email")
    private String email;

    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("username")
    private String username;

    @JsonProperty("bonuses_allowed")
    private Boolean bonusesAllowed;

    @JsonFormat(pattern = "YYYY-MM-dd")
    @JsonProperty("birthdate")
    private LocalDate birthdate;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("timezone_id")
    private Integer timezoneId;

    @JsonProperty("phone_number")
    private Integer phoneNumber;

    @JsonProperty("is_verified")
    private Boolean isVerified;

    @JsonProperty("country_id")
    private Integer countryId;
}