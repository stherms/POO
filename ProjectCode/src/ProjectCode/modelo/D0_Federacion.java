package ProjectCode.modelo;

public class D0_Federacion {
    private String codigo;
    private String nombre;

    /**
     * Constructor clase federacion
     * @param codigo código de la federación
     * @param nombre nombre de la federación
     */
    public D0_Federacion(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public D0_Federacion() {

    }
    /**
     * getter del código
     * @return código
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * setter del código de la federación
     * @param codigo: código asignado a la federación
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * getter del nombre de la federación
     * @return nombre  
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setter del nombre de la federación
     * @param nombre: nombre asignado a la federación
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * método que muestra los datos de las excursiones
     * @return datos del objeto federación en una cadena de texto
     */
    @Override
    public String toString() {
        return  "      - Codigo: " + codigo + '\n' +
                "      - Nombre: " + nombre + '\n' ;
    }
}