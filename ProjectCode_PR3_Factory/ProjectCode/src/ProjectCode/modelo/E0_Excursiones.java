package ProjectCode.modelo;

import java.time.LocalDate;

public class E0_Excursiones {
    private String codigo;
    private String descripcion;
    private LocalDate fecha;
    private int numDias;
    private double precio;

    /**
     * Constructor clase excursiones
     *
     * @param codigo código de la excursión
     * @param descripcion descripción de la excursión
     * @param fecha fecha de la excursión
     * @param numDias duración en días
     * @param precio precio de la excursión
    **/
    public E0_Excursiones(String codigo, String descripcion, LocalDate fecha, int numDias, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.numDias = numDias;
        this.precio = precio;
    }

    /**
     * getter del código
     * @return codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * setter del código
     * @param codigo: valor asignado al código
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * getter de la descripción
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * setter de la descripción
     * @param descripcion: descripción de la excursión asignada
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * getter de la fecha 
     * @return fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * setter de la fecha
     * @param fecha: fecha asignada a la excursión 
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * getter duración en días
     * @return numDias
     */
    public int getNumDias() {
        return numDias;
    }

    /**
     * setter duración en días
     * @param numDias: el total de días de la excursión
     */
    public void setNumDias(int numDias) {
        this.numDias = numDias;
    }

    /**
     * getter precio de la excursión
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * setter precio de la excursión
     * @param precio: precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    /**
     * 
     * @return String
    */
    public String nombreExcursion() {
        return  "- " + descripcion;
    }
    /**
     * método que muestra los datos de las excursiones
     * @return datos del objeto excursiones en una cadena de texto
     */
    @Override
    public String toString() {
        return  "Codigo: " + codigo + '\n' +
                "Descripcion: " + descripcion + '\n' +
                "Fecha: " + fecha + '\n' +
                "Numero dias: " + numDias + '\n' +
                "Precio: " + precio + '\n' ;
    }
}