package dev.sanggi.tetris.controller;

import dev.sanggi.tetris.screen.item.Piece;
import dev.sanggi.tetris.screen.ScreenConfig;
import dev.sanggi.tetris.screen.ScreenPrinter;
import dev.sanggi.tetris.screen.item.PlayField;
import dev.sanggi.tetris.screen.item.Score;

/**
 * @@author sanggi-son
 * @created 2020/03/14
 */
public class TetrisController {

    private final int PLAY_FILED_WIDTH = 10;
    private final int PLAY_FILED_HEIGHT = 20;
    private final int PLAY_FILED_X = 30;
    private final int PLAY_FILED_Y = 0;
    private final ScreenConfig PLAY_FILED_CONFIG = new ScreenConfig(PLAY_FILED_WIDTH, PLAY_FILED_HEIGHT, PLAY_FILED_X, PLAY_FILED_Y);

    private final int NEXT_BLOCK_X = 14;
    private final int NEXT_BLOCK_Y = 11;
    private final ScreenConfig NEXT_BLOCK_CONFIG = new ScreenConfig(0, 0, NEXT_BLOCK_X, NEXT_BLOCK_Y);

    private final int HOLD_BLOCK_X = 40;
    private final int HOLD_BLOCK_Y = 11;
    private final ScreenConfig HOLD_BLOCK_CONFIG = new ScreenConfig(0, 0, HOLD_BLOCK_X, HOLD_BLOCK_Y);

    private final int SCORE_X = 54;
    private final int SCORE_Y = 11;
    private final ScreenConfig SCORE_CONFIG = new ScreenConfig(0, 0, SCORE_X, SCORE_Y);

    private ScreenPrinter printer;

    private PlayField playField;

    private Piece currentPiece;
    private Piece nextPiece;
    private Piece holdPiece;

    private Score score;

    public TetrisController(ScreenPrinter printer) {
        this.printer = printer;
        this.playField = new PlayField(printer, PLAY_FILED_CONFIG);
        this.score = new Score(printer, SCORE_CONFIG);
        holdPiece = new Piece(printer, HOLD_BLOCK_CONFIG);
        holdPiece.visible = false;
        createNextPiece();
        createCurrentPiece();
        redraw();
        printer.flush();
    }

    public void createCurrentPiece() {

        nextPiece.hide();

        currentPiece = nextPiece;
        currentPiece.setPosition(new int[]{(PLAY_FILED_WIDTH - 4) / 2, 0, -1});
        currentPiece.setConfig(PLAY_FILED_CONFIG);

        if (!playField.positionOk(currentPiece, null)) exit();

        currentPiece.show();
        createNextPiece();
    }

    public void createNextPiece() {
        nextPiece = new Piece(printer, NEXT_BLOCK_CONFIG);
        nextPiece.show();
    }

    public void hold() {
        holdPiece.setData(currentPiece.getData());
        currentPiece.setData(holdPiece.getData());
        createCurrentPiece();
        holdPiece.show();
        playField.show();
    }

    public void rotate() {
        move(0, 0, 1);
    }

    public void right() {
        move(1, 0, 0);
    }

    public void left() {
        move(-1, 0, 0);
    }

    public boolean down() {
        if (move(0, 1, 0)) {
            return true;
        }
        createCurrentPiece();
        return false;
    }

    public void drop() {
        while (down()) ;
    }

    public boolean move(int dx, int dy, int dz) {
        int[] newPosition = currentPiece.newPosition(dx, dy, dz);
        if (playField.positionOk(currentPiece, newPosition)) {
            currentPiece.hide();
            currentPiece.setPosition(newPosition);
            currentPiece.show();
            return true;
        }

        if (dy == 0) return true;

        playField.pileUpPieces(currentPiece);
        int clearLine = playField.lineClear();
        printer.print(0, 10, clearLine + "");
        if (clearLine > 0) {
            score.update(clearLine);
            playField.show();
        }
        return false;
    }

    public void flush() {
        printer.flush();
    }

    public void redraw() {
        printer.clear();
        printer.hideCursor();
        playField.show();
        nextPiece.show();
        currentPiece.show();
        score.show();
    }

    public void exit() {
        printer.print(0, PLAY_FILED_HEIGHT + 1, "EXIT");
        printer.showCursor();
        printer.flush();
        try {
            String[] cmd = new String[]{"/bin/sh", "-c", "stty sane </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        System.exit(0);
    }
}
