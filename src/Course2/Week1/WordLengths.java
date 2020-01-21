package Course2.Week1;

import edu.duke.FileResource;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for (String s: resource.words()){
            int wordLength = s.length();
            if (!isFirstCharacterALetter(s)) wordLength -= 1;
            if (!isLastCharacterALetter(s)) wordLength-=1;
            if (wordLength<2){
                continue;
            }

            int maxWordLength = 30;
            if (wordLength > maxWordLength){
                counts[maxWordLength] += 1;
            } else{
                counts[wordLength] += 1;
            }
        }

        for (int i=0; i<counts.length; i++) {
            int count = counts[i];
            if(count != 0){
                System.out.printf("%d words of length %d%n", count, i);
            }
        }
    }
    private boolean isFirstCharacterALetter(String s){
        return Character.isLetter(s.charAt(0));
    }
    private boolean isLastCharacterALetter(String s) {
        return Character.isLetter(s.charAt(s.length() - 1));
    }

    public void testCountWordLengths(){
        int[] counts = new int[31];
        FileResource resource = new FileResource("data/manywords.txt");
        countWordLengths(resource, counts);
    }
}
