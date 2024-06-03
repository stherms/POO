package ProjectCode.modelo;

public class F0_Inscripciones {
    private int numInscripcion;
    private B0_Socio socio;
    private E0_Excursiones excursion;

    /**
     * constructor clase inscripciones
     * @param numInscripcion número de inscripción
     * @param socio socio que se inscribe
     * @param excursion excursión en que se inscribe
     */
    public F0_Inscripciones(int numInscripcion, B0_Socio socio, E0_Excursiones excursion) {
        this.numInscripcion = numInscripcion;
        this.socio = socio;
        this.excursion = excursion;
    }

    /**
     * getter número de inscripción
     * @return numInscripcion
     */
    public int getNumInscripcion() {
        return numInscripcion;
    }

    /**
     * setter número de inscripción
     * @param numInscripcion: número asignado la inscripción
     */
    public void setNumInscripcion(int numInscripcion) {
        this.numInscripcion = numInscripcion;
    }

    /**
     * getter socio que se inscribe
     * @return socio
     */
    public B0_Socio getSocio() {
        return socio;
    }

    /**
     * setter socio que se inscribe
     * @param socio: datos del socio que se inscribe
     */
    public void setSocio(B0_Socio socio) {
        this.socio = socio;
    }

    /**
     * getter para obtener la excursión asociada a una excursión 
     * @return excursion
     */
    public E0_Excursiones getExcursion() {
        return excursion;
    }

    /**
     * setter para asignar una inscripción a una excursión
     * @param excursion: excursión asignada
     */
    public void setExcursion(E0_Excursiones excursion) {
        this.excursion = excursion;
    }

    /**
     * método que muestra los datos de las excursiones
     * @return datos del objeto inscripción en una cadena de texto
     */
    @Override
    public String toString() {
        return "Numero de inscripcion: " + numInscripcion +
                "\nSocio: " + socio.toString() +
                "\nExcursion: " + excursion.toString();
    }
}
