package dev.sanggi.tetris.screen.item;

import dev.sanggi.tetris.screen.ScreenConfig;
import dev.sanggi.tetris.screen.ScreenItem;
import dev.sanggi.tetris.screen.ScreenPrinter;
import dev.sanggi.tetris.type.ColorType;

import java.util.ArrayList;
import java.util.List;

/**
 * @@author sanggi-son
 * @created 2020/03/14
 */
public class PlayField extends ScreenItem {

    private List<List<ColorType>> grid;

    public PlayField(ScreenPrinter screenPrinter, ScreenConfig screenConfig) {
        super(screenPrinter, screenConfig);

        grid = new ArrayList<>();

        for (int i = 0; i < config.HEIGHT; i++)
            grid.add(createEmptyRow());
    }

    @Override
    public void draw(boolean visible) {

        for (int y = 0; y < grid.size(); y++) {
            printer.print(config.X, config.Y + y, "");

            for (ColorType cell : grid.get(y)) {
                if (cell == null) printer.print(" .");
                else {
                    printer.setFg(cell);
                    printer.setBg(cell);
                    printer.print("[]");
                    printer.resetColor();
                }
            }
        }
    }

    public List<ColorType> createEmptyRow() {

        List<ColorType> row = new ArrayList<>();
        for (int j = 0; j < config.WIDTH; j++) row.add(null);
        return row;
    }

    // 행 삭제
    public int lineClear() {

        List<List<ColorType>> newGrid = new ArrayList<>();

        for (List<ColorType> row : grid) {
            if (row.indexOf(null) != -1) newGrid.add(row);
        }

        int clearLines = config.HEIGHT - newGrid.size();
        for (int i = 0; i < clearLines; i++) {
            newGrid.add(0, createEmptyRow());
        }

        grid = newGrid;
        return clearLines;
    }

    // 블럭 쌓기
    public void pileUpPieces(Piece piece) {
        for (int[] cell : piece.getCells(null)) {
            int x = cell[0];
            int y = cell[1];

            grid.get(y).set(x, piece.color);
        }
    }

    // 블럭이 위치할 수 있는지 여부
    public boolean positionOk(Piece piece, int[] position) {
        for (int[] cell : piece.getCells(position)) {
            int x = cell[0];
            int y = cell[1];
            printer.print(0, 2, "x: " + x + " y: " + y);
            printer.flush();
            if (x < 0 || x >= config.WIDTH || y < 0 || y >= config.HEIGHT || grid.get(y).get(x) != null) {
                return false;
            }
        }
        return true;
    }
}
