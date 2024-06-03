package ProjectCode;


import ProjectCode.controlador.Controlador;
import ProjectCode.modelo.Datos;
import ProjectCode.vista.A_MenuInicial;




public class Main {
    public static void main(String[] args) {

        Datos datos = new Datos();
        Controlador controlador = new Controlador(datos);
        A_MenuInicial prog = new A_MenuInicial(controlador);

        prog.cargadatos();
        prog.inicio();
    }
}

