package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Esta es la clase ventana en donde declaramos lo que ocupa para su funcionamiento, botones, labels, pestanias, etc.
 * */
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
    private JButton restartButton;
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
    ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/imgs/img0.png"));

    private int emptyVector[] = new int[0];



    /**
     * Aqui se configura el titulo de la ventana, establece el panel de contenido y define el comportamiento de cierre de la ventana.
     * */
    public Ventana() {
        super("Juego de Parejas");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        /* desordenarImgsButton.addActionListener(new ActionListener() {
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


            }
        });
        */


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
        /*
        * restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        * */

    }

/**
 * Aqui realiza una serie de acciones despues de hacer una verificacion de los campos y selecciones de los componentes. Establece los valores del jugador, genera un vector de imagenes, muestra el tablero de imagenes, cambia la pestania seleccionada en un contenedor de pestanias, y muestra un cuadro de dialogo de advertencia si faltan datos.
 * */
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
            tabbedPane1.setEnabledAt(2,false);

            tf_nombreJugador.setText("");
            cb_tipoImagen.setSelectedIndex(0);
            cb_dificultad.setSelectedIndex(0);

            iniciarTemporizador();
        }
        else{
            JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos solicitados", "ADVERTENCIA", JOptionPane.OK_CANCEL_OPTION);
        }
    }
    /**
     * Genera un vector de enteros con valores aleatorios entre 1 y la mitad de la longitud del vector,asignando parejas de números en posiciones aleatorias del vector.
     * */
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

    /**
     * Muestra las imágenes correspondientes a los valores del vector en las etiquetas de la interfaz gráfica.
     * Crea objetos Tarjeta asociados a cada imagen y los agrega a una lista.
     * */
    public ArrayList<Tarjeta> displayImages(int someVector[], String categoria) {

        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        for(int i=0; i <someVector.length; i++) {
            try {
                JLabel labelInList = labels[i];
                labelsList.add(labelInList);
                labelsList.get(i).setVisible(true);
                labelsList.get(i).setEnabled(true);
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

    /**
     * Comprueba si dos identificadores de imágenes son iguales.
     * */
    public Boolean checkImages(int identifier1, int identifier2){
        player.setIntentos(intentos=intentos+1);
        if(identifier1==identifier2){
            return true;
        }
        else{
            player.setIntentosFallidos(fallidos=fallidos+1);
            return false;
        }
    }
    /**
     * Asigna funcionalidad a las etiquetas de las tarjetas para permitir su selección y comprobar si se han adivinado.
     * Controla el estado de selección y desbloquea la selección después de un tiempo determinado.
     * */
    public void cardsToGuess() {
        final boolean[] seleccionBloqueada = {false}; // Variable para controlar el estado de selección
        if(labelsList.size()==0){
            return;
        }
        for (int i = 0; i < labelsList.size(); i++) {
            int finalI = i;
            labelsList.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for(int j=0; j<1; j++){
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
                                            contadorDeClicks=1;
                                            intentos=0;
                                            fallidos=0;
                                            tarjeta1=0;
                                            tarjeta2=0;
                                            indexTarjeta1=0;
                                            indexTarjeta2=0;
                                            for(int i=0; i<labelsList.size(); i++){
                                                labelsList.get(i).setEnabled(true);
                                                tableroActual.get(i).setGuessed(false);
                                                labelsList.get(i).setIcon(defaultIcon);
                                            }
                                            tabbedPane1.setSelectedIndex(2);
                                            tabbedPane1.setEnabledAt(0,false);
                                            tabbedPane1.setEnabledAt(1,false);

                                            String puntaje = "\n" +
                                                    "\nTiempo utilizado: " + actualizarTemporizador() +
                                                    "\nNombre jugador: " + player.getNombre() +
                                                    "\nDificultad: " + player.getDificultad() +
                                                    "\nCategoría: " + player.getCategoria() +
                                                    "\nNúmero de intentos: " + player.getIntentos() +
                                                    "\nNúmero de intentos fallidos: " + player.getIntentosFallidos();
                                            puntajeTextArea.append(puntaje);
                                        }
                                    } else {
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
                }
            });
        }
    }
    /**
     * Inicia un temporizador que cuenta los segundos transcurridos.
     * El temporizador se ejecuta cada segundo y actualiza el contador de segundos transcurridos.
     * */
    private void iniciarTemporizador() {
        segundosTranscurridos = 0;
        temporizador = new Timer();
        temporizador.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                segundosTranscurridos++;
            }
        }, 1000, 1000);
    }

    /**
     * Actualiza el temporizador y devuelve el tiempo transcurrido en un formato "MM:SS".
     * Calcula los minutos y segundos a partir de los segundos transcurridos y los formatea en un String.
     * El tiempo transcurrido se muestra en un componente de tu elección, y se devuelve el tiempo formateado.
     * */
    private String actualizarTemporizador() {
        int minutos = segundosTranscurridos / 60;
        int segundos = segundosTranscurridos % 60;
        String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);
        // Mostrar el tiempo transcurrido en un componente de tu elección
        // Ejemplo:
        return tiempoFormateado;
    }

    /**
     * Reinicia el juego, restaurando el estado inicial.
     * Elimina todas las tarjetas del tablero y oculta y deshabilita todos los componentes de las etiquetas.
     * Habilita el primer panel de pestañas y establece su índice seleccionado en 0.
     * Deshabilita el tercer panel de pestañas.
     */
    public void restartGame(){
        System.out.println(tableroActual.size());
        for (int i = tableroActual.size() - 1; i >= 0; i--) {
            tableroActual.remove(i);
        }
        for (int i = labelsList.size() - 1; i >= 0; i--) {
            labelsList.get(i).setVisible(false);
            labelsList.get(i).setEnabled(false);
            labelsList.remove(i);
        }
        tabbedPane1.setEnabledAt(0,true);
        tabbedPane1.setSelectedIndex(0);
        tabbedPane1.setEnabledAt(2,false);
    }

}
