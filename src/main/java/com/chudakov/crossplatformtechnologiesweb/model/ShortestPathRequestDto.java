package com.chudakov.crossplatformtechnologiesweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortestPathRequestDto {
    @JsonProperty("graph")
    @NotNull
    private Map<Integer, Set<Integer>> graph;

    @JsonProperty("source")
    @NotNull
    Integer source;

    @JsonProperty("target")
    @NotNull
    Integer target;
}
