package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.*;

public class Ventana extends JFrame {
    private JPanel panel1;
    private JLabel img1;
    private JLabel img2;
    private JLabel img3;
    private JLabel img4;
    private JLabel img5;
    private JLabel img6;
    private JLabel img7;
    private JLabel img8;
    private JLabel img9;
    private JLabel img10;
    private JLabel img11;
    private JLabel img12;
    private JLabel img13;
    private JLabel img14;
    private JLabel img15;
    private JLabel img16;
    private JButton desordenarImgsButton;
    private JLabel[] labels = {
            img1, img2, img3, img4, img5, img6, img7, img8,
            img9, img10, img11, img12, img13, img14, img15, img16,
    };
    int[] vector = new int[10];

    public Ventana() {
        super("Juego de Parejas");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        desordenarImgsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarVector();
                displayImages();
            }
        });

        generarVector();
        displayImages();
    }

    public void generarVector() {
        // Inicializa el vector con valor 0
        Arrays.fill(vector, 0);
        Random random = new Random();

        // Agrega los numeros entre 1 y 8 en dos posiciones diferentes de las 16 disponibles en el arreglo
        for (int i = 1; i <= 5; i++) {
            for (int j = 0; j < 2; j++) {
                int posicion;
                // El ciclo se ejecuta mientras haya un valor distinto de cero en la posición del arreglo
                // Esto indica que debe encontrar una posición en cero para asignar el valor de i
                do {
                    posicion = random.nextInt(10);
                } while (vector[posicion] != 0);
                vector[posicion] = i;
            }
        }

         System.out.println("El vector resultante es: " + Arrays.toString(vector));
    }

    public void displayImages() {
        for(int i=0; i < vector.length; i++) {
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource("/imgs/landscape" + vector[i] + ".jpg"));
                labels[i].setIcon(icon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
