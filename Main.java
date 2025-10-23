package com.studentassetsharing;

import com.formdev.flatlaf.FlatDarkLaf;
import com.studentassetsharing.ui.auth.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        // Apply the FlatLaf dark theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        // Run the GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}