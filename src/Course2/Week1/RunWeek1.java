package Course2.Week1;

public class RunWeek1 {

    public static void main(String[] args) {
	// write your code here
//        System.out.println("Hey");
//
//
//        String tmp = WordPlay.replaceVowels("Hello", '*');
//        System.out.println(tmp);
        CaesarCipher cc = new CaesarCipher();
//        System.out.println(cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
//        System.out.println(cc.encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8));
//        System.out.println(cc.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
        WordLengths wl = new WordLengths();
//        wl.testCountWordLengths();
        CaesarBreaker cb = new CaesarBreaker();
        String tmp = cb.decryptWithKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 14, 24);
        tmp = cb.decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
        System.out.println(tmp);
        cb.testDecrypt();
//        System.out.println(cb.decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
    }
}
