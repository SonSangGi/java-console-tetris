package dev.sanggi.tetris.type;

import java.util.Random;

/**
 * @@author sanggi-son
 * @created 2020/03/14
 */
public enum ColorType {
    RED(1),
    GREEN(2),
    YELLOW(3),
    BLUE(4),
    FUCHSIA(5),
    CYAN(6),
    WHITE(7);

    public final int value;

    ColorType(int value) {
        this.value = value;
    }

    private final ColorType VALUES[] = values();

    public ColorType getRandomColor() {
        return VALUES[new Random().nextInt(VALUES.length)];
    }
}
