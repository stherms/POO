package ProjectCode.modelo;
import java.util.ArrayList;
import java.util.Scanner;

public class B2_SocioFederado extends B0_Socio {
    private int cuota=100;
    private String nif;
    private final double DES_CUOTA_MENSUAL = 0.05;
    private final double DES_PRECIO_EXCURSION = 0.1;
    private String codigoFederacion;
    private String nomFederacion;
    private static ArrayList<B2_SocioFederado> SociosFederados = new ArrayList<>();

    public B2_SocioFederado(int numSocio, String nombre, String nif, String codigoFederacion, String nomFederacion) {
        super(numSocio, nombre);
        this.nif = nif;
        this.codigoFederacion = codigoFederacion;
        this.nomFederacion = nomFederacion;
        SociosFederados.add(this);
    }

    public B2_SocioFederado() {
        
    }

    /**
     * 
     * @return string
     */
    public String getNif() {
        return nif;
    }

    /**
     * 
     * @param nif
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * 
     * @return double
     */
    public double getDES_CUOTA_MENSUAL() {
        return DES_CUOTA_MENSUAL;
    }

    /**
     * 
     * @return double
     */
    public double getDES_PRECIO_EXCURSION() {
        return DES_PRECIO_EXCURSION;
    }

    /**
     * 
     * @return D0_Federacion
     */
    public String getCodigoFederacion() {
        return codigoFederacion;
    }

    /**
     *
     * @return D0_Federacion
     */
    public String getNomFederacion() {
        return nomFederacion;
    }

    /**
     * 
     * @param cuota
     */
    public void setCuota(int cuota){
        this.cuota=cuota;
    
        }
    
    /**
     * 
     * @return int
     */
    public int getCuota(){
        return cuota;   
    
    }

    public void setCodigoFederacion(String codigoFederacion) {
        this.codigoFederacion = codigoFederacion;
    }

    public void setNomFederacion(String nomFederacion) {
        this.nomFederacion = nomFederacion;
    }

    @Override
    public String toString() {
        return "\n  DATOS ESENCIALES:"
            + "\n      - Numero socio: " + getNumSocio() 
            + "\n      - Nombre: " + getNombre() 
            + "\n      - Nif: " + nif 
            + "\n  DATOS FEDERACION:"
            + "\n"+ "Codigo Federacion: " + getCodigoFederacion()
            + "\n"+ "Codigo Federacion: " + getNomFederacion();
    }
}
