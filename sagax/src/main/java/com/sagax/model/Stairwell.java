package com.sagax.model;

public class Stairwell {

    private int[] input;
    private int stepsPerStride;
    private int response;

    public Stairwell() {

    }

    public Stairwell(int[] input, int stepsPerStride, int response) {
        this.input = input;
        this.stepsPerStride = stepsPerStride;
        this.response = response;
    }

    public Stairwell(int[] input, int stepsPerStride) {
        this.input = input;
        this.stepsPerStride = stepsPerStride;
    }

    public int[] getInput() {
        return input;
    }

    public void setInput(int[] input) {
        this.input = input;
    }

    public int getStepsPerStride() {
        return stepsPerStride;
    }

    public void setStepsPerStride(int stepsPerStride) {
        this.stepsPerStride = stepsPerStride;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

}
