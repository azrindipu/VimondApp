package com.assignment.vimond.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Interval {

    @JsonProperty("startValue")
    private Integer startValue;

    @JsonProperty("endValue")
    private Integer endValue;
}
