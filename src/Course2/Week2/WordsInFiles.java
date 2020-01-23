package Course2.Week2;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList> wordMap;

    public WordsInFiles() {
        wordMap = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        String fileName = f.getName();
        ArrayList<String> fileList = new ArrayList<>();
        fileList.add(fileName);
        FileResource resource = new FileResource(f.getPath());
        for (String s : resource.words()) {
            if (!wordMap.containsKey(s)) {
                wordMap.put(s, fileList);
            } else {
                ArrayList<String> newFileList = new ArrayList<String>(wordMap.get(s));
                if (newFileList.indexOf(fileName) == -1) {
                    newFileList.add(fileName);
                    wordMap.put(s, newFileList);
                }
            }
        }

    }

    public void printWordMap() {
        for (String word : wordMap.keySet()) {
            ArrayList<String> fileList = wordMap.get(word);
            System.out.printf("Word: %s is in Files: ", word);
            for (String file : fileList) {
                System.out.print(file + " ");
            }
            System.out.println();
        }
    }

    public void buildWordFileMap() {
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            addWordsFromFile(file);
        }
    }

    public int maxNumber(){
        int maxNumber = 0;
        for (ArrayList<String> numFiles : wordMap.values()){
            int currentNumber = numFiles.size();
            if(currentNumber > maxNumber) maxNumber = currentNumber;
        }

        return maxNumber;
    }

    public ArrayList<String> wordInNumFiles(int number){
        ArrayList<String> wordsInFiles = new ArrayList<>();
        for(String word : wordMap.keySet()){
            int currentLength = wordMap.get(word).size();
            if (currentLength == number) wordsInFiles.add(word);
        }
        return wordsInFiles;
    }


    public void printFilesIn(String word){
        for (String key : wordMap.keySet()){
            if (key.equals(word)){
                for(Object file: wordMap.get(key)){
                    System.out.println(file);
                }
            }
        }
    }

    public void tester(){
        buildWordFileMap();

        int maxNumber = maxNumber();
        System.out.printf("Max Number of Files: %d", maxNumber);

        ArrayList<String> wordInFiles = wordInNumFiles(maxNumber);
        System.out.println();
        for(String word: wordInFiles) System.out.print(word + " ");
        System.out.println();
        for(String word: wordInFiles){
            System.out.println("For word: " + word);
            printFilesIn(word);
        }


    }

    public static void main(String[] args) {
        WordsInFiles wif = new WordsInFiles();

        wif.buildWordFileMap();

        System.out.println(wif.wordInNumFiles(4).size());

        wif.printFilesIn("tree");
    }
}
