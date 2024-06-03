package ProjectCode.controlador;

import ProjectCode.modelo.B1_SocioEstandar;
import ProjectCode.modelo.B2_SocioFederado;
import ProjectCode.modelo.B3_SocioInfantil;
import ProjectCode.modelo.ConexionHibernate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorMostrar implements Initializable {

    public static int idBuscar = -1;
    @FXML
    private TableView<B2_SocioFederado> tablaFederados;
    @FXML
    private TableView<B1_SocioEstandar> tablaEstandar;
    @FXML
    private TableView<B3_SocioInfantil> tablaInfantil;
    @FXML
    private Button btnBorrar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ConexionHibernate conexion = new ConexionHibernate();
            Controlador controlador = new Controlador(conexion);
            cargarSocioFederado(controlador);
            cargarSocioEstandar(controlador);
            cargarSocioInfantil(controlador);

            if (ControladorInicio.modoEjecucion.equals("borrar")) {
                btnBorrar.setDisable(false);
            }
            else if (ControladorInicio.modoEjecucion.equals("mostrar")) {
                btnBorrar.setDisable(true);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cargarSocioFederado( Controlador controlador){
        //Cargamos el array de socios del hibernate
        ArrayList<B2_SocioFederado> listaFed = controlador.mostrarSocioFederados();
        //Convertir el arraylist de socios en una lista observable de javafx
        ObservableList<B2_SocioFederado> datos = FXCollections.observableArrayList(listaFed);

        //A cada columna de la tabla le añadimos el dato
        tablaFederados.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("numSocio"));
        tablaFederados.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tablaFederados.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nif"));
        tablaFederados.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("codigoFederacion"));
        tablaFederados.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("nomFederacion"));
        //Cargamos todos las columnas en la tabla
        tablaFederados.setItems(datos);
    }

    private void cargarSocioEstandar(Controlador controlador){
        ArrayList<B1_SocioEstandar> listaEstandar = controlador.mostrarSocioEstandar("T",  idBuscar);
        ObservableList<B1_SocioEstandar> datos = FXCollections.observableArrayList(listaEstandar);

        tablaEstandar.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("numSocio"));
        tablaEstandar.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tablaEstandar.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nif"));
        tablaEstandar.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("seguro"));
        tablaEstandar.setItems(datos);
    }

    private void cargarSocioInfantil(Controlador controlador){
        ArrayList<B3_SocioInfantil> listaInfantil = controlador.mostrarSocioInfantil("T", idBuscar);
        ObservableList<B3_SocioInfantil> datos = FXCollections.observableArrayList(listaInfantil);

        tablaInfantil.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("numSocio"));
        tablaInfantil.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tablaInfantil.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("numSocioPadre"));
        tablaInfantil.setItems(datos);
    }

    public void volver(ActionEvent event) {
        // Obtiene la Stage (ventana) a partir del botón que generó el evento
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void borrarSocios() {
        try {
            ConexionHibernate conexion = new ConexionHibernate();
            Controlador controlador = new Controlador(conexion);
            if (tablaInfantil.getSelectionModel().getSelectedItem() != null) {
                idBuscar = tablaInfantil.getSelectionModel().getSelectedItem().getNumSocio();
                controlador.eliminarSocio(idBuscar, conexion);
                tablaInfantil.getItems().remove(tablaInfantil.getSelectionModel().getSelectedItem());
            }
            if (tablaEstandar.getSelectionModel().getSelectedItem() != null) {
                idBuscar = tablaEstandar.getSelectionModel().getSelectedItem().getNumSocio();
                controlador.eliminarSocio(idBuscar, conexion);
                tablaEstandar.getItems().remove(tablaEstandar.getSelectionModel().getSelectedItem());
            }
            if (tablaFederados.getSelectionModel().getSelectedItem() != null) {
                idBuscar = tablaFederados.getSelectionModel().getSelectedItem().getNumSocio();
                controlador.eliminarSocio(idBuscar, conexion);
                tablaFederados.getItems().remove(tablaFederados.getSelectionModel().getSelectedItem());
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Socio eliminado correctamente");
            alert.show();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void abrirSocioInfantilDetalle() {
        //Si se hace clic para mostrar los datos
        if (ControladorInicio.modoEjecucion.equals("mostrar")) {
            try {
                if (tablaInfantil.getSelectionModel().getSelectedItem() != null) {
                    idBuscar = tablaInfantil.getSelectionModel().getSelectedItem().getNumSocio();
                    Parent root = FXMLLoader.load(getClass().getResource("/ProjectCode/vista/SocioInfantilDetalle.fxml"));
                    Scene scene = new Scene(root, 700, 600);
                    Stage primaryStage = new Stage();
                    primaryStage.initStyle(StageStyle.DECORATED);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void abrirSocioEstandarDetalle() {
        //Si se hace clic para mostrar los datos
        if (ControladorInicio.modoEjecucion.equals("mostrar")) {
            try {
                if (tablaEstandar.getSelectionModel().getSelectedItem() != null) {
                    idBuscar = tablaEstandar.getSelectionModel().getSelectedItem().getNumSocio();
                    Parent root = FXMLLoader.load(getClass().getResource("/ProjectCode/vista/SocioEstandarDetalle.fxml"));
                    Scene scene = new Scene(root, 700, 600);
                    Stage primaryStage = new Stage();
                    primaryStage.initStyle(StageStyle.DECORATED);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void abrirSocioFederadoDetalle() {
        //Si se hace clic para mostrar los datos
        if (ControladorInicio.modoEjecucion.equals("mostrar")) {
            try {
                if (tablaEstandar.getSelectionModel().getSelectedItem() != null) {
                    idBuscar = tablaEstandar.getSelectionModel().getSelectedItem().getNumSocio();
                    Parent root = FXMLLoader.load(getClass().getResource("/ProjectCode/vista/SocioFederadoDetalle.fxml"));
                    Scene scene = new Scene(root, 700, 640);
                    Stage primaryStage = new Stage();
                    primaryStage.initStyle(StageStyle.DECORATED);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
