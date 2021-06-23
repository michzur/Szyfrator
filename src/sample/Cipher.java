package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Cipher {
Archive archive=new Archive();
String currentArchive;
    public Cipher() throws IOException {

    }
    @FXML
    public Button getKeyWord;

    @FXML
    public void getKeyWord(ActionEvent event) {
    }

    @FXML
    public TextField keyWordField;

    @FXML
    public void back(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); //wyjscie z okna
    }
    @FXML
    public TextField newOffset;

    @FXML
    public Button back1;

    @FXML
    public TextField kode;

    @FXML
    public TextArea inText;

    @FXML
    public TextArea outText1;

    @FXML
    public Button cipher;

    @FXML
    public Button decipher;

    @FXML
    public Button getKode;

    @FXML
    public Button accept;

    private void loadStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("scenes/Archive.fxml")));
        Parent archiveParent = fxmlLoader.load();
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);//brak paska minimalizacji i zamknięcia
        stage.setTitle("Archiwum");
        stage.setScene(new Scene(archiveParent));
        stage.showAndWait();
        Archive scene2Controller = fxmlLoader.getController();
        currentArchive=scene2Controller.archiveSelected;
    }

    @FXML
    public void archiveSave() {
        try {
            loadStage();
            archive.saveFile(outText1.getText(),Integer.parseInt(currentArchive));
        } catch (Exception e) {
            System.out.println("Bład w wyświetleniu strony!");
        }
    }
    
    @FXML
    public void archiveLoad() {
        try {
            loadStage();
            inText.setText(archive.readFile(Integer.parseInt(currentArchive)));
        } catch (Exception e) {
            System.out.println("Bład w wyświetleniu strony!");
        }
    }


    @FXML
    void newOffset(ActionEvent event) {

    }

    @FXML
    void cipher(ActionEvent event) throws IOException {

    }

    @FXML
    void decipher(ActionEvent event){
    }

    @FXML
    void getKode(ActionEvent event) {

    }

    @FXML
    void inText(ActionEvent event) {

    }

    @FXML
    void kode(ActionEvent event) {

    }

    @FXML
    void outText1(ActionEvent event) {

    }
    @FXML
    void keyWordField(ActionEvent event) {

    }
}
