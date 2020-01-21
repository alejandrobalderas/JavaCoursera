package Course2.Week1;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordPlayTest {

        @Test
        public void shouldReturnF() {
            WordPlay wp = new WordPlay();
            assertFalse(WordPlay.isVowel('F'));
        }

        @Test
        public void shouldReturnTrue() {
            WordPlay wp = new WordPlay();
            assertTrue(WordPlay.isVowel('a'));
        }

        @Test
        public void shouldEmphasizeText() {
            assertEquals("dn* ctg+*+ctg+", WordPlay.emphasize("dna ctgaaactga", 'a') );
            assertEquals("M+ry Bell+ +br*c*d*br+", WordPlay.emphasize("Mary Bella Abracadabra", 'a') );
        }

        @Test
        public void shouldReplaceVowels() {
            String replacedText = WordPlay.replaceVowels("Hello World", '*');
            assertEquals(replacedText, "H*ll* W*rld");
        }
    }