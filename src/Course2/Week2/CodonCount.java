package Course2.Week2;

import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> dnaMap;

    public CodonCount() {
        dnaMap = new HashMap<String, Integer>();
    }

    public void clearMap() {
        dnaMap.clear();
    }

    public ArrayList<String> separateCodons(int start, String dna) {
        ArrayList<String> dnaCodons = new ArrayList<String>();

        String dnaCodon;
        int codonRange;
        for (int i = 0; i < dna.length() / 3; i++) {
            codonRange = start + 3 * i;
            if (codonRange + 3 > dna.length()) continue;

            dnaCodon = dna.substring(codonRange, codonRange + 3);
            dnaCodons.add(dnaCodon);
        }

        return dnaCodons;
    }

    public void buildCodonMap(int start, String dna) throws Exception {
        clearMap();
        if (start > 2) throw new Exception("Start value out of range");

        ArrayList<String> dnaCodons = separateCodons(start, dna);

        for (String codon : dnaCodons) {
            if (!dnaMap.containsKey(codon)) {
                dnaMap.put(codon, 1);
            } else {
                dnaMap.put(codon, dnaMap.get(codon) + 1);
            }
        }

    }

    public String getMostCommonCodon() {
        int maxValue = 0;
        String maxString = "";
        for (String codon : dnaMap.keySet()) {
            int value = dnaMap.get(codon);
            if (value > maxValue) {
                maxValue = value;
                maxString = codon;
            }
        }
        return maxString;
    }

    public void printCodonCounts(int start, int end) {
        for (String codon : dnaMap.keySet()) {
            int value = dnaMap.get(codon);
            if (value > start && value < end) {
                System.out.printf("Codon: %s\tValue: %d%n", codon, value);
            }
        }
    }

    public void tester() throws Exception {
        FileResource resource = new FileResource("./Course2/data/dnaMystery2");

        for (String dna : resource.words()) {
            dna = dna.toUpperCase().trim();
            for (int startCounter = 0; startCounter < 3; startCounter++) {
                buildCodonMap(startCounter, dna);
                String mostCommonCodon = getMostCommonCodon();
                System.out.printf("%nStarting with: %d results in %d unique codons%n" +
                        "The most common codon is %s with count %d%n", startCounter, dnaMap.size(),
                        mostCommonCodon, dnaMap.get(mostCommonCodon));
                printCodonCounts(0, 10);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        CodonCount cc = new CodonCount();

        cc.tester();
    }
}
