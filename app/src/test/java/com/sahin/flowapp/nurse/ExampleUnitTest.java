package com.sahin.flowapp.nurse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *
 *
 * Bu test metodunda assertEquals(4, 2 + 2);
 * ifadesi bulunuyor. assertEquals metodu, iki değeri karşılaştırır ve eğer bu değerler birbirine eşit değilse bir hata fırlatır.
 * Burada 4 ile 2 + 2 ifadesi karşılaştırılıyor.
 * Eğer bu iki değer eşit değilse, bir hata oluşur ve test başarısız olur.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}