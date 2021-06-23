package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Polibiusza extends Cipher {
    private final StringBuilder alphabet = new StringBuilder("aąbcćdeęfghijklłmnńoópqrsśtuwxyzźż");
    private String keyWord ="start";

    public Polibiusza() throws IOException {
    }

    @FXML
    void cipher(ActionEvent event) {
        keyWord=keyWordField.getText();
        outText1.setText(encrypt(inText.getText()));
    }
    @FXML
    void decipher(ActionEvent event) {
        keyWord=keyWordField.getText();
        outText1.setText(decrypt( inText.getText()));
    }

    private final int columns = (int) (Math.ceil((double) alphabet.length() / 10) + 1);   // Obliczanie liczby kolumn zaokrąglone w górę (+1 ze względu na słowo klucz)

    private char[][] createKeyWordTable(){

        char[][] table = new char[9][9];
        for (int j = 0; j < keyWord.length();j++) table[0][j] = keyWord.charAt(j);   // zapełnianie pierwszego rzędu tablicy słowem kluczem
        for (char p : keyWord.toCharArray())
            if (alphabet.indexOf(Character.toString(p)) != -1)
                alphabet.deleteCharAt(alphabet.indexOf(Character.toString(p))); // Wykluczanie słowa klucza z alfabetu
        int index = 0;
        for (int i = 1; i < columns; i++)  // zapełnianie tablicy resztą alfabetu
            for (int j = 0; j < 9; j++)
                if (alphabet.length() - index > 0)
                {
                    table[i][j] = alphabet.charAt(index);
                    index++;
                }return table;
    }
    public String encrypt(String text){
        char[][] table=createKeyWordTable();
        StringBuilder result = new StringBuilder();
            for (char c : text.toLowerCase().toCharArray())  // sprawdzanie pozycji znaku w tablicy
                if (Character.isLetter(c))
                {
                    for (int i = 0; i < columns; i++)
                        for (int j = 0; j < 9; j++)
                            if (table[i][j] == c)
                            {
                                result.append(i);
                                result.append(j);
                                break;
                            }
                } else result.append(c);
                return result.toString();
    }

    public String decrypt(String text){
        StringBuilder result = new StringBuilder();
        char[][]table=createKeyWordTable();
        for(String z: text.split("\\n"))
        {   // Podział tekstu na linie
            for (String s : z.split(" "))
            {   // Podział na wyrazy
                for (int i = 0; i <= s.length() - 2; i += 2)
                    result.append(
                            table[Character.getNumericValue(s.charAt(i))]
                                    [Character.getNumericValue(s.charAt(i + 1))]);

                result.append(" ");
            } result.append("\n");
        }return result.toString();
    }
}





















