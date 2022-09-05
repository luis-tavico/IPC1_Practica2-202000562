package ipc1.modelo;

import java.util.Observable;

public class Tiempo extends Observable implements Runnable {

    int minuto = 0, segundo = 0, milisegundo = 0;
    public boolean parar = false;

    public Tiempo() {
    }

    @Override
    public void run() {
        String tiempo;
        try {
            while (parar == false) {
                tiempo = "";
                if (minuto < 10) {
                    tiempo += "0" + minuto;
                } else {
                    tiempo += minuto;
                }
                tiempo += ":";
                if (segundo < 10) {
                    tiempo += "0" + segundo;
                } else {
                    tiempo += segundo;
                }
                tiempo += ":";
                if (milisegundo < 10) {
                    tiempo += "0" + milisegundo;
                } else if (milisegundo >= 10 && milisegundo < 100) {
                    tiempo += "" + milisegundo;
                } else if (milisegundo >= 100) {
                    tiempo += milisegundo;
                }

                this.setChanged();
                this.notifyObservers(tiempo);
                this.clearChanged();

                milisegundo++;
                Thread.sleep(8);

                if (milisegundo == 100) {
                    segundo++;
                    milisegundo = 0;
                    if (segundo == 60) {
                        minuto++;
                        segundo = 0;
                        if (minuto == 60) {
                            minuto = 0;
                        }
                    }
                }
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        minuto = 0;
        segundo = 0;
        milisegundo = 0;
    }

}