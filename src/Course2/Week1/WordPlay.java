package Course2.Week1;

public class WordPlay {

    public static String replaceVowels(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder();
        for(int i=0; i<phrase.length(); i++){
            if (isVowel(phrase.charAt(i))) newPhrase.append(ch);
            else newPhrase.append(phrase.charAt(i));
        }
        return newPhrase.toString();
    }

    public static String emphasize(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder();
        for(int i=0; i<phrase.length(); i++){
            if (Character.toLowerCase(phrase.charAt(i)) == ch){
                if(i%2==0){
                    char newCharToAppend = '*';
                    newPhrase.append(newCharToAppend);
                }
                else{
                    char newCharToAppend = '+';
                    newPhrase.append(newCharToAppend);
                }
            }
            else newPhrase.append(phrase.charAt(i));
        }
        return newPhrase.toString();
    }

    public String hello(){
        System.out.println("Hello");
        return "Hello World";
    }

    public static boolean isVowel(char ch) {
        String vowels = "aeiou";
        return vowels.indexOf(ch) != -1;
    }

}