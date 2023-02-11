package com.assignment.vimond.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IntervalDto {

    @JsonProperty("includes")
    private List<Interval> includes;

    @JsonProperty("excludes")
    private List<Interval> excludes;
}
