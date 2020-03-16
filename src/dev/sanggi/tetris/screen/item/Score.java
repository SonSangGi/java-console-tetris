package dev.sanggi.tetris.screen.item;

import dev.sanggi.tetris.screen.ScreenConfig;
import dev.sanggi.tetris.screen.ScreenItem;
import dev.sanggi.tetris.screen.ScreenPrinter;
import dev.sanggi.tetris.type.ColorType;

/**
 * @@author SonSangGi
 * @created 2020/03/15
 */
public class Score extends ScreenItem {

    private int score = 0;
    private int clearLine = 0;

    public Score(ScreenPrinter printer, ScreenConfig config) {
        super(printer, config);
    }

    @Override
    public void draw(boolean visible) {
        printer.setFg(ColorType.BLUE);
        printer.print(config.X, config.Y, "SCORE: " + score);
        printer.print(config.X, config.Y + 1, "CLEAR LINE: " + clearLine);
        printer.resetColor();
    }

    public void update(int clearLine) {
        this.clearLine += clearLine;
        score += clearLine * clearLine;
        this.show();
    }
}
