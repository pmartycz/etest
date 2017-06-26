/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.client;

/**
 * Client instance builder.
 * 
 * @author pmart
 */
public class ClientBuilderImpl implements ClientBuilder {
    private Client client;

    public ClientBuilderImpl() {
        this.client = new Client();
    } 

    @Override
    public ClientBuilder setHost(String host) {
        client.setHost(host);
        return this;
    }

    @Override
    public ClientBuilder setPort(int port) {
        client.setPort(port);
        return this;
    }

    @Override
    public ClientBuilder setUser(String user) {
        client.setUser(user);
        return this;
    }

    @Override
    public ClientBuilder setPassword(String password) {
        client.setPassword(password);
        return this;
    }

    @Override
    public Client build() {
        return client;
    }
}
