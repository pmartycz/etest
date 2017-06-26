/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.gui;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 * Concrete factory producing question option radio buttons.
 * 
 * @author pmart
 */
public class RadioButtonFactory extends AbstractButtonFactory {
    private final ButtonGroup group = new ButtonGroup();
    
    @Override
    public AbstractButton make() {
        AbstractButton btn = new JRadioButton();
        group.add(btn);
        
        return btn;
    }
}
