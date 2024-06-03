/**
 *
 * Representa la vista del menu del usuario desde donde se interactua
 * con la clase controlador para añadir y visualizar datos
 *
 *@author ProjectCode
 *@version 1.0
 */
package ProjectCode.vista;

import ProjectCode.controlador.Controlador;
import ProjectCode.modelo.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class A_MenuInicial {
    Scanner teclado;
    private Controlador controlador;
    private ConexionHibernate conexion;
    ArrayList<String> menuInicial = new ArrayList<>();
    ArrayList<String> menuExcursiones = new ArrayList<>();
    ArrayList<String> menuSocios = new ArrayList<>();
    ArrayList<String> submenuSocios = new ArrayList<>();
    ArrayList<String> menuInscripciones = new ArrayList<>();

    /**
     * Constructor de la clase A_MenuIcial
     *
     * @param controlador Una instancia de la clase controlador
     */
    public A_MenuInicial(Controlador controlador) {

        this.controlador = controlador;
        this.teclado = new Scanner(System.in);
    }

    /**
     * Constructor por defecto
     */
    public A_MenuInicial(){

        this.teclado = new Scanner(System.in);
    }


    public void inicio() {
        if (menuInicial.size() <= 0) {
            addMenuInicial();
        }
        StringBuilder mensajeInicio = new StringBuilder();

        for (int i = 0; i < menuInicial.size(); i++) {
            mensajeInicio.append((i + 1) + ") " + menuInicial.get(i) + "\n");
        }

        int eleccion = obtenerEnteroPositivo(teclado,"====================\n  MENU INICIAL\n" + mensajeInicio.toString() + "====================");
        teclado.nextLine();
        while (eleccion <= 0 || eleccion > menuInicial.size()) {
            eleccion = obtenerEnteroPositivo(teclado,"====================\n  MENU INICIAL\n" + mensajeInicio.toString() + "====================");

        }
        switch (eleccion) {
            case 1:
                menuExcursiones();
                break;
            case 2:
                menuSocios();
                break;
            case 3:
                menuInscripciones();
                break;
            default:
                System.out.println("Cerrando programa...");
                break;
        }
    }

    public void addMenuInicial() {
        menuInicial.add("Excursiones");
        menuInicial.add("Socios");
        menuInicial.add("Inscripciones");
        menuInicial.add("Salir");
    }

    public void addMenuExcursiones() {
        menuExcursiones.add("Mostrar excursiones");
        menuExcursiones.add("Añadir excursion");
        menuExcursiones.add("Eliminar excursion");
        menuExcursiones.add("Atras");
        menuExcursiones.add("Salir");
    }

    public void addMenuSocios() {
        menuSocios.add("Mostrar Socios");
        menuSocios.add("Añadir socio Federado");
        menuSocios.add("Añadir socio Infantil");
        menuSocios.add("Añadir socio Estandar");
        menuSocios.add("Eliminar socio");
        menuSocios.add("Modificar seguro socio estandar");
        menuSocios.add("Mostrar facturas");
        menuSocios.add("Atras");
        menuSocios.add("Salir");
    }

    public void addSubMenuSocios() {
        submenuSocios.add("Mostrar todos los Socios");
        submenuSocios.add("Mostrar socios estandar");
        submenuSocios.add("Mostrar sociocios Federados");
        submenuSocios.add("Mostrar socios infantiles");
        submenuSocios.add("Inicio");
        submenuSocios.add("Atras");
        submenuSocios.add("Salir");
    }

    public void addMenuInscripciones() {
        menuInscripciones.add("Mostrar Inscripciones");
        menuInscripciones.add("Añadir inscripcion");
        menuInscripciones.add("Eliminar inscripcion");
        menuInscripciones.add("Atras");
        menuInscripciones.add("Salir");
    }

    private void menuExcursiones() {
        if (menuExcursiones.size() <= 0) {
            addMenuExcursiones();
        }
        StringBuilder mensajeExcursiones = new StringBuilder();

        for (int i = 0; i < menuExcursiones.size(); i++) {
            mensajeExcursiones.append((i + 1) + ") " + menuExcursiones.get(i) + "\n");
        }


        int eleccion = obtenerEnteroPositivo(teclado,"====================\n  MENU EXCURSIONES\n" + mensajeExcursiones.toString() + "====================");
        teclado.nextLine();

        while (eleccion <= 0 || eleccion > menuExcursiones.size()) {

            eleccion = obtenerEnteroPositivo(teclado,"====================\n  MENU EXCURSIONES\n" + mensajeExcursiones.toString() + "====================");
            teclado.nextLine();
        }

        switch (eleccion) {
            //MOSTRAR EXCURSION POR FILTRO DE FECHA
            case 1:
                LocalDate fechaIni = null;
                LocalDate fechaFin = null;

                boolean fechasCorrectas = true;

                do {
                    fechaIni = obtenerFecha(teclado, "FechaIni");
                    fechaFin = obtenerFecha(teclado, "FechaFin");

                    //La fecha de inicio debe ser anterior o igual a la de fin
                    if (fechaIni.isAfter(fechaFin)) {
                        System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin");
                        fechasCorrectas = false;
                    } else {
                        fechasCorrectas = true;
                    }
                } while (!fechasCorrectas);


                ArrayList<E0_Excursiones> excusiones = controlador.filtrarExcursiones(fechaIni, fechaFin);

                mostrar(excusiones);//muestra por pantalla la lista de excursiones filtradas
                menuExcursiones();


                break;
            case 2:
                //AÑADIR EXCURSIÓN
                System.out.println("introduce el codigo");
                String codigo = this.teclado.nextLine();
                System.out.println("introduce la descripción");
                String descr = this.teclado.nextLine();

                //ATRIBUTOS
                boolean controlFecha = true;
                LocalDate fecha;


                //Controla que la fecha de la excursión añadida no sea anterior a la fecha actual
                do{
                    fecha = obtenerFecha(teclado, "");// llama al método validar fecha correcta

                    if(fecha.isBefore(LocalDate.now())){
                        controlFecha = false;
                        System.out.println("La fecha de la excursión no puede ser anterior a la fecha actual");
                    }
                    else{
                        controlFecha = true;
                    }

                }while(!controlFecha);

                //Controla que el valor introducido sea de tipo entero y mayor que 0.
                int dias = obtenerEnteroPositivo(teclado,"introduce el numero de dias");

                teclado.nextLine();// limpia buffer
                //Controla que el valor introducido sea de tipo doble y admite el limitar decimal "." o "," .

                double precio = obtenerDoublePositivo(teclado,"Introduce precio por dia");

                controlador.añadirExcursion(codigo, descr, fecha, dias, precio);
                menuExcursiones();
                break;
            case 3:
                //ELIMINAR EXCURSION
                System.out.println("Introduce el codigo de la excursión a eliminar");
                String codigoEliminar = this.teclado.nextLine();
                controlador.eliminarExcursion(codigoEliminar);
                menuExcursiones();
                break;
            case 4:
                inicio();
                break;
            default:
                System.out.println("Cerrando programa...");
                break;
        }
    }

    private void menuSocios() {
        if (menuSocios.size() <= 0) {
            addMenuSocios();
        }
        StringBuilder mensajeSocios = new StringBuilder();

        for (int i = 0; i < menuSocios.size(); i++) {
            mensajeSocios.append((i + 1) + ") " + menuSocios.get(i) + "\n");
        }


        int eleccion = obtenerEnteroPositivo(teclado,"====================\n  MENU SOCIOS\n" + mensajeSocios.toString() + "====================");
        teclado.nextLine();

        while (eleccion <= 0 || eleccion > menuSocios.size()) {

            eleccion = obtenerEnteroPositivo(teclado,"====================\n  MENU SOCIOS\n" + mensajeSocios.toString() + "====================");
            teclado.nextLine();
        }

        switch (eleccion) {
            //MOSTRAR SOCIOS
            case 1:
                menusubSocios();
                break;
            // CREAR SOCIO FEDERADO
            case 2:
                int numFederado = obtenerEnteroPositivo(teclado," - Numero Socio: ");
                    teclado.nextLine();
                System.out.println("- Nombre: ");
                String nombreFederado = teclado.nextLine();
                System.out.println("- Nif: ");
                String nifFederado = teclado.nextLine();
                System.out.println("Elige una federacion del siguiente listado:");
                ArrayList<D0_Federacion> federaciones = controlador.mostrarFederaciones();

                for(int b=0; b<federaciones.size();b++){
                    System.out.println("    "+(b+1)+") "+federaciones.get(b).getNombre());
                }
                int eleccionFederacion = obtenerEnteroPositivo(teclado,"- Selecciona una Federacion:");
                teclado.nextLine();

                while(eleccionFederacion<=0 || eleccionFederacion>federaciones.size()){
                    System.out.println("Opcion no valida");
                    for(int b=0; b<federaciones.size();b++){

                        System.out.println("    "+(b+1)+") "+federaciones.get(b).getNombre());
                    }

    
                    eleccionFederacion = obtenerEnteroPositivo(teclado,"- Selecciona una Federacion:");
                        teclado.nextLine();
                }

                String nomFederacion = federaciones.get(eleccionFederacion-1).getNombre();
                String codigoFederacion = federaciones.get(eleccionFederacion-1).getCodigo();

                controlador.CrearSocioFederado(numFederado, nombreFederado, nifFederado, codigoFederacion, nomFederacion);

                System.out.println("Socio federado " + nombreFederado + " con numero " + numFederado + " ha sido creado");

                inicio();
                break;
            //CREAR SOCIO INFANTIL
            case 3:

                int numInfantil = obtenerEnteroPositivo(teclado,"- Numero socio: ");
                    teclado.nextLine();
                System.out.println("- Nombre: ");
                String nombreInfantil = teclado.nextLine();

                ArrayList<B1_SocioEstandar> socioEstandar = controlador.mostrarSocioEstandar();

                System.out.println("====================================="); 
                System.out.println("Socios Estándar existentes:");
                
                for(int a = 0; a<socioEstandar.size(); a++){
                    System.out.println("    "+ (a+1) + ") "+socioEstandar.get(a).getNombre());
                }


                int eleccion_padreInfantil =obtenerEnteroPositivo(teclado,"Cual es el padre o madre del socio infantil?");
                    teclado.nextLine();

                while(eleccion_padreInfantil<=0 || eleccion_padreInfantil>socioEstandar.size()){
                    System.out.println("Opcion no valida");
                    for(int a = 0; a<socioEstandar.size(); a++){
                        System.out.println("    "+ (a+1) + ") "+socioEstandar.get(a).getNombre()+" - " + socioEstandar.get(a).getNumSocio());
                    }

                    eleccion_padreInfantil = obtenerEnteroPositivo(teclado,"Cual es el padre o madre del socio infantil?");
                        teclado.nextLine();
                }

                int numSocioEstandarInfantil = socioEstandar.get(eleccion_padreInfantil-1).getNumSocio();

                controlador.CrearSocioInfantil(numInfantil,nombreInfantil,numSocioEstandarInfantil);

                System.out.println("Socio infantil " + nombreInfantil + " con numero " + numInfantil + " ha sido creado");

                inicio();
                break;
            //CREAR SOCIO ESTANDAR
            case 4:               

                int numEstandar = obtenerEnteroPositivo(teclado,"- Numero Socio:");
                teclado.nextLine();
                
                System.out.println("- Nombre: ");
                String nombreEstandar = teclado.nextLine();
                
                System.out.println("- Nif:");
                String nifEstandar = teclado.nextLine();
                System.out.println("Seguros disponibles:");

                ArrayList<C0_Seguro> seguros = controlador.mostrarSeguros();

                for(int x = 0; x<seguros.size(); x++){
                    System.out.println("    "+(x +1)+") " + seguros.get(x).getTipoSeguro() + " - " + seguros.get(x).getPrecioSeguro());
                }
                int eleccionSeguro = obtenerEnteroPositivo(teclado,"Selecciona un seguro:");
                    teclado.nextLine();

                while(eleccionSeguro<=0 || eleccionSeguro>seguros.size()){
                    System.out.println("Opcion no valida");
                    System.out.println("- Selecciona un seguro:");
                    
                    for(int x = 0; x<seguros.size(); x++){
                        System.out.println("    "+(x +1)+") " + seguros.get(x).getTipoSeguro() + " - " + seguros.get(x).getPrecioSeguro());
                    }
                    eleccionSeguro = obtenerEnteroPositivo(teclado,"- Selecciona un seguro:");
                        teclado.nextLine();
                }

                C0_Seguro.tipoSeguro tipoSeguro = seguros.get(eleccionSeguro-1).getTipoSeguro();
                int precios = seguros.get(eleccionSeguro-1).getPrecioSeguro();
                
                System.out.println("elemento escogido " + tipoSeguro + " con precio " + precios);
                C0_Seguro seguro = new C0_Seguro(tipoSeguro, precios);

                controlador.CrearSocioEstandar(numEstandar, nombreEstandar, nifEstandar, seguro);
                
                System.out.println("Socio Estandar " + nombreEstandar + " con numero " + numEstandar + " ha sido creado");

                inicio();
                break;
            //FUNCION ELIMINAR SOCIO 
            case 5:
                ArrayList<B1_SocioEstandar> socioEstandarE = controlador.mostrarSocioEstandar();
                ArrayList<B2_SocioFederado> socioFederadosE = controlador.mostrarSocioFederados();
                ArrayList<B3_SocioInfantil> socioInfantilE = controlador.mostrarSocioInfantil();
                
                System.out.println("///////////////<SOCIOS FEDERADOS>///////////////");
                for(int x=0; x<socioFederadosE.size();x++){
                    System.out.println(socioFederadosE.get(x).getNumSocio()+" - " + socioFederadosE.get(x).getNombre());
                }
                System.out.println("///////////////<SOCIOS ESTANDAR>///////////////");
                for(int x=0; x<socioEstandarE.size();x++){
                    System.out.println(socioEstandarE.get(x).getNumSocio()+" - " + socioEstandarE.get(x).getNombre());
                }
                System.out.println("///////////////<SOCIOS INFANTILES>///////////////");
                for(int x=0; x<socioInfantilE.size();x++){
                    System.out.println(socioInfantilE.get(x).getNumSocio()+" - " + socioInfantilE.get(x).getNombre());
                }
                
                // Solicitar al usuario que ingrese el número de socio a eliminar

                int numSocio = obtenerEnteroPositivo(teclado,"Introduce el número de socio a eliminar:");
                teclado.nextLine(); // Limpiar el buffer

                
            
                // Verificar si el socio está inscrito en alguna excursión
                ArrayList<F0_Inscripciones> inscripciones = controlador.mostrarInscripcionesPorSocio(numSocio);
                if (!inscripciones.isEmpty()) {
                    System.out.println("El socio con número " + numSocio + " está inscrito en las siguientes excursiones:");
                    for (F0_Inscripciones inscripcion : inscripciones) {
                        System.out.println("Excursión: " + inscripcion.getExcursion().getDescripcion());
                    }
                    System.out.println("No se puede eliminar al socio porque está inscrito en una o más excursiones.");
                } 
                else {

                    boolean eliminado = controlador.eliminarSocio(numSocio);
            
                    if (eliminado) {
                        System.out.println("El socio con número " + numSocio + " ha sido eliminado correctamente.");
                    } else {
                        System.out.println("No se pudo encontrar un socio con el número " + numSocio + ".");
                    }
                }
            
                inicio();
                break;

            //FUNCION CAMBIAR SEGURO
            case 6:
            ArrayList<B1_SocioEstandar> socioEstandarS = controlador.mostrarSocioEstandar();
            
            for(int a = 0; a<socioEstandarS.size(); a++){
                System.out.println((a+1) + ")  "+ socioEstandarS.get(a).getNombre()+
                " - " + socioEstandarS.get(a).getSeguro());
            }


            int numSocioS = obtenerEnteroPositivo(teclado,"Introduce el número de socio:");
            teclado.nextLine();
            while(numSocioS <= 0 || numSocioS > socioEstandarS.size()){
                System.out.println("opcion incorrecta");
                numSocioS = obtenerEnteroPositivo(teclado,"Introduce el número de socio:");
            }

            String NifSeguro=socioEstandarS.get(numSocioS-1).getNif();
            int NumSocioSeguro=socioEstandarS.get(numSocioS-1).getNumSocio();
            String NombreSocioSeguro=socioEstandarS.get(numSocioS-1).getNombre(); 
    
            System.out.println("Seguros disponibles:");

                ArrayList<C0_Seguro> segurosS = controlador.mostrarSeguros();

                for(int x = 0; x<segurosS.size(); x++){
                    System.out.println("    "+(x +1)+") " + segurosS.get(x).getTipoSeguro() + " - " + segurosS.get(x).getPrecioSeguro());
                }
                int eleccionSeguroS = obtenerEnteroPositivo(teclado,"Selecciona un seguro:");
                    teclado.nextLine();

                    while(eleccionSeguroS <= 0 || eleccionSeguroS > segurosS.size()){
                        System.out.println("opcion incorrecta");
                        eleccionSeguroS = obtenerEnteroPositivo(teclado,"Selecciona un seguro:");
                    }
                    C0_Seguro.tipoSeguro tipoSeguroS = segurosS.get(eleccionSeguroS-1).getTipoSeguro();
                    int preciosS = segurosS.get(eleccionSeguroS-1).getPrecioSeguro();
                    
                    System.out.println("elemento escogido " + tipoSeguroS + " con precio " + preciosS);
                    C0_Seguro seguroS = new C0_Seguro(tipoSeguroS, preciosS);
    
                    controlador.eliminarSocio(NumSocioSeguro);
                    
                    controlador.CrearSocioEstandar(NumSocioSeguro, NombreSocioSeguro, NifSeguro, seguroS); 
                    
                    System.out.println("Se le ha cambiado el seguro al socio " + NombreSocioSeguro);
                
                inicio();
                break;

             //FUNCION FACTURA 
            case 7:           
                


                int tipDeSocio =obtenerEnteroPositivo(teclado,"Selecciona tipo de socio a mostrar:\n 1)Federado\n 2)Estandar\n 3)Infantil");
                teclado.nextLine();
                while(tipDeSocio <= 0 || tipDeSocio > 3){
                    System.out.println("opcion incorrecta");
                    tipDeSocio =obtenerEnteroPositivo(teclado,"Selecciona tipo de socio a mostrar:\n 1)Federado\n 2)Estandar\n 3)Infantil");
                }

                
                switch (tipDeSocio) {
                    //FACTURA SOCIO FEDERADO
                    case 1:
                    ArrayList<B2_SocioFederado> socioFederadosFac = controlador.mostrarSocioFederados();
                    
                    for(int a = 0; a < socioFederadosFac.size(); a++){
                        System.out.println((a+1)+")"+ socioFederadosFac.get(a).getNombre());
                    }
                    

                    int eleccionNumSocioFed = obtenerEnteroPositivo(teclado,"Selecciona una opcion:");
                    teclado.nextLine();
                    while(eleccionNumSocioFed<=0 || eleccionNumSocioFed > socioFederadosFac.size()){
                        System.out.println("opción no valida");
                        eleccionNumSocioFed = obtenerEnteroPositivo(teclado,"Selecciona una opcion:");
                        teclado.nextLine();

                    }
                    int numSocioFed = socioFederadosFac.get(eleccionNumSocioFed-1).getNumSocio();
                    


                    // Verificar si el número de socio seleccionado existe
                    boolean encontrado = false;
                    for (B2_SocioFederado socio : socioFederadosFac) {
                        if (socio.getNumSocio() == numSocioFed) {
                            encontrado = true;
                            break;
                        }
                    }
                    
                    if (encontrado) {
                        B2_SocioFederado socioSeleccionado = null;
                        // Buscar el socio seleccionado
                        for (B2_SocioFederado socio : socioFederadosFac) {
                            if (socio.getNumSocio() == numSocioFed) {
                                socioSeleccionado = socio;
                                break;
                            }
                        }

                        LocalDate fechaInicio = null;
                        boolean fechaInicioValida = false;
                        while (!fechaInicioValida) {
                            try {
                                System.out.println("Introduce la fecha de inicio (YYYY-MM-DD):");
                                fechaInicio = LocalDate.parse(teclado.nextLine());
                                fechaInicioValida = true;
                            } catch (DateTimeParseException e) {
                                System.out.println("Formato de fecha incorrecto. Por favor, introduzca una fecha en formato YYYY-MM-DD.");
                            }
                        }

                        LocalDate fechaFin = null;
                        boolean fechaFinValida = false;
                        while (!fechaFinValida) {
                            try {
                                System.out.println("Introduce la fecha de fin (YYYY-MM-DD):");
                                fechaFin = LocalDate.parse(teclado.nextLine());
                                fechaFinValida = true;
                            } catch (DateTimeParseException e) {
                                System.out.println("Formato de fecha incorrecto. Por favor, introduzca una fecha en formato YYYY-MM-DD.");
                            }
                        }

                        // Verificar si la fecha de fin es posterior a la fecha de inicio
                        if (fechaInicio.isAfter(fechaFin)) {
                            System.out.println("La fecha de fin debe ser posterior a la fecha de inicio. Por favor, introduzca fechas válidas.");

                        } else {
                            // Lógica para calcular la factura mensual basada en el rango de fechas seleccionado
                            double facturaMensual = 0.0;
                            LocalDate fechaActual = fechaInicio;

                            while (!fechaActual.isAfter(fechaFin)) {
                            // Calcula la factura mensual para el mes actual y lo suma al total
                                facturaMensual += controlador.mostrarFacturaMensualSocioFederado(socioSeleccionado, fechaActual);
                                fechaActual = fechaActual.plusMonths(1);
                            }

                            ArrayList<F0_Inscripciones> inscripcioness = controlador.mostrarInscripcionesPorSocio(numSocioFed);                        
                            ArrayList<F0_Inscripciones> excursionesMensuales = new ArrayList<>(); // Cambié el nombre de la lista
                    
                            for (int j = 0; j < inscripcioness.size(); j++) {
                                LocalDate fechaExcursion = inscripcioness.get(j).getExcursion().getFecha();
                            
                                if (fechaExcursion.isAfter(fechaInicio) && fechaExcursion.isBefore(fechaFin)) {
                                    excursionesMensuales.add(inscripcioness.get(j)); 
                                }
                            }
                            double precioFinal = 0.0;

                            for (int r = 0; r < excursionesMensuales.size(); r++) {
                                double precioExcursion = excursionesMensuales.get(r).getExcursion().getPrecio();
                                double descuentoExcursion = socioFederadosFac.get(eleccionNumSocioFed-1).getDES_PRECIO_EXCURSION();
                                double precioConDescuento= precioExcursion-(precioExcursion*descuentoExcursion);
                                precioFinal += precioConDescuento;
                                
                                
                            } 
                            double facturaFinalFed = facturaMensual+precioFinal;
                            System.out.println("///////////////////////////////////");
                            System.out.println("La cuota del socio federado es: \n  " + facturaMensual + "e");                            
                            System.out.println("El seguro del socio federado es: \n   0" );
                            System.out.println("Excursiones del socio federado:");
                            for (int q = 0; q < excursionesMensuales.size(); q++) {
                                System.out.println(excursionesMensuales.get(q).getExcursion().nombreExcursion());                               
                                
                            } 

                            System.out.println("El coste de las excursiones para el socio federado es: \n   " + precioFinal + "e");
                            System.out.println("Coste de la factura final socio federado es: \n   " + facturaFinalFed + "e");
                            System.out.println("///////////////////////////////////");
                        }
                    } else {
                        System.out.println("Número de socio inválido. Por favor, seleccione un número de socio válido.");
                    }
                        
                    inicio();
                    break;
                    //FACTURA SOCIO ESTANDAR
                    case 2:
                        ArrayList<B1_SocioEstandar> socioEstandarFac = controlador.mostrarSocioEstandar();

                        for(int a = 0; a<socioEstandarFac.size(); a++){
                            System.out.println((a+1)+")"+ socioEstandarFac.get(a).getNombre());
                        }

                        int eleccionNumSocioEst = obtenerEnteroPositivo(teclado,"Selecciona una opcion:");
                        teclado.nextLine();
                        while(eleccionNumSocioEst<=0 || eleccionNumSocioEst > socioEstandarFac.size()){
                            System.out.println("opción no valida");
                            eleccionNumSocioEst = obtenerEnteroPositivo(teclado,"Selecciona una opcion:");
                            teclado.nextLine();

                        }
                        int tipoSocioEst = socioEstandarFac.get(eleccionNumSocioEst-1).getNumSocio();

                        // Verificar si el número de socio seleccionado existe
                        boolean encontradoE = false;
                        for (B1_SocioEstandar socio : socioEstandarFac) {
                            if (socio.getNumSocio() == tipoSocioEst) {
                                encontradoE = true;
                                break;
                            }
                        }
                        
                        if (encontradoE) {
                            B1_SocioEstandar socioSeleccionado = null;
                            // Buscar el socio seleccionado
                            for (B1_SocioEstandar socio : socioEstandarFac) {
                                if (socio.getNumSocio() == tipoSocioEst) {
                                    socioSeleccionado = socio;
                                    break;

                                }
                            }
                            double seguroAnualE= 0; //socioSeleccionado.getSeguro().getPrecioSeguro();

                            LocalDate fechaInicio = null;
                            boolean fechaInicioValida = false;
                            while (!fechaInicioValida) {
                                try {
                                    System.out.println("Introduce la fecha de inicio (YYYY-MM-DD):");
                                    fechaInicio = LocalDate.parse(teclado.nextLine());
                                    fechaInicioValida = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println("Formato de fecha incorrecto. Por favor, introduzca una fecha en formato YYYY-MM-DD.");
                                }
                            }

                            LocalDate fechaFin = null;
                            boolean fechaFinValida = false;
                            while (!fechaFinValida) {
                                try {
                                    System.out.println("Introduce la fecha de fin (YYYY-MM-DD):");
                                    fechaFin = LocalDate.parse(teclado.nextLine());
                                    fechaFinValida = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println("Formato de fecha incorrecto. Por favor, introduzca una fecha en formato YYYY-MM-DD.");
                                }
                            }

                            // Verificar si la fecha de fin es posterior a la fecha de inicio
                            if (fechaInicio.isAfter(fechaFin)) {
                                System.out.println("La fecha de fin debe ser posterior a la fecha de inicio. Por favor, introduzca fechas válidas.");
                                // Aquí podrías volver a pedir al usuario que ingrese las fechas
                            } else {
                                // Lógica para calcular la factura mensual basada en el rango de fechas seleccionado
                                double facturaMensual = 0.0;
                                LocalDate fechaActual = fechaInicio;
                                while (!fechaActual.isAfter(fechaFin)) {                                    
                                    facturaMensual += controlador.mostrarFacturaMensualSocioEstandar(socioSeleccionado, fechaActual);
                                    fechaActual = fechaActual.plusMonths(1);
                                }
                                ArrayList<F0_Inscripciones> inscripcioness = controlador.mostrarInscripcionesPorSocio(tipoSocioEst);                        
                                ArrayList<F0_Inscripciones> excursionesMensuales = new ArrayList<>(); 
                    
                                for (int j = 0; j < inscripcioness.size(); j++) {
                                    LocalDate fechaExcursion = inscripcioness.get(j).getExcursion().getFecha();
                            
                                if (fechaExcursion.isAfter(fechaInicio) && fechaExcursion.isBefore(fechaFin)) {
                                    excursionesMensuales.add(inscripcioness.get(j)); 
                                }
                            }
                                double precioFinal = 0.0;

                                for (int r = 0; r < excursionesMensuales.size(); r++) {
                                    double precioExcursion = excursionesMensuales.get(r).getExcursion().getPrecio();
                                    precioFinal += precioExcursion;                                
                                
                                    } 
                                
                                System.out.println("///////////////////////////////////");
                                System.out.println("La cuota del socio estandar es: \n  " + facturaMensual + "e");                            
                                System.out.println("El seguro del socio estandar es:\n  "+ seguroAnualE + "e" );
                                System.out.println("Excursiones del socio estandar:");

                                for (int q = 0; q < excursionesMensuales.size(); q++) {
                                    System.out.println(excursionesMensuales.get(q).getExcursion().nombreExcursion());                                  
                                } 
                            double facturaFinalFed = facturaMensual+precioFinal+seguroAnualE;
                            System.out.println("El coste de las excursiones para el socio estandar es: \n  " + precioFinal + "e");
                            System.out.println("Coste de la factura final socio estandar es: \n  " + facturaFinalFed + "e");
                            System.out.println("///////////////////////////////////");
                            }
                        } 
                        else {
                            System.out.println("Numero de socio invalido. Por favor, seleccione un numero de socio valido.");
                        }
                        inicio();
                        break;
                    //FACTURAS SOCIO INFANTIL
                    default:
                        ArrayList<B3_SocioInfantil> socioInfantilFac = controlador.mostrarSocioInfantil();

                        for(int a = 0; a<socioInfantilFac.size(); a++){
                            System.out.println((a+1)+")"+ socioInfantilFac.get(a).getNombre());
                        }

                        int eleccionNumSocioInf = obtenerEnteroPositivo(teclado,"Selecciona una opcion:");
                        teclado.nextLine();
                        while(eleccionNumSocioInf<=0 || eleccionNumSocioInf > socioInfantilFac.size()){
                            System.out.println("opción no valida");
                            eleccionNumSocioInf = obtenerEnteroPositivo(teclado,"Selecciona una opcion:");
                            teclado.nextLine();

                        }
                        int tipDeSocioInf = socioInfantilFac.get(eleccionNumSocioInf-1).getNumSocio();

                        // Verificar si el número de socio seleccionado existe
                        boolean encontradoInf = false;
                        for (B3_SocioInfantil socio : socioInfantilFac) {
                            if (socio.getNumSocio() == tipDeSocioInf) {
                                encontradoInf = true;
                                break;
                            }
                        }
                        
                        if (encontradoInf) {
                            B3_SocioInfantil socioSeleccionado = null;
                            // Buscar el socio seleccionado
                            for (B3_SocioInfantil socio : socioInfantilFac) {
                                if (socio.getNumSocio() ==tipDeSocioInf) {
                                    socioSeleccionado = socio;
                                    break;
                                }
                            }

                            LocalDate fechaInicio = null;
                            boolean fechaInicioValida = false;
                            while (!fechaInicioValida) {
                                try {
                                    System.out.println("Introduce la fecha de inicio (YYYY-MM-DD):");
                                    fechaInicio = LocalDate.parse(teclado.nextLine());
                                    fechaInicioValida = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println("Formato de fecha incorrecto. Por favor, introduzca una fecha en formato YYYY-MM-DD.");
                                }
                            }

                            LocalDate fechaFin = null;
                            boolean fechaFinValida = false;
                            while (!fechaFinValida) {
                                try {
                                    System.out.println("Introduce la fecha de fin (YYYY-MM-DD):");
                                    fechaFin = LocalDate.parse(teclado.nextLine());
                                    fechaFinValida = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println("Formato de fecha incorrecto. Por favor, introduzca una fecha en formato YYYY-MM-DD.");
                                }
                            }
                            
                            // Verificar si la fecha de fin es posterior a la fecha de inicio
                            if (fechaInicio.isAfter(fechaFin)) {
                                System.out.println("La fecha de fin debe ser posterior a la fecha de inicio. Por favor, introduzca fechas válidas.");
                                // Aquí podrías volver a pedir al usuario que ingrese las fechas
                            } else {
                                // Lógica para calcular la factura mensual basada en el rango de fechas seleccionado
                                double facturaMensual = 0.0;
                                LocalDate fechaActual = fechaInicio;
                                while (!fechaActual.isAfter(fechaFin)) {
                                    facturaMensual += controlador.mostrarFacturaMensualSocioInfantil(socioSeleccionado, fechaActual);
                                    fechaActual = fechaActual.plusMonths(1);
                                }

                                ArrayList<F0_Inscripciones> inscripcioness = controlador.mostrarInscripcionesPorSocio(tipDeSocioInf);                        
                                ArrayList<F0_Inscripciones> excursionesMensuales = new ArrayList<>(); // Cambié el nombre de la lista

                                for (int j = 0; j < inscripcioness.size(); j++) {
                                    LocalDate fechaExcursion = inscripcioness.get(j).getExcursion().getFecha();
                            
                                if (fechaExcursion.isAfter(fechaInicio) && fechaExcursion.isBefore(fechaFin)) {
                                    excursionesMensuales.add(inscripcioness.get(j)); 
                                }
                            }
                                double precioFinal = 0.0;

                            for (int r = 0; r < excursionesMensuales.size(); r++) {
                                double precioExcursion = excursionesMensuales.get(r).getExcursion().getPrecio();
                                precioFinal += precioExcursion;                                
                            } 
                            
                            double facturaFinalFed = facturaMensual+precioFinal;
                            System.out.println("///////////////////////////////////");
                            System.out.println("La cuota del socio infantil es: \n  " + facturaMensual + "e");                            
                            System.out.println("El seguro del socio infantil es de 0 Euros");
                            System.out.println("Excuriones del socio infantil:");

                            for (int q = 0; q < excursionesMensuales.size(); q++) {
                                System.out.println(excursionesMensuales.get(q).getExcursion().nombreExcursion());                                        
                            } 

                            System.out.println("El coste de las excursiones para el socio infantil es: \n  " + precioFinal + "e");
                            System.out.println("Coste de la factura final socio infantil es: \n  " + facturaFinalFed + "e");
                            System.out.println("///////////////////////////////////");
                        } 
                        } 
                        else {
                            System.out.println("Numero de socio invalido. Por favor, seleccione un numero de socio valido.");
                        }
                        inicio();
                        break;
                }
                    break;
                case 8:
                inicio();
                break;

            default:
                System.out.println("Cerrando programa...");
                break;
        }
    }

    private void menusubSocios() {
        if (submenuSocios.size() <= 0) {
            addSubMenuSocios();
        }
        StringBuilder mensajesubSocios = new StringBuilder();

        for (int i = 0; i < submenuSocios.size(); i++) {
            mensajesubSocios.append((i + 1) + ") " + submenuSocios.get(i) + "\n");
        }

        int eleccion = obtenerEnteroPositivo(teclado,"====================\n MENU SOCIOS\n" + mensajesubSocios.toString() + "====================");
        teclado.nextLine();

        while (eleccion <= 0 || eleccion > submenuSocios.size()) {
            eleccion = obtenerEnteroPositivo(teclado,"====================\n MENU SOCIOS\n" + mensajesubSocios.toString() + "====================");
            teclado.nextLine();
        }

        switch (eleccion) {
            case 1:
                System.out.println("funcion MostrarSocios");
                ArrayList<B1_SocioEstandar> socioEstandarX = controlador.mostrarSocioEstandar();
                ArrayList<B2_SocioFederado> socioFederadosX = controlador.mostrarSocioFederados();
                ArrayList<B3_SocioInfantil> socioInfantilX = controlador.mostrarSocioInfantil();
                System.out.println("///////////////<SOCIOS FEDERADOS>///////////////");
                mostrar(socioFederadosX);
                System.out.println("///////////////<SOCIOS ESTANDAR>///////////////");
                mostrar(socioEstandarX);
                System.out.println("///////////////<SOCIOS INFANTILES>///////////////");
                mostrar(socioInfantilX);
                System.out.println("//////////////////////////////");
                inicio();
                break;
            //VER SOCIOS ESTANDAR
            case 2:
                ArrayList<B1_SocioEstandar> socioEstandar = controlador.mostrarSocioEstandar();
                System.out.println("LISTADO DE SOCIOS ESTANDAR:");
                mostrar(socioEstandar);
                inicio();
                break;
            //VER SOCIOS FEDERADOS
            case 3:
                ArrayList<B2_SocioFederado> socioFederados = controlador.mostrarSocioFederados();
                System.out.println("LISTADO DE SOCIOS FEDERADOS:");
                mostrar(socioFederados);
                inicio();
                break;
            case 4:
                ArrayList<B3_SocioInfantil> socioInfantil = controlador.mostrarSocioInfantil();
                System.out.println("LISTADO DE SOCIOS INFANTILES:");
                mostrar(socioInfantil);
                inicio();
                break;
            case 5:
                inicio();
                break;
            case 6:
                menuSocios();
                break;
            default:
                System.out.println("Cerrando programa...");
                break;
        }
    }

    private void menuInscripciones() {
        if (menuInscripciones.size() <= 0) {
            addMenuInscripciones();
        }
        StringBuilder mensajeInscripciones = new StringBuilder();

        for (int i = 0; i < menuInscripciones.size(); i++) {
            mensajeInscripciones.append((i + 1) + ") " + menuInscripciones.get(i) + "\n");
        }

        System.out.println();
        int eleccion = obtenerEnteroPositivo(teclado,"====================\n MENU INSCRIPCIONES\n" + mensajeInscripciones.toString() + "====================");
        teclado.nextLine();

        while (eleccion <= 0 || eleccion > menuInscripciones.size()) {
            eleccion = obtenerEnteroPositivo(teclado,"====================\n MENU INSCRIPCIONES\n" + mensajeInscripciones.toString() + "====================");
            teclado.nextLine();
        }

        switch (eleccion) {
             case 1:
                 System.out.println("LISTADO DE INSCRIPCIONES:");

                 int filtrado = obtenerEnteroPositivo(teclado,"1. Filtrar por socio:\n2. Filtrar por fecha");

                 if (filtrado == 1){

                     int numSocio = obtenerEnteroPositivo(teclado,"- Numero Socio:");
                     ArrayList<F0_Inscripciones> inscripciones = controlador.mostrarInscripcionesPorSocio(numSocio);
                     mostrar(inscripciones);
                     if(inscripciones.isEmpty()){
                         System.out.println("El socio numero "+numSocio+" no tiene ninguna inscripción");
                     }

                 }
                 else if (filtrado == 2){
                     teclado.nextLine();
                     LocalDate fechaIni;
                     LocalDate fechaFin;
                     boolean fechasCorrectas = true;
                     do {
                         fechaIni = obtenerFecha(teclado, "FechaIni");
                         fechaFin = obtenerFecha(teclado, "FechaFin");
                     //La fecha de inicio debe ser anterior o igual a la de fin
                         if (fechaIni.isAfter(fechaFin)) {
                             System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin");
                             fechasCorrectas = false;
                         } else {
                             fechasCorrectas = true;
                         }
                     } while (!fechasCorrectas);

                     ArrayList<F0_Inscripciones> inscripciones = controlador.mostrarInscripcionesPorFechas(fechaIni, fechaFin);
                     mostrar(inscripciones);
                     if(inscripciones.isEmpty()){
                         System.out.println("No hay inscripción en esas fechas");
                     }
                 }
                 else {
                     System.out.println("Opción incorrecta");
                 }

                menuInscripciones();
                break;
            case 2:


                    int numInscripcion = obtenerEnteroPositivo(teclado,"- Numero Inscripcion:");
                    teclado.nextLine();

                    //Nif socio
                    List<?extends B0_Socio> socioEstandar = controlador.mostrarSocioEstandar();
                    System.out.println("LISTADO DE SOCIOS ESTANDAR:");
                    mostrarDatosSocioSimplificado(socioEstandar);
                    List<?extends B0_Socio> socioFederados = controlador.mostrarSocioFederados();
                    System.out.println("LISTADO DE SOCIOS FEDERADOS:");
                    mostrarDatosSocioSimplificado(socioFederados);
                    List<?extends B0_Socio> socioInfantil = controlador.mostrarSocioInfantil();
                    System.out.println("LISTADO DE SOCIOS INFANTILES:");
                    mostrarDatosSocioSimplificado(socioInfantil);

                    System.out.println("- Numero Socio:");
                    int numSocio = obtenerEnteroPositivo(teclado,"");
                    teclado.nextLine();

                    //Buscar el socio en ambas listas
                    B0_Socio encontrado = controlador.buscarSocio(socioEstandar, socioFederados, socioInfantil, numSocio);

                    //Si no se ha encontrado socio
                    if (encontrado == null) {
                        System.out.println("El socio no existe se procede a crearlo");
                        System.out.println("- Nombre: ");
                        String nombre = teclado.nextLine();

                        System.out.println("- Nif:");
                        String nif = teclado.nextLine();


                        int tipoSocio = obtenerEnteroPositivo(teclado, "Tipo de Socio.\n1. Socio federado\n2. Socio estandar\n3. Socio infantil");
                        while (tipoSocio < 1 || tipoSocio > 3) {
                            System.out.println("opcion incorrecta");
                            tipoSocio = obtenerEnteroPositivo(teclado, "Tipo de Socio.\n1. Socio federado\n2. Socio estandar\n3. Socio infantil");

                        }
                        if (tipoSocio == 1) {
                            System.out.println("- Selecciona una Federacion:");
                            ArrayList<D0_Federacion> federaciones = controlador.mostrarFederaciones();

                            for (int b = 0; b < federaciones.size(); b++) {
                                System.out.println("    " + (b + 1) + ") " + federaciones.get(b).getNombre());
                            }

                            int eleccionFederacion = obtenerEnteroPositivo(teclado, "");
                            teclado.nextLine();

                            while (eleccionFederacion <= 0 || eleccionFederacion > federaciones.size()) {
                                for (int b = 0; b < federaciones.size(); b++) {
                                    System.out.println("    " + (b + 1) + ") " + federaciones.get(b).getNombre());
                                }

                                eleccionFederacion = obtenerEnteroPositivo(teclado, "");
                                teclado.nextLine();
                            }

                            String nomFederacion = federaciones.get(eleccionFederacion - 1).getNombre();
                            String codigoFederacion = federaciones.get(eleccionFederacion - 1).getCodigo();

                            controlador.CrearSocioFederado(numSocio, nombre, nif, codigoFederacion, nomFederacion);

                            System.out.println("Socio federado " + nombre + " con numero " + numSocio + " ha sido creado");

                        } else if (tipoSocio == 2) {
                            System.out.println("- Seguros disponibles:");

                            ArrayList<C0_Seguro> seguros = controlador.mostrarSeguros();

                            for (int x = 0; x < seguros.size(); x++) {
                                System.out.println("    " + (x + 1) + ") " + seguros.get(x).getTipoSeguro() + " - " + seguros.get(x).getPrecioSeguro());
                            }
                            int eleccionSeguro = obtenerEnteroPositivo(teclado, "");
                            teclado.nextLine();

                            while (eleccionSeguro <= 0 || eleccionSeguro > seguros.size()) {
                                System.out.println("- Seguros disponibles:");

                                for (int x = 0; x < seguros.size(); x++) {
                                    System.out.println("    " + (x + 1) + ") " + seguros.get(x).getTipoSeguro() + " - " + seguros.get(x).getPrecioSeguro());
                                }
                                eleccionSeguro = obtenerEnteroPositivo(teclado, "");
                                teclado.nextLine();
                            }

                            C0_Seguro.tipoSeguro tipoSeguro = seguros.get(eleccionSeguro - 1).getTipoSeguro();
                            int precios = seguros.get(eleccionSeguro - 1).getPrecioSeguro();

                            System.out.println("elemento escogido " + tipoSeguro + " con precio " + precios);
                            C0_Seguro seguro = new C0_Seguro(tipoSeguro, precios);

                            controlador.CrearSocioEstandar(numSocio, nombre, nif, seguro);
                            System.out.println("Socio Estandar " + nombre + " con numero " + numSocio + " ha sido creado");
                        } else if (tipoSocio == 3) {


                            socioEstandar = controlador.mostrarSocioEstandar();

                            System.out.println("=====================================");
                            System.out.println("Socios Estándar existentes:");

                            for (int a = 0; a < socioEstandar.size(); a++) {
                                System.out.println("    " + (a + 1) + ") " + socioEstandar.get(a).getNombre());
                            }


                            int eleccion_padreInfantil = obtenerEnteroPositivo(teclado, "Cual es el padre o madre del socio infantil?");
                            teclado.nextLine();

                            while (eleccion_padreInfantil <= 0 || eleccion_padreInfantil > socioEstandar.size()) {
                                System.out.println("Opcion no valida");
                                for (int a = 0; a < socioEstandar.size(); a++) {
                                    System.out.println("    " + (a + 1) + ") " + socioEstandar.get(a).getNombre() + " - " + socioEstandar.get(a).getNumSocio());
                                }

                                eleccion_padreInfantil = obtenerEnteroPositivo(teclado, "Cual es el padre o madre del socio infantil?");
                                teclado.nextLine();
                            }

                            int numSocioEstandarInfantil = socioEstandar.get(eleccion_padreInfantil - 1).getNumSocio();

                            controlador.CrearSocioInfantil(numSocio, nombre, numSocioEstandarInfantil);

                            System.out.println("Socio infantil " + nombre + " con numero " + numSocio + " ha sido creado");

                        }

                        //Buscar nuevo el socio porque ahora ya se dio de alta
                        encontrado = controlador.buscarSocio(socioEstandar, socioFederados, socioInfantil, numSocio);
                    }
                    else{

                    //Si el socio se ha encontrado
                    ArrayList<E0_Excursiones> excursiones = controlador.mostrarExcursiones();
                    System.out.println("LISTADO DE EXCURSIONES:");
                    mostrarDatosExcursiones(excursiones);

                    System.out.println("- Codigo excursion:");
                    String codigoExcursion = teclado.nextLine();

                    E0_Excursiones excursion = controlador.buscarExcursion(excursiones, codigoExcursion);

                    if (excursion != null) {

                        //Clonar para no modificar los datos originales
                        E0_Excursiones copiaExcursion = new E0_Excursiones(excursion.getCodigo(), excursion.getDescripcion(),
                                                        excursion.getFecha(), excursion.getNumDias(), excursion.getPrecio());

                        //Aplicar descuento o recargos
                        if (encontrado instanceof B1_SocioEstandar) {
                            //Si es socio estandar el precio final sera el precio de la excursion mas
                            //el precio del seguro contratado
                            //copiaExcursion.setPrecio(excursion.getPrecio() + ((B1_SocioEstandar) encontrado).getSeguro().getPrecioSeguro());
                        }
                        else if (encontrado instanceof B2_SocioFederado){
                            //Si el socio es federado el precio final será el precio de la excursion menos el
                            //10% de descuento
                            copiaExcursion.setPrecio(excursion.getPrecio() - (excursion.getPrecio()) * 0.10);
                        }

                        //Creamos incripcion
                        controlador.CrearInscripcion(numInscripcion, encontrado, copiaExcursion);

                        System.out.println("Inscripción insertada correctamente");
                    } else {
                        System.out.println("No existe una excursion con el codigo: " + codigoExcursion);
                    }
                }


                menuInscripciones();
                break;
            case 3:
                //ELIMINAR INSCRIPCION


                    numInscripcion = obtenerEnteroPositivo(teclado,"Introduce el numero de la inscripcion a eliminar");
                    boolean borrado = controlador.eliminarInscripcion(numInscripcion);
                    if (borrado) {
                        System.out.println("Inscripción eliminada correctamente");
                    } else {
                        System.out.println("No se ha podido eliminar la inscripcion con el numero: " + numInscripcion);
                    }


                this.menuInscripciones();
                break;
            case 4:
                inicio();
                break;
            default:
                System.out.println("Cerrando programa...");
                break;
        }
    }

    //EXCEPCIONES
    /**
     * Devuelve una fecha validada con formato AAA-MM-DD corrrecto.
     *
     * @param teclado La entrada por Teclado
     * @param tipo Un string con el nombre de la fecha ("fecha_inicio" por ejemplo)
     * @return una fecha validada
     */
    public static LocalDate obtenerFecha(Scanner teclado, String tipo) {
        LocalDate fecha = null;
        boolean formatoFecha = false;

        do {
            try {
                System.out.println("Introduce la fecha de " + tipo + " en formato AAAA-MM-DD:");
                fecha = LocalDate.parse(teclado.nextLine());
                formatoFecha = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha incorrecto. Debe ser AAAA-MM-DD.");
            }
        } while (!formatoFecha);

        return fecha;
    }

    /**
     * Devuelve un numero validado como entero y mayor que 0
     *
     * @param teclado Un teclado para introducir el valor
     * @param mensaje El mensaje a mostrar como entrada
     * @return Un entero
     */

    public int obtenerEnteroPositivo(Scanner teclado,String mensaje){
        int numero = 0;
        boolean esValido = false;
        while(!esValido) {
            try {
                System.out.println(mensaje);
                numero = this.teclado.nextInt();
                if(numero > 0) {
                    esValido= true;
                }
                else{
                    System.out.println("Error debe ser un valor mayor a 0");
                }
            } catch (InputMismatchException e) {
                System.out.println("El valor debe ser numerico de tipo entero");
                teclado.nextLine();

            }
        }
        return numero;

    }

    /**
     *
     * @param teclado Un teclado para introducir el valor
     * @param mensaje El mensaje a mostrar como entrada
     * @return Un Double
     */
    public double obtenerDoublePositivo(Scanner teclado, String mensaje){
        double numero = 0.0;
        boolean esValido = false;

        while(!esValido) {
            try {
                System.out.println(mensaje);
                String input = teclado.nextLine().replace(',', '.');
                numero = Double.parseDouble(input);
                if(numero > 0) {
                    esValido= true;
                }
                else{
                    System.out.println("Error debe ser un valor mayor a 0");
                }

            } catch (NumberFormatException e) {
                System.out.println("El valor debe ser númerico  ");

            }
        }
        return numero;
    }
    // OTROS METODOS
    /**
     * Este metodo muestra por pantalla el contenido del ArrayList pasado como parametro
     * @param datosMostrar El Arraylist a mostrar
     * @param <T> cualquier elemento
     */

    public <T> void mostrar(ArrayList<T> datosMostrar) {
        for (T elemento : datosMostrar) {
            System.out.println(elemento.toString());
        }
    }

    public void mostrarDatosSocioEstandar(ArrayList<B1_SocioEstandar> datosMostrar) {
        for (B1_SocioEstandar elemento : datosMostrar) {
            System.out.println("-----------------------");
            System.out.println(elemento.getNumSocio() + "-" + elemento.getNombre());
        }
    }

    public void mostrarDatosSocioFederado(ArrayList<B2_SocioFederado> datosMostrar) {
        for (B2_SocioFederado elemento : datosMostrar) {
            System.out.println("-----------------------");
            System.out.println(elemento.getNumSocio() + "-" + elemento.getNombre());
        }
    }

    public void mostrarDatosSocioSimplificado(List<?extends B0_Socio> datosMostrar) {
        for (B0_Socio elemento : datosMostrar) {
            System.out.println("-----------------------");
            System.out.println(elemento.getNumSocio() + "-" + elemento.getNombre());
        }
    }

    public void mostrarDatosExcursiones(ArrayList<E0_Excursiones> datosMostrar) {
        for (E0_Excursiones elemento : datosMostrar) {
            System.out.println("-----------------------");
            System.out.println(elemento.getCodigo() + "-" + elemento.getDescripcion());
        }
    }
    
    /**
     * Carga Inicial de datos con valores por defecto.
     */
    public void cargadatos(){
        controlador.cargarFederaciones();
        controlador.cargarSeguros();
        controlador.cargaInicialSociosEstandar();
        controlador.cargaInicialSociosFederados();
        controlador.cargaInicialSociosInfantiles();
        controlador.cargaInicialExcursiones();
        controlador.cargaInicialInscripciones();
        controlador.cargarSegurosArray();
    }
}
