package ipc1.modelo;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import ipc1.vista.GraficaBarra;
import ipc1.vista.VentanaPrincipal;

public class QuickSort extends Thread implements Observer {

    double[] cantidades;
    String[] nombres;
    String[] encabezado;
    VentanaPrincipal vista = new VentanaPrincipal();
    GraficaBarra grafica = new GraficaBarra();
    String orden;
    int pasos = 0;

    public QuickSort(double[] cantidades, String[] nombres, String[] encabezado, String orden, VentanaPrincipal vista,
            GraficaBarra grafica) {
        this.cantidades = cantidades;
        this.nombres = nombres;
        this.encabezado = encabezado;
        this.vista = vista;
        this.orden = orden;
        this.grafica = grafica;
    }

    public void run() {
        if (orden.equals("Ascendente")) {
            String nombre_grafica1 = grafica.generarGrafica();
            Reporte reporte = new Reporte(vista);
            reporte.insertarDatosDesordenados(cantidades, nombres, encabezado, nombre_grafica1);
            Tiempo tiempo = new Tiempo();
            tiempo.parar = false;
            tiempo.addObserver(this);
            Thread nuevo_tiempo = new Thread(tiempo);
            vista.botonGraficar.setEnabled(false);
            vista.botonOrdenar.setEnabled(false);
            nuevo_tiempo.start();
            quickSortA(cantidades, nombres, 0, cantidades.length - 1);
            tiempo.parar = true;
            if (pasos == 0) {
                vista.botonGraficar.setEnabled(true);
                vista.botonOrdenar.setEnabled(true);
                JOptionPane.showMessageDialog(null, "¡La grafica ya esta en\norden ascendente!");
            } else {
                String nombre_grafica2 = grafica.generarGrafica();
                reporte.insertarDatos("QuickSort", "Ascendente");
                reporte.insertarDatosOrdenados(cantidades, nombres, encabezado, nombre_grafica2);
                reporte.generarArchivo();
                JOptionPane.showMessageDialog(null, "¡Ordenamiento Finalizado!");
                vista.botonGraficar.setEnabled(true);
                vista.botonOrdenar.setEnabled(true);
            }
        } else if (orden.equals("Descendente")) {
            String nombre_grafica1 = grafica.generarGrafica();
            Reporte reporte = new Reporte(vista);
            reporte.insertarDatosDesordenados(cantidades, nombres, encabezado, nombre_grafica1);
            Tiempo tiempo = new Tiempo();
            tiempo.parar = false;
            tiempo.addObserver(this);
            Thread nuevo_tiempo = new Thread(tiempo);
            vista.botonGraficar.setEnabled(false);
            vista.botonOrdenar.setEnabled(false);
            nuevo_tiempo.start();
            quickSortD(cantidades, nombres, 0, cantidades.length - 1);
            tiempo.parar = true;
            if (pasos == 0) {
                vista.botonGraficar.setEnabled(true);
                vista.botonOrdenar.setEnabled(true);
                JOptionPane.showMessageDialog(null, "¡La grafica ya esta en\norden descendente!");
            } else {
                String nombre_grafica2 = grafica.generarGrafica();
                reporte.insertarDatos("QuickSort", "Descendente");
                reporte.insertarDatosOrdenados(cantidades, nombres, encabezado, nombre_grafica2);
                reporte.generarArchivo();
                JOptionPane.showMessageDialog(null, "¡Ordenamiento Finalizado!");
                vista.botonGraficar.setEnabled(true);
                vista.botonOrdenar.setEnabled(true);
            }
        }
    }

    public void quickSortA(double cantidades[], String[] nombres, int inicio, int fin) {
        try {
            if (inicio >= fin) {
                return;
            }
            double pivote = cantidades[inicio];
            int elemIzq = inicio + 1;
            int elemDer = fin;
            while (elemIzq <= elemDer) {
                while (elemIzq <= fin && cantidades[elemIzq] < pivote) {
                    elemIzq++;
                }
                while (elemDer > inicio && cantidades[elemDer] >= pivote) {
                    elemDer--;
                }
                if (elemIzq < elemDer) {
                    double temp = cantidades[elemIzq];
                    String nom_temp = nombres[elemIzq];
                    cantidades[elemIzq] = cantidades[elemDer];
                    nombres[elemIzq] = nombres[elemDer];
                    cantidades[elemDer] = temp;
                    nombres[elemDer] = nom_temp;
                    grafica.graficar(vista.campoAlgoritmo.getText(), cantidades, nombres, encabezado);
                    vista.panelGrafica.removeAll();
                    vista.panelGrafica.add(grafica);
                    pasos++;
                    if (pasos < 10) {
                        vista.etiquetaPasos.setText("0" + String.valueOf(pasos));
                    } else {
                        vista.etiquetaPasos.setText(String.valueOf(pasos));
                    }
                    Thread.sleep(750);
                }
            }
            if (elemDer > inicio) {
                double temp = cantidades[inicio];
                String nom_temp = nombres[inicio];
                cantidades[inicio] = cantidades[elemDer];
                nombres[inicio] = nombres[elemDer];
                cantidades[elemDer] = temp;
                nombres[elemDer] = nom_temp;
                grafica.graficar(vista.campoAlgoritmo.getText(), cantidades, nombres, encabezado);
                vista.panelGrafica.removeAll();
                vista.panelGrafica.add(grafica);
                pasos++;
                if (pasos < 10) {
                    vista.etiquetaPasos.setText("0" + String.valueOf(pasos));
                } else {
                    vista.etiquetaPasos.setText(String.valueOf(pasos));
                }
                Thread.sleep(750);
            }
            quickSortA(cantidades, nombres, inicio, elemDer - 1);
            quickSortA(cantidades, nombres, elemDer + 1, fin);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int particion(double cantidades[], String[] nombres, int inicio, int fin) {
        double valor = cantidades[inicio];
        int i = inicio - 1;
        int j = fin + 1;
        double cant_temp;
        String nom_temp;
        try {
            do {
                do {
                    j--;
                } while (valor > cantidades[j]);

                do {
                    i++;
                } while (valor < cantidades[i]);

                if (i < j) {
                    cant_temp = cantidades[i];
                    nom_temp = nombres[i];
                    cantidades[i] = cantidades[j];
                    nombres[i] = nombres[j];
                    cantidades[j] = cant_temp;
                    nombres[j] = nom_temp;
                    grafica.graficar(vista.campoAlgoritmo.getText(), cantidades, nombres, encabezado);
                    vista.panelGrafica.removeAll();
                    vista.panelGrafica.add(grafica);
                    pasos++;
                    if (pasos < 10) {
                        vista.etiquetaPasos.setText("0" + String.valueOf(pasos));
                    } else {
                        vista.etiquetaPasos.setText(String.valueOf(pasos));
                    }
                    Thread.sleep(750);
                }
            } while (i < j);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        return j;
    }

    public void quickSortD(double cantidades[], String[] nombres, int inicio, int fin) {
        int middle;
        if (inicio < fin) {
            middle = particion(cantidades, nombres, inicio, fin);
            quickSortD(cantidades, nombres, inicio, middle);
            quickSortD(cantidades, nombres, middle + 1, fin);
        }
        return;
    }

    @Override
    public void update(Observable o, Object arg) {
        vista.etiquetaTemporizador.setText((String) arg);
    }

}
