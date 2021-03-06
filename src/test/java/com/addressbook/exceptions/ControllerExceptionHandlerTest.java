package com.addressbook.exceptions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sharatjagannath on 7/26/17.
 */

public class ControllerExceptionHandlerTest {


    private ControllerExceptionHandler controllerExceptionHandler;
    @Before
    public void setUp() throws Exception {
        controllerExceptionHandler = new ControllerExceptionHandler();

    }

    @Test
    public void testUniquenessExceptionAdvice() throws Exception {
        UniquenessException e = new UniquenessException("object already exists");
        assertNotNull(controllerExceptionHandler.handleUniquenessException(e));
        assertEquals(controllerExceptionHandler.handleUniquenessException(e), "object already exists");
    }

    @Test
    public void testMissingDataExceptonAdvice() throws Exception {
        MissingDataException e = new MissingDataException("data missing");
        assertNotNull(controllerExceptionHandler.handleMissingDataException(e));
        assertEquals(controllerExceptionHandler.handleMissingDataException(e), "data missing");

    }
}
