package com.addressbook.exceptions;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
/**
 * Created by sharatjagannath on 7/26/17.
 */
public class BaseExceptionTest {
    @Test
    public void testGetMessage() throws Exception {
        BaseException exception = new BaseException("sample test");
        assertEquals(exception.getMessage(), "sample test");
    }

    @Test
    public void testSetMessage() throws Exception {
        BaseException exception = new BaseException(null);
        exception.setMessage("set now");
        assertEquals(exception.getMessage(), "set now");
    }
}
