package com.sagax;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sagax.config.AppConfig;
import com.sagax.controller.ClimbingStairsController;
import com.sagax.filter.CORSFilter;
import com.sagax.model.StatusMessage;
import com.sagax.service.ClimbingStairsService;
import com.sagax.utils.Constants;

import junit.framework.TestCase;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class ClimbingStairsServiceWithMockTest extends TestCase {

    /*
     * The use of the mock is NOT necessary for this scenario as we do not access database or external APIs. It was used just to give a
     * example of how to use.
     */

    private MockMvc mockMvc;

    int response;
    int stepsPerStride;
    int[] input;

    @Mock
    private ClimbingStairsService climbingStairsService;

    @InjectMocks
    private ClimbingStairsController climbingStairsController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(climbingStairsController).addFilters(new CORSFilter()).build();
    }

    @Test
    public void test_get_minimum_number_of_strides_one_fly_success() throws Exception {
        response = 14;
        stepsPerStride = 3;
        input = new int[] { 17, 17 };

        when(climbingStairsService.checkValidStairwell(input, stepsPerStride)).thenReturn(new StatusMessage(null, Constants.OK_STATUS));
        when(climbingStairsService.calculateMinimumNumberOfStrides(input, stepsPerStride)).thenReturn(response);

        mockMvc.perform(get("/{input}/stepsPerStride/{stepsPerStride}/minimumnumberstrides", "17,17", stepsPerStride))
            .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.response", is(response))).andExpect(jsonPath("$.stepsPerStride", is(stepsPerStride)));

        verify(climbingStairsService, times(1)).checkValidStairwell(input, stepsPerStride);
        verify(climbingStairsService, times(1)).calculateMinimumNumberOfStrides(input, stepsPerStride);
        verifyNoMoreInteractions(climbingStairsService);
    }

    @Test
    public void test_get_minimum_number_of_strides_two_fly_success() throws Exception {
        response = 14;
        stepsPerStride = 3;
        input = new int[] { 17, 17 };

        when(climbingStairsService.checkValidStairwell(input, stepsPerStride)).thenReturn(new StatusMessage(null, Constants.OK_STATUS));
        when(climbingStairsService.calculateMinimumNumberOfStrides(input, stepsPerStride)).thenReturn(response);

        mockMvc.perform(get("/{input}/stepsPerStride/{stepsPerStride}/minimumnumberstrides", "17,17", stepsPerStride))
            .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.response", is(response))).andExpect(jsonPath("$.stepsPerStride", is(stepsPerStride)));

        verify(climbingStairsService, times(1)).checkValidStairwell(input, stepsPerStride);
        verify(climbingStairsService, times(1)).calculateMinimumNumberOfStrides(input, stepsPerStride);
        verifyNoMoreInteractions(climbingStairsService);
    }

    @Test
    public void test_get_minimum_number_of_strides_underStepByFlightLimit_fail() throws Exception {
        response = 14;
        stepsPerStride = 3;
        input = new int[] { 17, -17 };

        when(climbingStairsService.checkValidStairwell(input, stepsPerStride))
            .thenReturn(new StatusMessage(Constants.MSG_FLIGHT_STEP, Constants.FAIL_STATUS));
        when(climbingStairsService.calculateMinimumNumberOfStrides(input, stepsPerStride)).thenReturn(response);

        mockMvc.perform(get("/{input}/stepsPerStride/{stepsPerStride}/minimumnumberstrides", "17,-17", stepsPerStride))
            .andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.status", is(Constants.FAIL_STATUS)));

        verify(climbingStairsService, times(1)).checkValidStairwell(input, stepsPerStride);
        verifyNoMoreInteractions(climbingStairsService);
    }

    @Test
    public void test_get_minimum_number_of_strides_overStepByFlightLimit_fail() throws Exception {
        response = 25;
        stepsPerStride = 3;
        input = new int[] { 17, 50 };

        when(climbingStairsService.checkValidStairwell(input, stepsPerStride))
            .thenReturn(new StatusMessage(Constants.MSG_FLIGHT_STEP, Constants.FAIL_STATUS));
        when(climbingStairsService.calculateMinimumNumberOfStrides(input, stepsPerStride)).thenReturn(response);

        mockMvc.perform(get("/{input}/stepsPerStride/{stepsPerStride}/minimumnumberstrides", "17,50", stepsPerStride))
            .andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.status", is(Constants.FAIL_STATUS)));

        verify(climbingStairsService, times(1)).checkValidStairwell(input, stepsPerStride);
        verifyNoMoreInteractions(climbingStairsService);
    }

    @Test
    public void test_get_minimum_number_of_strides_underStepPerStridelLimit_fail() throws Exception {
        response = 0;
        stepsPerStride = 0;
        input = new int[] { 18, 14 };

        when(climbingStairsService.checkValidStairwell(input, stepsPerStride))
            .thenReturn(new StatusMessage(Constants.MSG_INVALID_STRIDE_PER_STEP, Constants.FAIL_STATUS));
        when(climbingStairsService.calculateMinimumNumberOfStrides(input, stepsPerStride)).thenReturn(response);

        mockMvc.perform(get("/{input}/stepsPerStride/{stepsPerStride}/minimumnumberstrides", "18,14", stepsPerStride))
            .andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.status", is(Constants.FAIL_STATUS)));

        verify(climbingStairsService, times(1)).checkValidStairwell(input, stepsPerStride);
        verifyNoMoreInteractions(climbingStairsService);
    }

    @Test
    public void test_get_minimum_number_of_strides_overStepPerStridelLimit_fail() throws Exception {
        stepsPerStride = 10;
        String inputStr = "18,14";
        input = new int[] { 18, 14 };

        when(climbingStairsService.checkValidStairwell(input, stepsPerStride))
            .thenReturn(new StatusMessage(Constants.MSG_INVALID_STRIDE_PER_STEP, Constants.FAIL_STATUS));
        when(climbingStairsService.calculateMinimumNumberOfStrides(input, stepsPerStride)).thenReturn(response);

        mockMvc.perform(get("/{input}/stepsPerStride/{stepsPerStride}/minimumnumberstrides", inputStr, stepsPerStride))
            .andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.status", is(Constants.FAIL_STATUS)));

        verify(climbingStairsService, times(1)).checkValidStairwell(input, stepsPerStride);
        verifyNoMoreInteractions(climbingStairsService);
    }

    @Test
    public void test_get_minimum_number_of_strides_overFlightPerStairwellLimit_fail() throws Exception {
        stepsPerStride = 9;
        String inputStr = "17, 17, 5, 8, 9, 8, 7, 7, 7, 8, 17, 17, 5, 8, 9, 8, 7, 7, 7, 8, 17, 17, 5, 8, 9, 8, 7, 7, 7, 8, 8";
        input = new int[] { 17, 17, 5, 8, 9, 8, 7, 7, 7, 8, 17, 17, 5, 8, 9, 8, 7, 7, 7, 8, 17, 17, 5, 8, 9, 8, 7, 7, 7, 8, 8 };

        when(climbingStairsService.checkValidStairwell(input, stepsPerStride))
            .thenReturn(new StatusMessage(Constants.MSG_FLIGHT_STAIRWELL, Constants.FAIL_STATUS));
        when(climbingStairsService.calculateMinimumNumberOfStrides(input, stepsPerStride)).thenReturn(response);

        mockMvc.perform(get("/{input}/stepsPerStride/{stepsPerStride}/minimumnumberstrides", inputStr, stepsPerStride))
            .andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.status", is(Constants.FAIL_STATUS)));

        verify(climbingStairsService, times(1)).checkValidStairwell(input, stepsPerStride);
        verifyNoMoreInteractions(climbingStairsService);
    }

    @Test
    public void test_get_minimum_number_of_strides_invalidinput_fail() throws Exception {
        String stepsPerStride = "9";
        String input = "test";

        mockMvc.perform(get("/{input}/stepsPerStride/{stepsPerStride}/minimumnumberstrides", input, stepsPerStride))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void test_get_minimum_number_of_strides_invalidstepsperstride_fail() throws Exception {

        mockMvc.perform(get("/{input}/stepsPerStride/{stepsPerStride}/minimumnumberstrides", "18,14", "String"))
            .andExpect(status().isBadRequest());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
