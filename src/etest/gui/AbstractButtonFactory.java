/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.gui;

import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractButton;

/**
 * Abstract factory producing question option buttons.
 * 
 * Contains a map for retrieving concrete factory based
 * on the type of test.
 * 
 * @author pmart
 */
public abstract class AbstractButtonFactory {
    private static final Map<String, AbstractButtonFactory> map = new HashMap<>();
    
    static {
        map.put("single", new RadioButtonFactory());
        map.put("multi", new CheckBoxButtonFactory());
    }
    
    /**
     * Returns a proper AbstractButtonFactory
     * based on type of the test.
     * 
     * @param type test type, either "single" or "multi"
     * @return button factory
     */
    public static AbstractButtonFactory getFactory(String type) {
        return map.get(type);
    }
    
    /**
     * Returns a new button.
     * 
     * @return a button
     */
    public abstract AbstractButton make();
}
