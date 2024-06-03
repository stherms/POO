package ProjectCode.controlador;

import ProjectCode.modelo.B3_SocioInfantil;
import ProjectCode.modelo.ConexionHibernate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorInfantil implements Initializable {

    @FXML
    private TextField tFNumSocio;
    @FXML
    private TextField tFNombre;
    @FXML
    private TextField tFPadre;
    @FXML
    private Button btnAceptar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ConexionHibernate conexion = new ConexionHibernate();
            Controlador controlador = new Controlador(conexion);
            if (ControladorInicio.modoEjecucion.equals("mostrar")) {
                ArrayList<B3_SocioInfantil> listaInfantil = controlador.mostrarSocioInfantil("F", ControladorMostrar.idBuscar);

                btnAceptar.setDisable(true);
                tFNumSocio.setText(String.valueOf(listaInfantil.get(0).getNumSocio()));
                tFNombre.setText(listaInfantil.get(0).getNombre());
                tFPadre.setText(String.valueOf(listaInfantil.get(0).getNumSocioPadre()));
            }
            else {
                btnAceptar.setDisable(false);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void volver(ActionEvent event) {
        // Obtiene la Stage (ventana) a partir del botón que generó el evento
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void aceptarInfantil() {
        Alert alert = null;

        //comprobar que se han introducido todos los datos
        if (tFNumSocio.getText().equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Introduce el numero de socio");
            alert.show();
        }
        else if (tFNombre.getText().equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Introduce el nombre del socio");
            alert.show();
        }
        else if (tFPadre.getText().equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Introduce el numero de socio del padre");
            alert.show();
        }
        else {
            try {
                ConexionHibernate conexion = new ConexionHibernate();
                Controlador controlador = new Controlador(conexion);
                controlador.CrearSocioInfantil(Integer.parseInt(tFNumSocio.getText()), tFNombre.getText(), Integer.parseInt(tFPadre.getText()));
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Socio infantil insertado correctamente");
                alert.show();
                conexion.desconectar();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
