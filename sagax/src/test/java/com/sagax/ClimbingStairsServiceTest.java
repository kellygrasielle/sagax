package com.sagax;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sagax.config.AppConfig;
import com.sagax.service.ClimbingStairsService;

import junit.framework.TestCase;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class ClimbingStairsServiceTest extends TestCase {

    @Autowired
    private ClimbingStairsService climbingStairsService;

    @Test
    public void test_get_minimum_number_of_strides_one_fly_success() throws Exception {

        assertEquals(6, climbingStairsService.calculateMinimumNumberOfStrides(new int[] { 17 }, 3));
        assertEquals(14, climbingStairsService.calculateMinimumNumberOfStrides(new int[] { 17, 17 }, 3));
        assertEquals(50, climbingStairsService.calculateMinimumNumberOfStrides(new int[] { 4, 9, 8, 11, 7, 20, 14 }, 2));
    }

    @Test
    public void test_get_minimum_number_of_strides_two_fly_fail() throws Exception {

        assertNotSame(12, climbingStairsService.calculateMinimumNumberOfStrides(new int[] { 17, 17 }, 3));
    }

}
