package dev.sanggi.tetris.screen.item;

import dev.sanggi.tetris.screen.ScreenItem;
import dev.sanggi.tetris.screen.ScreenPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * @@author sanggi-son
 * @created 2020/03/14
 */
public class GameScreen extends ScreenItem {

    private List<List<Integer>> cells;

    public GameScreen(ScreenPrinter screenPrinter, int WIDTH, int HEIGHT) {
        super(screenPrinter, WIDTH, HEIGHT);
        cells = new ArrayList<>();

        for (int i = 0; i < HEIGHT; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < WIDTH; j++) row.add(0);
            cells.add(row);
        }

    }

}
