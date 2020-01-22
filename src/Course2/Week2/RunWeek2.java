package Course2.Week2;

public class RunWeek2 {
    public static void main(String[] args) {
        System.out.println("Running Week2");
        WordFrequencies wf = new WordFrequencies();
//        wf.tester();

        GladLib gl = new GladLib();
        gl.makeStory();
        gl.printUniqueWords();
    }
}
