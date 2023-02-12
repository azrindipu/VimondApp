package com.assignment.vimond.service;

import com.assignment.vimond.dto.Interval;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntervalServiceImplTest {

    @InjectMocks
    private IntervalServiceImpl intervalService;
    private List<Interval> includes = new ArrayList<>();
    private List<Interval> excludes = new ArrayList<>();

    @Test
    void removeExcludeIntervals_excludeIsNull() {
        includes = new ArrayList<>();
        includes.add(new Interval(1, 2));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, null);
        Assert.assertEquals(1, actual.size());
    }

    @Test
    void removeExcludeIntervals_excludeIsEmpty() {
        includes = new ArrayList<>();
        includes.add(new Interval(1, 2));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, new ArrayList<>());
        Assert.assertEquals(1, actual.size());
    }

    @Test
    void removeExcludeIntervals_includeIsNull() {
        excludes.add(new Interval(1, 2));
        List<Interval> actual = intervalService.removeExcludeIntervals(null, excludes);
        Assert.assertEquals(0, actual.size());
    }

    @Test
    void removeExcludeIntervals_includeIsEmpty() {
        excludes.add(new Interval(1, 2));
        List<Interval> actual = intervalService.removeExcludeIntervals(new ArrayList<>(), excludes);
        Assert.assertEquals(0, actual.size());
    }

    @Test
    void removeExcludeIntervals_includeContainsDuplicateInterval() {
        includes.add(new Interval(1, 2));
        includes.add(new Interval(3, 4));
        includes.add(new Interval(3, 4));

        excludes.add(new Interval(1, 2));
        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(3, 4));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }

    @Test
    void removeExcludeIntervals_includeContainsOverlap() {
        includes.add(new Interval(1, 2));
        includes.add(new Interval(2, 5));

        excludes.add(new Interval(2, 2));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1, 1));
        expected.add(new Interval(3, 5));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(1).getEndValue(), actual.get(1).getEndValue());
    }

    @Test
    void removeExcludeIntervals_includeContainsUnorderedIntervals() {
        includes.add(new Interval(2, 5));
        includes.add(new Interval(1, 2));

        excludes.add(new Interval(2, 2));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1, 1));
        expected.add(new Interval(3, 5));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(1).getEndValue(), actual.get(1).getEndValue());
    }

    @Test
    void removeExcludeIntervals_excludeBelongsLeftSideFully() {
        includes.add(new Interval(10, 30));
        excludes.add(new Interval(5, 9));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(10, 30));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }

    @Test
    void removeExcludeIntervals_excludeBelongsRightSideFully() {
        includes.add(new Interval(10, 30));
        excludes.add(new Interval(31, 35));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(10, 30));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }

    @Test
    void removeExcludeIntervals_excludeLeftSide() {
        includes.add(new Interval(10, 30));
        excludes.add(new Interval(5, 15));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(16, 30));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }

    @Test
    void removeExcludeIntervals_excludeRightSide() {
        includes.add(new Interval(10, 30));
        excludes.add(new Interval(15, 35));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(10, 14));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }

    @Test
    void removeExcludeIntervals_excludeFromMiddle() {
        includes.add(new Interval(10, 30));
        excludes.add(new Interval(20, 25));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(10, 19));
        expected.add(new Interval(26, 30));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }

    @Test
    void removeExcludeIntervals_excludeFull() {
        includes.add(new Interval(10, 30));
        excludes.add(new Interval(10, 30));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();

        Assert.assertEquals(expected.size(), actual.size());
    }

    @Test
    void removeExcludeIntervals_excludeIsLargerThanInclude() {
        includes.add(new Interval(10, 30));
        excludes.add(new Interval(5, 35));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();

        Assert.assertEquals(expected.size(), actual.size());
    }

    @Test
    void removeExcludeIntervals_includesContainMoreIntervals() {
        includes.add(new Interval(10, 30));
        includes.add(new Interval(40, 50));

        excludes.add(new Interval(20, 25));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(10, 19));
        expected.add(new Interval(26, 30));
        expected.add(new Interval(40, 50));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }

    @Test
    void removeExcludeIntervals_testCaseGivenWithAssignment_01() {
        includes.add(new Interval(10, 100));
        excludes.add(new Interval(20, 30));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(10, 19));
        expected.add(new Interval(31, 100));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }

    @Test
    void removeExcludeIntervals_testCaseGivenWithAssignment_02_01() {
        includes.add(new Interval(50, 5000));
        includes.add(new Interval(10, 100));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, null);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(10, 5000));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }

    @Test
    void removeExcludeIntervals_testCaseGivenWithAssignment_02_02() {
        includes.add(new Interval(50, 5000));
        includes.add(new Interval(10, 100));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, new ArrayList<>());

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(10, 5000));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }

    @Test
    void removeExcludeIntervals_testCaseGivenWithAssignment_03() {
        includes.add(new Interval(200, 300));
        includes.add(new Interval(50, 150));

        excludes.add(new Interval(95, 205));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(50, 94));
        expected.add(new Interval(206, 300));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }

    @Test
    void removeExcludeIntervals_testCaseGivenWithAssignment_04() {
        includes.add(new Interval(200, 300));
        includes.add(new Interval(10, 100));
        includes.add(new Interval(400, 500));

        excludes.add(new Interval(410, 420));
        excludes.add(new Interval(95, 205));
        excludes.add(new Interval(100, 150));

        List<Interval> actual = intervalService.removeExcludeIntervals(includes, excludes);

        List<Interval> expected = new ArrayList<>();
        expected.add(new Interval(10, 94));
        expected.add(new Interval(206, 300));
        expected.add(new Interval(400, 409));
        expected.add(new Interval(421, 500));

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getStartValue(), actual.get(0).getStartValue());
        Assert.assertEquals(expected.get(0).getEndValue(), actual.get(0).getEndValue());
    }
}
