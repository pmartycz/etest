/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.entities;

/**
 * Status of the test.
 *
 * @author pmart
 */
public enum TestStatus {
    /**
     * Test is not available now but will be available in the future.
     */
    CLOSED,
    
    /**
     * Test is available and user hasn't started solving it.
     */
    OPEN,
    
    /**
     * Test is being solved by the user but hasn't been
     * submitted to test server.
     */
    PENDING,
    
    /**
     * Test has been submitted to test server.
     */
    SENT,
    
    /**
     * Test is not available now but was available in the past.
     */
    FINISHED;
    
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
