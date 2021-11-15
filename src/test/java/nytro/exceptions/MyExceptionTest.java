package nytro.exceptions;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class MyExceptionTest {

    @Test
    void MyException1() {
        MyException myException = new MyException();
        assertEquals(myException.getMessage(), null);
    }

    @Test
    void MyException2() {
        String message = "messaggio";
        MyException myException2 = new MyException(message);
        assertEquals(myException2.getMessage(), message);
    }

}