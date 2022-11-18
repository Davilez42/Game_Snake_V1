import javax.lang.model.util.ElementScanner6;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GUI_Snake extends JFrame implements Runnable{

    Container contenedor;
    String matriz[][] = new String[20][20];
    Manejador manejadorEvent = new Manejador();
    boolean arriba = false, abajo = false, izquierda = false, derecha = false, comio = false, muerte = false;;
    int i=0, j=0, i_ant, j_ant;
    ArrayList<Integer> coordenadas_ant = new ArrayList<>();
    int puntos = 0, puntaje_Max = 0;
    int b = 3, n = 4;

    public GUI_Snake() {
        super("Snake");
        contenedor = getContentPane();
        setResizable(false);
        setSize(825, 900);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(manejadorEvent);

        limpiarMatriz();
        matriz[0][0] = "x";
       

        coordenadas_ant.add(0);
        coordenadas_ant.add(0);

    }
    @Override
    public void run() {
        while(true){
            
            try {
                System.out.println("sub proceso");
                System.out.println("ESTADOS: arriba:"+arriba + " abajo:"+abajo + " izq:"+izquierda + " der:"+derecha);
                repaint();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
       
    }


    public void limpiarMatriz() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                matriz[i][j] = "";
            }

        }
        comidaRandom();

    }

    public void comidaRandom() {

        int i1 = (int) (2 + Math.random() * 18);
        int j1 = (int) (2 + Math.random() * 18);

        if (matriz[i1][j1] == "") {
            matriz[i1][j1] = "*";

        } else {
            comidaRandom();
        }

    }

    public void paint(Graphics e) {
        if (puntos != 60) {
            e.setColor(Color.white);
            e.drawString("Puntos " + puntos, 700, 100);
            e.drawString("Puntaje Max " + puntaje_Max, 600, 100);

            i_ant = i;
            j_ant = j;
           // if(i<20 && j<20){
            coordenadas_ant.add(i_ant);
            coordenadas_ant.add(j_ant);
            //}

            if (arriba) {

                i--;
                if (i >= 0 && i < 20) {
                    if (matriz[i][j] == "*") {
                        comio = true;
                    }

                    if (matriz[i][j].equals("x")) {

                        muerte = true;
                    } else {
                        muerte = false;
                    }

                    matriz[i][j] = "x";
                } else {
                    if (i < 0) {
                        i = 20 + i;
                        if (matriz[i][j] == "*") {
                            comio = true;
                        }
                        matriz[i][j] = "x";
                    }

                }

              

            }
            if (abajo) {
                i++;
                if (i >= 0 && i < 20) {
                    if (matriz[i][j] == "*") {
                        comio = true;
                    }
                    if (matriz[i][j].equals("x")) {

                        muerte = true;
                    } else {
                        muerte = false;
                    }
                    matriz[i][j] = "x";
                } else {
                    if (i >= 20) {
                        i = 0;
                        if (matriz[i][j] == "*") {
                            comio = true;
                        }

                        matriz[i][j] = "x";
                    }

                }

            }
            if (izquierda) {
                j--;

                if (j >= 0 && j < 20) {
                    if (matriz[i][j] == "*") {
                        comio = true;
                    }
                    if (matriz[i][j].equals("x")) {

                        muerte = true;
                    } else {
                        muerte = false;
                    }
                    matriz[i][j] = "x";
                } else {
                    if (j < 0) {
                        j = 20 + j;
                        if (matriz[i][j] == "*") {
                            comio = true;
                        }
                        matriz[i][j] = "x";
                    }

                }

            }

            if (derecha) {
                j++;
                if (j >= 0 && j < 20) {
                    if (matriz[i][j] == "*") {
                        comio = true;
                    }
                    if (matriz[i][j].equals("x")) {

                        muerte = true;
                    } else {
                        muerte = false;
                    }
                    matriz[i][j] = "x";
                } else {
                    if (j >= 20) {
                        j = 0;
                        if (matriz[i][j] == "*") {
                            comio = true;
                        }
                        matriz[i][j] = "x";
                    }

                }

            }
            if (muerte) {
                limpiarMatriz();
                coordenadas_ant.clear();
                b = 3;
                n = 4;
                if (puntos > puntaje_Max) {
                    puntaje_Max = puntos;

                }
                puntos = 0;
                coordenadas_ant.add(0);
                coordenadas_ant.add(0);
                JOptionPane.showMessageDialog(null, "Perdistes", "", JOptionPane.WARNING_MESSAGE);

            } else {

                if (comio) {

                    b += 2;
                    n += 2;
                    comidaRandom();
                    puntos++;

                    comio = false;

                } else {

                    int yaux = coordenadas_ant.size() - b;
                    int xaux = coordenadas_ant.size() - n;

                    int xult = coordenadas_ant.get(xaux);
                    int yult = coordenadas_ant.get(yaux);
                    matriz[xult][yult] = "";

                }

                int x = 0, y = 30;

                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {

                        if (matriz[i][j].equals("")) {
                            e.setColor(Color.black);
                            e.fillRect(x, y, 40, 40);
                        } else {
                            if (matriz[i][j].equals("x")) {
                                e.setColor(Color.white);
                                e.fillRect(x, y, 40, 40);
                            }
                            if (matriz[i][j].equals("*")) {
                                e.setColor(Color.black);
                                e.fillRect(x, y, 40, 40);
                                e.setColor(Color.red);
                                e.fillRect(x + 9, y + 9, 20, 20);
                            }

                        }
                        x += 40;

                    }
                    x = 0;
                    y += 40;
                }

            }
            

        } else {
            int x = 0, y = 30;

            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {

                    int r = (int) (Math.random() * 255);
                    int g = (int) (Math.random() * 255);
                    int b = (int) (Math.random() * 255);
                    e.setColor(new Color(r, g, b));
                    e.fillRect(x, y, 40, 40);

                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    x += 40;

                }
                x = 0;
                y += 40;
            }
            JOptionPane.showMessageDialog(contenedor, "GANASTES");
            limpiarMatriz();
            coordenadas_ant.clear();
            b = 3;
            n = 4;
            if (puntos > puntaje_Max) {
                puntaje_Max = puntos;

            }
            puntos = 0;
            coordenadas_ant.add(0);
            coordenadas_ant.add(0);
            x = 0;
            y = 30;
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {

                    if (matriz[i][j].equals("")) {
                        e.setColor(Color.black);
                        e.fillRect(x, y, 40, 40);
                    } else {
                        if (matriz[i][j].equals("x")) {
                            e.setColor(Color.white);
                            e.fillRect(x, y, 40, 40);
                        }
                        if (matriz[i][j].equals("*")) {
                            e.setColor(Color.black);
                            e.fillRect(x, y, 40, 40);
                            e.setColor(Color.red);
                            e.fillRect(x + 9, y + 9, 20, 20);
                        }

                    }
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    x += 40;

                }
                x = 0;
                y += 40;
            }

        }
        e.setColor(Color.white);
            e.drawString("Puntos " + puntos, 700, 100);
            e.drawString("Puntaje Max " + puntaje_Max, 600, 100);

    }

    private class Manejador implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyPressed(KeyEvent e) {

            System.out.println(e.getKeyCode());
            if (e.getKeyCode() == 38) {// arriba
                arriba = true;
              
            } else {
                arriba = false;
            }
            if (e.getKeyCode() == 40) {// abajo
                abajo = true;
               
            } else {
                abajo = false;
            }
            if (e.getKeyCode() == 37) {// izquierda
                izquierda = true;
               

            } else {
                izquierda = false;

            }

            if (e.getKeyCode() == 39) {// derecha
                derecha = true;
           
            } else {
                derecha = false;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub

        }

    }

    public static void main(String[] args) {
        GUI_Snake s = new GUI_Snake();
        Thread n  = new Thread(s);
        n.start();
    }

    
}
