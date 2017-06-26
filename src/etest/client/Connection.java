/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.client;

import static etest.Application.logger;
import etest.JsonHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Allows to send requests and receive responses from test server.
 * 
 * @author pmart
 */
public final class Connection implements Closeable {
    private final Socket socket;
    private final BufferedReader in;
    private final BufferedWriter out;
    
    private final AuthenticationAgent authAgent = new AuthenticationAgent(this); 
    
    private AuthorizationLevel authorizationLevel = AuthorizationLevel.UNAUTHORIZED;

    /**
     * Gets authorization level of client for this connection.
     * 
     * @return current authorization level 
     */
    public AuthorizationLevel getAuthorizationLevel() {
        return authorizationLevel;
    }

    /**
     * Sets authorization level of client for this connection.
     * 
     * @param authorizationLevel new authorization level
     */
    public void setAuthorizationLevel(AuthorizationLevel authorizationLevel) {
        this.authorizationLevel = authorizationLevel;
    }
    
    /**
     * Creates and initializes connection to the test server.
     * 
     * @param host server hostname
     * @param port port server is listening on
     * @throws IOException network error
     * @throws ProtocolException protocol error
     */
    public Connection(String host, int port) throws IOException, ProtocolException {
        socket = new Socket(host, port);
        out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), 
                            StandardCharsets.UTF_8));
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), 
                        StandardCharsets.UTF_8));
        
        // Etestd x.y.z
        receiveResponse();
    }
    
    /**
     * Sends a line of text to the test server.
     * 
     * @param s string to send
     * @throws IOException network error
     */
    public void send(String s) throws IOException {
        logger.fine(s);
        out.write(s + "\r\n");
        out.flush();
    }
    
    /**
     * Receives a line of text from the test server.
     * 
     * @return line of text
     * @throws IOException network error
     */
    private String receive() throws IOException {
        String s = in.readLine();
        logger.fine(s);
        return s;
    }
    
    /**
     * Receives response to request from the test server.
     * 
     * @return response tokens
     * @throws IOException network error
     * @throws ProtocolException protocol error
     */
    public String[] receiveResponse() throws IOException, ProtocolException {
        String response = receive();
        
        if (response == null)
            throw new ProtocolException("No response from server");
        
        String[] responseTokens = response.split(" ");
        String statusToken = responseTokens[0];
        String[] restTokens = (responseTokens.length > 1) ? 
                Arrays.copyOfRange(responseTokens, 1, responseTokens.length) : 
                new String[] { "" };
        
        if ("-ERR".equals(statusToken))
            throw new ProtocolException("Error response from server: " + String.join(" ", restTokens));
        if (!"+OK".equals(statusToken))
            throw new ProtocolException("Invalid response from server: " + response);
        
        return restTokens;
    }
    
    /**
     * Returns a java object from json retrieved from the test server.
     * 
     * @param <T> type of java object
     * @param classOfT class of java object
     * @return java object
     */
    public <T> T receiveJson(Class<T> classOfT) {
        T object = JsonHelper.fromJson(in, classOfT);
        logger.fine(JsonHelper.toJson(object));
        return object;
    }
    
    /**
     * Authenticates with the server.
     * 
     * @param user user name
     * @param password user password
     * @throws IOException network error
     * @throws ProtocolException protocol error
     */
    public void login(String user, String password) throws IOException, ProtocolException {
        authAgent.login(user, password);
    }
    
    @Override
    public void close() throws IOException {
        send("BYE");
        
        if (in != null)
            in.close();
        if (out != null)
            out.close();
        if (socket != null)
            socket.close();
    }
    
}
