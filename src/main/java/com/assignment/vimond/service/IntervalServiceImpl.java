package com.assignment.vimond.service;

import com.assignment.vimond.dto.Interval;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervalServiceImpl implements IntervalService {

    @Override
    public List<Interval> removeExcludeIntervals(List<Interval> includes, List<Interval> excludes) {
        ExcludeIntervalsStrategy strategy = new ExcludeIntervalsStrategyBasic();
        return strategy.removeExcludeIntervals(includes, excludes);
    }
}
