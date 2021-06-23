package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
@FXML
    void changeStage(ActionEvent event){
        String stageID=((Button)event.getSource()).getId();
        String stageName=((Button)event.getSource()).getText();
        String stagePath="scenes/"+stageID+".fxml";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource((stagePath)));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(stageName);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("Bład w wyświetleniu strony!");
        }
    }
    @FXML
    void exit() { //wyjście z programu
        System.exit(0);
    }

}
