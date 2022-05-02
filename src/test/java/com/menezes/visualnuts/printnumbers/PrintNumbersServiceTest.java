package com.menezes.visualnuts.printnumbers;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.menezes.visualnuts.languages.constants.Constants.NUTS;
import static com.menezes.visualnuts.languages.constants.Constants.VISUAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrintNumbersServiceTest {

    private PrintNumbersService printNumbersService;

    @BeforeAll
    public void init() {
        printNumbersService = new PrintNumbersService();
    }

    @Test
    void verifyNumberDisibleBy3() {
        assertEquals(VISUAL, printNumbersService.verifyNumber(3));
    }

    @Test
    void verifyNumberDisibleBy5() {
        assertEquals(NUTS, printNumbersService.verifyNumber(5));
    }

    @Test
    void verifyNumberDisibleBy3Or5() {
        assertEquals(VISUAL.concat(" ").concat(NUTS), printNumbersService.verifyNumber(15));
    }

    @Test
    void verifyNumberNotDisibleBy3Or5() {
        assertEquals("1", printNumbersService.verifyNumber(1));
    }

}