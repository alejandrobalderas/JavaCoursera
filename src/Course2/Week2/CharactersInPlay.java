package Course2.Week2;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Collections;

public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> characterFrequency;

    public CharactersInPlay() {
        characterNames = new ArrayList<String>();
        characterFrequency = new ArrayList<Integer>();
    }

    public void update(String name){
        int index = characterNames.indexOf(name);
        if(index == -1){
            characterNames.add(name);
            characterFrequency.add(1);
        } else {
            int freq = characterFrequency.get(index);
            characterFrequency.set(index, freq+1);
        }
    }


    public void clearVariables(){
        characterNames.clear();
        characterFrequency.clear();
    }

    public void findAllCharacters(){
        clearVariables();
        FileResource resource = new FileResource("./Course2/data/likeit.txt");

        for (String s: resource.lines()){
            int indexOfFirstDot = s.indexOf(".");

            if(indexOfFirstDot != -1){
                String textBeforeDot = s.substring(0, indexOfFirstDot);
                update(textBeforeDot);
            }
        }
    }

    public int findMax(){
        int max = characterFrequency.get(0);
        int maxIndex = 0;
        for(int k=0; k < characterFrequency.size(); k++){
            if (characterFrequency.get(k) > max){
                max = characterFrequency.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public void charactersWithNumParts(int moreThanThis, int lessThanThis){
        findAllCharacters();
        for(int i=0; i<characterFrequency.size(); i++){
            int frequency = characterFrequency.get(i);
            if(frequency>moreThanThis && frequency<lessThanThis){
                System.out.printf("Character: %s\t%d%n", characterNames.get(i), characterFrequency.get(i));
            }
        }
    }

    public void sortCharacterList(){

    }


    public void printAllCharacters(){
        for(int i=0; i<characterNames.size(); i++){
            System.out.printf("Character: %s\t%d%n", characterNames.get(i), characterFrequency.get(i));
        }
    }

    public void tester(){
        findAllCharacters();
        System.out.println("# unique characters: "+characterNames.size());
        int index = findMax();
        System.out.println("max word/freq: "+characterNames.get(index)+" "+characterFrequency.get(index) + "\n");

    }

    public static void main(String[] args){
        CharactersInPlay cip = new CharactersInPlay();
        cip.findAllCharacters();
        cip.tester();
        cip.charactersWithNumParts(10, 1000);
    }
}
