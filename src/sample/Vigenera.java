package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Vigenera extends Cipher {
    private final Vigener vigener = new Vigener();
    private String keyword = "start";
    Alphabet alphabet = new Alphabet();

    public Vigenera() throws IOException {
    }

    @FXML
    public void cipher(ActionEvent event) {
        vigener.cipher();
    }

    @FXML
    public void decipher(ActionEvent event) {
        vigener.decipher();
    }

    public class Vigener {

        private void cipher() {
            String str = (inText.getText());
            keyword = (keyWordField.getText());
            String key = generateKey(str, keyword);
            String cipher_text = cipherText(str, key);
            outText1.setText(cipher_text);
        }

        private void decipher() {
            String str = (inText.getText());
            keyword = (keyWordField.getText());
            String key = generateKey(str, keyword);
            outText1.setText(originalText(str, key));
        }


        private String generateKey(String str, String key) {
            int x = str.length();
            StringBuilder keyBuilder = new StringBuilder(key);
            for (int i = 0; ; i++)
            {
                if (x == i)
                    i = 0;

                if (keyBuilder.length() == str.length())
                    break;

                keyBuilder.append(keyBuilder.charAt(i));
            }
            key = keyBuilder.toString();
            return key;
        }

        private  String cipherText(String str, String key)
        {
            StringBuilder cipher_text= new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isLetter(str.charAt(i)))
                    cipher_text.append(str.charAt(i));
                else    // konwersja symboli za pomoca tabeli Vigenera z wykorzystaniem modulo
                    cipher_text.append(alphabet.getCharAt(  // przypisywanie znaku z alfabetu wg. indeksów
                            (alphabet.getIndexOf(str.charAt(i)) + alphabet.getIndexOf(key.charAt(i)))
                                    % alphabet.getLength(), str.charAt(i)));  //sprawdzanie Case'ów w alphabet.getCharAt
            }
            return cipher_text.toString();
                      /* Poprzednie:
                     else {
                    // konwersja symboli za pomoca tabeli Vigenera z wykorzystaniem modulo
                    int x = (str.charAt(i) + key.charAt(i)) % 26;

                    // zamiana symboli na litery
                    x += 'A';
                    cipher_text.append((char) (x));
                     */
        }

        private String originalText(String cipher_text, String key) {
            StringBuilder orig_text = new StringBuilder();
            for (int i = 0; i < cipher_text.length() &&
                    i < key.length(); i++) {
                if (!Character.isLetter(cipher_text.charAt(i)))
                    orig_text.append(cipher_text.charAt(i));

                else   //  konwersja symboli za pomoca tabeli Vigenera z wykorzystaniem modulo
                    orig_text.append(alphabet.getCharAt(  // Pobieranie miejsca znaków tekstu i klucza z alfabetu
                            (alphabet.getIndexOf(cipher_text.charAt(i)) - alphabet.getIndexOf(key.charAt(i)) + alphabet.getLength())
                                    % alphabet.getLength(), cipher_text.charAt(i)));
            }
            return orig_text.toString();
        }
            /*
            Poprzednie:
                  else {
                    //  konwersja symboli za pomoca tabeli Vigenera z wykorzystaniem modulo
                    int x = (cipher_text.charAt(i) -
                            key.charAt(i) + 26) % 26;

                    // zamiana symboli na litery
                    x += 'A';
                    orig_text.append((char) (x));
                    }
            return orig_text.toString();
                }
             */
    }

}




