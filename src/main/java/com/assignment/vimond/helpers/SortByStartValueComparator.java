package com.assignment.vimond.helpers;

import com.assignment.vimond.dto.Interval;

import java.util.Comparator;

public class SortByStartValueComparator implements Comparator<Interval> {
    @Override
    public int compare(Interval interval, Interval t1) {
        return interval.getStartValue() - t1.getStartValue();
    }
}
