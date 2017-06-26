/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest;

/**
 * Class containing misceallaneous static helper methods.
 * 
 * @author pmart
 */
public class Utils {
    
    public static boolean contains(final boolean[] array, final boolean v) {
        for (final boolean e : array)
            if (e == v)
                return true;
        return false;
    }
}
