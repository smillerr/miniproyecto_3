package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

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
    private JPanel puntajePanel;
    private JTextArea puntajeTextArea;
    private Jugador player;
    int vectorHard[] = new int[16];

    int vectorMedium[] = new int[8];

    int vectorEasy[] = new int[4];
    int fallidos = 0;
    int tarjeta1 = 0;
    int indexTarjeta1=0;
    int tarjeta2 = 0;
    int indexTarjeta2=0;
    int contadorDeClicks = 1;
    int intentos = 0;

     private ArrayList<Tarjeta> tableroActual = new ArrayList<>();

     private ArrayList<JLabel> labelsList = new ArrayList<>();

    private Timer temporizador;
    private int segundosTranscurridos;






    public Ventana() {
        super("Juego de Parejas");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        desordenarImgsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //generarVector();
                //displayImages();
                 /**
                if(player.getDificultad().contains("16")){
                    generarVector(vectorHard);
                    for(int i=0; i<tableroActual.size(); i++){
                        tableroActual.remove(i);
                    }
                    tableroActual = displayImages(vectorHard, player.getCategoria());
                }
                 **/
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
    }


    public void jugar(){

        //
        if(!tf_nombreJugador.getText().equals("") && !cb_tipoImagen.getSelectedItem().equals("") && !cb_dificultad.getSelectedItem().equals("")){

            player = new Jugador();
            String dificultad = (String) cb_dificultad.getSelectedItem();
            int indexCategoria = cb_tipoImagen.getSelectedIndex();
            String categoria;

            if(indexCategoria == 1){
                categoria="rocket";
            } else if(indexCategoria == 2){
                categoria="counter";
            }
            else{
                categoria="mc";
            }

            player.setNombre(tf_nombreJugador.getText());
            player.setCategoria(categoria);
            player.setDificultad(dificultad);

            if(dificultad.contains("4")){
                generarVector(vectorEasy);
                tableroActual=displayImages(vectorEasy, categoria);
            }
            else if(dificultad.contains("8")){
                generarVector(vectorMedium);
                tableroActual=displayImages(vectorMedium, categoria);
            }
            else if (dificultad.contains("16")){
                generarVector(vectorHard);
                tableroActual=displayImages(vectorHard, categoria);
            }

            tabbedPane1.setSelectedIndex(1);
            tabbedPane1.setEnabledAt(0,false);

            iniciarTemporizador();
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

    public ArrayList<Tarjeta> displayImages(int someVector[], String categoria) {
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();

        for(int i=0; i < someVector.length; i++) {
            try {
                JLabel labelInList = labels[i];
                labelsList.add(labelInList);
                //labelsList.add()
                ImageIcon icon = new ImageIcon(getClass().getResource("/imgs/img0.png"));
                labels[i].setIcon(icon);
                Tarjeta slot = new Tarjeta();
                slot.setCustomImage("/imgs/"+categoria+someVector[i]+".png");
                slot.setGuessed(false);
                slot.setIdentifier(someVector[i]);
                tarjetas.add(slot);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cardsToGuess();
        return tarjetas;
    }

    public Boolean checkImages(int identifier1, int identifier2){
        player.setIntentos(intentos++);
        if(identifier1==identifier2){
            return true;
        }
        else{
            player.setIntentosFallidos(fallidos++);
            return false;
        }
    }
    public void cardsToGuess() {
        final boolean[] seleccionBloqueada = {false}; // Variable para controlar el estado de selección

        for (int i = 0; i < labelsList.size(); i++) {
            int finalI = i;
            labelsList.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    //Variable para verificar si la tarjeta en el tablero ya fue adivinada
                    if(tableroActual.get(finalI).isGuessed()){
                        return;
                    }
                    // Verificar si la selección está bloqueada
                    if (seleccionBloqueada[0]) {
                        return; // Salir si la selección está bloqueada
                    }

                    ImageIcon customImage = new ImageIcon(getClass().getResource(tableroActual.get(finalI).getCustomImage()));
                    labelsList.get(finalI).setIcon(customImage);

                    if (contadorDeClicks % 2 == 1) {
                        tarjeta1 = tableroActual.get(finalI).getIdentifier();
                        indexTarjeta1 = finalI;
                    }
                    if (contadorDeClicks % 2 == 0) {
                        tarjeta2 = tableroActual.get(finalI).getIdentifier();
                        indexTarjeta2 = finalI;
                    }

                    if (contadorDeClicks % 2 == 0) {
                        // Bloquear la selección
                        seleccionBloqueada[0] = true;

                        Timer temporizador = new Timer();
                        temporizador.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Boolean match = checkImages(tarjeta1, tarjeta2);
                                if (match) {
                                    labelsList.get(indexTarjeta1).setEnabled(false);
                                    labelsList.get(indexTarjeta2).setEnabled(false);
                                    tableroActual.get(indexTarjeta1).setGuessed(true);
                                    tableroActual.get(indexTarjeta2).setGuessed(true);
                                    System.out.println(player.getIntentos());
                                    System.out.println(player.getIntentosFallidos());
                                        if((player.getIntentos())-(player.getIntentosFallidos())==labelsList.size()/2){
                                            contadorDeClicks=0;
                                            intentos=0;
                                            fallidos=0;
                                            tabbedPane1.setSelectedIndex(2);
                                            tabbedPane1.setEnabledAt(0,false);
                                            tabbedPane1.setEnabledAt(1,false);
                                            String puntaje = "PUNTAJE" +
                                                    "\nTiempo utilizado: " + temporizador +
                                                    "\nNúmero de intentos: " + player.getIntentos() +
                                                    "\nNúmero de intentos fallidos: " + player.getIntentosFallidos();
                                            puntajeTextArea.setText(puntaje);
                                        }
                                } else {
                                    ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/imgs/img0.png"));
                                    labelsList.get(indexTarjeta1).setIcon(defaultIcon);
                                    labelsList.get(indexTarjeta2).setIcon(defaultIcon);
                                }

                                // Desbloquear la selección después de que el temporizador haya terminado
                                seleccionBloqueada[0] = false;
                            }
                        }, 1000); // Esperar 1 segundo (1000 milisegundos) antes de ejecutar el código

                    }
                    contadorDeClicks++;
                }
            });
        }
    }
    private void iniciarTemporizador() {
        segundosTranscurridos = 0;
        temporizador = new Timer();
        temporizador.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                segundosTranscurridos++;
                actualizarTemporizador();
            }
        }, 1000, 1000);
    }

    private void actualizarTemporizador() {
        int minutos = segundosTranscurridos / 60;
        int segundos = segundosTranscurridos % 60;
        String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);
        // Mostrar el tiempo transcurrido en un componente de tu elección
        // Ejemplo:
        setTitle("Juego de Parejas - Tiempo: " + tiempoFormateado);
    }
}
