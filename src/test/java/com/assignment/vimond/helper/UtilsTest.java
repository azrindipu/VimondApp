package com.assignment.vimond.helper;

import com.assignment.vimond.dto.Interval;
import com.assignment.vimond.helpers.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UtilsTest {

    @Test
    void removeInvalidAndDuplicateInterval_emptyList() {
        List<Interval> list = Utils.removeInvalidAndDuplicateIntervals(new ArrayList<>());
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    void removeInvalidAndDuplicateInterval_nullList() {
        List<Interval> list = Utils.removeInvalidAndDuplicateIntervals(null);
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    void removeInvalidAndDuplicateInterval_invalidInterval() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 5));
        intervals.add(new Interval(4, 2));
        intervals.add(new Interval(-1, -4));

        List<Interval> actualList = Utils.removeInvalidAndDuplicateIntervals(intervals);

        List<Interval> expectedList = new ArrayList<>();
        expectedList.add(new Interval(1, 5));


        Assert.assertEquals(expectedList.size(), actualList.size());
        Assert.assertEquals(expectedList.get(0).getStartValue(), actualList.get(0).getStartValue());
        Assert.assertEquals(expectedList.get(0).getEndValue(), actualList.get(0).getEndValue());
    }

    @Test
    void removeInvalidAndDuplicateInterval_duplicateInterval() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 5));
        intervals.add(new Interval(1, 5));
        intervals.add(new Interval(10, 30));
        intervals.add(new Interval(10, 30));
        intervals.add(new Interval(-5, -1));
        intervals.add(new Interval(-5, -1));

        List<Interval> actualList = Utils.removeInvalidAndDuplicateIntervals(intervals);

        List<Interval> expectedList = new ArrayList<>();
        expectedList.add(new Interval(1, 5));
        expectedList.add(new Interval(-5, -1));
        expectedList.add(new Interval(10, 30));

        Assert.assertEquals(expectedList.size(), actualList.size());
    }

    @Test
    void removeOverlapIntervals_null() {
        List<Interval> actual = Utils.removeOverlapIntervals(null);
        Assert.assertEquals(0, actual.size());
    }

    @Test
    void removeOverlapIntervals_empty() {
        List<Interval> actual = Utils.removeOverlapIntervals(new ArrayList<>());
        Assert.assertEquals(0, actual.size());
    }

    @Test
    void removeOverlapIntervals_overLap() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 5));
        intervals.add(new Interval(5, 10));
        intervals.add(new Interval(8, 20));

        List<Interval> actual = Utils.removeOverlapIntervals(intervals);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1, 20));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }

    @Test
    void removeOverlapIntervals_nonOverLap() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 5));
        intervals.add(new Interval(6, 10));

        List<Interval> actual = Utils.removeOverlapIntervals(intervals);

        Assert.assertEquals(2, actual.size());
    }

}
