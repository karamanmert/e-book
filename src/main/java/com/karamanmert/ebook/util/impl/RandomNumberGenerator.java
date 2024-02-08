package com.karamanmert.ebook.util.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

/**
 * @author karamanmert
 */

@Getter
@Setter
@Builder
public final class RandomNumberGenerator {

    private static final int ISBN_SIZE = 13;
    private static final Random RANDOM = new Random(); // refactor it later

    private RandomNumberGenerator() {
    }

    public static String generate() {
        StringBuilder isbn = new StringBuilder();
        for (int i = 0; i < ISBN_SIZE; i++) {
            isbn.append(RANDOM.nextInt(10));
        }
        return isbn.toString();
    }
}
