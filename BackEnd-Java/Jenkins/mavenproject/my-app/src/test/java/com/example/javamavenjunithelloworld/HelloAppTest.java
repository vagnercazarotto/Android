package com.example.javamavenjunithelloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * Unit test for HelloApp.
 * <p/>
 * A unit test aims to test all code and code paths of a specific class.
 * <p/>
 * This test uses PowerMock and Mockito to mock objects.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({System.class, HelloApp.class})
public class HelloAppTest {

    @Test
    public void testMain() {
        String[] args = {"1"};
        HelloApp.main(args);
    }

 
}
