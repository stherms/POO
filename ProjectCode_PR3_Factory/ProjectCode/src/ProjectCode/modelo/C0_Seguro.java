package ProjectCode.modelo;

public class C0_Seguro {
    //ATRIBUTOS
    private tipoSeguro tipoSeguro;
    private int precioSeguro;

    //ENUM Tipo de seguro
    public enum tipoSeguro{
        BASICO, COMPLETO
    }

    //CONSTRUCTOR
    /**
     * 
     * @param tipoSeguro
     * @param precioSeguro
     */
    public C0_Seguro(tipoSeguro tipoSeguro, int precioSeguro) {
        this.tipoSeguro = tipoSeguro;
        this.precioSeguro = precioSeguro;
    }
    public C0_Seguro() {

    }
    //METODO_TipoSeguro
    /**
     * 
     * @param tipoSeguro 
     */
    public void setTipoSeguro(tipoSeguro tipoSeguro){
        this.tipoSeguro = tipoSeguro;
    }

    /**
     * 
     * @return tipoSeguro
     */
    public tipoSeguro getTipoSeguro(){
        return tipoSeguro;
    }

    //METODO_PrecioSeguro
    /**
     * 
     * @param precioSeguro
     */
    public void setPrecioSeguro(int precioSeguro){
        this.precioSeguro = precioSeguro;
    }
    /**
     * 
     * @return double
     */
    public int getPrecioSeguro(){
        return precioSeguro;
    }

    public String toString() {
        return "    \n       - Tipo seguro: "+getTipoSeguro()
                +"  \n       - Precio: " + getPrecioSeguro() + "e";
    }
}
