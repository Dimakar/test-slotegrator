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
public class PlayerDto {

    @JsonProperty("bonuses_allowed")
    private Boolean bonusesAllowed;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("birthdate")
    private LocalDate birthdate;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("timezone_id")
    private Integer timezoneId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone_number")
    private Integer phoneNumber;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("is_verified")
    private Boolean isVerified;

    @JsonProperty("country_id")
    private Integer countryId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;
}