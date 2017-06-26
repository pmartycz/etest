/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.entities;

import etest.JsonHelper;

import java.time.Duration;
import java.time.Instant;

/**
 * A test without questions/answers.
 * 
 * @author pmart
 */
public class TestHeader implements JsonHelper.Initializable {
    public static TestHeader fromJson(String json) {
        return JsonHelper.fromJson(json, TestHeader.class);
    }
    
    /**
     * UUID of the test.
     */
    private String id;
    
    /**
     * Display name of the test.
     */
    private String name;
    
    /**
     * Type of the test. 
     * 
     * Tests are either single choice or multi choice.
     * Value is "single" or "multi".
     */
    private final String type;
    
    /**
     * Username of the test creator.
     */
    private String owner;
    
    /**
     * Groups of students the test has been made available to.
     */
    private String[] groups;
    
    /**
     * Amount of time to solve the test and send answers.
     */
    private Duration timeLimit;
    
    /**
     * Timestamp when the test becomes available for solving.
     */
    private Instant startTime;
    
    /**
     * Timestamp when the test becomes unavailable for solving.
     */
    private Instant endTime;
    
    /**
     * Timestamp when the user started solving the test.
     */
    private Instant userStartTime;
    
    private boolean resultsAvailable;
    private boolean userSubmittedAnswers;
    
    /**
     * Test status calculated from other fields.
     */
    private transient TestStatus status;
    
    protected TestHeader(String type) {
        this.type = type;
    }
    
    @Override
    public void init() {
        Instant now = Instant.now();
        if (startTime.isAfter(now))
            status = TestStatus.CLOSED;
        else if (endTime.isBefore(now))
            status = TestStatus.FINISHED;
        else if (userSubmittedAnswers)
            status = TestStatus.SENT;
        else if (userStartTime == null)
            status = TestStatus.OPEN;
        else
            status = TestStatus.PENDING;
    }
    
    
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public TestStatus getStatus() {
        return status;
    }    
    public String getType() {
        return type;
    }
    public String getOwner() {
        return owner;
    }
    public Duration getTimeLimit() {
        return timeLimit;
    }
    public Instant getStartTime() {
        return startTime;
    }
    public Instant getEndTime() {
        return endTime;
    }
    public boolean isResultsAvailable() {
        return resultsAvailable;
    }
    
    public boolean isMultiChoice() {
        return "multi".equals(type);
    }
    
    public boolean isStatus(TestStatus status) {
        return this.status == status;
    }
    
    public String toJson() {
        return JsonHelper.toJson(this);
    }
    
    /**
     * Returns number of seconds remaining to solve
     * the test.
     * 
     * This will be negative if it's already too late.
     * 
     * @return number of seconds remaining to solve the test
     */
    public long getNumberOfSecondsRemaining() {
        return Duration.between(Instant.now(), userStartTime.plus(timeLimit)).getSeconds();
    }
       
}
