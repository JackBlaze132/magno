package com.unibague.backend.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Sex {
    @JsonProperty("Masculino")
    MALE,
    @JsonProperty("Femenino")
    FEMALE
}
