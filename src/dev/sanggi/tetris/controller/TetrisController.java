package dev.sanggi.tetris.controller;

import dev.sanggi.tetris.model.Piece;
import dev.sanggi.tetris.screen.ScreenConfig;
import dev.sanggi.tetris.screen.ScreenPrinter;
import dev.sanggi.tetris.screen.item.GameScreen;
import sun.security.krb5.Config;

/**
 * @@author sanggi-son
 * @created 2020/03/14
 */
public class TetrisController {
    private ScreenPrinter printer;

    private GameScreen gameScreen;
    private Piece currentPiece;
    private Piece nextPiece;

    private final int GAME_SCREEN_WIDTH = 10;
    private final int GAME_SCREEN_HEIGHT = 20;
    private final int GAME_SCREEN_X = 30;
    private final int GAME_SCREEN_Y = 0;

    public TetrisController(ScreenPrinter printer) {
        this.printer = printer;
        this.gameScreen = new GameScreen(printer, new ScreenConfig(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, GAME_SCREEN_X, GAME_SCREEN_Y));
        createCurrentPiece();
        redraw();
        printer.flush();
    }

    public void createCurrentPiece() {
        currentPiece = new Piece(printer, new ScreenConfig(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT, GAME_SCREEN_X, GAME_SCREEN_Y));
        currentPiece.setPosition(new int[]{(GAME_SCREEN_WIDTH - 4) / 2, 0, -1});
        currentPiece.show();
    }

    public void right() {
        move(1, 0, 0);
    }

    public void left() {
        move(-1, 0, 0);
    }

    public boolean down() {
        boolean is = move(0, 1, 0);
        printer.print(0,3, "isDown: "+is);
        if (is) {
            return true;
        }
        createCurrentPiece();
        return false;
    }

    public boolean move(int dx, int dy, int dz) {
        int[] newPosition = currentPiece.newPosition(dx, dy, dz);
        if(gameScreen.positionOk(currentPiece, newPosition)) {
            currentPiece.hide();
            currentPiece.setPosition(newPosition);
            currentPiece.show();
            return true;
        }
        return false;
    }

    public void flush() {
        printer.flush();
    }

    public void redraw() {
        printer.clear();
        printer.hideCursor();
        gameScreen.show();
    }

    public void exit() {
        printer.print(0, GAME_SCREEN_HEIGHT + 1, "EXIT");
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
