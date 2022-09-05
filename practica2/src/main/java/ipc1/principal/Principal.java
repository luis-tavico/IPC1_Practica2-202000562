package ipc1.principal;

import ipc1.controlador.Controlador;
import ipc1.vista.VentanaPrincipal;

public class Principal {

    public static void main(String[] args) {
        VentanaPrincipal vista = new VentanaPrincipal();
        Controlador controlador = new Controlador(vista);
        vista.setVisible(true);
    }
}
