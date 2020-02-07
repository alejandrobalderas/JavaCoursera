package Course2.Week4;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedMessage = new StringBuilder();
        for (int i=whichSlice; i<message.length(); i+=totalSlices){
            slicedMessage.append(message.charAt(i));
        }
        return slicedMessage.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i=0; i<key.length; i++){
            CaesarCracker caesarCracker = new CaesarCracker(mostCommon);
            String slicedMessage = sliceString(encrypted, i, klength);
            key[i] = caesarCracker.getKey(slicedMessage);
        }
        return key;
    }



    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<>();
        for (String line: fr.lines()){
            line = line.toLowerCase();
            if (!dictionary.contains(line)){
                dictionary.add(line);
            }
        }
        return dictionary;
    }

    public int countWords (String message, HashSet<String> dictionary){
        int counts = 0;
        String[] splitMessage = message.split("\\W");
        for (String word: splitMessage){
            if (dictionary.contains(word.toLowerCase())){counts++;}
        }
        return counts;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        String finalMessage = "";
        int maxKeyLength = 100;
        int maxWordCount = 0;
        int [] bestKeysEncryptions = new int[0];
        int bestKeyLength = 0;
        for (int key=1; key<= maxKeyLength; key++){
            int [] keysEncryption = tryKeyLength(encrypted, key, 'e');
            VigenereCipher vigenereCipher = new VigenereCipher(keysEncryption);
            String decryptedMessage = vigenereCipher.decrypt(encrypted);
            int currentWordCount = countWords(decryptedMessage, dictionary);
            if (key==38){System.out.println(currentWordCount);}
            if (currentWordCount>maxWordCount){
                maxWordCount = currentWordCount;
                finalMessage = decryptedMessage;
                bestKeyLength = key;
                bestKeysEncryptions = keysEncryption;
            }
        }
        for (int key: bestKeysEncryptions){System.out.println(key);}
        System.out.println();

        System.out.println(bestKeyLength);
        System.out.printf("%nMax word count: %d%n", maxWordCount);
        return finalMessage;
    }


    public void breakVigenere () {
//        String fileName = "./src/Course2/data/Week4/athens_keyflute.txt";
        String fileName = "./src/Course2/Week4/messages/secretmessage2.txt";
        FileResource resource = new FileResource(fileName);
        String fileAsString = resource.asString();
        FileResource dict = new FileResource("./src/Course2/Week4/dictionaries/English");
        HashSet<String> dictionary = readDictionary(dict);
        String decryptedMessage = breakForLanguage(fileAsString, dictionary);
//        System.out.println(decryptedMessage);

    }

}
