package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Archive {
    public String archiveSelected;
    private final String FILE="saves.txt";
    private final int archiveMaxLength = 5;
    private BufferedWriter fileWriter=new BufferedWriter(new FileWriter(FILE,true));

    public Archive() throws IOException {
    }

    @FXML
    void changeArchive(ActionEvent event){
        archiveSelected= ((Button) event.getSource()).getText();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); //wyjscie z okna
    }

        public void saveFile(String text,int index) throws IOException {
            BufferedReader fileReader=new BufferedReader(new FileReader(FILE));
            ArrayList<StringBuilder> temporaryList=new ArrayList<>();

            for(int i=1;i<=archiveMaxLength;i++)    // pętla czytająca folder tekstowy i zamieniająca podaną linijkę tekstem użytkownika
            {
                String line=fileReader.readLine();
                String s = (i==index) ? textToBinary(text) : (line==null) ? "" : line;     // tak wygląda uzależnienie od if elsów
                temporaryList.add(new StringBuilder(s));
            }
            fileWriter=new BufferedWriter(new FileWriter(FILE,false));

            for(StringBuilder sb:temporaryList)
            {
                fileWriter.write(sb.toString());
                fileWriter.newLine();
            }
            fileWriter.flush();

            for(int i=1;i<=archiveMaxLength;i++)
                fileReader.readLine();
        }

        public String readFile(int index) throws IOException {
            BufferedReader fileReader=new BufferedReader(new FileReader(FILE));
            for(int i=1; i<=archiveMaxLength; i++)
            {
                String s = fileReader.readLine();
                if (i == index)
                    return binaryToText(s);
            }
            return binaryToText(textToBinary("Error 404: Text not found."));
        }

    public void clear() throws IOException {
        System.out.println("Czyszczenie archiwum...");
        fileWriter=new BufferedWriter(new FileWriter(FILE,false));

        for(int i=1;i<=archiveMaxLength;i++)
            fileWriter.newLine();

        fileWriter.flush();
    }

    private String textToBinary(String text) {
            byte[] bytes = text.getBytes();
            StringBuilder binary = new StringBuilder();
            for (byte b : bytes)
            {
                int val = b;
                for (int i = 0; i < 8; i++)
                {
                    binary.append((val & 128) == 0 ? 0 : 1);
                    val <<= 1;
                }
                binary.append(' ');
            }
            return binary.toString();
        }

        public String binaryToText(String binaryText) {
            StringBuilder s = new StringBuilder();
            int shift;
            for (int index = 0; index < binaryText.length(); index += 9)
            {
                shift=0;
                String temp = binaryText.substring(index, index + 8);
                // sprawdzanie wielkość zakodowanego znaku
                if(temp.startsWith("110"))           //  znak dwubajtowy
                    shift=9;                         // przesunięcie o 8 bitów + znak spacji
                else if(temp.startsWith("1110"))     // znak trzybajtowy
                    shift=18;                        // przesunięcie o 16 bitów + 2 znaki spacji
                else if(temp.startsWith("11110"))    // znak czterobajtowy
                    shift=27;                        // przesunięcie o 24 bitów + 3 znaki spacji

                if(shift>0) temp=binaryText.substring(index,index+8+shift);//  jeśli nastapiło przesunięcie, utworzenie stringu posiadającego wszystkie bajty znaku
                index += shift;                                                 // uaktualnienie indexu o przesunięcie
                temp = temp.replaceAll("\\s+", "");            // usunięcie białych znaków

                // Zamiana zapisu binarnego na UTF_8 (przy pomocy tymczasowego byte[] o wielkości max 4 bajtów)
                // a następnie na tekst przy pomocy konstruktora new String(byte[],Charset)
                s.append(new String(
                        ByteBuffer.allocate(4).putInt(
                                Integer.parseInt(temp, 2)).array()
                                    ,StandardCharsets.UTF_8 ));
            }
            return s.toString();
        }
    }



