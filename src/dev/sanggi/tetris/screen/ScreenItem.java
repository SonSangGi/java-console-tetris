package dev.sanggi.tetris.screen;

/**
 * @@author sanggi-son
 * @created 2020/03/14
 */
abstract public class ScreenItem {
    private ScreenPrinter screenPrinter;
    private int WIDTH;
    private int HEIGHT;

    public ScreenItem(ScreenPrinter screenPrinter, final int WIDTH, final int HEIGHT) {
        this.screenPrinter = screenPrinter;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

}
