package com.assignment.vimond;

import com.assignment.vimond.dto.Interval;
import com.assignment.vimond.helpers.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class VimondApplicationTests {

	@InjectMocks
	private Utils utils;


	@Test
	void contextLoads() {
		List<Interval> list = Utils.removeInvalidAndDuplicateIntervals(new ArrayList<>());
		Assert.assertEquals(list.size(), 0);
	}



}
