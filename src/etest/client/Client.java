/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.client;

import etest.JsonHelper;
import etest.entities.Test;
import etest.entities.TestHeader;
import java.io.IOException;

/**
 * Creates connections to test server and uses it's API methods.
 * 
 * @author pmart
 */
public class Client {
    /**
     * Hostname of test server
     */
    private String host = "localhost";
    
    /**
     * Port test server is listening on
     */
    private int port = 50000;
    
    /**
     * User name on the test server
     */
    private String username = "anonymous";
    
    /**
     * User password on the test server
     */
    private String password = "";
    
    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUser(String user) {
        this.username = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Gets test with the specified id.
     * 
     * @param id id of the test to get
     * @return
     * @throws IOException
     * @throws ProtocolException 
     */
    public Test getTest(String id) throws IOException, ProtocolException {
        try (Connection connection = new Connection(host, port)) {
           connection.login(username, password);
           connection.send("GET TEST " + id);
           connection.receiveResponse();
           return connection.receiveJson(Test.class);
        }
    }
    
    /**
     * Gets headers of tests avaiable to a user.
     * 
     * @return an array of test headers
     * @throws IOException
     * @throws ProtocolException 
     */
    public TestHeader[] getTests() throws IOException, ProtocolException {
        try (Connection connection = new Connection(host, port)) {
            connection.login(username, password);
            connection.send("GET TESTS");
            connection.receiveResponse();
            return connection.receiveJson(TestHeader[].class);
        }
    }
    
    /**
     * Sends user answers to test.
     * 
     * @param answers answers to test
     * @throws IOException
     * @throws ProtocolException 
     */
    public void putAnswers(Test test) throws IOException, ProtocolException {
        try (Connection connection = new Connection(host, port)) {
            connection.login(username, password);
            connection.send("PUT ANSWERS " + test.getId());
            connection.receiveResponse();
            connection.send(JsonHelper.toJson(test.getUserAnswers()));
            connection.receiveResponse();
        }
    }
}
