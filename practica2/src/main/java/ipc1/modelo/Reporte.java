package ipc1.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import ipc1.vista.VentanaPrincipal;

public class Reporte {

    VentanaPrincipal vista = new VentanaPrincipal();
    String reporte = "";
    String datos = "";

    public Reporte(VentanaPrincipal vista) {
        this.vista = vista;
    }

    public void insertarDatos(String algoritmo, String orden) {
        reporte += "<!DOCTYPE html>\n"
                + "<html lang=\"es\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "    <title>Reporte</title>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "    <h1>Pedro Luis Pu Tavico</h1>\n"
                + "    <p>202000562</p>\n"
                + "    <h2>" + algoritmo + "</h2>\n"
                + "    <div class=\"tbl-content\">\n"
                + "        <table>\n"
                + "            <tr>\n"
                + "                <th>Tiempo</th>\n"
                + "                <td>" + vista.etiquetaTemporizador.getText() + "</td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <th>Pasos</th>\n"
                + "                <td>" + vista.etiquetaPasos.getText() + "</td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <th>Orden</th>\n"
                + "                <td>" + orden + "</td>\n"
                + "            </tr>\n"
                + "        </table>\n"
                + "    </div>\n"
                + "    <br>\n"
                + "    <hr>\n";
    }

    public void insertarDatosDesordenados(double[] cantidades, String[] nombres, String[] encabezado,
            String nombre_imagen) {
        datos += "    <h2>Datos desordenados</h2>\n"
                + "    <div class=\"tbl-content\">\n"
                + "        <table>\n"
                + "            <tr>\n"
                + "                <th>" + encabezado[0] + "</th>\n";
        for (int i = 0; i < nombres.length; i++) {
            datos += "                <td>" + nombres[i] + "</td>\n";
        }
        datos += "            </tr>\n"
                + "            <tr>\n"
                + "                <th>" + encabezado[1] + "</th>\n";
        for (int i = 0; i < cantidades.length; i++) {
            datos += "                <td>" + cantidades[i] + "</td>\n";
        }
        datos += "            </tr>\n"
                + "        </table>\n"
                + "    </div>\n"
                + "    <br>\n"
                + "    <img src=\"" + nombre_imagen + "\" alt=\"Grafica de barras\">\n"
                + "    <br>\n"
                + "    <hr>\n";
    }

    public void insertarDatosOrdenados(double[] cantidades, String[] nombres, String[] encabezado,
            String nombre_imagen) {
        datos += "    <h2>Datos ordenados</h2>\n"
                + "    <div class=\"tbl-content\">\n"
                + "        <table>\n"
                + "            <tr>\n"
                + "                <th>" + encabezado[0] + "</th>\n";
        for (int i = 0; i < nombres.length; i++) {
            datos += "                <td>" + nombres[i] + "</td>\n";
        }
        datos += "            </tr>\n"
                + "            <tr>\n"
                + "                <th>" + encabezado[1] + "</th>\n";
        for (int i = 0; i < cantidades.length; i++) {
            datos += "                <td>" + cantidades[i] + "</td>\n";
        }

        datos += "            </tr>\n"
                + "        </table>\n"
                + "    </div>\n"
                + "    <br>\n"
                + "    <img src=\"" + nombre_imagen + "\" alt=\"Grafica de barras\">\n"
                + "\n"
                + "</body>\n"
                + "</html>";
    }

    public void generarArchivo() {
        reporte += datos;
        DateFormat dateFormat = new SimpleDateFormat("HH;mm;ss");
        Date date = new Date();
        try {
            File archivo = new File("reporte(" + dateFormat.format(date) + ").html");
            FileWriter escribir = new FileWriter(archivo);
            BufferedWriter buffer = new BufferedWriter(escribir);
            PrintWriter print = new PrintWriter(buffer);
            print.write(reporte);
            print.close();
            buffer.close();
            if (archivo.createNewFile()) {
                JOptionPane.showMessageDialog(null, "¡Se ha generado un reporte.html!");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "¡No se pudo crear el archivo!", "Error", 0);
        }
    }

}
