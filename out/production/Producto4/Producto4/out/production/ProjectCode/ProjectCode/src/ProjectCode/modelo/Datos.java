package ProjectCode.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Datos {

    private ArrayList<D0_Federacion> federaciones;
    private C0_Seguro basico;
    private C0_Seguro completo;
    private ArrayList<E0_Excursiones> excursiones;
    private ArrayList<B1_SocioEstandar> sociosEstandars;
    private ArrayList<B2_SocioFederado> sociosFederados;
    private ArrayList<B3_SocioInfantil> sociosInfantiles;
    private ArrayList<F0_Inscripciones> cargaInscripciones;
    private ArrayList<C0_Seguro> seguros;

    public Datos() {
        this.federaciones = new ArrayList<>();
        this.excursiones = new ArrayList<>();
        this.sociosEstandars = new ArrayList<>();
        this.sociosFederados = new ArrayList<>();
        this.sociosInfantiles = new ArrayList<>();
        this.cargaInscripciones = new ArrayList<>();
        this.seguros = new ArrayList<>();
    }

    /**
     * Realiza una carga inicial de una lista de federaciones
     */
    //CARGA INCIAL FEDERACIONES
    public void cargarFederaciones() {
        federaciones.add(new D0_Federacion("1", "Federacion española de montañistas"));
        federaciones.add(new D0_Federacion("2", "Asocioación de excursionistas"));
        federaciones.add(new D0_Federacion("3", "El club del senderismo"));
        federaciones.add(new D0_Federacion("4", "Federacion vive la naturaleza"));
    }

    /**
     * Crea los dos Seguros disponibles para los Socios Estandars basico y completo
     */
    //CARGA DE SEGUROS
    public void cargarSeguros() {

        basico = new C0_Seguro(C0_Seguro.tipoSeguro.BASICO, 15);
        completo = new C0_Seguro(C0_Seguro.tipoSeguro.COMPLETO, 30);
    }

    //CARGA DE SEGUROS
    public void cargarSegurosArray() {
        seguros.add(new C0_Seguro(C0_Seguro.tipoSeguro.BASICO, 15));
        seguros.add(new C0_Seguro(C0_Seguro.tipoSeguro.COMPLETO, 30));
    }

    /**
     * Realiza una carga inicial de una lista de Socios Estandar
     */
    //CARGA INICIAL SOCIOS ESTANDAR
    public void cargaInicialSociosEstandar() {

        sociosEstandars.add(new B1_SocioEstandar(1, "Pedro Sanchez", "38456625-T", completo.toString()));
        sociosEstandars.add(new B1_SocioEstandar(2, "Antonio López", "30585524-z", completo.toString()));
        sociosEstandars.add(new B1_SocioEstandar(3, "Sonia Ruiz ", "35658623-V", basico.toString()));
    }

    /**
     * Realiza una carga inicial de una lista de Socios federados
     */
    //CARGA INICIAL DE SOCIOS FEDERADOS
    public void cargaInicialSociosFederados() {

        sociosFederados.add(new B2_SocioFederado(4, "Andrew Skurka", "38525625-T", "1",
                "Federacion española de montañistas"));
        sociosFederados.add(new B2_SocioFederado(5, "Heather Anderson", "40368524-z", "2",
                "Asocioación de excursionistas"));
        sociosFederados.add(new B2_SocioFederado(6, "Jennifer Pharr Davis", "48525623-V", "3",
                "El club del senderismo"));
        sociosFederados.add(new B2_SocioFederado(7, "Earl Shaffer", "38525625-T", "4",
                "Federacion vive la naturaleza"));
    }

    /**
     * Realiza una carga inicial de una lista de Socios Infantiles
     */
    //CARGA INICIAL DE SOCIOS INFANTILES
    public void cargaInicialSociosInfantiles() {

        sociosInfantiles.add(new B3_SocioInfantil(8, "Pedrito Sanchez", 1));
        sociosInfantiles.add(new B3_SocioInfantil(9, "Sandra Lopez", 2));
    }

    /**
     * Realiza una carga inicial de Excursiones
     */
    //CARGA INICIAL EXCURSIONES
    public void cargaInicialExcursiones() {
        excursiones.add(new E0_Excursiones("M1", "Excursión por el Montseny", LocalDate.parse("2024-04-19"), 2, 50.5));
        excursiones.add(new E0_Excursiones("A1", "Excursión por los Alpes", LocalDate.parse("2024-05-26"), 3, 220));
        excursiones.add(new E0_Excursiones("PE1", "Visita a los picos de Europa", LocalDate.parse("2024-08-22"), 5, 250));
        excursiones.add(new E0_Excursiones("E1", "Subida al Everest ", LocalDate.parse("2024-08-11"), 7, 1250));
    }

    /**
     * Realiza una carga inicial de Inscripciones
     */
    //CARGA INICIAL INSCRIPCIONES
    public void cargaInicialInscripciones() {
        cargaInscripciones.add(new F0_Inscripciones(1, sociosFederados.get(1), excursiones.get(1)));
        cargaInscripciones.add(new F0_Inscripciones(2, sociosEstandars.get(1), excursiones.get(0)));
    }

// METODOS DE EXCURSIONES
    /**
     * Añade nuevas excursiones
     *
     * @param codigo El codigo de la excursion
     * @param descr  Una descripcion
     * @param fecha  La Fecha de la Excursion
     * @param dias   La cantidad de dias que dura la excursion
     * @param precio El precio de la excursión
     */
    // AÑADIR EXCURSION
    public void añadirExcursion(String codigo, String descr, LocalDate fecha, int dias, double precio) {
        excursiones.add(new E0_Excursiones(codigo, descr, fecha, dias, precio));
    }

    /**
     * Filtra las excursiones comprendidas entre dos fechas y las añade al ArrayList excursionesFiltradas
     *
     * @param fechaIni Fecha de inicio que queremos visualizar
     * @param fechafin Fecha de Fin que queremos visualizar
     * @return una lista de Excursiones filtrada por fechas
     */
    //FUNCION FILTRAR EXCURSIONES POR RANGO DE FECHAS
    public ArrayList<E0_Excursiones> filtrarExcursiones(LocalDate fechaIni, LocalDate fechafin) {
        ArrayList<E0_Excursiones> excusionesFiltradas = new ArrayList<>();
        for (E0_Excursiones excursion : excursiones) {
            if ((excursion.getFecha().isAfter(fechaIni) || excursion.getFecha().isEqual(fechaIni))
                    && (excursion.getFecha().isBefore(fechafin) || excursion.getFecha().isEqual(fechafin))) {
                excusionesFiltradas.add(excursion);
            }
        }
        return excusionesFiltradas;
    }
    
    //FUNCION MOSTRAR EXCURSIONES
    public ArrayList<E0_Excursiones> mostrarExcursiones(){
        return excursiones;
    }
    
    /**
     * Elimina la excursion con el codigo recivido como parametro.
     *
     * @param codigoExcursion El codigo de la excursion a eliminar
     */

     //FUNCION ELIMINAR EXCURSION
    public void eliminarExcursion(String codigoExcursion) {
        int contador = 0;
        //creamos iterador para la lista de excursiones
        Iterator<E0_Excursiones> iterador = excursiones.iterator();
        //mientras queden elementos..
        while (iterador.hasNext()) {
            E0_Excursiones excursionActual = iterador.next();//Almacenamos el siguiente elemento de la lista

            // si el codigo del elemento actual es igual al codigoExcursion iterator elimina ese elemento.
            if (excursionActual.getCodigo().equals(codigoExcursion)) {
                iterador.remove();// borramos el elemento
                contador++;
                break; // salimos del bucle si el elemento ha sido ya eliminado
            }
        }
        if(contador ==0){
            System.out.println("No se ha encontrado el codio de excusion a buscar");
        }
            System.out.println("Se han eliminado "+contador +" excursiones");
    }

//METODO FEDERACIONES
    //Ver federaciones
    public ArrayList<D0_Federacion> mostrarFederaciones(){
        return federaciones;
    }

//METODO SEGUROS
    //Ver Seguros
    public ArrayList<C0_Seguro> mostrarSeguros(){
        return seguros;
    }

//METODOS DE SOCIOS
//METODOS SOCIO FEDERADO
    /**
     * añade nuevos socios Federados
     *
     * @param numSocio El numero de Socio Federado
     * @param nombre El nombre del socio Federado
     * @param nif El nif del Socio Federado
     * @param codigoFederacion El codigo de la Federacion
     * @param nomFederacion El nombre de la Federación
     */
    //FUNCION CREAR SOCIO FEDERADO
    public void CrearSocioFederado(int numSocio, String nombre, String nif, String codigoFederacion, String nomFederacion){
        sociosFederados.add(new B2_SocioFederado(numSocio, nombre, nif, codigoFederacion, nomFederacion));
    }

    /**
     * Solicita la lista de Socios Federados
     *
     * @return Una lista de Socios Federados
     */
    //FUNCION MOSTRAR SOCIOS FEDERADOS
    public ArrayList<B2_SocioFederado> mostrarsociosFederados(){
        return sociosFederados;
    }

//METODOS SOCIO ESTANDAR
    /**
     * añade nuevos Socios Estandars
     *
     * @param numSocio El numero de Socio Estandar
     * @param nombre El nombre del socio Estandar
     * @param nif El nif del Socio Estandar
     * @param seguro El seguro obligatorio del Socio Estandar
     */
    //FUNCION CREAR SOCIO ESTANDAR
    public void CrearSocioEstandar(int numSocio, String nombre, String nif, C0_Seguro seguro){
        sociosEstandars.add(new B1_SocioEstandar(numSocio, nombre,  nif, seguro.toString()));
    }

    /**
     * solicita la lista de Socios Estandar
     *
     * @return Una lista de Socios Estandar
     */
     //FUNCION MOSTRAR SOCIO ESTANDAR
    public ArrayList<B1_SocioEstandar> mostrarSocioEstandar(){
        return sociosEstandars;
    }

    //METODOS SOCIO INFANTIL
    /**
     * Añade nuevos Socios Infantiles.
     * @param numSocio El numero de Socio Infantil
     * @param nombre El nombre del Socio Infantil
     * @param numSocioPadre En numero del Socio de Padre/Madre del Socio Infantil
     */

    public void CrearSocioInfantil(int numSocio, String nombre, int numSocioPadre){

        sociosInfantiles.add(new B3_SocioInfantil(numSocio, nombre, numSocioPadre));
    }

    /**
     * solicita la lista de Socios Infantiles
     * @return Una lista de Socios Infantiles
     */
    public ArrayList<B3_SocioInfantil> mostrarSocioInfantil(){
        return sociosInfantiles;
    }

    /**
     * 
     * @param numSocio
     * @return boolean
     */
     //FUNCION ELIMINAR SOCIOS 
    public boolean eliminarSocio(int numSocio){
        Iterator<B2_SocioFederado> iteradorFederado = sociosFederados.iterator();
        
        while (iteradorFederado.hasNext()) {
            B2_SocioFederado socioFederado = iteradorFederado.next();
            if (socioFederado.getNumSocio() == numSocio) {
                iteradorFederado.remove();
                return true; // Socio encontrado y eliminado
            }
        }
        // Iterar sobre la lista de socios estándar
        Iterator<B1_SocioEstandar> iteradorEstandar = sociosEstandars.iterator();

        while (iteradorEstandar.hasNext()) {
            B1_SocioEstandar socioEstandar = iteradorEstandar.next();
            if (socioEstandar.getNumSocio() == numSocio) {
                iteradorEstandar.remove();
                return true; // Socio encontrado y eliminado
            }
        }

        // Iterar sobre la lista de socios infantiles
        Iterator<B3_SocioInfantil> iteradorInfantil = sociosInfantiles.iterator();

        while (iteradorInfantil.hasNext()) {
            B3_SocioInfantil socioInfantil = iteradorInfantil.next();
            if (socioInfantil.getNumSocio() == numSocio) {
                iteradorInfantil.remove();
                return true; // Socio encontrado y eliminado
            }
        }
        return false;
    }


    //FUNCION BUSCAR SOCIO
    public B0_Socio buscarSocio(List<?extends B0_Socio> socioEstandars, List<?extends B0_Socio> socioFederados, List<?extends B0_Socio> socioInfantil, int numeroSocio) {
        B0_Socio encontrado = null;
        //Buscamos en la lista estandar
        for (int i = 0; i < socioEstandars.size() && encontrado == null; i++) {

            if (socioEstandars.get(i).getNumSocio() == numeroSocio) {
                encontrado = socioEstandars.get(i);
            }
        }

        //Si no lo he encontrado en la lista estandar, lo busco en la de federado
        if (encontrado == null) {
            //Buscamos en la lista federados
            for (int i = 0; i < socioFederados.size() && encontrado == null; i++) {

                if (socioFederados.get(i).getNumSocio() == numeroSocio) {
                    encontrado = socioFederados.get(i);
                }
            }
        }
        //Si no lo he encontrado en la lista federado, lo busco en la de infantil
        if (encontrado == null) {
            //Buscamos en la lista federados
            for (int i = 0; i < socioInfantil.size() && encontrado == null; i++) {

                if (socioInfantil.get(i).getNumSocio() == numeroSocio) {
                    encontrado = socioInfantil.get(i);
                }
            }
        }
        return encontrado;
    }

    //FUNCION CREAR INSCRIPCION
    public void CrearInscripcion(int numInscripcion, B0_Socio socio, E0_Excursiones excursion){
        cargaInscripciones.add(new F0_Inscripciones(numInscripcion, socio, excursion));
    }

    /**
     * Devuelve la lista de inscripciones según el numero de socio
     * @return lista con todas las incripciones según el numero de socio
     */
    //FUNCION MOSTRAR INSCRIPCIONES POR NUMERO DE SOCIO
    public ArrayList<F0_Inscripciones> mostrarInscripcionesPorSocio(int numSocio){
        ArrayList<F0_Inscripciones> listaFiltrada = new ArrayList<>();

        for (int i = 0; i < cargaInscripciones.size(); i++) {
            if (cargaInscripciones.get(i).getSocio().getNumSocio() == numSocio){
                listaFiltrada.add(cargaInscripciones.get(i));
            }
        }
        return listaFiltrada;
    }

    /**
     * Devuelve la lista de inscripciones según la fecha
     * @return lista con todas las incripciones según la fecha
     */
    //FUNCION MOSTRAR INSCRIPCIONES POR RANGO DE FECHAS
    public ArrayList<F0_Inscripciones> mostrarInscripcionesPorFechas(LocalDate fechaIni, LocalDate fechaFin){
        ArrayList<F0_Inscripciones> listaFiltrada = new ArrayList<>();

        for (int i = 0; i < cargaInscripciones.size(); i++) {
            if ((cargaInscripciones.get(i).getExcursion().getFecha().isAfter(fechaIni) ||
                cargaInscripciones.get(i).getExcursion().getFecha().isEqual(fechaIni)) &&
                (cargaInscripciones.get(i).getExcursion().getFecha().isBefore(fechaFin) ||
                cargaInscripciones.get(i).getExcursion().getFecha().isEqual(fechaFin))) {
                listaFiltrada.add(cargaInscripciones.get(i));
            }
        }
        return listaFiltrada;
    }


    /**
     * Elimina una inscripcion de la lista
     * @param numeroInscripcion numero de inscripcion a eliminar
     */
    //BORRAR INSCRIPCION
    public boolean eliminarInscripcion(int numeroInscripcion) {
        //creamos iterador para la lista de inscripciones
        Iterator<F0_Inscripciones> iterador = cargaInscripciones.iterator();
        //mientras queden elementos..
        while (iterador.hasNext()) {

            F0_Inscripciones inscripcionActual = iterador.next();//Almacenamos el siguiente elemento de la lista

            // si el codigo del elemento actual es igual al codigoExcursion iterator elimina ese elemento.
            if (inscripcionActual.getNumInscripcion() == numeroInscripcion
                && LocalDate.now().isBefore(inscripcionActual.getExcursion().getFecha())) {
                iterador.remove();
                return true; 
            }
        }
        return false;
    }

//FUNCIONES FACTURAS
    //FACTURA SOCIO FEDERADO
    public double mostrarFacturaMensualSocioFederado(B2_SocioFederado socioFederado, LocalDate fechaActual) {
        int cuotaBase = socioFederado.getCuota();
        double descuentoCuota = socioFederado.getDES_CUOTA_MENSUAL();
        double cuotaFinal = cuotaBase - (cuotaBase * descuentoCuota);
                
            return cuotaFinal;
    }


    //FACTURA SOCIO ESTANDAR
    public double mostrarFacturaMensualSocioEstandar(B1_SocioEstandar socioEstandar, LocalDate fechaActual){
        int cuotaBase= socioEstandar.getCuota();
        double cuotaFinal= cuotaBase;
        
        return cuotaFinal;
    }


    //FACTURA SOCIO INFANTIL
    public double mostrarFacturaMensualSocioInfantil(B3_SocioInfantil socioInfantil, LocalDate fechaActual){
        int cuotaBase= socioInfantil.getCuota();
        double descuentoCuota = socioInfantil.getDESCUENTO();
        double cuotaFinal = cuotaBase - (cuotaBase * descuentoCuota);
    
        return cuotaFinal;
    }
}
