/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest;

import etest.gui.LoginPanel;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main class of Etest Application.
 * 
 * @author pmart
 */
public class Application {
    private static JFrame frame;
    public static final Logger logger = Logger.getGlobal();
    
    static {
        Level level = Level.FINE;
        
        Handler handler = new ConsoleHandler();
        handler.setLevel(level);
        
        logger.setLevel(level);
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        frame = new JFrame("Etest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        switchPanel(new LoginPanel());
        frame.setVisible(true);
    }
    
    /**
     * Switch to new panel
     * 
     * @param panel a new panel 
     */
    public static void switchPanel(JPanel panel) {
        frame.setContentPane(panel);
        frame.pack();
    }

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
