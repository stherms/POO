package ProjectCode;


import ProjectCode.controlador.Controlador;
import ProjectCode.modelo.ConexionHibernate;
import ProjectCode.modelo.Datos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ProjectCode/vista/Inicio.fxml"));
            Scene scene = new Scene(root, 640, 400);
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setScene(scene);

            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            //Lanzar el metodo start
            launch(args);
        }
        catch (Exception ex){
           ex.printStackTrace();
        }
    }
}
