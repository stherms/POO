package ProjectCode.modelo;

/**
 *
 * La clase Socio representa una clases Abstracta con datos comunes de todos los socios, que son el numero de socio
 * y el nif
 *
 *@author ProjectCode
 *@version 1.0
 */

public abstract class B0_Socio {

    private int numSocio;
    private String nombre;

    //CONSTRUCTOR

    /**
     *Constructor de la clase Socio.
     *
     * @param numSocio El numero de socio.
     * @param nombre El nombre del socio.
     */

    public B0_Socio(int numSocio, String nombre) {
        this.numSocio = numSocio;
        this.nombre = nombre;
    }

    //Constructor vacio
    public B0_Socio() {

    }
    /**
     * Obtiene el número de socio.
     *
     * @return El número de socio.
     */

    public int getNumSocio() {
        return numSocio;
    }

    /**
     * Establece el número de socio.
     *
     * @param numSocio El número de socio.
     */

    public void setNumSocio(int numSocio) {
        this.numSocio = numSocio;
    }

    /**
     * Obtiene el nombre del Socio.
     *
     * @return El nombre del Socio.
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del Socio
     *
     * @param nombre El nombre del Socio
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "B0_Socio{" +
                "numSocio=" + numSocio +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}