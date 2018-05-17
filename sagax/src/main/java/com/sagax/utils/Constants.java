package com.sagax.utils;

public final class Constants {

    public static final String OK_STATUS = "OK";

    public static final String FAIL_STATUS = "FAIL";

    public static final int MAX_STRIDE_STEP = 4;

    public static final int MIN_STRIDE_STEP = 1;

    public static final int MAX_FLIGHT_STEP = 20;

    public static final int MAX_FLIGHT_STAIRWELL = 30;

    public static final String MSG_INVALID_STRIDE_PER_STEP = "Invalid stride per step. You can stride between 1 and 4 steps inclusive.";

    public static final String MSG_FLIGHT_STAIRWELL = "Invalid number of flight. The stairwell has between 1 and 30 flights inclusive.";

    public static final String MSG_FLIGHT_STEP = "Invalid number of step per flight. Each flight can have a maximum of 20 steps.";

    private Constants() {
        // Prevents this class to be instantiated
        throw new AssertionError();
    }
}
