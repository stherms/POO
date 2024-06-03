package ProjectCode.controlador;

import ProjectCode.modelo.B2_SocioFederado;
import ProjectCode.modelo.ConexionHibernate;
import com.sun.org.apache.xml.internal.security.Init;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorInicio {

    public static String modoEjecucion = "";

    public void mostrarSocios() {
        try {
            modoEjecucion = "mostrar";
            Parent root = FXMLLoader.load(getClass().getResource("/ProjectCode/vista/MostrarSocios.fxml"));
            Scene scene = new Scene(root, 700, 800);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void borrarSocios() {
        try {
            modoEjecucion = "borrar";
            Parent root = FXMLLoader.load(getClass().getResource("/ProjectCode/vista/MostrarSocios.fxml"));
            Scene scene = new Scene(root, 700, 800);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirInsertarInfantil() {
        try {
            ControladorInicio.modoEjecucion = "insertar";
            Parent root = FXMLLoader.load(getClass().getResource("/ProjectCode/vista/SocioInfantilDetalle.fxml"));
            Scene scene = new Scene(root, 640, 400);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirInsertarEstandar() {
        try {
            ControladorInicio.modoEjecucion = "insertar";
            Parent root = FXMLLoader.load(getClass().getResource("/ProjectCode/vista/SocioEstandarDetalle.fxml"));
            Scene scene = new Scene(root, 640, 400);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirInsertarFederado() {
        try {
            ControladorInicio.modoEjecucion = "insertar";
            Parent root = FXMLLoader.load(getClass().getResource("/ProjectCode/vista/SocioFederadoDetalle.fxml"));
            Scene scene = new Scene(root, 640, 400);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void salir() {
        System.exit(0);
    }
}
