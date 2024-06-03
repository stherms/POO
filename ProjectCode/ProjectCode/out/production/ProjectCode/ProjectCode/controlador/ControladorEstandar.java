package ProjectCode.controlador;

import ProjectCode.modelo.B1_SocioEstandar;
import ProjectCode.modelo.B3_SocioInfantil;
import ProjectCode.modelo.C0_Seguro;
import ProjectCode.modelo.ConexionHibernate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorEstandar implements Initializable {

    @FXML
    private TextField tFNumSocio;
    @FXML
    private TextField tFNombre;
    @FXML
    private TextField tFNIF;
    @FXML
    private Button btnAceptar;
    @FXML
    private ComboBox cBSeguro;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            cBSeguro.getItems().addAll(
                    "BASICO",
                    "COMPLETO"
            );
            cBSeguro.getSelectionModel().select(0);

            ConexionHibernate conexion = new ConexionHibernate();
            Controlador controlador = new Controlador(conexion);
            if (ControladorInicio.modoEjecucion.equals("mostrar")) {
                ArrayList<B1_SocioEstandar> listaEstandar = controlador.mostrarSocioEstandar("F", ControladorMostrar.idBuscar);

                btnAceptar.setDisable(true);
                tFNumSocio.setText(String.valueOf(listaEstandar.get(0).getNumSocio()));
                tFNombre.setText(listaEstandar.get(0).getNombre());
                tFNIF.setText(String.valueOf(listaEstandar.get(0).getNif()));
                cBSeguro.getSelectionModel().select(listaEstandar.get(0).getSeguro());
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

    public void aceptarEstandar() {
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
        else {
            try {
                ConexionHibernate conexion = new ConexionHibernate();
                Controlador controlador = new Controlador(conexion);

                int precio = 0;
                if (cBSeguro.getSelectionModel().getSelectedIndex() == 0){
                    precio = 15;
                }
                else {
                    precio = 30;
                }

                C0_Seguro seguro = new C0_Seguro(C0_Seguro.tipoSeguro.valueOf(String.valueOf(cBSeguro.getSelectionModel().getSelectedItem())), precio);
                controlador.CrearSocioEstandar(Integer.parseInt(tFNumSocio.getText()), tFNombre.getText(), tFNIF.getText(), seguro);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Socio estandar insertado correctamente");
                alert.show();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }
}
