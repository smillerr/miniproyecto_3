package org.example;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Ventana();
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        int opc = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Mensaje de confirmación", JOptionPane.OK_CANCEL_OPTION);
                        if (opc == 0) {
                            System.exit(0);
                        }
                    }
                });
            }
        });
    }
}