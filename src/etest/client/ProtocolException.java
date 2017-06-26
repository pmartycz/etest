/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.client;

/**
 * Exception representing violation of Etest client-server protocol.
 * 
 * @author pmart
 */
public class ProtocolException extends Exception {
    public ProtocolException() { super(); }
    public ProtocolException(String message) { super(message); }
    public ProtocolException(String message, Throwable cause) { super(message, cause); }
    public ProtocolException(Throwable cause) { super(cause); }
}
