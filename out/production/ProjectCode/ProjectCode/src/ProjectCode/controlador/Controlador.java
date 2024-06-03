/**
 * Clase controlador realizar la interconexion entre la vista y el modelo
 */

package ProjectCode.controlador;

import ProjectCode.modelo.*;
import org.hibernate.Session;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private Datos datos;
    private A_MenuInicial vista;
    private ConexionHibernate conexion;

     //CONSTRUCTORES
     //recive/envia de vista y recive/envia a modelo

    /**
      * Constructor de la clase controlador.
      * @param datos instancia de la clase Datos
      * @param vista instancia de la clase Vista
      */
    public Controlador(Datos datos, A_MenuInicial vista) {
        this.datos = datos;
        this.vista = vista;
    }
    /**
      * Constructor de la clase controlador.
      * @param datos instancia de la clase Datos
      */
    public Controlador(Datos datos, ConexionHibernate conexion){
        this.datos = datos;
        this.conexion = conexion;
    }

     //METODOS CARGA DE DATOS
    /**
      * Interactua con el metodo de datos de la clase Datos del modelo, para cargar las Federaciones
      * Este método se llama al inicio de la aplicación para cargar las federaciones disponibles por defecto
      *
      */
    public void cargarFederaciones() {
        datos.cargarFederaciones();
    }
    /**
      * Interactua con el metodo de datos de la clase Datos del modelo, para cargar los seguros
      * Este método se llama al inicio de la aplicación para cargar los seguros disponibles por defecto
      *
      */
    public void cargarSeguros(){
        datos.cargarSeguros();
    }

    public void cargarSegurosArray(){
        datos.cargarSegurosArray();
    }
    /**
      * Interactua con el metodo de datos de la clase Datos del modelo, para cargar los Socios Estandars
      * Este método se llama al inicio de la aplicación para cargar los Socios Estandars disponibles por defecto
      *
      */
    public void cargaInicialSociosEstandar(){
        datos.cargaInicialSociosEstandar();
    }
    /**
      * Interactua con el metodo de datos de la clase Datos del modelo, para cargar los Socios Federados
      * Este método se llama al inicio de la aplicación para cargar los Socios Federados disponibles por defecto
      *
      */

    public void cargaInicialSociosFederados(){
        datos.cargaInicialSociosFederados();
    }
    /**
      * Interactua con el metodo de datos de la clase Datos del modelo, para cargar los Socios Infantiles
      * Este método se llama al inicio de la aplicación para cargar los Socios Infantiles disponibles por defecto
      *
      */
    public void cargaInicialSociosInfantiles(){
        datos.cargaInicialSociosInfantiles();
    }
    /**
      * Interactua con el metodo de datos de la clase Datos del modelo, para cargar las Excursiones
      * Este método se llama al inicio de la aplicación para cargar las Excursiones disponibles por defecto
      *
      */
    public void cargaInicialExcursiones(){
        datos.cargaInicialExcursiones();
    }
    /**
      * Interactua con el metodo de datos de la clase Datos del modelo, para cargar las Inscripciones
      * Este método se llama al inicio de la aplicación para cargar las Inscripciones disponibles por defecto
      *
      */
    public void cargaInicialInscripciones(){
        datos.cargaInicialInscripciones();
    }


    //METODOS EXCURSIONES

    /**
      * Interactua con el metodo de datos de la clase Datos del modelo, para añadir nuevas excursiones
      *
      * @param codigo El codigo de la excursion
      * @param descr Una descripcion
      * @param fecha La Fecha de la Excursion
      * @param dias La cantidad de dias que dura la excursion
      * @param precio El precio de la excursión
      */
    public void añadirExcursion(String codigo, String descr, LocalDate fecha, int dias, double precio){
        datos.añadirExcursion(codigo,descr,fecha,dias,precio);
    }

    /**
      * Interactua con el metodo de datos de la clase Datos del modelo para solicitar un lista de excursiones
      * comprendidas entre dos fechas.
      *
      * @param fechaIni Fecha de inicio que queremos visualizar
      * @param fechafin Fecha de Fin que queremos visualizar
      * @return una lista de Excursiones filtrada por fechas
      */

    public ArrayList<E0_Excursiones> filtrarExcursiones(LocalDate fechaIni, LocalDate fechafin){
        ArrayList<E0_Excursiones> excursiones= new ArrayList<>();
        excursiones = datos.filtrarExcursiones(fechaIni,fechafin);
        return  excursiones;
    }

    /**
      * Interactua con el metodo de datos de la clase Datos del modelo para eliminar una excursion.
      * @param codigoExcursion El codigo de la excursion a eliminar
      */
    public void eliminarExcursion(String codigoExcursion){
        datos.eliminarExcursion(codigoExcursion);
    }


    /**
     * Interactua con el metodo de datos de la clase Datos del modelo para solicitar la lista de excursiones
     * @return lista de todas las excursiones
     */
    public ArrayList<E0_Excursiones> mostrarExcursiones(){

        return datos.mostrarExcursiones();
    }

    /**
     * Busca la excursion en la lista de excursiones a partir del codigo
     * @param excursiones lista de excursiones
     * @param codigoExcursion codigo de excursion
     * @return objeto Excursion a partir del codigo
     */
    public E0_Excursiones buscarExcursion(ArrayList<E0_Excursiones> excursiones, String codigoExcursion) {
        E0_Excursiones encontrado = null;

        //Buscamos en la lista de excursiones
        for (int i = 0; i < excursiones.size() && encontrado == null; i++){

            if (excursiones.get(i).getCodigo().equals(codigoExcursion)){
                encontrado = excursiones.get(i);
            }
        }
        return encontrado;
    }

     //METODO FEDERACIONES
     //Mostrar federaciones

    /**
      * Interactua con el metodo de datos de la clase Datos del modelo para solicitar un lista de Federaciones
      * @return Una lista de Federaciones.
      */
    public ArrayList<D0_Federacion> mostrarFederaciones(){
        return datos.mostrarFederaciones();
    }
     //METODO SEGUROS
     //Ver Seguros
    /**
      * Interactua con el metodo de datos de la clase Datos del modelo para solicitar un lista de Seguros
      * @return Una lista de Seguros.
      */
    public ArrayList<C0_Seguro> mostrarSeguros(){
        return datos.mostrarSeguros();
    }
    //METODOS SOCIOS
     // METODOS SOCIOS FEDERADOS

    /**
      * Interactua con el metodo de datos de la clase Datos del modelo para añadir nuevos socios Federados
      *
      * @param numSocio El numero de Socio Federado
      * @param nombre El nombre del socio Federado
      * @param nif El nif del Socio Federado
      * @param codigoFederacion El codigo de la Federacion
      * @param nomFederacion El nombre de la Federación
      */
    public void CrearSocioFederado(int numSocio, String nombre, String nif, String codigoFederacion, String nomFederacion){
        try {
            socioFederadoDAO.crearSocioFederado(numSocio, nombre, nif, codigoFederacion, nomFederacion, conexion);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
      * Interactua con el metodo de datos de la clase Datos del modelo para solicitar la lista de Socios Federados
      *
      * @return Una lista de Socios Federados
      */
    public ArrayList<B2_SocioFederado> mostrarSocioFederados(){
        ArrayList<B2_SocioFederado> socioFederados = new ArrayList<>();
        try {
            socioFederados = socioFederadoDAO.mostrarSocioFederado(conexion);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return socioFederados;
    }

     //METODOS SOCIO ESTANDAR
    /**
      * Interactua con el metodo de datos de la clase Datos del modelo para añadir nuevos Socios Estandars
      *
      * @param numSocio El numero de Socio Estandar
      * @param nombre El nombre del socio Estandar
      * @param nif El nif del Socio Estandar
      * @param seguro El seguro obligatorio del Socio Estandar
      */

    public void CrearSocioEstandar(int numSocio, String nombre, String nif, C0_Seguro seguro){
        try {
            socioEstandarDAO.crearSocioEstandar(numSocio, nombre, nif, seguro.getTipoSeguro().toString(), conexion);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
      * Interactua con el metodo de datos de la clase Datos del modelo para solicitar la lista de Socios Estandar
      *
      * @return Una lista de Socios Estandar
      */

    public ArrayList<B1_SocioEstandar> mostrarSocioEstandar(){
        ArrayList<B1_SocioEstandar> socioEstandars = new ArrayList<>();
        try {
            socioEstandars = socioEstandarDAO.mostrarSocioEstandar(conexion);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return socioEstandars;
    }

     //METODOS SOCIOS INFANTILES

    /**
      * Interactua con el metodo de datos de la clase Datos del modelo para añadir nuevos Socios Infantiles.
      *
      * @param numSocio El numero de Socio Infantil
      * @param nombre El nombre del Socio Infantil
      * @param numSocioPadre En numero del Socio de Padre/Madre del Socio Infantil
      */
    public void CrearSocioInfantil(int numSocio, String nombre, int numSocioPadre){

        try {
            socioInfantilDAO.crearSocioInfantil(numSocio, nombre, numSocioPadre, conexion);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
      * Interactua con el metodo de datos de la clase Datos del modelo para solicitar la lista de Socios Infantiles
      * @return Una lista de Socios Infantiles
      */
    public ArrayList<B3_SocioInfantil> mostrarSocioInfantil(){
        ArrayList<B3_SocioInfantil> socioInfantil = new ArrayList<>();
        try {
            socioInfantil = socioInfantilDAO.mostrarSocioInfantil(conexion);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return socioInfantil;
    }

    /**
     * Busca el socio en las listas de socios estandar y socios federados a partir del numero de socio
     * @param socioEstandars lista de socios estandar
     * @param socioFederados lista de socios federados
     * @param numeroSocio numero de socio a buscar
     * @return objeto Socio según el numero de socio buscado
     */
    public B0_Socio buscarSocio(List<?extends B0_Socio> socioEstandars, List<?extends B0_Socio> socioFederados, List<?extends B0_Socio> socioInfantil, int numeroSocio) {
        B0_Socio encontrado = null;


        encontrado = datos.buscarSocio(socioEstandars,socioFederados, socioInfantil, numeroSocio);
        return encontrado;
    }

    //ELIMINAR SOCIOS //24-3-24
    public boolean eliminarSocio(int numSocio){
        try {
            return socioDAO.eliminarSocio(numSocio);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return false;
    }


    //FACTURAS //30-3-24 


    public double mostrarFacturaMensualSocioFederado(B2_SocioFederado socioFederado, LocalDate fechaActual) {
        Datos datos=new Datos();         
        double cuotaFinal=datos.mostrarFacturaMensualSocioFederado(socioFederado,fechaActual);

        return cuotaFinal;
        }

    public double mostrarFacturaMensualSocioEstandar(B1_SocioEstandar socioEstandar, LocalDate fechaActual){
        Datos datos=new Datos();
        double cuotaFinal=datos.mostrarFacturaMensualSocioEstandar(socioEstandar,fechaActual);

        return cuotaFinal;
    }

    public double mostrarFacturaMensualSocioInfantil(B3_SocioInfantil socioInfantil, LocalDate fechaActual){

        Datos datos=new Datos ();
        double cuotaFinal=datos.mostrarFacturaMensualSocioInfantil(socioInfantil,fechaActual);

        return cuotaFinal;
    }


    //INSCRIPCIONES

    /**
     * Metodo que crea una nueva inscripcion
     * @param numInscripcion numero de la nueva inscripcion
     * @param socio Objeto socio asociado a la inscripcion
     * @param excursion Objeto excursion asociado a la inscripcion
     */
    public void CrearInscripcion(int numInscripcion, B0_Socio socio, E0_Excursiones excursion){

        datos.CrearInscripcion(numInscripcion, socio, excursion);
    }

    /**
     * Metodo que muestra las inscripciones por un socio
     * @return lista con todas las inscripciones para ese socio
     */
    public ArrayList<F0_Inscripciones> mostrarInscripcionesPorSocio(int numSocio){
        ArrayList<F0_Inscripciones> inscripciones;
        inscripciones = datos.mostrarInscripcionesPorSocio(numSocio);
        return inscripciones;
    }

    /**
     *Metodo que muestra las inscripciones por fecha
     * @return lista con las inscripciones para las fechas indicadas
     */
    public ArrayList<F0_Inscripciones> mostrarInscripcionesPorFechas(LocalDate fechaIni, LocalDate fechaFin){
        ArrayList<F0_Inscripciones> inscripciones;
        inscripciones = datos.mostrarInscripcionesPorFechas(fechaIni, fechaFin);
        return inscripciones;
    }

    /**
     * Metodo que elimina una inscripcion
     * @param numeroInscripcion numero de la inscripcion a borrar
     */
    public boolean eliminarInscripcion(int numeroInscripcion){

        return datos.eliminarInscripcion(numeroInscripcion);

    }
}
