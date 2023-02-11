package com.assignment.vimond.service;

import com.assignment.vimond.dto.Interval;
import com.assignment.vimond.helpers.SortByStartValueComparator;
import com.assignment.vimond.helpers.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class IntervalServiceImpl implements IntervalService {

    @Override
    public List<Interval> removeExcludeIntervals(List<Interval> includes, List<Interval> excludes) {
        List<Interval> result = new ArrayList<>();

        if (includes == null || includes.isEmpty()) return result;
        if (excludes == null || excludes.isEmpty()) return includes;

        includes = Utils.removeInvalidAndDuplicateIntervals(includes);
        excludes = Utils.removeInvalidAndDuplicateIntervals(excludes);

        Collections.sort(includes, new SortByStartValueComparator());
        Collections.sort(excludes, new SortByStartValueComparator());

        int includeIndexTracker = 0;
        int excludeIndexTracker = 0;
        while (includeIndexTracker < includes.size() && excludeIndexTracker < excludes.size()) {
            /*
            when exclude belongs to left side fully.
            Example: includes: [10, 30] and excludes: [5 9]
             */
            if (excludes.get(excludeIndexTracker).getEndValue() < includes.get(includeIndexTracker).getStartValue()) {
                excludeIndexTracker++;
            }

            /*
            when exclude belongs to right side fully.
            Example: includes: [10, 30] and excludes: [31 35]
            */
            else if (excludes.get(excludeIndexTracker).getStartValue() > includes.get(includeIndexTracker).getEndValue()) {
                result.add(includes.get(includeIndexTracker));
                includeIndexTracker++;
            }
            /*
            when include and exclude overlaps.
            Example: includes: [10, 30] and excludes: [15 35]
            Example: includes: [10, 30] and excludes: [5 15]
            Example: includes: [10, 30] and excludes: [20 25]
            Example: includes: [10, 30] and excludes: [10 30]
            Example: includes: [10, 30] and excludes: [5 35]
            */
            else {
                /*
                When we can take the left portion of the include interval
                Example: includes: [10, 30] and excludes: [15 35]
                */
                if (excludes.get(excludeIndexTracker).getStartValue() > includes.get(includeIndexTracker).getStartValue()) {
                    result.add(new Interval(includes.get(includeIndexTracker).getStartValue(),
                            excludes.get(excludeIndexTracker).getStartValue() - 1));
                }
                /*
                When include end value is larger then exclude end value.
                Example: includes: [10, 30] and excludes: [5 15]
                Example: includes: [10, 30] and excludes: [20 25]
                */
                if (includes.get(includeIndexTracker).getEndValue() > excludes.get(excludeIndexTracker).getEndValue()) {
                    includes.get(includeIndexTracker).setStartValue(excludes.get(excludeIndexTracker).getEndValue() + 1);
                    excludeIndexTracker++;
                } else {
                    /*
                    When include end value and exclude end value are same.
                    Example: includes: [10, 30] and excludes: [10 30]
                    */
                    if (includes.get(includeIndexTracker).getEndValue() == excludes.get(excludeIndexTracker).getEndValue()) {
                        includeIndexTracker++;
                        excludeIndexTracker++;
                    }
                    /*
                    When exclude end value is larger than exclude end value
                    Example: includes: [10, 30] and excludes: [5 35]
                    */
                    else {
                        includeIndexTracker++;
                    }
                }
            }
        }
        /*When we iterate all exclude but includes are still left.*/
        while (includeIndexTracker < includes.size()) {
            result.add(includes.get(includeIndexTracker));
            includeIndexTracker++;
        }
        /*As the includes and excludes are sorted, our result set will be sorted too.*/
        return result;
    }
}
