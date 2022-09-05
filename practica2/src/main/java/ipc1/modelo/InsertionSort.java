package ipc1.modelo;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import ipc1.vista.GraficaBarra;
import ipc1.vista.VentanaPrincipal;

public class InsertionSort extends Thread implements Observer {

    double[] cantidades;
    String[] nombres;
    String[] encabezado;
    VentanaPrincipal vista = new VentanaPrincipal();
    GraficaBarra grafica = new GraficaBarra();
    String orden;

    public InsertionSort(double[] cantidades, String[] nombres, String[] encabezado, String orden,
            VentanaPrincipal vista, GraficaBarra grafica) {
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
                int pos;
                int pasos = 0;
                Tiempo tiempo = new Tiempo();
                tiempo.parar = false;
                tiempo.addObserver(this);
                Thread nuevo_tiempo = new Thread(tiempo);
                String titulo = vista.campoAlgoritmo.getText();
                vista.botonGraficar.setEnabled(false);
                vista.botonOrdenar.setEnabled(false);
                nuevo_tiempo.start();
                for (int i = 0; i < cantidades.length; i++) {
                    pos = i;
                    cantidad_aux = cantidades[i];
                    nombre_aux = nombres[i];
                    while ((pos > 0) && (cantidades[pos - 1] > cantidad_aux)) {
                        cantidades[pos] = cantidades[pos - 1];
                        nombres[pos] = nombres[pos - 1];
                        cantidades[pos - 1] = cantidad_aux;
                        nombres[pos - 1] = nombre_aux;
                        pos--;
                        grafica.graficar(titulo, cantidades, nombres, encabezado);
                        vista.panelGrafica.removeAll();
                        vista.panelGrafica.add(grafica);
                        pasos++;
                        vista.etiquetaPasos.setText(String.valueOf(pasos));
                        Thread.sleep(750);
                    }
                }
                tiempo.parar = true;
                if (pasos == 0) {
                    vista.botonGraficar.setEnabled(true);
                    vista.botonOrdenar.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "¡La grafica ya esta en\norden ascendente!");
                } else {
                    String nombre_grafica2 = grafica.generarGrafica();
                    reporte.insertarDatos("InsertionSort", "Ascendente");
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
                int pos;
                int pasos = 0;
                Tiempo tiempo = new Tiempo();
                tiempo.parar = false;
                tiempo.addObserver(this);
                Thread nuevo_tiempo = new Thread(tiempo);
                String titulo = vista.campoAlgoritmo.getText();
                vista.botonGraficar.setEnabled(false);
                vista.botonOrdenar.setEnabled(false);
                nuevo_tiempo.start();
                for (int i = 0; i < cantidades.length; i++) {
                    pos = i;
                    cantidad_aux = cantidades[i];
                    nombre_aux = nombres[i];
                    while ((pos > 0) && (cantidades[pos - 1] < cantidad_aux)) {
                        cantidades[pos] = cantidades[pos - 1];
                        nombres[pos] = nombres[pos - 1];
                        cantidades[pos - 1] = cantidad_aux;
                        nombres[pos - 1] = nombre_aux;
                        pos--;
                        grafica.graficar(titulo, cantidades, nombres, encabezado);
                        vista.panelGrafica.removeAll();
                        vista.panelGrafica.add(grafica);
                        pasos++;
                        vista.etiquetaPasos.setText(String.valueOf(pasos));
                        Thread.sleep(750);
                    }
                }
                tiempo.parar = true;
                if (pasos == 0) {
                    vista.botonGraficar.setEnabled(true);
                    vista.botonOrdenar.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "¡La grafica ya esta en\norden descendente!");
                } else {
                    String nombre_grafica2 = grafica.generarGrafica();
                    reporte.insertarDatos("InsertionSort", "Descendente");
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
