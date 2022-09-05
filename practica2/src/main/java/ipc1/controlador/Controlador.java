package ipc1.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import ipc1.modelo.BubbleSort;
import ipc1.modelo.InsertionSort;
import ipc1.modelo.QuickSort;
import ipc1.vista.GraficaBarra;
import ipc1.vista.VentanaPrincipal;

public class Controlador implements ActionListener {

    VentanaPrincipal vista = new VentanaPrincipal();
    String[] encabezado = new String[0];
    String[] nombres = new String[0];
    double[] cantidades = new double[0];
    GraficaBarra grafica = new GraficaBarra();

    public Controlador(VentanaPrincipal vista) {
        this.vista = vista;
        vista.botonExaminar.addActionListener(this);
        vista.botonGraficar.addActionListener(this);
        vista.botonOrdenar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.botonExaminar) {
            vista.explorador.showOpenDialog(null);
            File archivo = vista.explorador.getSelectedFile();
            if (archivo != null) {
                vista.campoRuta.setText(archivo.getAbsolutePath());
            }
        } else if (e.getSource() == vista.botonGraficar) {
            if (!vista.campoRuta.getText().equals("")) {
                if (!vista.campoAlgoritmo.getText().equals("")) {
                    int numLinea = 0;
                    String titulos = "";
                    String valores = "";
                    try {
                        FileReader lectura = new FileReader(vista.campoRuta.getText());
                        BufferedReader archivoLeer = new BufferedReader(lectura);
                        String lineaLeida;
                        while ((lineaLeida = archivoLeer.readLine()) != null) {
                            if (numLinea == 0) {
                                titulos += lineaLeida;
                                numLinea++;
                            } else {
                                valores += lineaLeida + ",";
                                numLinea++;
                            }
                        }
                        archivoLeer.close();
                        lectura.close();
                        String[] datos = valores.split(",");
                        encabezado = titulos.split(",");
                        nombres = new String[numLinea - 1];
                        ;
                        cantidades = new double[numLinea - 1];
                        int posicion = 0;
                        //////////////////////////////////////////////////////
                        for (int i = 0; i < nombres.length; i++) {
                            nombres[i] = datos[posicion];
                            posicion += 2;
                        }
                        //////////////////////////////////////////////////////
                        posicion = 0;
                        for (int i = 0; i < cantidades.length; i++) {
                            cantidades[i] = Double.parseDouble(datos[posicion + 1]);
                            posicion += 2;
                        }
                        //////////////////////////////////////////////////////
                        grafica.graficar(vista.campoAlgoritmo.getText(), cantidades, nombres, encabezado);
                        vista.panelGrafica.removeAll();
                        vista.panelGrafica.add(grafica);
                        vista.panelGrafica.revalidate();
                        vista.panelGrafica.repaint();
                        //////////////////////////////////////////////////////
                        vista.etiquetaTemporizador.setText("00:00:00");
                        vista.etiquetaPasos.setText("00");

                    } catch (FileNotFoundException ea) {
                        JOptionPane.showMessageDialog(null, "El sistema no puede encontrar\nel archivo especificado.",
                                "Error", 0);
                    } catch (IOException ea) {
                        JOptionPane.showMessageDialog(null, ea.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡Ingrese un titulo para la grafica!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Complete el campo ruta!");
            }
        } else if (e.getSource() == vista.botonOrdenar) {
            if (vista.botonRadioAscendente.isSelected()) {
                if (vista.botonRadioBubblesort.isSelected()) {
                    if (cantidades.length == 0) {
                        JOptionPane.showMessageDialog(null, "¡Genere una grafica primero!");
                    } else if (vista.campoAlgoritmo.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "¡Ingrese un titulo para la grafica!");
                    } else {
                        BubbleSort hilo = new BubbleSort(cantidades, nombres, encabezado,
                                vista.botonRadioAscendente.getText(), vista, grafica);
                        hilo.start();
                    }
                } else if (vista.botonRadioInsertionsort.isSelected()) {
                    if (cantidades.length == 0) {
                        JOptionPane.showMessageDialog(null, "¡Genere una grafica primero!");
                    } else if (vista.campoAlgoritmo.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "¡Ingrese un titulo para la grafica!");
                    } else {
                        InsertionSort hilo = new InsertionSort(cantidades, nombres, encabezado,
                                vista.botonRadioAscendente.getText(), vista, grafica);
                        hilo.start();
                    }
                } else if (vista.botonRadioQuicksort.isSelected()) {
                    if (cantidades.length == 0) {
                        JOptionPane.showMessageDialog(null, "¡Genere una grafica primero!");
                    } else if (vista.campoAlgoritmo.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "¡Ingrese un titulo para la grafica!");
                    } else {
                        QuickSort hilo = new QuickSort(cantidades, nombres, encabezado,
                                vista.botonRadioAscendente.getText(), vista, grafica);
                        hilo.start();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡Seleccione un tipo de orden!");
                }
            } else if (vista.botonRadioDescendente.isSelected()) {
                if (vista.botonRadioBubblesort.isSelected()) {
                    if (cantidades.length == 0) {
                        JOptionPane.showMessageDialog(null, "¡Genere una grafica primero!");
                    } else if (vista.campoAlgoritmo.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "¡Ingrese un titulo para la grafica!");
                    } else {
                        BubbleSort hilo = new BubbleSort(cantidades, nombres, encabezado,
                                vista.botonRadioDescendente.getText(), vista, grafica);
                        hilo.start();
                    }
                } else if (vista.botonRadioInsertionsort.isSelected()) {
                    if (cantidades.length == 0) {
                        JOptionPane.showMessageDialog(null, "¡Genere una grafica primero!");
                    } else if (vista.campoAlgoritmo.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "¡Ingrese un titulo para la grafica!");
                    } else {
                        InsertionSort hilo = new InsertionSort(cantidades, nombres, encabezado,
                                vista.botonRadioDescendente.getText(), vista, grafica);
                        hilo.start();
                    }
                } else if (vista.botonRadioQuicksort.isSelected()) {
                    if (cantidades.length == 0) {
                        JOptionPane.showMessageDialog(null, "¡Genere una grafica primero!");
                    } else if (vista.campoAlgoritmo.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "¡Ingrese un titulo para la grafica!");
                    } else {
                        QuickSort hilo = new QuickSort(cantidades, nombres, encabezado,
                                vista.botonRadioDescendente.getText(), vista, grafica);
                        hilo.start();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡Seleccione un tipo de orden!");
                }
            } else if ((vista.botonRadioAscendente.isSelected() || vista.botonRadioDescendente.isSelected())
                    && (vista.botonRadioBubblesort.isSelected() || vista.botonRadioInsertionsort.isSelected()
                            || vista.botonRadioQuicksort.isSelected())
                    && cantidades.length == 0) {
            } else {
                JOptionPane.showMessageDialog(null, "¡Seleccione un orden Ascendente/Descendente!");
            }
        }
    }
}
