package com.sagax.service;

import com.sagax.model.StatusMessage;

/**
 *
 */
public interface ClimbingStairsService {

    public int calculateMinimumNumberOfStrides(int[] input, int stepsPerStride);

    public StatusMessage checkValidStairwell(int[] input, int stepsPerStride);

}
