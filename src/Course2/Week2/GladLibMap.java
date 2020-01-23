package Course2.Week2;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {

    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> wordsUsed;
    private ArrayList<String> labelsUsed;
    private Random myRandom;
    private String dataSourceDirectory = "./Course2/data";


    public GladLibMap() {
        myMap = new HashMap<>();
        wordsUsed = new ArrayList<>();
        labelsUsed = new ArrayList<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source) {
        myMap = new HashMap<>();
        wordsUsed = new ArrayList<>();
        labelsUsed= new ArrayList<>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    public void initializeFromSource(String source) {
        String[] labels = {"adjective", "noun", "color",
                "country", "name", "animal",
                "timeframe", "verb", "fruit"};

        for (String s : labels) {
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
    }


    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        if (labelsUsed.indexOf(label) == -1)labelsUsed.add(label);
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));

        int indexAlreadyInUse = wordsUsed.indexOf(sub);
        while (indexAlreadyInUse != -1) {
            sub = getSubstitute(w.substring(first + 1, last));
            indexAlreadyInUse = wordsUsed.indexOf(sub);
        }
        wordsUsed.add(sub);

        return prefix + sub + suffix;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory() {
        System.out.println("\n");
        String story = fromTemplate("./Course2/data/madtemplate2.txt");
        printOut(story, 60);
    }


    public void printUniqueWords() {
        System.out.println("\nUnique Words:\n");
        String words = "";
        for (String s : wordsUsed) {
            words += s + " ";
        }
        System.out.println(words);
    }

    public int totalWordsInMap(){
        int total = 0;
        for(ArrayList<String> list : myMap.values()){
            total += list.size();
        }
        return total;
    }

    public int totalWordsConsidered(){
        int total = 0;

        for (String list: labelsUsed ){
            total += myMap.get(list).size();
        }

        return total;
    }
}
