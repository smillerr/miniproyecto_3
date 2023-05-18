package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Ventana extends JFrame {
    //Declaracion de variables para la pestaña del juego de las parejas
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
    private JTabbedPane tabbedPane1;
    private JLabel[] labels = {
            img1, img2, img3, img4, img5, img6, img7, img8,
            img9, img10, img11, img12, img13, img14, img15, img16,
    };

    //Declaracion de variables para la pestaña del menu del juego
    private JTextField tf_nombreJugador;
    private JComboBox cb_tipoImagen;
    private JComboBox cb_dificultad;
    private JButton btn_jugar;
    private JButton btn_score;
    private JPanel juegoPanel;
    private JPanel inicioPanel;
    private Jugador player;
    int tananio=2;
    int[] vector = new int[16];

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

    /**
     * listener del boton de jugar
     */
        btn_jugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugar();
            }
        });
        /**
         * listener del boton de puntajes
         */
        btn_score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //registerUser();
            }
        });
    }


    public void jugar(){
        //
        if(!tf_nombreJugador.getText().equals("") && !cb_tipoImagen.getSelectedItem().equals("") && !cb_dificultad.getSelectedItem().equals("")){
            player = new Jugador();
            player.setNombre(tf_nombreJugador.getText());
            player.setCategoria((String) cb_tipoImagen.getSelectedItem());
            player.setDificultad((String) cb_dificultad.getSelectedItem());

            System.out.print(player.getNombre());

            tabbedPane1.setSelectedIndex(1);
            inicioPanel.setEnabled(false);
            return;
        }

        JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos solicitados", "ADVERTENCIA", JOptionPane.OK_CANCEL_OPTION);

    }
    public void generarVector() {
        // Inicializa el vector con valor 0
        Arrays.fill(vector, 0);
        Random random = new Random();

        // Agrega los numeros entre 1 y 8 en dos posiciones diferentes de las 16 disponibles en el arreglo
        for (int i = 1; i <= 8; i++) {
            for (int j = 0; j < 2; j++) {
                int posicion;
                // El ciclo se ejecuta mientras haya un valor distinto de cero en la posición del arreglo
                // Esto indica que debe encontrar una posición en cero para asignar el valor de i
                do {
                    posicion = random.nextInt(16);
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
