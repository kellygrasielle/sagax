package com.sagax;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.ContextConfiguration;

import com.sagax.config.AppConfig;

@RunWith(Suite.class)
@SuiteClasses({ ClimbingStairsServiceTest.class })
@ContextConfiguration(classes = { AppConfig.class })
public class SagaxControllerIntegrationTest {

}
