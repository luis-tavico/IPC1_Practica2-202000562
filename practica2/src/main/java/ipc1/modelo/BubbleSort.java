package ipc1.modelo;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import ipc1.vista.GraficaBarra;
import ipc1.vista.VentanaPrincipal;

public class BubbleSort extends Thread implements Observer {

    double[] cantidades;
    String[] nombres;
    String[] encabezado;
    VentanaPrincipal vista = new VentanaPrincipal();
    GraficaBarra grafica = new GraficaBarra();
    String orden;

    public BubbleSort(double[] cantidades, String[] nombres, String[] encabezado, String orden, VentanaPrincipal vista,
            GraficaBarra grafica) {
        this.cantidades = cantidades;
        this.nombres = nombres;
        this.encabezado = encabezado;
        this.vista = vista;
        this.orden = orden;
        this.grafica = grafica;
    }

    @Override
    public void run() {
        try {
            if (orden.equals("Ascendente")) {
                String nombre_grafica1 = grafica.generarGrafica();
                Reporte reporte = new Reporte(vista);
                reporte.insertarDatosDesordenados(cantidades, nombres, encabezado, nombre_grafica1);
                double cantidad_aux;
                String nombre_aux;
                int pasos = 0;
                Tiempo tiempo = new Tiempo();
                tiempo.parar = false;
                tiempo.addObserver(this);
                Thread nuevo_tiempo = new Thread(tiempo);
                String titulo = vista.campoAlgoritmo.getText();
                vista.botonGraficar.setEnabled(false);
                vista.botonOrdenar.setEnabled(false);
                nuevo_tiempo.start();
                for (int i = 0; i < cantidades.length - 1; i++) {
                    for (int j = 0; j < cantidades.length - 1; j++) {
                        if (cantidades[j] > cantidades[j + 1]) {
                            cantidad_aux = cantidades[j];
                            nombre_aux = nombres[j];
                            cantidades[j] = cantidades[j + 1];
                            nombres[j] = nombres[j + 1];
                            cantidades[j + 1] = cantidad_aux;
                            nombres[j + 1] = nombre_aux;
                            grafica.graficar(titulo, cantidades, nombres, encabezado);
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
                }
                tiempo.parar = true;
                if (pasos == 0) {
                    vista.botonGraficar.setEnabled(true);
                    vista.botonOrdenar.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "¡La grafica ya esta en\norden ascendente!");
                } else {
                    String nombre_grafica2 = grafica.generarGrafica();
                    reporte.insertarDatos("BubbleSort", "Ascendente");
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
                double cantidad_aux;
                String nombre_aux;
                int pasos = 0;
                Tiempo tiempo = new Tiempo();
                tiempo.parar = false;
                tiempo.addObserver(this);
                Thread nuevo_tiempo = new Thread(tiempo);
                String titulo = vista.campoAlgoritmo.getText();
                vista.botonGraficar.setEnabled(false);
                vista.botonOrdenar.setEnabled(false);
                nuevo_tiempo.start();
                for (int i = 0; i < cantidades.length - 1; i++) {
                    for (int j = 0; j < cantidades.length - 1; j++) {
                        if (cantidades[j] < cantidades[j + 1]) {
                            cantidad_aux = cantidades[j];
                            nombre_aux = nombres[j];
                            cantidades[j] = cantidades[j + 1];
                            nombres[j] = nombres[j + 1];
                            cantidades[j + 1] = cantidad_aux;
                            nombres[j + 1] = nombre_aux;
                            grafica.graficar(titulo, cantidades, nombres, encabezado);
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
                }
                tiempo.parar = true;
                if (pasos == 0) {
                    vista.botonGraficar.setEnabled(true);
                    vista.botonOrdenar.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "¡La grafica ya esta en\norden descendente!");
                } else {
                    String nombre_grafica2 = grafica.generarGrafica();
                    reporte.insertarDatos("BubbleSort", "Descendente");
                    reporte.insertarDatosOrdenados(cantidades, nombres, encabezado, nombre_grafica2);
                    reporte.generarArchivo();
                    JOptionPane.showMessageDialog(null, "¡Ordenamiento Finalizado!");
                    vista.botonGraficar.setEnabled(true);
                    vista.botonOrdenar.setEnabled(true);
                }
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        vista.etiquetaTemporizador.setText((String) arg);
    }

}
