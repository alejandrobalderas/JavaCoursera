package Course2.Week1;

import org.junit.Assert;
import org.junit.Test;

public class CaesarCipherTest {

    @Test
    public void shouldCipherText(){
        CaesarCipher cc = new CaesarCipher();
        String encryptedText = cc.encrypt("FIRST LEGION ATTACK EAST FLANK!", 23);
        Assert.assertEquals(encryptedText, "CFOPQ IBDFLK XQQXZH BXPQ CIXKH!");
    }

    @Test
    public void shouldEncryptWithTwoKeys() {
        CaesarCipher cc = new CaesarCipher();
        String encryptedText;
        encryptedText = cc.encryptTwoKeys("First Legion", 23, 17);
        Assert.assertEquals("Czojq Ivdzle", encryptedText);
    }

    @Test
    public void shouldCipherWithUpperAndLowerCase() {
        CaesarCipher cc = new CaesarCipher();
        String encryptedText = cc.encrypt("First Legion", 23);
        Assert.assertEquals("Cfopq Ibdflk", encryptedText);
        String encryptedText2 = cc.encrypt("First Legion", 17);
        Assert.assertEquals("Wzijk Cvxzfe", encryptedText2);
    }
}

