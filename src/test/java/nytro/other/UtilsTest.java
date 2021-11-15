package nytro.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void getAlphaNumericString() {
        int n = 16;
        String string = Utils.getAlphaNumericString(n);
        assertEquals(n, string.length());
    }

    @Test
    void generatePwdOk() {
        String password = "123stella";
        String passwordCriptata = "e4187f73b067afa42e5d24245e770c65a6038927";
        assertEquals(Utils.generatePwd(password), passwordCriptata);
    }

    @Test
    void checkImgOk1() {
        String fileName = "immagine.png";
        assertEquals(true, Utils.checkImg(fileName));
    }

    @Test
    void checkImgOk2() {
        String fileName = "immagine.jpg";
        assertEquals(true, Utils.checkImg(fileName));
    }

    @Test
    void checkImgOk3() {
        String fileName = "immagine.jpeg";
        assertEquals(true, Utils.checkImg(fileName));
    }


    @Test
    void checkImgNotOk() {
        String fileName = "immagine.pdf";
        assertEquals(false, Utils.checkImg(fileName));
    }
}