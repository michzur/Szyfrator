package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class zmianaPodstawien {
    Alphabet alphabet=new Alphabet();
    private ArrayList<String> subAlphabet = new ArrayList<>();
    private final int characterDefaultLength = 2;
    private int characterLength = characterDefaultLength;

    public int getCharacterLength(){
        return characterLength;
    }

    public ArrayList<String> getSubAlphabet() {
        return subAlphabet;
    }


    public void setup() {   // Ustawianie domyślnej tablicę znaków
        dlugoscZnaku.setText(String.valueOf(characterDefaultLength));
        int i = 10; // 10 = pierwsza litera (a)
        for (Node node : dlugoscZ.getChildren())
        {
            String number = Integer.toString(i);
            if (node instanceof TextField
                    && !node.getId().equals("dlugoscZnaku"))
            {
                ((TextField) node).setText(number);
                subAlphabet.add(number);
                i++;
            }
        }
    }

    public void changeAlphabet() {
        subAlphabet = new ArrayList<>();
        characterLength = Integer.parseInt(dlugoscZnaku.getText());

        for (int i = 0; i < alphabet.getLength(); i++)
            for (Node node : dlugoscZ.getChildren())
                if (node instanceof TextField
                        && !node.getId().equals("dlugoscZnaku")
                        && node.getId().equals(Character.toString(alphabet.getCharAt(i))))
                {
                    fixLength((TextField) node);    // dopasowywanie wielkości do characterLength
                    subAlphabet.add(((TextField) node).getText());
                }
    }

    public void fixLength (TextField letter) {
        if(letter.getText().length() != characterLength)
        {
            StringBuilder s = new StringBuilder();
            if (letter.getText().length() > characterLength)
            {
                while (s.length() < characterLength - characterDefaultLength)
                    s.append("0");

                letter.setText((alphabet.getIndexOf(letter.getId()) + 10) + s.toString());
            }
            else {
                while (letter.getText().length() < characterLength)
                    letter.setText(letter.getText() + "0");

                letter.setText(letter.getText() + s);
                 }
        }
    }
    @FXML
    private AnchorPane dlugoscZ;

    @FXML
    private TextField dlugoscZnaku;

    @FXML
    void dlugoscZnaku(ActionEvent event) {

    }

    @FXML
    void accept(ActionEvent event){
        try {
            changeAlphabet();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); //wyjscie z okna
        }catch(Exception e){
            System.out.println("Błędna długość znaku! Wprowadź prawidłową liczbę.");
        }
    }
}
