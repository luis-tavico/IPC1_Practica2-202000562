package ipc1.vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class VentanaPrincipal extends javax.swing.JFrame {

    private ButtonGroup botonesOrdenamiento;
    private ButtonGroup botonesAlgoritmo;
    private JPanel panelGeneral;
    public JTextField campoRuta;
    public JButton botonExaminar;
    public JButton botonGraficar;
    public JTextField campoAlgoritmo;
    public JRadioButton botonRadioAscendente;
    public JRadioButton botonRadioDescendente;
    public JPanel panelGrafica;
    private JLabel etiquetaTiempo;
    public JLabel etiquetaTemporizador;
    public JButton botonOrdenar;
    private JSeparator separador;
    public JLabel etiquetaPasos;
    private JLabel etiquetaPaso;
    public JRadioButton botonRadioBubblesort;
    public JRadioButton botonRadioInsertionsort;
    public JRadioButton botonRadioQuicksort;
    public JFileChooser explorador;
    final private Font fuente = new Font("arial", 0, 14);
    final private Font fuenteNumeros = new Font("eras medium itc", 0, 16);

    public VentanaPrincipal() {
        setSize(590, 385);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Ordenamiento");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciarComponentes();
    }

    public void iniciarComponentes() {

        panelGeneral = new JPanel();
        panelGeneral.setLayout(null);
        this.getContentPane().add(panelGeneral);
        ///////////////////////////////////////
        campoRuta = new JTextField();
        campoRuta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
        campoRuta.setBounds(15, 10, 410, 23);
        panelGeneral.add(campoRuta);
        ///////////////////////////////////////
        campoAlgoritmo = new JTextField();
        campoAlgoritmo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
        campoAlgoritmo.setBounds(15, 41, 410, 23);
        panelGeneral.add(campoAlgoritmo);
        ///////////////////////////////////////
        botonExaminar = new JButton();
        botonExaminar.setText("Examinar");
        botonExaminar.setFont(fuente);
        botonExaminar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        botonExaminar.setForeground(new Color(255, 255, 255));
        botonExaminar.setBackground(Color.gray);
        botonExaminar.setFocusPainted(false);
        botonExaminar.setBounds(435, 10, 125, 23);
        panelGeneral.add(botonExaminar);
        ///////////////////////////////////////
        botonGraficar = new JButton();
        botonGraficar.setText("Graficar");
        botonGraficar.setFont(fuente);
        botonGraficar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        botonGraficar.setForeground(new Color(255, 255, 255));
        botonGraficar.setBackground(new Color(60, 90, 153));
        botonGraficar.setFocusPainted(false);
        botonGraficar.setBounds(435, 41, 125, 23);
        panelGeneral.add(botonGraficar);
        ///////////////////////////////////////
        etiquetaTiempo = new JLabel();
        etiquetaTiempo.setText("Tiempo:");
        etiquetaTiempo.setFont(fuente);
        etiquetaTiempo.setBounds(15, 77, 60, 23);
        panelGeneral.add(etiquetaTiempo);
        ///////////////////////////////////////
        etiquetaPaso = new JLabel();
        etiquetaPaso.setText("Pasos:");
        etiquetaPaso.setFont(fuente);
        etiquetaPaso.setBounds(335, 77, 100, 23);
        panelGeneral.add(etiquetaPaso);
        ///////////////////////////////////////
        etiquetaTemporizador = new JLabel();
        etiquetaTemporizador.setFont(fuenteNumeros);
        etiquetaTemporizador.setHorizontalAlignment(JTextField.CENTER);
        etiquetaTemporizador.setText("00:00:00");
        etiquetaTemporizador.setBounds(75, 77, 75, 23);
        etiquetaTemporizador.setBorder(new MatteBorder(1, 1, 1, 1, Color.gray));
        panelGeneral.add(etiquetaTemporizador);
        ///////////////////////////////////////
        etiquetaPasos = new JLabel();
        etiquetaPasos.setFont(fuenteNumeros);
        etiquetaPasos.setHorizontalAlignment(JTextField.CENTER);
        etiquetaPasos.setText("00");
        etiquetaPasos.setBounds(386, 77, 35, 23);
        etiquetaPasos.setBorder(new MatteBorder(1, 1, 1, 1, Color.gray));
        panelGeneral.add(etiquetaPasos);
        ///////////////////////////////////////
        botonRadioAscendente = new JRadioButton();
        botonRadioAscendente.setText("Ascendente");
        botonRadioAscendente.setFont(fuente);
        botonRadioAscendente.setFocusPainted(false);
        botonRadioAscendente.setBounds(442, 110, 100, 23);
        panelGeneral.add(botonRadioAscendente);
        ///////////////////////////////////////
        botonRadioDescendente = new JRadioButton();
        botonRadioDescendente.setText("Descendente");
        botonRadioDescendente.setFont(fuente);
        botonRadioDescendente.setFocusPainted(false);
        botonRadioDescendente.setBounds(442, 138, 120, 23);
        panelGeneral.add(botonRadioDescendente);
        ///////////////////////////////////////
        separador = new JSeparator();
        separador.setBounds(445, 171, 102, 23);
        panelGeneral.add(separador);
        ///////////////////////////////////////
        botonRadioBubblesort = new JRadioButton();
        botonRadioBubblesort.setText("Bubblesort");
        botonRadioBubblesort.setFont(fuente);
        botonRadioBubblesort.setFocusPainted(false);
        botonRadioBubblesort.setBounds(442, 181, 100, 23);
        panelGeneral.add(botonRadioBubblesort);
        ///////////////////////////////////////
        botonRadioInsertionsort = new JRadioButton();
        botonRadioInsertionsort.setText("Insertionsort");
        botonRadioInsertionsort.setFont(fuente);
        botonRadioInsertionsort.setFocusPainted(false);
        botonRadioInsertionsort.setBounds(442, 209, 120, 23);
        panelGeneral.add(botonRadioInsertionsort);
        ///////////////////////////////////////
        botonRadioQuicksort = new JRadioButton();
        botonRadioQuicksort.setText("Quicksort");
        botonRadioQuicksort.setFont(fuente);
        botonRadioQuicksort.setFocusPainted(false);
        botonRadioQuicksort.setBounds(442, 237, 100, 23);
        panelGeneral.add(botonRadioQuicksort);
        ///////////////////////////////////////
        botonesOrdenamiento = new ButtonGroup();
        botonesOrdenamiento.add(botonRadioAscendente);
        botonesOrdenamiento.add(botonRadioDescendente);
        ///////////////////////////////////////
        botonesAlgoritmo = new ButtonGroup();
        botonesAlgoritmo.add(botonRadioBubblesort);
        botonesAlgoritmo.add(botonRadioInsertionsort);
        botonesAlgoritmo.add(botonRadioQuicksort);
        ///////////////////////////////////////
        botonOrdenar = new JButton();
        botonOrdenar.setText("Ordenar");
        botonOrdenar.setFont(fuente);
        botonOrdenar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        botonOrdenar.setForeground(new Color(255, 255, 255));
        botonOrdenar.setBackground(new Color(41, 167, 37));
        botonOrdenar.setFocusPainted(false);
        botonOrdenar.setBounds(435, 270, 125, 23);
        panelGeneral.add(botonOrdenar);
        ///////////////////////////////////////
        panelGrafica = new JPanel();
        panelGrafica.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
        panelGrafica.setBounds(15, 110, 410, 220);
        panelGeneral.add(panelGrafica);
        ///////////////////////////////////////
        explorador = new JFileChooser();

    }

}
