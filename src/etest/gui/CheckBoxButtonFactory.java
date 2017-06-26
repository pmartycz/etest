/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.gui;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;

/**
 * Concrete factory producing question option checkboxes.
 * 
 * @author pmart
 */
public class CheckBoxButtonFactory extends AbstractButtonFactory {
    
    @Override
    public AbstractButton make() {
        AbstractButton btn = new JCheckBox();
        
        return btn;
    }
}
