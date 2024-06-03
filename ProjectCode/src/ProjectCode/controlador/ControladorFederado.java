package ProjectCode.controlador;

import ProjectCode.modelo.ConexionHibernate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorFederado implements Initializable {

    @FXML
    private TextField tFNumSocio;
    @FXML
    private TextField tFNombre;
    @FXML
    private TextField tFNIF;
    @FXML
    private TextField tFCodFederacion;
    @FXML
    private TextField tFNomFederacion;
    @FXML
    private Button btnAceptar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void volver(ActionEvent event) {
        // Obtiene la Stage (ventana) a partir del botón que generó el evento
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void aceptarFederado() {
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
        else if (tFNIF.getText().equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Introduce el NIF del socio");
            alert.show();
        }
        else if (tFCodFederacion.getText().equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Introduce el codigo de federación");
            alert.show();
        }
        else if (tFNomFederacion.getText().equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Introduce el nombre de federación");
            alert.show();
        }
        else {
            try {
                ConexionHibernate conexion = new ConexionHibernate();
                Controlador controlador = new Controlador(conexion);
                controlador.CrearSocioFederado(Integer.parseInt(tFNumSocio.getText()), tFNombre.getText(), tFNIF.getText(),
                                                tFCodFederacion.getText(), tFNomFederacion.getText());
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Socio federado insertado correctamente");
                alert.show();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }
}
