package ProjectCode.modelo;

public class SocioFactory {

    public B0_Socio getSocio(String tipoSocio, int numSocio, String nombre, String nif, C0_Seguro seguro, int numSocioPadre, String codigoFederacion, String nomFederacion) {

        if (tipoSocio == null){
            return null;
        }
        else {
            if (tipoSocio.equals("ESTANDAR")) {
                return new B1_SocioEstandar(numSocio, nombre, nif, seguro);
            } else if (tipoSocio.equals("INFANTIL")) {
                return new B3_SocioInfantil(numSocio, nombre, numSocioPadre);
            } else if (tipoSocio.equals("FEDERADO")) {
                return new B2_SocioFederado(numSocio, nombre, nif, codigoFederacion, nomFederacion);
            } else {
                return null;
            }
        }
    }
}
