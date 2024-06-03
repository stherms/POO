package ProjectCode.modelo;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * La clase SocioInfantil representa un Socio Infantil que hereda de la clase Padre Socio, con atributos propios
 * numSocioPadre y el descuento que tiene. *
 *
 *@author ProjectCode
 *@version 1.0
 */
public class B3_SocioInfantil extends B0_Socio {
    
    private int cuota=100;
    private int numSocioPadre;
    private final double DESCUENTO = 0.5;
    private static ArrayList<B3_SocioInfantil> SociosInfantiles = new ArrayList<>();

    /**
     *
     * @param numSocioPadre El numero de Socio Infantil
     * @param nombre El nombre del Socio Infantil
     * @param numSocioPadre El numero de Socio de un padre
     */

    public B3_SocioInfantil(int numSocio, String nombre, int numSocioPadre) {
        super(numSocio, nombre);
        this.numSocioPadre = numSocioPadre;
    }
    public B3_SocioInfantil() {
        System.out.println("prueba socio infantil");
    }

    /**
     * Obtiene el numero de Socio del padre.
     *
     * @return El numero de Socio del padre.
     */
    public int getNumSocioPadre() {
        return numSocioPadre;
    }


    /**
     * Establece el numero de Socio del padre.
     *
     * @param numSocioPadre El numero de Socio del padre
     */
    public void setNumSocioPadre(int numSocioPadre) {
        this.numSocioPadre = numSocioPadre;
    }

    /**
     * Obtiene el porcentaje de descuento que tiene el Socio Infantil.
     *
     * @return El porcentaje de descuento que tiene el Socio Infantil.
     */
    public double getDESCUENTO() {
        return getDESCUENTO();
    }

    /**
     * Establece la cuota del socio infantil
     * @param cuota la cuota para el socio infantil
     */
    public void setCuota(int cuota){
        this.cuota=cuota;
    
        }
    
        public int getCuota(){
            return cuota;   
    
        }
    /**
     * Obtiene los datos del Socio infantil
     *
     * @return Los datos del Socio Infantil
     */

    @Override
    public String toString() {
        return "    DATOS ESENCIALES"
        +"\n        - Num socio: "+ getNumSocio()
        +"\n        - Nombre: " + getNombre()
        +"\n    DATOS CODIGO FAMILIAR:"
        +"\n        - Num socio familiar: " + getNumSocioPadre() +"\n";
    }


}
