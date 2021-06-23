package sample;

public class Alphabet {
private final String ALPHABET="aąbcćdeęfghijklłmnńoópqrsśtuwxyzźż";
//private final String ALPHABET="abcdefghijklmnopqrstuvwxyz";
    public String getAlphabet() {
        return ALPHABET;
    }
    public int getLength(){
        return ALPHABET.length();
    }
    public char getCharAt(int i){
        return ALPHABET.charAt(i);
    }
    public char getCharAt(int i,char c){
        return Character.isUpperCase(c) ?
                Character.toUpperCase(ALPHABET.charAt(i)) : Character.toLowerCase(ALPHABET.charAt(i));
    }
    public int getIndexOf(char c){
        StringBuilder str = new StringBuilder(
                Character.isLowerCase(c) ? ALPHABET.toLowerCase() : ALPHABET.toUpperCase());
        return str.indexOf(Character.toString(c));
    }
    public int getIndexOf(String c){
        return ALPHABET.indexOf(c);
    }
}
