package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    int vectorSize=0;
    int vectorHard[] = new int[16];

    int vectorMedium[] = new int[8];

    int vectorEasy[] = new int[4];

    public Ventana() {
        super("Juego de Parejas");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        desordenarImgsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //generarVector();
                //displayImages();
            }
        });


        //displayImages();

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
        for(JLabel card : labels){
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println("I got clicked");
                }
            });
        }

    }


    public void jugar(){
        //
        if(!tf_nombreJugador.getText().equals("") && !cb_tipoImagen.getSelectedItem().equals("") && !cb_dificultad.getSelectedItem().equals("")){
            player = new Jugador();
            String dificultad = (String) cb_dificultad.getSelectedItem();
            String categoria = (String) cb_tipoImagen.getSelectedItem();

            player.setNombre(tf_nombreJugador.getText());
            player.setCategoria(categoria);
            player.setDificultad(dificultad);

            if(dificultad.contains("4")){
                generarVector(vectorEasy);
                displayImages(vectorEasy);
            }
            else if(dificultad.contains("8")){
                generarVector(vectorMedium);
                displayImages(vectorMedium);
            }
            else if (dificultad.contains("16")){
                generarVector(vectorHard);
                displayImages(vectorHard);
            }

            System.out.print(player.getNombre());

            tabbedPane1.setSelectedIndex(1);
            inicioPanel.setEnabled(false);

        }
        else{
            JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos solicitados", "ADVERTENCIA", JOptionPane.OK_CANCEL_OPTION);
        }

    }
    public void generarVector(int someVector[]) {
        // Inicializa el vector con valor 0
        Arrays.fill(someVector, 0);
        Random random = new Random();

        // Agrega los numeros entre 1 y 8 en dos posiciones diferentes de las 16 disponibles en el arreglo
        for (int i = 1; i <= someVector.length/2; i++) {
            for (int j = 0; j < 2; j++) {
                int posicion;
                // El ciclo se ejecuta mientras haya un valor distinto de cero en la posición del arreglo
                // Esto indica que debe encontrar una posición en cero para asignar el valor de i
                do {
                    posicion = random.nextInt(someVector.length);
                } while (someVector[posicion] != 0);
                someVector[posicion] = i;
            }
        }

         System.out.println("El vector resultante es: " + Arrays.toString(someVector));
    }

    public void displayImages(int someVector[]) {
        for(int i=0; i < someVector.length; i++) {
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource("/imgs/landscape" + someVector[i] + ".jpg"));
                labels[i].setIcon(icon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
