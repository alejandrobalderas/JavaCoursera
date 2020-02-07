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
            char mostCommonChar = mostCommonCharIn(dictionary);
            int [] keysEncryption = tryKeyLength(encrypted, key, mostCommonChar);
            VigenereCipher vigenereCipher = new VigenereCipher(keysEncryption);
            String decryptedMessage = vigenereCipher.decrypt(encrypted);
            int currentWordCount = countWords(decryptedMessage, dictionary);
            if (currentWordCount>maxWordCount){
                maxWordCount = currentWordCount;
                finalMessage = decryptedMessage;
                bestKeyLength = key;
                bestKeysEncryptions = keysEncryption;
            }
        }
        return finalMessage;
    }

    private char findMostCommonInHashmap(HashMap<Character, Integer> map){
        int mostAmountOfTimes = 0;
        char mostCommonChar = ' ';
        for (Character c : map.keySet()){
            int currentAmountOftimes = map.get(c);
            if (currentAmountOftimes > mostAmountOfTimes){
                mostAmountOfTimes = currentAmountOftimes;
                mostCommonChar = c;
            }
        }
        return mostCommonChar;
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (String word:dictionary){
            word = word.toLowerCase();
            for (char c: word.toCharArray()){
                if (!charMap.containsKey(c)){
                    charMap.put(c, 1);
                }
                else {
                    charMap.put(c, charMap.get(c)+1);
                }
            }
        }
        char mostCommonChar = findMostCommonInHashmap(charMap);
        return mostCommonChar;
    }

    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages){
        int mostNumberOfWord = 0;
        String bestLanguageForEncryption = "";
        String finalMessage = "";
        for (String language : languages.keySet()){
            System.out.printf("Breaking for: %s%n", language);
            HashSet<String> dictionary = languages.get(language);
            String currentEncryptedMessage = breakForLanguage(encrypted, dictionary);
            int currentNumberOfWords = countWords(currentEncryptedMessage, dictionary);
            if (currentNumberOfWords > mostNumberOfWord){
                mostNumberOfWord = currentNumberOfWords;
                bestLanguageForEncryption = language;
                finalMessage = currentEncryptedMessage;
            }
        }

        System.out.println(finalMessage);
        System.out.println(bestLanguageForEncryption);
        System.out.printf("Number of Words: %d", mostNumberOfWord);
    }

    public HashMap<String, HashSet<String>> getAllDictionaries(){
        HashMap<String, HashSet<String>> allDictionaries = new HashMap<>();
        String [] languages = {"Danish","Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for (String s : languages){
            FileResource frDict = new FileResource("./src/Course2/Week4/dictionaries/" + s);
            HashSet<String> dictionary = readDictionary(frDict);
            allDictionaries.put(s, dictionary);
        }
        return allDictionaries;
    }


    public void breakVigenere () {
        String fileAsString = new FileResource("./src/Course2/Week4/messages/secretmessage3.txt").asString();
        System.out.println("Getting Dictionaries");
        HashMap<String, HashSet<String>> allDictionaries = getAllDictionaries();
        breakForAllLanguages(fileAsString, allDictionaries);
    }

}
