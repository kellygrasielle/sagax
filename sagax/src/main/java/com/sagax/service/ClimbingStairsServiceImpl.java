package com.sagax.service;

import org.springframework.stereotype.Service;

import com.sagax.model.StatusMessage;
import com.sagax.utils.Constants;

/**
 *
 */
@Service
public class ClimbingStairsServiceImpl implements ClimbingStairsService {

    @Override
    public int calculateMinimumNumberOfStrides(int[] input, int stepsPerStride) {

        stepsPerStride = Math.abs(stepsPerStride);

        Integer countStrides = 0;

        // Adding the landings steps
        countStrides = (input.length - 1) * 2;

        // Adding strides by flight
        for (int steps : input) {

            steps = Math.abs(steps);

            if (steps % stepsPerStride == 0) {
                countStrides += (steps - (steps % stepsPerStride)) / stepsPerStride;
            } else {
                countStrides += 1 + ((steps - (steps % stepsPerStride)) / stepsPerStride);
            }

        }

        return countStrides;
    }

    @Override
    public StatusMessage checkValidStairwell(int[] input, int stepsPerStride) {

        if (stepsPerStride < Constants.MIN_STRIDE_STEP || stepsPerStride > Constants.MAX_STRIDE_STEP) {
            return new StatusMessage(Constants.MSG_INVALID_STRIDE_PER_STEP, Constants.FAIL_STATUS);
        } else if (input.length > Constants.MAX_FLIGHT_STAIRWELL) {
            return new StatusMessage(Constants.MSG_FLIGHT_STAIRWELL, Constants.FAIL_STATUS);
        }
        for (int s : input) {
            if (s > Constants.MAX_FLIGHT_STEP) {
                return new StatusMessage(Constants.MSG_FLIGHT_STEP, Constants.FAIL_STATUS);
            }
        }
        return new StatusMessage(null, Constants.OK_STATUS);

    }

}
