package Course2.Week1;

public class CaesarCipher {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        for (int i=0; i<input.length(); i++){
            boolean isLowerCase = Character.isLowerCase(input.charAt(i));
            int indexInAlphabet = alphabet.indexOf(Character.toUpperCase(input.charAt(i)));
            if (indexInAlphabet != -1) {
                char charToAppend;
                if (isLowerCase) charToAppend=Character.toLowerCase(shiftedAlphabet.charAt(indexInAlphabet));
                else charToAppend=shiftedAlphabet.charAt(indexInAlphabet);
                encrypted.append(charToAppend);
            }
            else encrypted.append(input.charAt(i));
        }
        return encrypted.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder();
        for (int i=0; i<input.length(); i++){
            String charToEncrypt = String.valueOf(input.charAt(i));
            if (i%2==0) encrypted.append(encrypt(charToEncrypt, key1));
            else encrypted.append(encrypt(charToEncrypt, key2));
        }
        return encrypted.toString();
    }
}
