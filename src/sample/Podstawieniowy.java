package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Podstawieniowy extends Cipher {

    Stage stage;
    zmianaPodstawien sceneController;

    public Podstawieniowy() throws IOException {
        prepareScene();

    }

    private void prepareScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("scenes/zmianaPodstawien.fxml")));
        Parent firstPage = (Parent) fxmlLoader.load();
        stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);  //brak paska minimalizacji i zamknięcia
        stage.setTitle("Zmiana podstawień");
        stage.setScene(new Scene(firstPage));

        sceneController = fxmlLoader.getController();
        sceneController.setup();
    }

    @FXML
    void change(ActionEvent event) {
        try {
            stage.show();
        } catch (Exception e) {
            System.out.println("Bład w wyświetleniu strony!");
        }
    }

    @FXML
    void cipher(ActionEvent event) {
        outText1.setText(encrypt(inText.getText()));
    }

    @FXML
    void decipher(ActionEvent event) {
        outText1.setText(decrypt(inText.getText()));
    }

    public String encrypt(String text) {
        Alphabet alphabet = new Alphabet();
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            String resultC = Character.toString(c);
            if (Character.isLetter(c))
                for (int i = 0; i < alphabet.getLength(); i++)
                    if (alphabet.getCharAt(i, c) == c)    // size check at alphabet.getCharAt
                        resultC=(sceneController.getSubAlphabet().get(i));

                result.append(resultC);
        }
        return result.toString();
    }

    public String decrypt(String text) {
        String alphabet = new Alphabet().getAlphabet();
        StringBuilder result = new StringBuilder();
        int size = sceneController.getCharacterLength();
        {
            try {
                for (String z : text.split("\\n"))
                {        // podział tekstu na linie
                    for (String s : z.split(" "))
                    {    // podział tekstu na słowa
                        for (int i = 0; i <= s.length() - size; i += size)
                            for (int j = 0; j < sceneController.getSubAlphabet().size(); j++)
                            {
                                String temporary = s.substring(i, i + size);
                                if (temporary.equals(sceneController.getSubAlphabet().get(j)))
                                    result.append(alphabet.charAt(j));
                            }
                        result.append(" ");
                    }
                    result.append("\n");
                }
            } catch (Exception e) {
                System.out.println("Błędna tablica znaków!");
                result = new StringBuilder("Błędna tablica znaków! Sprawdź Podstawienia.");
            }
            return result.toString();
        }
    }
}


