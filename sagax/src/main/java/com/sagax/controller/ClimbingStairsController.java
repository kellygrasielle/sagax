package com.sagax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sagax.model.Stairwell;
import com.sagax.model.StatusMessage;
import com.sagax.service.ClimbingStairsService;
import com.sagax.utils.Constants;

@RestController
public class ClimbingStairsController {

    @Autowired
    private ClimbingStairsService climbingStairsService;

    @RequestMapping(value = "/{input}/stepsPerStride/{stepsPerStride}/minimumnumberstrides", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getMinimumNumberOfStrides(@PathVariable int[] input, @PathVariable int stepsPerStride) {

        StatusMessage valid = climbingStairsService.checkValidStairwell(input, stepsPerStride);
        if (valid.getStatus().equals(Constants.OK_STATUS)) {
            Stairwell stair = new Stairwell();
            stair.setInput(input);
            stair.setStepsPerStride(stepsPerStride);

            try {
                int response = climbingStairsService.calculateMinimumNumberOfStrides(input, stepsPerStride);
                stair.setResponse(response);
                return new ResponseEntity(stair, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(stair, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(valid, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getMinimumNumberOfStrides(@RequestBody Stairwell stairwell) {

        StatusMessage valid = climbingStairsService.checkValidStairwell(stairwell.getInput(), stairwell.getStepsPerStride());
        if (valid.getStatus().equals(Constants.OK_STATUS)) {
            try {
                int response = climbingStairsService.calculateMinimumNumberOfStrides(stairwell.getInput(), stairwell.getStepsPerStride());
                stairwell.setResponse(response);
                return new ResponseEntity(stairwell, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(stairwell, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(valid, HttpStatus.BAD_REQUEST);
        }

    }

}
