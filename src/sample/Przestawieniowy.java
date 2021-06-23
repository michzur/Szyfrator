package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class Przestawieniowy extends Cipher {

    Alphabet alphabet=new Alphabet();
    private int offset = 3;

    public Przestawieniowy() throws IOException {

    }
private char shift(int i){
        return alphabet.getCharAt((i+offset) % alphabet.getLength());
}
    public String caesarCipher(String text,int offset) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray())
        {
            char resultC=c;
            if (Character.isAlphabetic(c))
                for (int i = 0; i < alphabet.getLength(); i++)
                    if (alphabet.getCharAt(i, c)==c)    // size check at alphabet.getCharAt
                        resultC=(alphabet.getCharAt((i+offset) % alphabet.getLength(),c));

            result.append(resultC);
        }
        return result.toString();
    }

    @FXML
    void cipher(ActionEvent event) {
        offset=Integer.parseInt(newOffset.getText());
        outText1.setText(caesarCipher(inText.getText(), offset % alphabet.getLength()));
    }

    @FXML
    void decipher(ActionEvent event) {
        offset=Integer.parseInt(newOffset.getText());
        outText1.setText(
                caesarCipher(inText.getText(),
                        alphabet.getLength() - (offset % alphabet.getLength())));
    }
   }



