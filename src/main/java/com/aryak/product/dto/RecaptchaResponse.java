package com.aryak.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RecaptchaResponse(

        boolean success,

        @JsonProperty(value = "challenge_ts")
        String challengeTimestamp,

        @JsonProperty(value = "hostname")
        String hostname) {
}
