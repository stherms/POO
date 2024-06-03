package ProjectCode.modelo;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class socioDAO {
    /**
     * Método CRUD "delete" eliminar el socio infantil elegido según el número de socio del padre de la BBDD
     */
    public static boolean eliminarSocio(int numSocio, ConexionHibernate conexion) throws SQLException {

        Session session = conexion.getSession();
        //Creo la transaccion a partir de la sesion de hibernate
        Transaction tx = session.beginTransaction();

        try {
            try {
                B1_SocioEstandar socioEstandar = (B1_SocioEstandar) session.load(B1_SocioEstandar.class, numSocio);
                session.delete(socioEstandar);
            }
            catch (ObjectNotFoundException ex) {
            }

            try {
                B2_SocioFederado socioFederado = (B2_SocioFederado) session.load(B2_SocioFederado.class, numSocio);
                session.delete(socioFederado);
            }
            catch (ObjectNotFoundException ex) {
            }

            try {
                B3_SocioInfantil socioInfantil = (B3_SocioInfantil) session.load(B3_SocioInfantil.class, numSocio);
                session.delete(socioInfantil);
            }
            catch (ObjectNotFoundException ex) {
            }

            tx.commit();

            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        }

        return false;
    }
}
