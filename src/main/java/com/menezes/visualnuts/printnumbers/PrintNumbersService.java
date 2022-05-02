package com.menezes.visualnuts.printnumbers;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.menezes.visualnuts.languages.constants.Constants.*;

@Service
public class PrintNumbersService {

    private static final Integer MAX = 100;

    public List<String> verifyNumberAndPrint(Integer maxNumber) {
        return IntStream.rangeClosed(1, Objects.nonNull(maxNumber) ? maxNumber : 1)
                .mapToObj(number -> verifyNumber(number))
                .collect(Collectors.toList());

    }

    public String verifyNumber(int value) {
        String textToPrint = String.valueOf(value);
        if (is3and5Divisible(value)) {
            if (Objects.nonNull(VISUAL) && Objects.nonNull(NUTS))
                textToPrint = VISUAL.concat(" ").concat(NUTS);
        } else if (is3Divisible(value)) {
            if (Objects.nonNull(VISUAL))
                textToPrint = VISUAL;
        } else if (is5Divisible(value)) {
            if (Objects.nonNull(NUTS))
                textToPrint = NUTS;
        }

        return textToPrint;
    }

    private boolean is3Divisible(int value) {
        return value % 3 == 0;
    }

    private boolean is5Divisible(int value) {
        return value % 5 == 0;
    }

    private boolean is3and5Divisible(int value) {
        return is3Divisible(value) && is5Divisible(value);
    }
}
