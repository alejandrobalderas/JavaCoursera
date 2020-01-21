package Course2.Week1;

import edu.duke.FileResource;

public class CaesarBreaker {
    public String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private int[] countLetters(String message){
        int [] counts = new int[26];
        message = message.toLowerCase();
        for (char c: message.toCharArray()){
            int indexInAlphabet = alphabet.indexOf(c);
            if (indexInAlphabet != -1)counts[indexInAlphabet] += 1;
        }
        return counts;
    }

    public int maxIndex(int[] array){
        int maxAt = 0;
        for (int i = 0; i < array.length; i++) {
            maxAt = array[i] > array[maxAt] ? i : maxAt;
        }
        return maxAt;
    }
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int key = getKey(encrypted);
        String message = cc.encrypt(encrypted.toString(), 26-key);
        return message;
    }

    public String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for (int i=start;i<message.length();i+=2) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxIdx = maxIndex(freqs);
        int key = maxIdx -4;
        if(maxIdx < 4){
            key = 26 - (4-maxIdx);
        }
        return key;
    }

    public String decryptTwoKeys(String encrypted){

        String firstString = halfOfString(encrypted,0);
        String secondString = halfOfString(encrypted,1);
        int firstKey = getKey(firstString);
        int secondKey = getKey(secondString);
        CaesarCipher cc = new CaesarCipher();
        String decryptedFirst = cc.encrypt(firstString, 26-firstKey);
        String decryptedSecond = cc.encrypt(secondString, 26-secondKey);
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<decryptedFirst.length();i++){
            sb.append(decryptedFirst.charAt(i));
            if (i!=decryptedSecond.length()) sb.append(decryptedSecond.charAt(i));
        }
        System.out.printf("First Key: %d%nSecond Key:% d%n", firstKey, secondKey);
        return sb.toString();
    }
    public String decryptWithKeys(String encrypted, int key1, int key2){
        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
    }

    public void testDecrypt(){
        FileResource resource = new FileResource("Course2/data/hamlet.txt");
        Iterable<String> listOfWords = resource.words();
        StringBuilder sb = new StringBuilder();
        for (String word : listOfWords) sb.append(word).append(" ");

        String tmp1 = decryptTwoKeys(sb.toString());
        System.out.println(tmp1);
//        String tmp = halfOfString("Qbkm Zgis", 1);
//        System.out.println(tmp);
    }
}
