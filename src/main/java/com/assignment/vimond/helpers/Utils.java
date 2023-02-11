package com.assignment.vimond.helpers;

import com.assignment.vimond.dto.Interval;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    public static List<Interval> removeInvalidAndDuplicateIntervals(List<Interval> intervals) {
        Set<Interval> validAndUniqueIntervals = new HashSet<>();
        if (intervals == null || intervals.isEmpty() || intervals.size() == 0) return new ArrayList<>();
        for (Interval interval : intervals) {
            if (interval != null && interval.getStartValue() != null
                    && interval.getEndValue() != null && interval.getStartValue() >= interval.getEndValue()) {
                validAndUniqueIntervals.add(interval);
            }
        }
        return new ArrayList<>(validAndUniqueIntervals);
    }
}
