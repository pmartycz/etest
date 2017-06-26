/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.client;

import java.io.IOException;

/**
 * Interface for authentication strategies.
 * 
 * @author pmart
 */
public interface AuthenticationStrategy {
    void authenticate() throws IOException;
}
