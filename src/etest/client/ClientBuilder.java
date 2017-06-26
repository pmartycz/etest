/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.client;

/**
 * Interface for building instances of Client.
 * 
 * @author pmart
 */
public interface ClientBuilder {
    ClientBuilder setHost(String host);
    ClientBuilder setPort(int port);
    ClientBuilder setUser(String user);
    ClientBuilder setPassword(String password);

    Client build();
}
