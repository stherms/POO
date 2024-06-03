package ProjectCode;


import ProjectCode.controlador.Controlador;
import ProjectCode.modelo.ConexionHibernate;
import ProjectCode.modelo.Datos;


public class Main {
    public static void main(String[] args) {

        try {
            Datos datos = new Datos();
            ConexionHibernate conexion = new ConexionHibernate();
            Controlador controlador = new Controlador(datos, conexion);
            A_MenuInicial prog = new A_MenuInicial(controlador);

            prog.cargadatos();
            prog.inicio();
        }
        catch (Exception ex){
           ex.printStackTrace();
        }
    }
}
