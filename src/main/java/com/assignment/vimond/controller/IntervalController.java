package com.assignment.vimond.controller;

import com.assignment.vimond.dto.Interval;
import com.assignment.vimond.dto.IntervalDto;
import com.assignment.vimond.service.IntervalServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interval")
public class IntervalController {

    @Autowired
    private IntervalServiceImpl intervalService;

    @PostMapping("/")
    public ResponseEntity<JSONObject> removeExcludeIntervals(@Parameter(name = "payload", description = "payload")
                                                             @RequestBody IntervalDto intervalDto) {
        List<Interval> result = intervalService.removeExcludeIntervals(intervalDto.getIncludes(), intervalDto.getExcludes());
        JSONObject responseBody = new JSONObject();
        responseBody.put("result", result);
        return ResponseEntity
                .status(HttpStatus.OK).body(responseBody);
    }
}
