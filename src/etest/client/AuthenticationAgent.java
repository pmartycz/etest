/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides authentication with the test server
 * according to its authentication strategy.
 * 
 * @author pmart
 */
public class AuthenticationAgent {

    private AuthenticationStrategy strategy;
    private Connection connection;
    
    public AuthenticationAgent(Connection connection) {
        this.connection = connection;
    }
    
    public void login(String user, String password) throws IOException, ProtocolException {
        String response;
        
        connection.send("USER " + user);
        response = connection.receiveResponse()[0];
        
        switch (response) {
            case "PLAINTEXT":
                strategy = new PlaintextAuthenticationStrategy(password);
                break;
            default:
                strategy = new ChallengeAuthenticationStrategy(password, response);
                break;
        }
     
        strategy.authenticate();
        
        response = connection.receiveResponse()[0];
            
        switch (response) {
            case "STUDENT":
                connection.setAuthorizationLevel(AuthorizationLevel.STUDENT);
                break;
            case "EXAMINATOR":
                connection.setAuthorizationLevel(AuthorizationLevel.EXAMINATOR);
                break;
            case "ADMINISTRATOR":
                connection.setAuthorizationLevel(AuthorizationLevel.ADMINISTRATOR);
                break;    
        }
    }
    
    private class ChallengeAuthenticationStrategy implements AuthenticationStrategy {
        private final MessageDigest md;   
        private final String challengeToken;
        private final String password;
        
        public ChallengeAuthenticationStrategy(String password, String challengeToken) {
            this.password = password;
            this.challengeToken = challengeToken;
            
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException("Failed to create MessageDigest Instance", ex);
            }
        }

        @Override
        public void authenticate() throws IOException {
            try {
                String passwordDigest = javax.xml.bind.DatatypeConverter.printHexBinary(
                        md.digest(password.getBytes("UTF-8"))
                ).toLowerCase();
                String combinedDigest = javax.xml.bind.DatatypeConverter.printHexBinary(
                        md.digest((passwordDigest + challengeToken).getBytes("UTF-8"))
                ).toLowerCase();         
                connection.send(combinedDigest);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ChallengeAuthenticationStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    private class PlaintextAuthenticationStrategy implements AuthenticationStrategy {
        private final String password;
        
        public PlaintextAuthenticationStrategy(String password) {
            this.password = password;
        }
        
        @Override
        public void authenticate() throws IOException {
            connection.send(password);
        }
        
    }
}
