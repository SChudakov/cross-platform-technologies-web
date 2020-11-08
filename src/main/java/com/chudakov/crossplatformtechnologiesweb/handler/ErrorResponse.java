package com.chudakov.crossplatformtechnologiesweb.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    @NotNull
    @JsonProperty("message")
    private String message;
}
