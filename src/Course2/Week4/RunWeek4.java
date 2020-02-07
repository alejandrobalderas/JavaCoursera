package Course2.Week4;

import edu.duke.FileResource;

import java.util.HashSet;

public class RunWeek4 {

    public static void main(String[] args){
        String fileName = "./src/Course2/data/Week4/athens_keyflute.txt";
//        String fileName = "./src/Course2/Week4/messages/secretmessage1.txt";
        FileResource resource = new FileResource(fileName);
        String fileAsString = resource.asString();


        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        vigenereBreaker.breakVigenere();
//
//        FileResource dict = new FileResource("./src/Course2/Week4/dictionaries/English");
//        HashSet<String> dictionary = vigenereBreaker.readDictionary(dict);
//
//        String decryptedMessage = vigenereBreaker.breakForLanguage(fileAsString, dictionary);
////        System.out.println(decryptedMessage);

    }
}
