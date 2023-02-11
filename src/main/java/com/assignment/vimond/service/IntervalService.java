package com.assignment.vimond.service;

import com.assignment.vimond.dto.Interval;

import java.util.List;

public interface IntervalService {
    List<Interval> removeExcludeIntervals(List<Interval> includes, List<Interval> excludes);
}
