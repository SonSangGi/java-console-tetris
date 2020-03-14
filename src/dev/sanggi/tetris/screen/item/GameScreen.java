package dev.sanggi.tetris.screen.item;

import dev.sanggi.tetris.model.Piece;
import dev.sanggi.tetris.screen.ScreenConfig;
import dev.sanggi.tetris.screen.ScreenItem;
import dev.sanggi.tetris.screen.ScreenPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @@author sanggi-son
 * @created 2020/03/14
 */
public class GameScreen extends ScreenItem {

    private List<List<Integer>> cells;

    public GameScreen(ScreenPrinter screenPrinter, final ScreenConfig screenConfig) {
        super(screenPrinter, screenConfig);
        cells = new ArrayList<>();

        for (int i = 0; i < screenConfig.getHeight(); i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < screenConfig.getWidth(); j++) row.add(0);
            cells.add(row);
        }
    }

    @Override
    public void draw(boolean visible) {
        IntStream.range(0, config.getHeight())
                .forEach(i -> {
                    printer.print(config.getX(), config.getY() + i, "");
                    cells.get(i).forEach(row -> {
                        if (row == 0) {
                            printer.print(" .");
                        } else {
                            printer.print("[]");
                        }
                    });
                });
    }

    public boolean positionOk(Piece piece, int[] position) {
        for (int[] cell : piece.getCells(position)) {
            int x = cell[0];
            int y = cell[1];
            printer.print(0,2, "x: " + x + " y: " + y);
            printer.flush();
            if (x < 0 || x >= config.getWidth() || y < 0 || y >= config.getHeight() || cells.get(y).get(x) != 0) {
                return false;
            }
        }
        return true;
    }
}
