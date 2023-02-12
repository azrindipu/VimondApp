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

    public static List<Interval> removeOverlapIntervals(List<Interval> intervals){
        List<Interval> result = new ArrayList<>();
        result.add(intervals.get(0));
        for(int i=1; i<intervals.size(); i++){
            Interval lastAddedInterval = result.get(result.size()-1);
            if(lastAddedInterval.getEndValue() >= intervals.get(i).getStartValue()){
                lastAddedInterval.setEndValue(Math.max(lastAddedInterval.getEndValue(), intervals.get(i).getEndValue()));
                result.remove(result.size()-1);
                result.add(lastAddedInterval);
            }else{
                result.add(intervals.get(i));
            }
        }
        return result;
    }
}
