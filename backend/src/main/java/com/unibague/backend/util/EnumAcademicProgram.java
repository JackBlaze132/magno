package com.unibague.backend.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EnumAcademicProgram {

    @JsonProperty("Ingeniería Mecánica")
    INGMECANICA,
    @JsonProperty("Ingeniería de Sistemas")
    INGSISTEMAS,
    @JsonProperty("Ingeniería Industrial")
    INGINDUSTRIAL,
    @JsonProperty("Ingeniería Electrónica")
    INGELECTRONICA,
    @JsonProperty("Ingeniería Civil")
    INGCIVIL
}
